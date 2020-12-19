package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login_Activity extends AppCompatActivity {

    private EditText loginemailaddres, loginpassword;
    private Button loginbtn;
    private TextView loginregisterbtn;
    private TextView loginforgotpassword;
    private FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {

            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loging...");

            loginemailaddres = findViewById(R.id.loginemailaddress);
            loginpassword = findViewById(R.id.loginpassword);
            loginbtn = findViewById(R.id.loginbtn);
            loginregisterbtn = findViewById(R.id.loginregisterbtn);
            loginforgotpassword = findViewById(R.id.loginforgotpassword);
            firebaseAuth = FirebaseAuth.getInstance();

            loginregisterbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login_Activity.this, Register_Activity.class));
                }
            });

            loginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = loginemailaddres.getText().toString();
                    String password = loginpassword.getText().toString();
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(Login_Activity.this, "All fireds are Required", Toast.LENGTH_SHORT).show();
                    } else {
                        login(email, password);
                    }
                }
            });

            loginforgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login_Activity.this, ForgotPassword_Activity.class);
                    startActivity(intent);
                }
            });

//            videoView();
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

    private void login(String email, String password)
    {
        try {
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        sharedPreferences = getSharedPreferences("data", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userid", task.getResult().getUser().getUid());
                        editor.putString("useremail", task.getResult().getUser().getEmail());
                        editor.putString("username", task.getResult().getUser().getDisplayName());
                        editor.putBoolean("login_status", true);
                        editor.apply();


                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
