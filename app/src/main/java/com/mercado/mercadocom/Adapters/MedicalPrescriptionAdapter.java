package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercado.mercadocom.Model.MedicalPrescriptionModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MedicalPrescriptionAdapter extends RecyclerView.Adapter<MedicalPrescriptionAdapter.ImageHolder>
{
    Context context;
    List<MedicalPrescriptionModel> medicalPrescriptionModels;
    MedicalPrescriptionAdapter.OnitemClickListener mlistener;

    public MedicalPrescriptionAdapter(Context context, List<MedicalPrescriptionModel> medicalPrescriptionModels) {
        this.context = context;
        this.medicalPrescriptionModels = medicalPrescriptionModels;
    }

    @NonNull
    @Override
    public MedicalPrescriptionAdapter.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.prescription, parent, false);
        return new MedicalPrescriptionAdapter.ImageHolder(view);

    }

    @Override
    public int getItemCount() {
        return medicalPrescriptionModels.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView medicalPrsImage;
        TextView medicalPrsQty, medicalPrsStatus;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            medicalPrsImage = itemView.findViewById(R.id.medicalPrsImage);
            medicalPrsQty = itemView.findViewById(R.id.medicalPrsQty);
            medicalPrsStatus = itemView.findViewById(R.id.medicalPrsStatus);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mlistener != null)
            {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION)
                {
                    mlistener.onItemClick(position);
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalPrescriptionAdapter.ImageHolder holder, int position)
    {

        MedicalPrescriptionModel medicalPrescriptionModel = medicalPrescriptionModels.get(position);

        Picasso.get()
                .load(medicalPrescriptionModel.getImage())
                .placeholder(R.mipmap.loader_png)
                .into(holder.medicalPrsImage);

        holder.medicalPrsQty.setText(medicalPrescriptionModel.getQty());

        String temp = medicalPrescriptionModel.getStatus();
        if(temp.equals("0"))
        {
            holder.medicalPrsStatus.setText("Pending, Not yet Delivered");
        }
        else if(temp.equals("1"))
        {
            holder.medicalPrsStatus.setText("Order is Picked up by the Delivery Agent");
        }
        else if(temp.equals("2"))
        {
            holder.medicalPrsStatus.setText("Delivered");
        }
    }

    public interface OnitemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(MedicalPrescriptionAdapter.OnitemClickListener listener)
    {
        mlistener = listener;
    }
}
