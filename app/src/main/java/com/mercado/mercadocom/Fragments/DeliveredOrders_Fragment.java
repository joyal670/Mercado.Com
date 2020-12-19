package com.mercado.mercadocom.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mercado.mercadocom.Adapters.orderAdapter;
import com.mercado.mercadocom.Model.CheckoutModel;
import com.mercado.mercadocom.R;

import java.util.ArrayList;
import java.util.List;


public class DeliveredOrders_Fragment extends Fragment {

    RecyclerView DeliverdOrdersRecyclerView;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    List<CheckoutModel> mCheckoutmodel;
    orderAdapter orderAdapter;
    public ProgressDialog progressDialog;
    String status = "2";

    public DeliveredOrders_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivered_orders_, container, false);

        try{

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading Order Details...");

            DeliverdOrdersRecyclerView = view.findViewById(R.id.DeliverdOrdersRecyclerView);

            sharedPreferences = this.getActivity().getSharedPreferences("data", 0);
            final String a1 = sharedPreferences.getString("userid", "");

            DeliverdOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            DeliverdOrdersRecyclerView.setHasFixedSize(true);

            progressDialog.show();
            databaseReference = FirebaseDatabase.getInstance().getReference("order_data");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    mCheckoutmodel.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        if (a1.equals(dataSnapshot1.child("userId").getValue().toString()))
                        {
                            if(status.equals(dataSnapshot1.child("status").getValue().toString()))
                            {
                                    CheckoutModel model = dataSnapshot1.getValue(CheckoutModel.class);
                                    mCheckoutmodel.add(model);

                            }
                        }
                    }
                    orderAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            mCheckoutmodel = new ArrayList<>();
            orderAdapter = new orderAdapter(getContext(), mCheckoutmodel);
            DeliverdOrdersRecyclerView.setAdapter(orderAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }
}