package com.mercado.mercadocom.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.Model.CheckoutModel;
import com.mercado.mercadocom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Checkout_Activity extends AppCompatActivity {

    TextView checkoutaddressLine1, checkoutaddressLine2;
    TextView chekoutcity, chekoutdistrict;
    TextView chekoutpincode;
    TextView chekoutcontactname, chekoutcontactnumber, chekoutcontactalternatenumber;

    Button chekoutdeliveryaddressChangeBtn;
    ListView checkoutListView;

    TextView chekouttotalprice;
    TextView chekoutqty;
    Button chekoutbutton;

    List<MobUser> UserList;

    int tempId,tempQty;
    float tempPrice,total;

    final Context context = this;

//    TextView newaddline1, newaddline2;
//    TextView newaddcity;
//    Spinner newadddistrict;
//    TextView newaddpincode;
//    TextView newaddname, newaddnumber, newaddalternatenumber;
//    Button newaddcancelbtn, newaddsavebtn;

    SharedPreferences sharedPreferences;
    String a1;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    String s7;
    String s8;
    String s9;
    String status = "0";
    String pushKey;

    private DatabaseReference mdatabaseReference;
    private StorageReference mstorageReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private StorageTask mUploadTask;
    CheckoutModel checkoutModel;

    public ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");

            checkoutaddressLine1 = findViewById(R.id.checkoutaddressLine1);
            checkoutaddressLine2 = findViewById(R.id.checkoutaddressLine2);
            chekoutcity = findViewById(R.id.chekoutcity);
            chekoutdistrict = findViewById(R.id.chekoutdistrict);
            chekoutpincode = findViewById(R.id.chekoutpincode);
            chekoutcontactname = findViewById(R.id.chekoutcontactname);
            chekoutcontactnumber = findViewById(R.id.chekoutcontactnumber);
            chekoutcontactalternatenumber = findViewById(R.id.chekoutcontactalternatenumber);
            chekoutdeliveryaddressChangeBtn = findViewById(R.id.chekoutdeliveryaddressChangeBtn);
            chekouttotalprice = findViewById(R.id.chekouttotalprice);
            chekoutqty = findViewById(R.id.chekoutqty);
            chekoutbutton = findViewById(R.id.chekoutbutton);

            try {
                List<MobUser> UserCheckout = SQLite.select().from(MobUser.class).queryList();
                for (MobUser mu : UserCheckout)
                {
                    tempQty = 0;
                    tempPrice = 0;
                    try {
                        tempId = mu.getId();
                        tempQty = tempQty + Integer.parseInt(mu.getPqty());
                        tempPrice = tempPrice + Float.parseFloat(mu.getpPrice());
                        float p = tempPrice * tempQty;
                        total = total + p;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sharedPreferences = getSharedPreferences("data", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("totalprice", chekouttotalprice.getText().toString());
                editor.apply();

                chekouttotalprice.setText(total + "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            chekoutdeliveryaddressChangeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddressSelectionAlert();
                }
            });

            chekoutbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalCheckOut();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finalCheckOut()
    {
        try {
            String checkVal = checkoutaddressLine1.getText().toString();
            if (checkVal.isEmpty()) {
                Toast.makeText(context, "Please add Delivery Address", Toast.LENGTH_SHORT).show();
            } else {
                sharedPreferences = getSharedPreferences("data", 0);
                String a1 = sharedPreferences.getString("userid", "");
                s1 = sharedPreferences.getString("house_number", "");
                s2 = sharedPreferences.getString("area", "");
                s3 = sharedPreferences.getString("city", "");
                s4 = sharedPreferences.getString("district", "");
                s5 = sharedPreferences.getString("pincode", "");
                s6 = sharedPreferences.getString("cName", "");
                s7 = sharedPreferences.getString("cNumber", "");
                s8 = sharedPreferences.getString("cAltNumber", "");
                s9 = sharedPreferences.getString("totalprice", "");

                mdatabaseReference = FirebaseDatabase.getInstance().getReference("order_data");
                checkoutModel = new CheckoutModel();

                UserList = SQLite.select().from(MobUser.class).queryList();
                List<MobUser> UserCheckout = SQLite.select().from(MobUser.class).queryList();
                for (MobUser mu : UserCheckout)
                {
                    checkoutModel.setProductImageUrl(mu.getpImage());
                    checkoutModel.setProductName(mu.getpName());
                    checkoutModel.setProductDescription(mu.getpDescription());
                    checkoutModel.setProductPrice(mu.getpPrice());
                    checkoutModel.setProductType(mu.getpType());
                    checkoutModel.setProductStoke(mu.getpStoke());
                    checkoutModel.setShopownerId(mu.getpUserKey());
                    checkoutModel.setProductId(mu.getpMkey());
                    checkoutModel.setUserId(a1);
                    checkoutModel.setProductQty(mu.getPqty());

                    checkoutModel.setHouse(s1);
                    checkoutModel.setArea(s2);
                    checkoutModel.setCity(s3);
                    checkoutModel.setDistrict(s4);
                    checkoutModel.setPincode(s5);
                    checkoutModel.setContName(s6);
                    checkoutModel.setContNumber(s7);
                    checkoutModel.setContAltNumber(s8);
                    checkoutModel.setTotalPrice(s9);
                    checkoutModel.setStatus("0");
                    checkoutModel.setDeliveryBoyId("");

                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    checkoutModel.setOrderDate(date);

                    checkoutModel.setTemp1("");
                    checkoutModel.setTemp2("");
                    checkoutModel.setOrderDeliveryDate("");

                    pushKey = mdatabaseReference.push().getKey();
                    checkoutModel.setOrderId(pushKey);
                    mdatabaseReference.child(pushKey).setValue(checkoutModel);

                }
                Intent intent = new Intent(Checkout_Activity.this, orderConfirm_Activity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddressSelectionAlert()
    {
        Intent intent = new Intent(Checkout_Activity.this, Address_Activity.class);
        startActivity(intent);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sharedPreferences = getSharedPreferences("data", 0);
        s1 = sharedPreferences.getString("house_number", "");
        s2 = sharedPreferences.getString("area", "");
        s3 = sharedPreferences.getString("city", "");
        s4 = sharedPreferences.getString("district", "");
        s5 = sharedPreferences.getString("pincode", "");
        s6 = sharedPreferences.getString("cName", "");
        s7 = sharedPreferences.getString("cNumber", "");
        s8 = sharedPreferences.getString("cAltNumber", "");

        checkoutaddressLine1.setText(s1);
        checkoutaddressLine2.setText(s2);
        chekoutcity.setText(s3);
        chekoutdistrict.setText(s4);
        chekoutpincode.setText(s5);
        chekoutcontactname.setText(s6);
        chekoutcontactnumber.setText(s7);
        chekoutcontactalternatenumber.setText(s8);

    }

//    @Override
//    public void onBackPressed() {
//        try {
//        super.onBackPressed();
//
//
//            Mycart_Fragment fragment = new Mycart_Fragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame_layout, fragment, "My Cart");
//            fragmentTransaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
