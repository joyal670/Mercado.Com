package com.mercado.mercadocom.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercado.mercadocom.Activity.MainActivity;
import com.mercado.mercadocom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings_Fragment extends Fragment {

    SharedPreferences sharedPreferences;
    FirebaseUser user;
    FirebaseAuth auth;
    String userId;
    public ProgressDialog progressDialog;

    public Settings_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        try {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Changing Password Hang On...");

            auth = FirebaseAuth.getInstance();
            userId = auth.getCurrentUser().getUid();
            user = auth.getCurrentUser();

            changePassword();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void changePassword()
    {
        try {
            sharedPreferences = getActivity().getSharedPreferences("data", 0);
            String a1 = sharedPreferences.getString("userid", "");

            LayoutInflater li = LayoutInflater.from(getContext());
            View changePassword = li.inflate(R.layout.change_password_page, null);
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
            alertBuilder.setView(changePassword);
            alertBuilder.setCancelable(false);

            final EditText changepasswordNewPass, changepasswordNewPass1;
            Button changepasswordCancel, changepasswordSave;

            changepasswordNewPass = changePassword.findViewById(R.id.changepasswordNewPass);
            changepasswordNewPass1 = changePassword.findViewById(R.id.changepasswordNewPass1);
            changepasswordCancel = changePassword.findViewById(R.id.changepasswordCancel);
            changepasswordSave = changePassword.findViewById(R.id.changepasswordSave);

            final AlertDialog alertDialog = alertBuilder.create();

            changepasswordSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String new1 = changepasswordNewPass.getText().toString();
                    String new2 = changepasswordNewPass1.getText().toString();

                    if (new1.isEmpty() || new2.isEmpty()) {
                        if (new1.isEmpty()) {
                            changepasswordNewPass.setError("New Password Required");
                        }
                        if (new2.isEmpty()) {
                            changepasswordNewPass1.setError("Conform Password Required");
                        }
                    } else {
                        if (new1.equals(new2)) {
                            progressDialog.show();
                            user.updatePassword(new2).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Password Updated", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            Toast.makeText(getContext(), "Password Not Matched", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            });

            changepasswordCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });

            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
