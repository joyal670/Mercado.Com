package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercado.mercadocom.Model.CheckoutModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.ImageHolder>
{
    Context context;
    List<CheckoutModel> mCheckout;

    public orderAdapter(Context context, List<CheckoutModel> mCheckout) {
        this.context = context;
        this.mCheckout = mCheckout;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.orderd_items, parent, false);
        return new ImageHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position)
    {
        CheckoutModel currentUpload = mCheckout.get(position);

        holder.ordered_itemname.setText(currentUpload.getProductName());
        holder.ordered_itemprice.setText(currentUpload.getProductPrice());
        holder.ordered_itemqty.setText(currentUpload.getProductQty());
        holder.ordered_date.setText(currentUpload.getOrderDate());

        if(currentUpload.getStatus().equalsIgnoreCase("0"))
        {
            holder.ordered_itemstatus.setText("Pending, Not yet Delivered");
            holder.ordered_deliverddate.setText("Pending");
        }
        else if(currentUpload.getStatus().equalsIgnoreCase("1"))
        {
            holder.ordered_itemstatus.setText("Order is Picked up by the Delivery Agent");
            holder.ordered_deliverddate.setText("Out of Delivery");

        }
        else if(currentUpload.getStatus().equalsIgnoreCase("2"))
        {

            holder.ordered_itemstatus.setText("Delivered");
            holder.ordered_deliverddate.setText(currentUpload.getOrderDeliveryDate());
        }
        else
        {
            holder.ordered_itemstatus.setText("Cancelled");
        }

        Picasso.get()
                .load(currentUpload.getProductImageUrl())
                .placeholder(R.mipmap.loader_png)
                .into(holder.ordered_itemimage);

        int price = Integer.parseInt(currentUpload.getProductPrice());
        int qty = Integer.parseInt(currentUpload.getProductQty());
        int total = qty * price;
        String tot = String.valueOf(total);

        holder.ordered_itemtotal.setText(tot);
    }


    @Override
    public int getItemCount() {
        return mCheckout.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder
    {
        TextView ordered_itemname, ordered_itemqty, ordered_itemprice, ordered_itemstatus, ordered_date, ordered_deliverddate, ordered_itemtotal;
        ImageView ordered_itemimage;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            ordered_itemimage = itemView.findViewById(R.id.ordered_itemimage);
            ordered_itemqty = itemView.findViewById(R.id.ordered_itemqty);
            ordered_itemprice = itemView.findViewById(R.id.ordered_itemprice);
            ordered_itemstatus = itemView.findViewById(R.id.ordered_itemstatus);
            ordered_itemname = itemView.findViewById(R.id.ordered_itemname);
            ordered_date = itemView.findViewById(R.id.ordered_date);
            ordered_deliverddate = itemView.findViewById(R.id.ordered_deliverddate);
            ordered_itemtotal = itemView.findViewById(R.id.ordered_itemtotal);

        }

    }


}
