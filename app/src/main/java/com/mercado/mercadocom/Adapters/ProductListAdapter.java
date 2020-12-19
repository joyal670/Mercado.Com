package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercado.mercadocom.Activity.HomeSearch_Activity;
import com.mercado.mercadocom.Model.ProductModel;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {
    private Context context;
    List<ProductModel> mProductmodel;

    public ProductListAdapter(Context context, List<ProductModel> mProductmodel) {
        this.context = context;
        this.mProductmodel = mProductmodel;
    }

    @Override
    public int getCount() {
        return mProductmodel.size();
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.productlist_items, null);

        ImageView listpimage;
        TextView listpname, listpdescription, listpprice, listpstoke;
        Button listpcart;

        listpimage = convertView.findViewById(R.id.listpimage);
        listpname = convertView.findViewById(R.id.listpname);
        listpdescription = convertView.findViewById(R.id.listpdescription);
        listpprice = convertView.findViewById(R.id.listpprice);
        listpstoke = convertView.findViewById(R.id.listpstoke);
        listpcart = convertView.findViewById(R.id.listpcart);

        Picasso.get().load(mProductmodel.get(position).getpImageUrl()).into(listpimage);
        listpname.setText(mProductmodel.get(position).getpName());
        listpdescription.setText(mProductmodel.get(position).getmDescription());
        listpprice.setText(mProductmodel.get(position).getpPrice());
        listpstoke.setText(mProductmodel.get(position).getpStoke());

        final String s, getpName, getmDescription, getpPrice, getpType, getpStoke, getpUserKey, getmKey;
        final int aposition;

        s = mProductmodel.get(position).getpImageUrl();
        getpName = mProductmodel.get(position).getpName();
        getmDescription = mProductmodel.get(position).getmDescription();
        getpPrice = mProductmodel.get(position).getpPrice();
        getpType = mProductmodel.get(position).getpType();
        getpStoke = mProductmodel.get(position).getpStoke();
        getpUserKey = mProductmodel.get(position).getpUserKey();
        getmKey = mProductmodel.get(position).getmKey();
        aposition = position;

        listpcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                HomeSearch_Activity.listInterface.add(s, getpName, getmDescription, getpPrice, getpType, getpStoke, getpUserKey, getmKey, aposition);
            }
        });

        return convertView;
    }
}
