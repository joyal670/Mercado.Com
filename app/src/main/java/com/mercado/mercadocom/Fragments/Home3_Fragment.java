package com.mercado.mercadocom.Fragments;

import android.Manifest;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mercado.mercadocom.Activity.HomeSearch_Activity;
import com.mercado.mercadocom.Activity.MainActivity;
import com.mercado.mercadocom.Activity.ProductList_Activity;
import com.mercado.mercadocom.Activity.ShopList_Activity;
import com.mercado.mercadocom.Activity.UserAccount_Activity;
import com.mercado.mercadocom.Adapters.imageSliderAdapter;
import com.mercado.mercadocom.Model.imageSliderModel;
import com.mercado.mercadocom.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Home3_Fragment extends Fragment {

    Button searchHome3;
    LinearLayoutCompat Supermarket, BakeryShop, Restaurant, Fishmarket, Butchershop, Vegetableshop, Medicalshop, Electronicshop, Fashion;
    SliderView sliderView;
    imageSliderAdapter imageSliderAdapter;
    List<imageSliderModel> imageSliderModelList;
    TextView home3shop1, home3shop2;
    Button home3shop1click, home3shop2click;
    TextView home3pname1, home3pname2, home3pname3, home3pname4, home3pname5, home3pname6, home3pname7, home3pname8, home3pname9, home3pname10, home3pname11, home3pname12, home3pname13, home3pname14, home3pname15, home3pname16, home3pname17, home3pname18;
    ImageView home3pimage1, home3pimage2, home3pimage3, home3pimage4, home3pimage5, home3pimage6, home3pimage7, home3pimage8, home3pimage9, home3pimage10, home3pimage11, home3pimage12, home3pimage13, home3pimage14, home3pimage15, home3pimage16, home3pimage17, home3pimage18;

    BottomNavigationView navigationView;


    public Home3_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home3, container, false);

        try {

            searchHome3 = view.findViewById(R.id.searchHome3);

            navigationView = view.findViewById(R.id.bottomNavigationHome3);

            Supermarket = view.findViewById(R.id.newProvisionHome3);
            BakeryShop = view.findViewById(R.id.newBakeryShopHome3);
            Restaurant = view.findViewById(R.id.newRestaurantHome3);
            Fishmarket = view.findViewById(R.id.newFishmarketHome3);
            Butchershop = view.findViewById(R.id.newButchershopHome3);
            Vegetableshop = view.findViewById(R.id.newVegetableshopHome3);
            Medicalshop = view.findViewById(R.id.newMedicalshopHome3);
            Electronicshop = view.findViewById(R.id.newElectronicshopHome3);
            Fashion = view.findViewById(R.id.newFashionHome3);

            home3shop1 = view.findViewById(R.id.home3shop1);
            home3shop2 = view.findViewById(R.id.home3shop2);

            home3shop1click = view.findViewById(R.id.home3shop1click);
            home3shop2click = view.findViewById(R.id.home3shop2click);

            home3pimage1 = view.findViewById(R.id.home3pimage1);
            home3pimage2 = view.findViewById(R.id.home3pimage2);
            home3pimage3 = view.findViewById(R.id.home3pimage3);
            home3pimage4 = view.findViewById(R.id.home3pimage4);
            home3pimage5 = view.findViewById(R.id.home3pimage5);
            home3pimage6 = view.findViewById(R.id.home3pimage6);
            home3pimage7 = view.findViewById(R.id.home3pimage7);
            home3pimage8 = view.findViewById(R.id.home3pimage8);
            home3pimage9 = view.findViewById(R.id.home3pimage9);
            home3pimage10 = view.findViewById(R.id.home3pimage10);
            home3pimage11 = view.findViewById(R.id.home3pimage11);
            home3pimage12 = view.findViewById(R.id.home3pimage12);
            home3pimage13 = view.findViewById(R.id.home3pimage13);
            home3pimage14 = view.findViewById(R.id.home3pimage14);
            home3pimage15 = view.findViewById(R.id.home3pimage15);
            home3pimage16 = view.findViewById(R.id.home3pimage16);
            home3pimage17 = view.findViewById(R.id.home3pimage17);
            home3pimage18 = view.findViewById(R.id.home3pimage18);


            home3pname1 = view.findViewById(R.id.home3pname1);
            home3pname2 = view.findViewById(R.id.home3pname2);
            home3pname3 = view.findViewById(R.id.home3pname3);
            home3pname4 = view.findViewById(R.id.home3pname4);
            home3pname5 = view.findViewById(R.id.home3pname5);
            home3pname6 = view.findViewById(R.id.home3pname6);
            home3pname7 = view.findViewById(R.id.home3pname7);
            home3pname8 = view.findViewById(R.id.home3pname8);
            home3pname9 = view.findViewById(R.id.home3pname9);
            home3pname10 = view.findViewById(R.id.home3pname10);
            home3pname11 = view.findViewById(R.id.home3pname11);
            home3pname12 = view.findViewById(R.id.home3pname12);
            home3pname13 = view.findViewById(R.id.home3pname13);
            home3pname14 = view.findViewById(R.id.home3pname14);
            home3pname15 = view.findViewById(R.id.home3pname15);
            home3pname16 = view.findViewById(R.id.home3pname16);
            home3pname17 = view.findViewById(R.id.home3pname17);
            home3pname18 = view.findViewById(R.id.home3pname18);


            imageSliderModelList = new ArrayList<>();
            sliderView = view.findViewById(R.id.home3imageSlider);
            imageSliderModelList.add(new imageSliderModel(R.drawable.img1));
            imageSliderModelList.add(new imageSliderModel(R.drawable.img2));
            imageSliderModelList.add(new imageSliderModel(R.drawable.img3));
            sliderView.setSliderAdapter(new imageSliderAdapter(getContext(), imageSliderModelList));
            sliderView.startAutoCycle();

            searchHome3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), HomeSearch_Activity.class);
                    startActivity(intent);
                }
            });

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

            home3shop1click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Home").child("home3shop1click");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final String value = (String) dataSnapshot.getValue();

                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Home").child("home3shop1type");
                            reference1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String v1 = (String) dataSnapshot.getValue();

                                    Intent intent = new Intent(getContext(), ProductList_Activity.class);
                                    intent.putExtra("sId", value);
                                    intent.putExtra("sType",v1);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            home3shop2click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Home").child("home3shop2click");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final String value = (String) dataSnapshot.getValue();

                            DatabaseReference reference1 =  FirebaseDatabase.getInstance().getReference("Home").child("home3shop2type");
                            reference1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String v1 = (String) dataSnapshot.getValue();
                                    Intent intent = new Intent(getContext(), ProductList_Activity.class);
                                    intent.putExtra("sId", value);
                                    intent.putExtra("sType",v1);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int id = menuItem.getItemId();
                    if (id == R.id.bottomnavHome) {
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        return true;
                    } else if (id == R.id.bottomnavSearch) {
                        Intent intent = new Intent(getContext(), HomeSearch_Activity.class);
                        startActivity(intent);
                        return true;
                    } else if (id == R.id.bottomnavCart) {
                        Mycart_Fragment mycart_fragment = new Mycart_Fragment();

                        getFragmentManager().beginTransaction().replace(R.id.frame_layout, mycart_fragment).commit();
                        return true;
                    } else if (id == R.id.bottomnavProfile) {
                        Intent intent = new Intent(getContext(), UserAccount_Activity.class);
                        startActivity(intent);
                        return true;
                    } else if (id == R.id.bottomnavCall) {
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
                        return true;
                    }

                    return true;
                }
            });

            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void loadData() {
        try {

            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname1");
            ref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname1.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname2");
            ref2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname2.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname3");
            ref3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname3.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname4");
            ref4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname4.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref5 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname5");
            ref5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname5.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref6 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname6");
            ref6.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname6.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref7 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname7");
            ref7.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname7.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref8 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname8");
            ref8.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname8.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref9 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname9");
            ref9.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname9.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref10 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname10");
            ref10.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname10.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref11 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname11");
            ref11.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname11.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref12 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname12");
            ref12.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname12.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref13 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname13");
            ref13.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname13.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref14 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname14");
            ref14.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname14.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref15 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname15");
            ref15.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname15.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref16 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname16");
            ref16.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname16.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref17 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname17");
            ref17.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname17.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref18 = FirebaseDatabase.getInstance().getReference("Home").child("home3pname18");
            ref18.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3pname18.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref19 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage1");
            ref19.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref20 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage2");
            ref20.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref21 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage3");
            ref21.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage3);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref22 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage4");
            ref22.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage4);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref23 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage5");
            ref23.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage5);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref24 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage6");
            ref24.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage6);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref25 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage7");
            ref25.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage7);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref26 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage8");
            ref26.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage8);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref27 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage9");
            ref27.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage9);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref28 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage10");
            ref28.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage10);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref29 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage11");
            ref29.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage11);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref30 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage12");
            ref30.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage12);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref31 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage13");
            ref31.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage13);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref32 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage14");
            ref32.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage14);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref33 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage15");
            ref33.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage15);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref34 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage16");
            ref34.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage16);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref35 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage17");
            ref35.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage17);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref36 = FirebaseDatabase.getInstance().getReference("Home").child("home3pimage18");
            ref36.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    Picasso.get().load(value).into(home3pimage18);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref37 = FirebaseDatabase.getInstance().getReference("Home").child("home3shop1");
            ref37.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3shop1.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            DatabaseReference ref38 = FirebaseDatabase.getInstance().getReference("Home").child("home3shop2");
            ref38.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    home3shop2.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

//        DatabaseReference ref39 = FirebaseDatabase.getInstance().getReference("Home").child("images");
//        ref39.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                imageSliderModelList.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
//                {
//                    imageSliderModel model = dataSnapshot1.getValue(imageSliderModel.class);
//                    imageSliderModelList.add(model);
//                }
//                imageSliderAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        imageSliderModelList = new ArrayList<>();
//        imageSliderAdapter = new imageSliderAdapter(getContext(), imageSliderModelList);
//        sliderView.setSliderAdapter(imageSliderAdapter);
//        sliderView.startAutoCycle();

//        DatabaseReference ref39 = FirebaseDatabase.getInstance().getReference("Home").child("images");
//        imageSliderModel model = new imageSliderModel();
//        model.setImage(R.drawable.batch);
//        ref39.setValue(model);

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