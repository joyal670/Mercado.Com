package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mercado.mercadocom.Adapters.DeliveryAddressAdapter;
import com.mercado.mercadocom.Model.DeliveryAddressModel;
import com.mercado.mercadocom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Address_Activity extends AppCompatActivity {

    TextView addAddressaddressLine1, addAddressaddressLine2, addAddresscity, addAddressdistrict, addAddresspincode, addAddresscontactname, addAddresscontactnumber, addAddresscontactalternatenumber;
    ListView addAddressListView;
    FloatingActionButton addAddressfloatingActionButton;

    TextView newaddline1, newaddline2;
    TextView newaddcity;
    Spinner newadddistrict;
    TextView newaddpincode;
    TextView newaddname, newaddnumber, newaddalternatenumber;
    Button newaddcancelbtn, newaddsavebtn;

    public ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    final Context context = this;

    private DatabaseReference mdatabaseReference;

    String UserId = "";

    DeliveryAddressModel deliveryAddressModel;
    DeliveryAddressAdapter deliveryAddressAdapter;
    List<DeliveryAddressModel> mDeliveryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");


        addAddressaddressLine1 = findViewById(R.id.addAddressaddressLine1);
        addAddressaddressLine2 = findViewById(R.id.addAddressaddressLine2);
        addAddresscity = findViewById(R.id.addAddresscity);
        addAddressdistrict = findViewById(R.id.addAddressdistrict);
        addAddresspincode = findViewById(R.id.addAddresspincode);
        addAddresscontactname = findViewById(R.id.addAddresscontactname);
        addAddresscontactnumber = findViewById(R.id.addAddresscontactnumber);
        addAddresscontactalternatenumber = findViewById(R.id.addAddresscontactalternatenumber);

        addAddressListView = findViewById(R.id.addAddressListView);

        addAddressfloatingActionButton = findViewById(R.id.addAddressfloatingActionButton);

        sharedPreferences = getSharedPreferences("data", 0);
        UserId = sharedPreferences.getString("userid", "");

        addAddressfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAddress();
            }
        });


        progressDialog.show();
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("user_data").child(UserId).child("Delivery_Address");
        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                mDeliveryModel.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    DeliveryAddressModel model = dataSnapshot1.getValue(DeliveryAddressModel.class);
                    mDeliveryModel.add(model);
                }
                deliveryAddressAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(Address_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mDeliveryModel = new ArrayList<>();
        deliveryAddressAdapter = new DeliveryAddressAdapter(Address_Activity.this, mDeliveryModel);
        addAddressListView.setAdapter(deliveryAddressAdapter);
        addAddressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                addAddressaddressLine1.setText(mDeliveryModel.get(position).getHouse_number());
                addAddressaddressLine2.setText(mDeliveryModel.get(position).getArea());
                addAddresscity.setText(mDeliveryModel.get(position).getCity());
                addAddressdistrict.setText(mDeliveryModel.get(position).getDistrict());
                addAddresspincode.setText(mDeliveryModel.get(position).getPincode());
                addAddresscontactname.setText(mDeliveryModel.get(position).getcName());
                addAddresscontactnumber.setText(mDeliveryModel.get(position).getcNumber());
                addAddresscontactalternatenumber.setText(mDeliveryModel.get(position).getcAltNumber());

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("house_number", mDeliveryModel.get(position).getHouse_number());
                editor.putString("area", mDeliveryModel.get(position).getArea());
                editor.putString("city", mDeliveryModel.get(position).getCity());
                editor.putString("district", mDeliveryModel.get(position).getDistrict());
                editor.putString("pincode", mDeliveryModel.get(position).getPincode());
                editor.putString("cName", mDeliveryModel.get(position).getcName());
                editor.putString("cNumber", mDeliveryModel.get(position).getcNumber());
                editor.putString("cAltNumber", mDeliveryModel.get(position).getcAltNumber());
                editor.apply();

                Intent intent = new Intent(Address_Activity.this, Checkout_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void AddNewAddress()
    {
        try {
            LayoutInflater li = LayoutInflater.from(context);
            View addAddress = li.inflate(R.layout.prompt, null);
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            alertBuilder.setView(addAddress);
            alertBuilder.setCancelable(false);

            newaddline1 = addAddress.findViewById(R.id.newaddline1);
            newaddline2 = addAddress.findViewById(R.id.newaddline2);
            newaddcity = addAddress.findViewById(R.id.newaddcity);
            newadddistrict = addAddress.findViewById(R.id.newadddistrict);
            newaddpincode = addAddress.findViewById(R.id.newaddpincode);
            newaddname = addAddress.findViewById(R.id.newaddname);
            newaddnumber = addAddress.findViewById(R.id.newaddnumber);
            newaddalternatenumber = addAddress.findViewById(R.id.newaddalternatenumber);
            newaddcancelbtn = addAddress.findViewById(R.id.newaddcancelbtn);
            newaddsavebtn = addAddress.findViewById(R.id.newaddsavebtn);

            final AlertDialog alertDialog = alertBuilder.create();

            String [] states = {"Kottayam","Kasargod","Kannur","Wayanad","Kozikode","Malapuram","Palakad","Thrissur","Eranakulam","Idukki","Alapuzha","Pathanamthitta","Kollam","Thiruvananthapuram"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,states);
            newadddistrict.setAdapter(adapter);

            newaddsavebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String a1, a2, ac, ad, ap, ana, anu, aanum;
                    a1 = newaddline1.getText().toString();
                    a2 = newaddline2.getText().toString();
                    ac = newaddcity.getText().toString();
                    ad = newadddistrict.getSelectedItem().toString();
                    ap = newaddpincode.getText().toString();
                    ana = newaddname.getText().toString();
                    anu = newaddnumber.getText().toString();
                    aanum = newaddalternatenumber.getText().toString();

                    if (a1.isEmpty() || a2.isEmpty() || ac.isEmpty() || ad.isEmpty() || ap.isEmpty() || ana.isEmpty() || anu.isEmpty()) {
                        if (a1.isEmpty()) {
                            newaddline1.setError("House No or Building Name Required");
                        }

                        if (a2.isEmpty()) {
                            newaddline2.setError("Road Name, Area, Colony Required");
                        }

                        if (ac.isEmpty()) {
                            newaddcity.setError("City Required");
                        }

                        if (ad.isEmpty()) {

                        }

                        if (ap.isEmpty()) {
                            newaddpincode.setError("Pincode Required");
                        }

                        if (ana.isEmpty()) {
                            newaddname.setError("Name Required");
                        }

                        if (anu.isEmpty()) {
                            newaddnumber.setError("Number Required");
                        }

                    } else {
                        progressDialog.show();

                        String puskKey;

                        deliveryAddressModel = new DeliveryAddressModel();

                        deliveryAddressModel.setHouse_number(a1);
                        deliveryAddressModel.setArea(a2);
                        deliveryAddressModel.setCity(ac);
                        deliveryAddressModel.setDistrict(ad);
                        deliveryAddressModel.setPincode(ap);
                        deliveryAddressModel.setcName(ana);
                        deliveryAddressModel.setcNumber(anu);
                        deliveryAddressModel.setcAltNumber(aanum);

                        puskKey = mdatabaseReference.push().getKey();
                        mdatabaseReference.child(puskKey).setValue(deliveryAddressModel);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("house_number", a1);
                        editor.putString("area", a2);
                        editor.putString("city", ac);
                        editor.putString("district", ad);
                        editor.putString("pincode", ap);
                        editor.putString("cName", ana);
                        editor.putString("cNumber", anu);
                        editor.putString("cAltNumber", aanum);
                        editor.apply();

                        addAddressaddressLine1.setText(a1);
                        addAddressaddressLine2.setText(a2);
                        addAddresscity.setText(ac);
                        addAddressdistrict.setText(ad);
                        addAddresspincode.setText(ap);
                        addAddresscontactname.setText(ana);
                        addAddresscontactnumber.setText(anu);
                        addAddresscontactalternatenumber.setText(aanum);

                        Intent intent = new Intent(Address_Activity.this, Checkout_Activity.class);
                        startActivity(intent);
                        finish();

                        alertDialog.dismiss();

                        progressDialog.dismiss();
                    }

                }
            });

            newaddcancelbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                }
            });

            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String s1,s2,s3,s4,s5,s6,s7,s8;

        sharedPreferences = getSharedPreferences("data", 0);
        s1 = sharedPreferences.getString("house_number", "");
        s2 = sharedPreferences.getString("area", "");
        s3 = sharedPreferences.getString("city", "");
        s4 = sharedPreferences.getString("district", "");
        s5 = sharedPreferences.getString("pincode", "");
        s6 = sharedPreferences.getString("cName", "");
        s7 = sharedPreferences.getString("cNumber", "");
        s8 = sharedPreferences.getString("cAltNumber", "");

        addAddressaddressLine1.setText(s1);
        addAddressaddressLine2.setText(s2);
        addAddresscity.setText(s3);
        addAddressdistrict.setText(s4);
        addAddresspincode.setText(s5);
        addAddresscontactname.setText(s6);
        addAddresscontactnumber.setText(s7);
        addAddresscontactalternatenumber.setText(s8);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        Intent intent = new Intent(Address_Activity.this, Checkout_Activity.class);
//        startActivity(intent);
//        finish();
    }
}