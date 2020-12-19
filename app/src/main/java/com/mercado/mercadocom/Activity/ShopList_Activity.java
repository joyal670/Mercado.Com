package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.mercado.mercadocom.Adapters.ShopAdapter;
import com.mercado.mercadocom.R;
import com.mercado.mercadocom.Model.ShopModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ShopList_Activity extends AppCompatActivity implements ShopAdapter.OnitemClickListener  {

    RecyclerView recyclerView;
    ShopAdapter imageAdapter;
    DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private ValueEventListener mDBListener;
    List<ShopModel> mShopModel;
    SharedPreferences sharedPreferences;
    public ProgressDialog progressDialog;

    String alldata = "";
    String indivigualData = "";
    String[] mydata;
    String id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading Shops...");

            recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            final String catagory = getIntent().getExtras().getString("shopcatagory");

            progressDialog.show();
            databaseReference = FirebaseDatabase.getInstance().getReference("shop_owner");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    mShopModel.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    alldata = alldata + String.valueOf(databaseReference.child(dataSnapshot1.getKey()) + " ");
//                    indivigualData = alldata.replace("https://mercado-20bad.firebaseio.com/shop_owner/", " ");
//                    mydata = indivigualData.split(" ");

                        for (DataSnapshot msg : dataSnapshot1.child("shop_details").getChildren()) {
                            if (catagory.equals(msg.child("mType").getValue().toString())) {
                                sharedPreferences = getSharedPreferences("data", 0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("shop_ownerId", msg.child("mUserKey").getValue().toString());
                                editor.apply();

                                ShopModel shopModel = msg.getValue(ShopModel.class);
                                mShopModel.add(shopModel);
                            }
                        }
                        imageAdapter.notifyDataSetChanged();
                    }

//                try {
//                    for (int i = 0; i < mydata.length; i++) {
//                        if (!mydata[i].isEmpty()) {
//                            id = id + mydata[i] + ",";
//                        }
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

//                System.out.println(id);
//                loadShopList(id);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(ShopList_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            mShopModel = new ArrayList<>();
            imageAdapter = new ShopAdapter(ShopList_Activity.this, mShopModel);
            recyclerView.setAdapter(imageAdapter);
            imageAdapter.setOnClickListener(ShopList_Activity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadShopList(String mId)
    {

        String[] newId = mId.split(",");
        String temp="";
        for(int i=0; i<newId.length; i++)
        {
            if ( newId[i].contains(","))
            {

            }
            else
            {
                temp = temp + "" + newId[i];
            }
        }
        System.out.println(temp);
    }

    @Override
    public void onItemClick(int position) {
        ShopModel selectedItem = mShopModel.get(position);
        String selectedKey = selectedItem.getmKey();

        Intent intent = new Intent(ShopList_Activity.this,ProductList_Activity.class);
        intent.putExtra("sId",mShopModel.get(position).getmUserKey());
        intent.putExtra("sType",mShopModel.get(position).getmType());
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            databaseReference.removeEventListener(mDBListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
