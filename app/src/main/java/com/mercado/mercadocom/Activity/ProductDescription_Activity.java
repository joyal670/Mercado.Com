package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mercado.mercadocom.Adapters.imageSliderAdapter;
import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.Database.MobUser_Table;
import com.mercado.mercadocom.Fragments.Mycart_Fragment;
import com.mercado.mercadocom.Interface.cartInterface;
import com.mercado.mercadocom.Model.ProductModel;
import com.mercado.mercadocom.Model.imageSliderModel;
import com.mercado.mercadocom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductDescription_Activity extends AppCompatActivity  {

    ImageView desc_image;
    TextView desc_price, desc_stock, desc_name;
    ImageButton desc_remove, desc_add;
    TextView desc_qty;
    TextView desc_description;
    Button desc_addtoCart;
    SharedPreferences sharedPreferences;
    List<MobUser> UserList;

    String s, getpName, getmDescription, getpPrice, getpType, getpStoke, getpUserKey, getmKey, position;

    public static cartInterface cartInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        try {

        s = getIntent().getExtras().getString("s");
        getpName = getIntent().getExtras().getString("getpName");
        getmDescription = getIntent().getExtras().getString("getmDescription");
        getpPrice = getIntent().getExtras().getString("getpPrice");
        getpType = getIntent().getExtras().getString("getpType");
        getpStoke = getIntent().getExtras().getString("getpStoke");
        getpUserKey = getIntent().getExtras().getString("getpUserKey");
        getmKey = getIntent().getExtras().getString("getmKey");
        position = getIntent().getExtras().getString("position");

        desc_image = findViewById(R.id.desc_image);
        desc_price = findViewById(R.id.desc_price);
        desc_stock = findViewById(R.id.desc_stock);
        desc_name = findViewById(R.id.desc_name);
        desc_description = findViewById(R.id.desc_description);
        desc_addtoCart = findViewById(R.id.desc_addtoCart);



        Picasso.get().load(s).into(desc_image);
        desc_price.setText(getpPrice);
        desc_stock.setText(getpStoke);
        desc_name.setText(getpName);
        desc_description.setText(getmDescription);

        desc_addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartFn();
            }
        });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToCartFn()
    {
        try{

        sharedPreferences = getSharedPreferences("data",0);
        String a1 = sharedPreferences.getString("shop_ownerId","");

        if(getpStoke.equals("In Stock"))
        {
            UserList = SQLite.select().from(MobUser.class).where(MobUser_Table.pMkey.eq(getmKey)).queryList();
            if(UserList.size() == 0 )
            {
                MobUser mobUser = new MobUser();
                mobUser.InsertData(s,getpName,getmDescription,getpPrice,getpType,getpStoke,getpUserKey,getmKey,"1");
                boolean checkSave =  mobUser.save();
                Toast.makeText(ProductDescription_Activity.this, "Added to Cart", Toast.LENGTH_SHORT).show();

            }
            else
            {
                int q = Integer.parseInt(UserList.get(0).getPqty());
                q = q + 1;
                String w = String.valueOf(q);
                SQLite.update(MobUser.class).set(MobUser_Table.pqty.eq(w)).where(MobUser_Table.pMkey.eq(getmKey)).execute();
                Toast.makeText(this, "Already exists in cart, Added one more Item !!!", Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            Toast.makeText(this, "Out of Stock", Toast.LENGTH_SHORT).show();
        }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}