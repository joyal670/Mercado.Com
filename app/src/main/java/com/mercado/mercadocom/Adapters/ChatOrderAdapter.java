package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercado.mercadocom.Model.CheckoutModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatOrderAdapter extends BaseAdapter
{
    Context context;
    List<CheckoutModel> mCheckoutmodel;

    public ChatOrderAdapter(Context context, List<CheckoutModel> mCheckoutmodel) {
        this.context = context;
        this.mCheckoutmodel = mCheckoutmodel;
    }

    @Override
    public int getCount() {
        return mCheckoutmodel.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.chatorders, null);

        ImageView charOrderImage;
        TextView chatOrderId, chatOrderStatus;

        charOrderImage = convertView.findViewById(R.id.charOrderImage);
        chatOrderId = convertView.findViewById(R.id.chatOrderId);
        chatOrderStatus = convertView.findViewById(R.id.chatOrderStatus);

        Picasso.get().load(mCheckoutmodel.get(position).getProductImageUrl()).into(charOrderImage);
        chatOrderId.setText(mCheckoutmodel.get(position).getOrderId());

        String status = mCheckoutmodel.get(position).getStatus();
        if(status.equals("0"))
        {
            chatOrderStatus.setText("Pending, Not yet Delivered");
        }
        else  if(status.equals("1"))
        {
            chatOrderStatus.setText("Order is Picked up by the Delivery Agent");
        }
        else if(status.equals("2"))
        {
            chatOrderStatus.setText("Delivered");
        }
        else if(status.equals("3"))
        {
            chatOrderStatus.setText("Cancelled");
        }


        return convertView;
    }
}
