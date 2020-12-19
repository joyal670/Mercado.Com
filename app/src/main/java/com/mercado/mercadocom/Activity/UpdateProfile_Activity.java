package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mercado.mercadocom.Model.ProfileModel;
import com.mercado.mercadocom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UpdateProfile_Activity extends AppCompatActivity {

    ImageView updatepicture;
    Button updatebtn;
    private StorageTask mUploadTask;
    private DatabaseReference mdatabaseReference;
    private StorageReference mstorageReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private static int image_pic_request = 1;
    private Uri mImageUri;
    SharedPreferences sharedPreferences;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        try {

            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Uploading Please Wait...");

            updatepicture = findViewById(R.id.updatepicture);
            updatebtn = findViewById(R.id.updatebtn);


            sharedPreferences = getSharedPreferences("data", 0);
            final String a1 = sharedPreferences.getString("userid", "");

            mdatabaseReference = FirebaseDatabase.getInstance().getReference("user_data").child(a1).child("user_details");
                mstorageReference = FirebaseStorage.getInstance().getReference("user_data/user_picture");

            updatepicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFileChooser();
                }
            });

            updatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mUploadTask != null && mUploadTask.isInProgress()) {
                        Toast.makeText(UpdateProfile_Activity.this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
                    } else {
                        uploadFile();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileExtention(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile() {

        try {

            if (mImageUri != null)
            {
                progressDialog.show();
                StorageReference fileRef = mstorageReference.child(System.currentTimeMillis() + "." + getFileExtention(mImageUri));
                mUploadTask = fileRef.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful()) ;
                        Uri downloadUrl = uriTask.getResult();

                        String uploadId = mdatabaseReference.push().getKey();

                        ProfileModel profileModel = new ProfileModel(downloadUrl.toString(), uploadId);
                        Toast.makeText(UpdateProfile_Activity.this, "Success", Toast.LENGTH_SHORT).show();

                        mdatabaseReference.child(uploadId).setValue(profileModel);

                        progressDialog.dismiss();

                        Intent intent = new Intent(UpdateProfile_Activity.this, UserAccount_Activity.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(UpdateProfile_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "No file Selected", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, image_pic_request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == image_pic_request && resultCode == RESULT_OK && data != null && data.getData() != null) {
                mImageUri = data.getData();
                Picasso.get().load(mImageUri).into(updatepicture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
