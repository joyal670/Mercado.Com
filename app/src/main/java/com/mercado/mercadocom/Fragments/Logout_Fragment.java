package com.mercado.mercadocom.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercado.mercadocom.Activity.Login_Activity;
import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.raizlabs.android.dbflow.sql.language.Delete;

/**
 * A simple {@link Fragment} subclass.
 */
public class Logout_Fragment extends Fragment {

    SharedPreferences sharedPreferences;
    public ProgressDialog progressDialog;

    public Logout_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Signing Out...");

        sharedPreferences = getActivity().getSharedPreferences("data",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Delete.table(MobUser.class);

        progressDialog.show();

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), Login_Activity.class);
        startActivity(intent);
        getActivity().finish();

        progressDialog.dismiss();
        return view;
    }
}
