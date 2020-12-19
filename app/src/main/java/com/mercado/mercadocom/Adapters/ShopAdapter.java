package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercado.mercadocom.R;
import com.mercado.mercadocom.Model.ShopModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ImageHolder> {

    Context context;
    List<ShopModel> mShopModel;
    OnitemClickListener mlistener;

    public ShopAdapter(Context context, List<ShopModel> mShopModel) {
        this.context = context;
        this.mShopModel = mShopModel;
    }

    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name;
        TextView place;
        TextView phone;
        ImageView imageView;
        TextView type;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            place = itemView.findViewById(R.id.place);
            phone = itemView.findViewById(R.id.phone);
            type = itemView.findViewById(R.id.type);

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

    public interface OnitemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(OnitemClickListener listener)
    {
        mlistener = listener;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.shop_view, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        ShopModel currentShopModel = mShopModel.get(position);
        holder.name.setText(currentShopModel.getmName());
        holder.phone.setText(currentShopModel.getmPhone());
        holder.place.setText(currentShopModel.getmPlace());
        holder.type.setText(currentShopModel.getmType());
        Picasso.get()
                .load(currentShopModel.getmImageUrl())
                .placeholder(R.mipmap.loader_png)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mShopModel.size();
    }



}
