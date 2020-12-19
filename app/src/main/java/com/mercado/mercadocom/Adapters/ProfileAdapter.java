package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercado.mercadocom.Model.ProfileModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ImageHolder>
{

    Context context;
    List<ProfileModel> model;
    OnitemClickListener mlistener;

    public ProfileAdapter(Context context, List<ProfileModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.profile, parent, false);
        return new ImageHolder(view);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView useraccount_image;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            useraccount_image = itemView.findViewById(R.id.useraccount_image);

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
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        ProfileModel profileModel = model.get(position);

        Picasso.get()
                .load(profileModel.getProfileImage())
                .placeholder(R.mipmap.loader_png)
                .into(holder.useraccount_image);
    }

    public interface OnitemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnitemClickListener listener)
    {
        mlistener = listener;
    }
}
