package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mercado.mercadocom.Adapters.ProductAdapter;
import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.Database.MobUser_Table;
import com.mercado.mercadocom.Interface.cartInterface;
import com.mercado.mercadocom.Interface.productInterface;
import com.mercado.mercadocom.Model.ProductModel;
import com.mercado.mercadocom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class ProductList_Activity extends AppCompatActivity implements productInterface {

    SharedPreferences sharedPreferences;
    RecyclerView viewproductrecycler;
    SearchView search_Products;
    Query databaseReference;
    List<ProductModel> mProductmodel;
    ProductAdapter productAdapter;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private StorageTask mUploadTask;
    private FirebaseAuth firebaseAuth;
    private ValueEventListener mDBListener;
    public static productInterface productInterface;
    List<MobUser> UserList;
    public ProgressDialog progressDialog;

    String sId;
    String sType;

    FloatingActionButton AddPrescription;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        try {


            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading Products...");

            sharedPreferences = getSharedPreferences("data", 0);
            String a1 = sharedPreferences.getString("shop_ownerId", "");

            sId = getIntent().getExtras().getString("sId");
            sType = getIntent().getExtras().getString("sType");

            productInterface = this;

            AddPrescription = findViewById(R.id.AddPrescription);
            if(sType.equals("Medicalshop"))
            {
                AddPrescription.setVisibility(View.VISIBLE);
            }

            AddPrescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    String[] items = {"Upload Prescription"};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ProductList_Activity.this);
                    dialog.setTitle("Select Options");
                    dialog.setItems(items, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            if (which == 0)
                            {
                                Intent intent = new Intent(ProductList_Activity.this, UploadPrescription_Activity.class);
                                startActivity(intent);
                            }

                        }
                    });
                    dialog.create().show();
                }
            });

            search_Products = findViewById(R.id.search);
            search_Products.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    search(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return false;
                }
            });


            viewproductrecycler = findViewById(R.id.productrecyclerview);
//            viewproductrecycler.setLayoutManager(new LinearLayoutManager(this));
//            viewproductrecycler.setHasFixedSize(true);
            firebaseStorage = FirebaseStorage.getInstance();

            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference("products")
                    .orderByChild("pUserKey").equalTo(sId);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    mProductmodel.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        ProductModel upload = dataSnapshot1.getValue(ProductModel.class);
                        upload.setmKey(dataSnapshot1.getKey());
                        mProductmodel.add(upload);
                    }
                    productAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(ProductList_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            mProductmodel = new ArrayList<>();
            productAdapter = new ProductAdapter(ProductList_Activity.this, mProductmodel);
            viewproductrecycler.setAdapter(productAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search(String query)
    {
        try {
            ArrayList<ProductModel> myList = new ArrayList<>();
            for (ProductModel obj : mProductmodel)
            {
                if (obj.getpName().toLowerCase().contains(query.toLowerCase())) {
                    myList.add(obj);
                }
            }
            ProductAdapter adapter = new ProductAdapter(ProductList_Activity.this, myList);
            viewproductrecycler.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


//    public void addProduct(String s, String getpName, String getmDescription, String getpPrice, String getpType, String getpStoke, String getpUserKey, String getmKey, int position)
//    {
////        Intent intent = new Intent(ProductList_Activity.this, ProductDescription_Activity.class);
////        intent.putExtra("s", s);
////        intent.putExtra("getpName", getpName);
////        intent.putExtra("getmDescription", getmDescription);
////        intent.putExtra("getpPrice", getpPrice);
////        intent.putExtra("getpType", getpType);
////        intent.putExtra("getpStoke", getpStoke);
////        intent.putExtra("getpUserKey", getpUserKey);
////        intent.putExtra("getmKey", getmKey);
////        intent.putExtra("position", position);
////        startActivity(intent);
//
//
//        sharedPreferences = getSharedPreferences("data",0);
//        String a1 = sharedPreferences.getString("shop_ownerId","");
//
//        ProductModel model = mProductmodel.get(position);
//        String key = model.getmKey();
//
//        UserList = SQLite.select().from(MobUser.class).where(MobUser_Table.pMkey.eq(key)).queryList();
//        if(UserList.size() == 0 )
//        {
//            MobUser mobUser = new MobUser();
//            mobUser.InsertData(s,getpName,getmDescription,getpPrice,getpType,getpStoke,getpUserKey,getmKey,"1");
//            boolean checkSave =  mobUser.save();
//            Toast.makeText(ProductList_Activity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
//
//        }
//        else
//        {
//            int q = Integer.parseInt(UserList.get(0).getPqty());
//            q = q + 1;
//            String w = String.valueOf(q);
//            SQLite.update(MobUser.class).set(MobUser_Table.pqty.eq(w)).where(MobUser_Table.pMkey.eq(key)).execute();
//            Toast.makeText(this, "Already exists in cart, Added one more Item !!!", Toast.LENGTH_SHORT).show();
//
//            //myqty = myqty + 1;
//            //String myquantity = String.valueOf(myqty);
//            //SQLite.update(MobUser.class).set(MobUser_Table.qty.eq(myquantity) ).execute();
//            //Toast.makeText(this, "Already exists in cart", Toast.LENGTH_SHORT).show();
//
//        }
//    }
//

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void currentProduct(String s, String getpName, String getmDescription, String getpPrice, String getpType, String getpStoke, String getpUserKey, String getmKey, int position)
    {
       try{

           Intent intent = new Intent(ProductList_Activity.this, ProductDescription_Activity.class);
           intent.putExtra("s", s);
           intent.putExtra("getpName", getpName);
           intent.putExtra("getmDescription", getmDescription);
           intent.putExtra("getpPrice", getpPrice);
           intent.putExtra("getpType", getpType);
           intent.putExtra("getpStoke", getpStoke);
           intent.putExtra("getpUserKey", getpUserKey);
           intent.putExtra("getmKey", getmKey);
           intent.putExtra("position", position);
           startActivity(intent);


       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
