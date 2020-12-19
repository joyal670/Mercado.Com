package com.mercado.mercadocom.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mercado.mercadocom.Activity.HomeSearch_Activity;
import com.mercado.mercadocom.Activity.ProductList_Activity;
import com.mercado.mercadocom.Activity.ShopList_Activity;
import com.mercado.mercadocom.Adapters.imageSliderAdapter;
import com.mercado.mercadocom.Model.imageSliderModel;
import com.mercado.mercadocom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Home2_Fragment extends Fragment {

    LinearLayoutCompat Supermarket, BakeryShop, Restaurant, Fishmarket, Butchershop, Vegetableshop, Medicalshop, Electronicshop, Fashion;
    FloatingActionButton floatingActionButtonCallBtn;
    SliderView sliderView;
    List<imageSliderModel> imageSliderModelList;
    Button searchEdittext;
    public ProgressDialog progressDialog;


    TextView home2pname1, home2pname2, home2pname3, home2pname4, home2pname5, home2pname6, home2pname7, home2pname8, home2pname9;
    ImageView home2pimage1, home2pimage2, home2pimage3, home2pimage4, home2pimage5, home2pimage6, home2pimage7, home2pimage8, home2pimage9;
    TextView home2shop1, home2shop2;

    LinearLayout home2shop1click, home2shop2click;

    public Home2_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        try {

            imageSliderModelList = new ArrayList<>();
            sliderView = view.findViewById(R.id.home2imageSlider);
            imageSliderModelList.add(new imageSliderModel(R.drawable.img1));
            imageSliderModelList.add(new imageSliderModel(R.drawable.img2));
            imageSliderModelList.add(new imageSliderModel(R.drawable.img3));
            sliderView.setSliderAdapter(new imageSliderAdapter(getContext(), imageSliderModelList));
            sliderView.startAutoCycle();


            home2pname1 = view.findViewById(R.id.home2pname1);
            home2pname2 = view.findViewById(R.id.home2pname2);
            home2pname3 = view.findViewById(R.id.home2pname3);
            home2pname4 = view.findViewById(R.id.home2pname4);
            home2pname5 = view.findViewById(R.id.home2pname5);
            home2pname6 = view.findViewById(R.id.home2pname6);
            home2pname7 = view.findViewById(R.id.home2pname7);
            home2pname8 = view.findViewById(R.id.home2pname8);
            home2pname9 = view.findViewById(R.id.home2pname9);

            home2pimage1 = view.findViewById(R.id.home2pimage1);
            home2pimage2 = view.findViewById(R.id.home2pimage2);
            home2pimage3 = view.findViewById(R.id.home2pimage3);
            home2pimage4 = view.findViewById(R.id.home2pimage4);
            home2pimage5 = view.findViewById(R.id.home2pimage5);
            home2pimage6 = view.findViewById(R.id.home2pimage6);
            home2pimage7 = view.findViewById(R.id.home2pimage7);
            home2pimage8 = view.findViewById(R.id.home2pimage8);
            home2pimage9 = view.findViewById(R.id.home2pimage9);

            home2shop1 = view.findViewById(R.id.home2shop1);
            home2shop2 = view.findViewById(R.id.home2shop2);

            home2shop1click = view.findViewById(R.id.home2shop1click);
            home2shop2click = view.findViewById(R.id.home2shop2click);

            home2shop1click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref31 = FirebaseDatabase.getInstance().getReference("Home").child("home2shop1click");
                    ref31.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = (String) dataSnapshot.getValue();
                            Intent intent = new Intent(getContext(), ProductList_Activity.class);
                            intent.putExtra("sId", value);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            home2shop2click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference ref32 = FirebaseDatabase.getInstance().getReference("Home").child("home2shop2click");
                    ref32.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = (String) dataSnapshot.getValue();
                            Intent intent = new Intent(getContext(), ProductList_Activity.class);
                            intent.putExtra("sId", value);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            searchEdittext = view.findViewById(R.id.searchHome2);
            searchEdittext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), HomeSearch_Activity.class);
                    startActivity(intent);
                }
            });

            Supermarket = view.findViewById(R.id.newProvisionHome2);
            BakeryShop = view.findViewById(R.id.newBakeryShopHome2);
            Restaurant = view.findViewById(R.id.newRestaurantHome2);
            Fishmarket = view.findViewById(R.id.newFishmarketHome2);
            Butchershop = view.findViewById(R.id.newButchershopHome2);
            Vegetableshop = view.findViewById(R.id.newVegetableshopHome2);
            Medicalshop = view.findViewById(R.id.newMedicalshopHome2);
            Electronicshop = view.findViewById(R.id.newElectronicshopHome2);
            Fashion = view.findViewById(R.id.newFashionHome2);
            floatingActionButtonCallBtn = view.findViewById(R.id.floatingActionButtonCallBtnHome2);

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Fetching Data...");

            progressDialog.show();

            Supermarket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Supermarket");
                }
            });

            BakeryShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Bakery");
                }
            });

            Restaurant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Restaurant");
                }
            });

            Fishmarket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Fishmarket");
                }
            });

            Butchershop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Butchershop");
                }
            });

            Vegetableshop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Vegetableshop");
                }
            });

            Medicalshop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Medicalshop");
                }
            });

            Electronicshop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Electronicshop");
                }
            });

            Fashion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCategory("Fashion");
                }
            });

            progressDialog.dismiss();

            floatingActionButtonCallBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assistant();
                }
            });

            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void loadData()
    {
        try {
            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname1");
            ref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname1.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname2");
            ref2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname2.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname3");
            ref3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname3.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname4");
            ref4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname4.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref5 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname5");
            ref5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname5.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref6 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname6");
            ref6.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname6.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref7 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname7");
            ref7.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname7.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref8 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname8");
            ref8.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname8.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref9 = FirebaseDatabase.getInstance().getReference("Home").child("home2pname9");
            ref9.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2pname9.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref11 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage1");
            ref11.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref12 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage2");
            ref12.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref13 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage3");
            ref13.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage3);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref14 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage4");
            ref14.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage4);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref15 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage5");
            ref15.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage5);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref16 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage6");
            ref16.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage6);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref17 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage7");
            ref17.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage7);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref18 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage8");
            ref18.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage8);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref19 = FirebaseDatabase.getInstance().getReference("Home").child("home2pimage9");
            ref19.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home2pimage9);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref21 = FirebaseDatabase.getInstance().getReference("Home").child("home2shop1");
            ref21.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2shop1.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref22 = FirebaseDatabase.getInstance().getReference("Home").child("home2shop2");
            ref22.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home2shop2.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assistant()
    {
        try {
            String[] items = {"Make a Call"};
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle("Help Assistant");
            dialog.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        if ((ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + "7994980358"));
                            startActivity(intent);
                        }
                    }
                }
            });
            dialog.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectCategory(String category) {
        Intent intent = new Intent(getContext(), ShopList_Activity.class);
        intent.putExtra("shopcatagory", category);
        startActivity(intent);
    }
}