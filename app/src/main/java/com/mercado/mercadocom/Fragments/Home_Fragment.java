package com.mercado.mercadocom.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;

import com.mercado.mercadocom.Activity.HomeSearch_Activity;
import com.mercado.mercadocom.Activity.ShopList_Activity;
import com.mercado.mercadocom.Adapters.imageSliderAdapter;
import com.mercado.mercadocom.Model.imageSliderModel;
import com.mercado.mercadocom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    public ProgressDialog progressDialog;
    AdapterViewFlipper adapterViewFlipper;
    int[] IMAGES = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4, R.mipmap.pic5};
    CardView SuperMarket, BakeryShop, Restaurant, Fishmarket, Butchershop, Vegetableshop, Medicalshop, Electronicshop, Fashion;
    FloatingActionButton floatingActionButtonCallBtn;

    Button searchEdittext;

    SliderView sliderView;
    List<imageSliderModel> imageSliderModelList;

    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        imageSliderModelList = new ArrayList<>();
        sliderView = view.findViewById(R.id.imageSlider);
        imageSliderModelList.add(new imageSliderModel(R.drawable.img1));
        imageSliderModelList.add(new imageSliderModel(R.drawable.img2));
        imageSliderModelList.add(new imageSliderModel(R.drawable.img3));
//        imageSliderModelList.add(new imageSliderModel(R.drawable.banner2));
        sliderView.setSliderAdapter(new imageSliderAdapter(getContext(),imageSliderModelList));
        sliderView.startAutoCycle();

//        adapterViewFlipper = view.findViewById(R.id.adapterViewFlipper);

//        homeSearchView = view.findViewById(R.id.homeSearchView);
//        homeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query)
//            {
//                Intent intent = new Intent(getContext(), HomeSearch_Activity.class);
//                intent.putExtra("key", query);
//                startActivity(intent);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

        searchEdittext = view.findViewById(R.id.searchhome);
        searchEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeSearch_Activity.class);
                startActivity(intent);
            }
        });


        SuperMarket = view.findViewById(R.id.Provision);
        BakeryShop = view.findViewById(R.id.BakeryShop);
        Restaurant = view.findViewById(R.id.Restaurant);
        Fishmarket = view.findViewById(R.id.Fishmarket);
        Butchershop = view.findViewById(R.id.Butchershop);
        Vegetableshop = view.findViewById(R.id.Vegetableshop);
        Medicalshop = view.findViewById(R.id.Medicalshop);
        Electronicshop = view.findViewById(R.id.Electronicshop);
        Fashion = view.findViewById(R.id.Fashion);
        floatingActionButtonCallBtn = view.findViewById(R.id.floatingActionButtonCallBtn);

//        ref = FirebaseDatabase.getInstance().getReference("shop_owner");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                mProductmodel.clear();
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
//                {
//                    for (DataSnapshot msg : dataSnapshot1.getChildren())
//                    {
//                        for (DataSnapshot d : msg.getChildren())
//                        {
//                            ProductModel upload = d.getValue(ProductModel.class);
//                            mProductmodel.add(upload);
//                        }
//                    }
//                }
//                productAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        AdapterFlipper adapterFlipper = new AdapterFlipper(getContext(), IMAGES);
//        adapterViewFlipper.setAdapter(adapterFlipper);
//        adapterViewFlipper.setFlipInterval(2600);
//        adapterViewFlipper.setAutoStart(true);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");


        progressDialog.show();
        SuperMarket.setOnClickListener(new View.OnClickListener() {
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

        return view;
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
//                else if (which == 1)
//                {
//                    String number = "919061243470";
//                    try {
//                        Intent intent = new Intent("android.intent.action.MAIN");
//                        intent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
//                        intent.putExtra("jid", PhoneNumberUtils.stripSeparators(number) + "@s.whatsapp.net");
//                        startActivity(intent);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
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
