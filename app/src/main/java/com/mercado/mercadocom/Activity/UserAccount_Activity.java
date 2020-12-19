package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mercado.mercadocom.Adapters.ProfileAdapter;
import com.mercado.mercadocom.Model.ProfileModel;
import com.mercado.mercadocom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAccount_Activity extends AppCompatActivity implements ProfileAdapter.OnitemClickListener{

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;

    TextView useraccount_email, useraccount_name;
    ImageView accountImage;
    Button useraccount_editprofile;
    RecyclerView useraccount_listview;
    List<ProfileModel> mProfileModel;
    ProfileAdapter profileAdapter;
    public ProgressDialog progressDialog;
    private ValueEventListener mDBListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading Your Data...");

            sharedPreferences = getSharedPreferences("data", 0);
            final String a1 = sharedPreferences.getString("userid", "");

            useraccount_email = findViewById(R.id.useraccount_email);
            useraccount_name = findViewById(R.id.useraccount_name);
            useraccount_editprofile = findViewById(R.id.useraccount_editprofile);
            useraccount_listview = findViewById(R.id.useraccount_listview);
            accountImage = findViewById(R.id.accountImage);

            try {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String em = sharedPreferences.getString("useremail", "");
                String nm = sharedPreferences.getString("username", "");
                useraccount_email.setText(em);
                useraccount_name.setText(nm);
            } catch (Exception e) {
                e.printStackTrace();
            }


            final String temp = sharedPreferences.getString("profileimage", "");
            if (temp.equals("")) {
                Picasso.get().load(R.drawable.nopic).into(accountImage);
            } else {
                Picasso.get().load(temp).into(accountImage);

            }

            progressDialog.show();
            databaseReference = FirebaseDatabase.getInstance().getReference("user_data").child(a1).child("user_details");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    mProfileModel.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ProfileModel model = dataSnapshot1.getValue(ProfileModel.class);
                        mProfileModel.add(model);
                    }
                    profileAdapter.notifyDataSetChanged();
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(UserAccount_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            mProfileModel = new ArrayList<>();
            profileAdapter = new ProfileAdapter(UserAccount_Activity.this, mProfileModel);
            useraccount_listview.setAdapter(profileAdapter);
            profileAdapter.setOnClickListener(UserAccount_Activity.this);


            useraccount_editprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserAccount_Activity.this, UpdateProfile_Activity.class);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserAccount_Activity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position)
    {
        Picasso.get().load(mProfileModel.get(position).getProfileImage()).into(accountImage);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileimage", mProfileModel.get(position).getProfileImage());
        editor.apply();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            databaseReference.removeEventListener(mDBListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
