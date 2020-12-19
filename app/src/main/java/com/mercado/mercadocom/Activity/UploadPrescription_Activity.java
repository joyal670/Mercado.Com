package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.mercado.mercadocom.Adapters.MedicalPrescriptionAdapter;
import com.mercado.mercadocom.Adapters.ProfileAdapter;
import com.mercado.mercadocom.Model.MedicalPrescriptionModel;
import com.mercado.mercadocom.Model.ProfileModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UploadPrescription_Activity extends AppCompatActivity implements MedicalPrescriptionAdapter.OnitemClickListener {

    ImageView uploadPrescriptionImg, viewPresriptionImage;
    Button uploadPrescriptionBtn, viewPresriptionReorder, opengallery;
    EditText UploadPrescriptionQtyEtx, UploadPrescriptionPhoneNumber;
    SharedPreferences sharedPreferences;
    private static int image_pic_request = 1;
    private Uri mImageUri;
    private StorageTask mUploadTask;
    private DatabaseReference mdatabaseReference;
    private StorageReference mstorageReference;
    public ProgressDialog progressDialog;
    List<MedicalPrescriptionModel> medicalPrescriptionModels;
    MedicalPrescriptionAdapter medicalPrescriptionAdapter;
    final Context context = this;
    String a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_prescription_);

        try {

            uploadPrescriptionImg = findViewById(R.id.uploadPrescriptionImg);
            uploadPrescriptionBtn = findViewById(R.id.uploadPrescriptionBtn);

            opengallery = findViewById(R.id.opengallery);
            UploadPrescriptionQtyEtx = findViewById(R.id.UploadPrescriptionQtyEtx);
            UploadPrescriptionPhoneNumber = findViewById(R.id.UploadPrescriptionPhoneNumber);

            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Uploading Please Wait...");

            sharedPreferences = getSharedPreferences("data", 0);
            a1 = sharedPreferences.getString("userid", "");

            mdatabaseReference = FirebaseDatabase.getInstance().getReference("medical_details");
            mstorageReference = FirebaseStorage.getInstance().getReference("medical_details/medical_prescription");


            opengallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFileChooser();
                }
            });

//            uploadPrescriptionAdd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String temp;
//                    temp = uploadPrescriptionQty.getText().toString();
//                    int qq = Integer.parseInt(temp);
//                    qq = qq + 1;
//
//                    uploadPrescriptionQty.setText(String.valueOf(qq));
//                }
//            });
//
//            uploadPrescriptionRemove.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String temp1;
//                    temp1 = uploadPrescriptionQty.getText().toString();
//                    int rr = Integer.parseInt(temp1);
//                    rr = rr - 1;
//                    if (rr <= 1) {
//                        rr = 1;
//                        Toast.makeText(UploadPrescription_Activity.this, "Less than Zero", Toast.LENGTH_SHORT).show();
//                    }
//                    uploadPrescriptionQty.setText(String.valueOf(rr));
//                }
//            });

            uploadPrescriptionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mUploadTask != null && mUploadTask.isInProgress()) {
                        Toast.makeText(UploadPrescription_Activity.this, "Uploading in Progress", Toast.LENGTH_SHORT).show();
                    } else {
                        uploadFile();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getFileExtention(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile() {
        try {
            final String tempqty = UploadPrescriptionQtyEtx.getText().toString();
            final String tempphone = UploadPrescriptionPhoneNumber.getText().toString();
            if ((mImageUri != null))
            {
                if(tempqty.isEmpty() || tempphone.isEmpty())
                {
                    if(tempqty.isEmpty())
                    {
                        UploadPrescriptionQtyEtx.setError("Quantity Required");
                    }

                    if(tempphone.isEmpty())
                    {
                        UploadPrescriptionPhoneNumber.setError("Phone Number Required");
                    }
                }
                else
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

                            MedicalPrescriptionModel model = new MedicalPrescriptionModel(downloadUrl.toString(), uploadId, tempqty, "0", a1,tempphone,"");
                            Toast.makeText(UploadPrescription_Activity.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                            mImageUri = null;

                            UploadPrescriptionQtyEtx.setText("");
                            UploadPrescriptionPhoneNumber.setText("");
                            uploadPrescriptionImg.setImageResource(R.drawable.uploadicon);

                            mdatabaseReference.child(uploadId).setValue(model);

                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadPrescription_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

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
                Picasso.get().load(mImageUri).into(uploadPrescriptionImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(final int position) {
//        try {
//            LayoutInflater li = LayoutInflater.from(context);
//            View viewPrescription = li.inflate(R.layout.fullscreenprescription, null);
//            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//            alertBuilder.setView(viewPrescription);
//            alertBuilder.setCancelable(true);
//
//            final  EditText viewPresriptionMobile, viewPresriptionQty;
//            final Button viewPresriptionReorderCancel, viewPresriptionReorderOrder;
//
//            viewPresriptionImage = viewPrescription.findViewById(R.id.viewPresriptionImage);
//            viewPresriptionReorder = viewPrescription.findViewById(R.id.viewPresriptionReorder);
//            viewPresriptionReorderCancel = viewPrescription.findViewById(R.id.viewPresriptionReorderCancel);
//            viewPresriptionReorderOrder = viewPrescription.findViewById(R.id.viewPresriptionReorderOrder);
//            viewPresriptionMobile = viewPrescription.findViewById(R.id.viewPresriptionMobile);
//            viewPresriptionQty = viewPrescription.findViewById(R.id.viewPresriptionQty);
//
//            Picasso.get().load(medicalPrescriptionModels.get(position).getImage()).into(viewPresriptionImage);
//
//            final AlertDialog alertDialog = alertBuilder.create();
//
//            viewPresriptionReorder.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v)
//                {
//                    viewPresriptionReorderCancel.setVisibility(View.VISIBLE);
//                    viewPresriptionReorderOrder.setVisibility(View.VISIBLE);
//                    viewPresriptionMobile.setVisibility(View.VISIBLE);
//                    viewPresriptionQty.setVisibility(View.VISIBLE);
//
//                    viewPresriptionReorderCancel.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            alertDialog.cancel();
//                        }
//                    });
//
//                    viewPresriptionReorderOrder.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v)
//                        {
//                            String temp = medicalPrescriptionModels.get(position).getStatus();
//                            if (temp.equals("2"))
//                            {
//                                String tempqty = viewPresriptionQty.getText().toString();
//                                String tempMobile = viewPresriptionMobile.getText().toString();
//                                if(tempqty.isEmpty() || tempMobile.isEmpty())
//                                {
//                                    if(tempMobile.isEmpty())
//                                    {
//                                        viewPresriptionMobile.setError("Mobile Number Required");
//                                    }
//                                    if(tempqty.isEmpty())
//                                    {
//                                        viewPresriptionQty.setError("Quantity Required");
//                                    }
//                                }
//                                else
//                                {
//                                    String id = medicalPrescriptionModels.get(position).getUploadId();
//                                    progressDialog.show();
//                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("medical_details").child(id);
//                                    reference.child("status").setValue("0");
//                                    reference.child("qty").setValue(tempqty);
//                                    reference.child("phone").setValue(tempMobile);
//                                    progressDialog.dismiss();
//                                    Toast.makeText(UploadPrescription_Activity.this, "Selected item is Re-Orderd", Toast.LENGTH_SHORT).show();
//                                    alertDialog.dismiss();
//                                }
//
//                            } else {
//                                Toast.makeText(UploadPrescription_Activity.this, "Sorry you can re-order, only after the product is delivered", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });
//                }
//            });
//
//            alertDialog.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}