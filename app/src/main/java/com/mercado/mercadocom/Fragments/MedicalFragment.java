package com.mercado.mercadocom.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mercado.mercadocom.Activity.UploadPrescription_Activity;
import com.mercado.mercadocom.Adapters.MedicalPrescriptionAdapter;
import com.mercado.mercadocom.Model.MedicalPrescriptionModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MedicalFragment extends Fragment implements MedicalPrescriptionAdapter.OnitemClickListener {

    RecyclerView medicalFrgRecyclerView;
    SharedPreferences sharedPreferences;
    public ProgressDialog progressDialog;
    String a1;
    private DatabaseReference mdatabaseReference;
    List<MedicalPrescriptionModel> medicalPrescriptionModels;
    MedicalPrescriptionAdapter medicalPrescriptionAdapter;
    ImageView noordersimage;

    public MedicalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical, container, false);

        try{

        medicalFrgRecyclerView = view.findViewById(R.id.medicalFrgRecyclerView);
        noordersimage = view.findViewById(R.id.noordersimage);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Please Wait...");

        sharedPreferences = this.getActivity().getSharedPreferences("data", 0);
        a1 = sharedPreferences.getString("userid", "");

        progressDialog.show();
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("medical_details");
        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                medicalPrescriptionModels.clear();
                progressDialog.dismiss();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.getValue() == null)
                    {
                        noordersimage.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        if(a1.equals(dataSnapshot1.child("userId").getValue().toString()))
                        {
                            noordersimage.setVisibility(View.INVISIBLE);
                            MedicalPrescriptionModel model = dataSnapshot1.getValue(MedicalPrescriptionModel.class);
                            medicalPrescriptionModels.add(model);
                        }

                    }

                }
                medicalPrescriptionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        medicalPrescriptionModels = new ArrayList<>();
        medicalPrescriptionAdapter = new MedicalPrescriptionAdapter(getActivity(), medicalPrescriptionModels);
        medicalFrgRecyclerView.setAdapter(medicalPrescriptionAdapter);
        medicalPrescriptionAdapter.setOnClickListener(MedicalFragment.this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onItemClick(final int position)
    {
        try {
            LayoutInflater li = LayoutInflater.from(getContext());
            View viewPrescription = li.inflate(R.layout.fullscreenprescription, null);
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
            alertBuilder.setView(viewPrescription);
            alertBuilder.setCancelable(true);


            final Button viewPresriptionReorderCancel, viewPresriptionReorderOrder, viewPresriptionReorder;
            final ImageView viewPresriptionImage;
            final EditText viewPresriptionMobile, viewPresriptionQty;


            viewPresriptionImage = viewPrescription.findViewById(R.id.viewPresriptionImage);
            viewPresriptionReorder = viewPrescription.findViewById(R.id.viewPresriptionReorder);
            viewPresriptionReorderCancel = viewPrescription.findViewById(R.id.viewPresriptionReorderCancel);
            viewPresriptionReorderOrder = viewPrescription.findViewById(R.id.viewPresriptionReorderOrder);
            viewPresriptionMobile = viewPrescription.findViewById(R.id.viewPresriptionMobile);
            viewPresriptionQty = viewPrescription.findViewById(R.id.viewPresriptionQty);

            Picasso.get().load(medicalPrescriptionModels.get(position).getImage()).into(viewPresriptionImage);

            final AlertDialog alertDialog = alertBuilder.create();

            viewPresriptionReorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    viewPresriptionReorderCancel.setVisibility(View.VISIBLE);
                    viewPresriptionReorderOrder.setVisibility(View.VISIBLE);
                    viewPresriptionMobile.setVisibility(View.VISIBLE);
                    viewPresriptionQty.setVisibility(View.VISIBLE);

                    viewPresriptionReorderCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });

                    viewPresriptionReorderOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String temp = medicalPrescriptionModels.get(position).getStatus();
                            if (temp.equals("2"))
                            {
                                String tempqty = viewPresriptionQty.getText().toString();
                                String tempMobile = viewPresriptionMobile.getText().toString();
                                if(tempqty.isEmpty() || tempMobile.isEmpty())
                                {
                                    if(tempMobile.isEmpty())
                                    {
                                        viewPresriptionMobile.setError("Mobile Number Required");
                                    }
                                    if(tempqty.isEmpty())
                                    {
                                        viewPresriptionQty.setError("Quantity Required");
                                    }
                                }
                                else{
                                    String id = medicalPrescriptionModels.get(position).getUploadId();
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("medical_details").child(id);
                                    reference.child("status").setValue("0");
                                    reference.child("temp2").setValue("");
                                    reference.child("qty").setValue(tempqty);
                                    reference.child("phone").setValue(tempMobile);
                                    Toast.makeText(getContext(), "Selected item is Re-Orderd", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }


                            } else {
                                Toast.makeText(getContext(), "Sorry you can re-order, only after the product is delivered", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            });

            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }