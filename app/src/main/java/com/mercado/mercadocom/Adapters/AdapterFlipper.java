package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mercado.mercadocom.R;

public class AdapterFlipper extends BaseAdapter {

    Context context;
    int[] images;
    LayoutInflater inflater;

    public AdapterFlipper(Context context, int[] images) {
        this.context = context;
        this.images = images;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapterview_listitems,null);
        ImageView image = convertView.findViewById(R.id.adapterImageview);
        image.setImageResource(images[position]);
        return convertView;
    }
}
