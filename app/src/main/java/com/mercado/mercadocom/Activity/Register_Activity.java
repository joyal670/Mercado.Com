package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mercado.mercadocom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class Register_Activity extends AppCompatActivity {

    private EditText registerusername, registeremailaddress, registerpassword;
    private TextView goback;
    private Button registerbtn;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Registering Please Wait...");

            registerusername = findViewById(R.id.registerusername);
            registeremailaddress = findViewById(R.id.registeremailaddress);
            registerpassword = findViewById(R.id.registerpassword);
            registerbtn = findViewById(R.id.registerbtn);
            goback = findViewById(R.id.goback);

            firebaseAuth = FirebaseAuth.getInstance();

            registerbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username = registerusername.getText().toString();
                    final String emailaddress = registeremailaddress.getText().toString();
                    final String password = registerpassword.getText().toString();

                    if (username.isEmpty() || emailaddress.isEmpty() || password.isEmpty()) {
                        Toast.makeText(Register_Activity.this, "All Fileds are Required", Toast.LENGTH_SHORT).show();
                    } else {
                        register(username, emailaddress, password);
                    }
                }
            });

            goback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
            });

//            videoView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(final String username, final String emailaddress, String password)
    {
        try {
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(emailaddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser rUser = firebaseAuth.getCurrentUser();
                        String userId = rUser.getUid();

                        sharedPreferences = getSharedPreferences("data", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userid", userId);
                        editor.putString("useremail", task.getResult().getUser().getEmail());
                        editor.putBoolean("login_status", true);
                        editor.putString("username", task.getResult().getUser().getDisplayName());
                        editor.apply();

                        databaseReference = FirebaseDatabase.getInstance().getReference("user_data").child(userId);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("userId", userId);
                        hashMap.put("userName", username);
                        hashMap.put("userEmail", emailaddress);
                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(Register_Activity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(Register_Activity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Register_Activity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void videoView()
//    {
//        VideoView videoView =(VideoView)findViewById(R.id.video);
//        MediaController mediaController= new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.giphy);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//            }
//        });
//    }
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//            videoView();
//    }

}
