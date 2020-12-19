package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.mercado.mercadocom.Activity.ProductList_Activity;
import com.mercado.mercadocom.Model.ProductModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ImageHolder> {

    Context context;
    List<ProductModel> mProduct;

    public ProductAdapter(Context context, List<ProductModel> mProduct) {
        this.context = context;
        this.mProduct = mProduct;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_items, parent, false);
        return new ImageHolder(view);

    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder
    {
        TextView productitemname, productitemdescription, productitemprice, productitemstoke;
        ImageView productitemimage;
        MaterialCardView productCardView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            productitemimage = itemView.findViewById(R.id.productitemimage);
            productitemname = itemView.findViewById(R.id.productitemname);
            productitemdescription = itemView.findViewById(R.id.productitemdescription);
            productitemprice = itemView.findViewById(R.id.productitemprice);
            productitemstoke = itemView.findViewById(R.id.productitemstoke);
            productCardView = itemView.findViewById(R.id.productCardView);


        }

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, final int position) {

        final ProductModel currentUpload = mProduct.get(position);

        holder.productitemname.setText(currentUpload.getpName());
        holder.productitemdescription.setText(currentUpload.getmDescription());
        holder.productitemprice.setText(currentUpload.getpPrice());
        holder.productitemstoke.setText(currentUpload.getpStoke());
        Picasso.get()
                .load(currentUpload.getpImageUrl())
                .placeholder(R.mipmap.loader_png)
                .into(holder.productitemimage);

        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductList_Activity.productInterface.currentProduct(currentUpload.getpImageUrl(), currentUpload.getpName(), currentUpload.getmDescription(), currentUpload.getpPrice(), currentUpload.getpType(), currentUpload.getpStoke(), currentUpload.getpUserKey(), currentUpload.getmKey(),position);
            }
        });

//        String temp = currentUpload.getpStoke();
//        if(temp.equals("In Stock"))
//        {
//            holder.productitemstoke.setVisibility(View.INVISIBLE);
//        }
//        else
//        {
//            holder.productitemstoke.setVisibility(View.VISIBLE);
//        }

    }


}
