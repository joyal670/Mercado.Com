package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.mercado.mercadocom.Adapters.ProductListAdapter;
import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.Database.MobUser_Table;
import com.mercado.mercadocom.Interface.listInterface;
import com.mercado.mercadocom.Model.ProductModel;
import com.mercado.mercadocom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class HomeSearch_Activity extends AppCompatActivity implements listInterface {

    String search;
    ListView searchHomeRecycler;
    Query databaseReference;
    List<ProductModel> mProductmodel;
    ProductListAdapter productAdapter;
    List<MobUser> UserList;
    SearchView homeSearchView;
    private ValueEventListener mDBListener;
    public ProgressDialog progressDialog;
    public static listInterface listInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);

        try {

            listInterface = this;

            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading Products...");

            homeSearchView = findViewById(R.id.homeSearchView);
            homeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchProduct(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchProduct(newText);
                    return false;
                }
            });

//            search = getIntent().getExtras().getString("key");

            searchHomeRecycler = findViewById(R.id.searchHomeRecycler);
            searchHomeRecycler.setVisibility(View.INVISIBLE);

            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference("products");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    mProductmodel.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ProductModel upload = dataSnapshot1.getValue(ProductModel.class);
                        upload.setmKey(dataSnapshot1.getKey());
                        mProductmodel.add(upload);
//                        if(upload.getpName().toLowerCase().contains(search.toLowerCase()))
//                        {
//
//                            mProductmodel.add(upload);
//                        }

                    }
                    productAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(HomeSearch_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            mProductmodel = new ArrayList<>();
            productAdapter = new ProductListAdapter(HomeSearch_Activity.this, mProductmodel);
            searchHomeRecycler.setAdapter(productAdapter);
//            searchHomeRecycler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//                {
//
//                    Intent intent = new Intent(HomeSearch_Activity.this, ProductDescription_Activity.class);
//                    intent.putExtra("s",mProductmodel.get(position).getpImageUrl());
//                    intent.putExtra("getpName",mProductmodel.get(position).getpName());
//                    intent.putExtra("getmDescription",mProductmodel.get(position).getmDescription());
//                    intent.putExtra("getpPrice",mProductmodel.get(position).getpPrice());
//                    intent.putExtra("getpType",mProductmodel.get(position).getpType());
//                    intent.putExtra("getpStoke",mProductmodel.get(position).getpStoke());
//                    intent.putExtra("getpUserKey",mProductmodel.get(position).getpUserKey());
//                    intent.putExtra("getmKey",mProductmodel.get(position).getmKey());
//                    intent.putExtra("position",position);
//                    startActivity(intent);
//
//                }
//            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchProduct(String query) {
        try {
            progressDialog.show();
            ArrayList<ProductModel> myList = new ArrayList<>();
            for (ProductModel obj : mProductmodel) {
                if (obj.getpName().toLowerCase().contains(query.toLowerCase())) {
                    myList.add(obj);
                }
            }

            searchHomeRecycler.setVisibility(View.VISIBLE);
            ProductListAdapter adapter = new ProductListAdapter(HomeSearch_Activity.this, myList);
            searchHomeRecycler.setAdapter(adapter);
            progressDialog.dismiss();
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

    @Override
    public void add(String s, String getpName, String getmDescription, String getpPrice, String getpType, String getpStoke, String getpUserKey, String getmKey, int position)
    {
        if(getpStoke.equals("In Stock"))
        {
            ProductModel model = mProductmodel.get(position);
            String key = model.getmKey();

            UserList = SQLite.select().from(MobUser.class).where(MobUser_Table.pMkey.eq(key)).queryList();
            if(UserList.size() == 0 )
            {
                MobUser mobUser = new MobUser();
                mobUser.InsertData(s,getpName,getmDescription,getpPrice,getpType,getpStoke,getpUserKey,getmKey,"1");
                boolean checkSave =  mobUser.save();
                Toast.makeText(HomeSearch_Activity.this, "Added to Cart", Toast.LENGTH_SHORT).show();

            }
            else
            {
                int q = Integer.parseInt(UserList.get(0).getPqty());
                q = q + 1;
                String w = String.valueOf(q);
                SQLite.update(MobUser.class).set(MobUser_Table.pqty.eq(w)).where(MobUser_Table.pMkey.eq(key)).execute();
                Toast.makeText(this, "Already exists in cart, Added one more Item !!!", Toast.LENGTH_SHORT).show();

                //myqty = myqty + 1;
                //String myquantity = String.valueOf(myqty);
                //SQLite.update(MobUser.class).set(MobUser_Table.qty.eq(myquantity) ).execute();
                //Toast.makeText(this, "Already exists in cart", Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            Toast.makeText(this, "Out of Stock", Toast.LENGTH_SHORT).show();
        }

    }
}