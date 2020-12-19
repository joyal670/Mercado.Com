package com.mercado.mercadocom.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mercado.mercadocom.Activity.Address_Activity;
import com.mercado.mercadocom.Activity.Chat_Activity;
import com.mercado.mercadocom.Adapters.ChatAdapter;
import com.mercado.mercadocom.Adapters.ChatOrderAdapter;
import com.mercado.mercadocom.Adapters.DeliveryAddressAdapter;
import com.mercado.mercadocom.Adapters.orderAdapter;
import com.mercado.mercadocom.Model.ChatModel;
import com.mercado.mercadocom.Model.CheckoutModel;
import com.mercado.mercadocom.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Chat_Fragment extends Fragment {

    ListView chatOrderListView;
    public ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    List<CheckoutModel> mCheckoutmodel;
    ChatOrderAdapter chatOrderAdapter;

    public Chat_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        try{


        chatOrderListView = view.findViewById(R.id.chatOrderListView);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Your Orders...");

        sharedPreferences = this.getActivity().getSharedPreferences("data", 0);
        final String a1 = sharedPreferences.getString("userid", "");

        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference("order_data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                mCheckoutmodel.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if (a1.equals(dataSnapshot1.child("userId").getValue().toString())) {
                        CheckoutModel model = dataSnapshot1.getValue(CheckoutModel.class);
                        mCheckoutmodel.add(model);
                    }
                }
               chatOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mCheckoutmodel = new ArrayList<>();
        chatOrderAdapter = new ChatOrderAdapter(getContext(), mCheckoutmodel);
        chatOrderListView.setAdapter(chatOrderAdapter);
        chatOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getContext(), Chat_Activity.class);
                intent.putExtra("orderid", mCheckoutmodel.get(position).getOrderId());
                startActivity(intent);
            }
        });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


}