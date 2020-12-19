package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CheckoutAdapter extends BaseAdapter {
    private Context context;
    List<MobUser> count;


    public CheckoutAdapter(Context context, List<MobUser> count) {
        this.context = context;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.checkout_items, null);

        TextView name, type, price, qty;
        ImageView image;
        final CheckBox checkoutcheckbox;

        name = convertView.findViewById(R.id.checkoutitemname);
        type = convertView.findViewById(R.id.checkoutitemtype);
        price = convertView.findViewById(R.id.checkoutitemprice);
        qty = convertView.findViewById(R.id.checkoutitemqty);
        image = convertView.findViewById(R.id.checkoutitemImage);
        checkoutcheckbox = convertView.findViewById(R.id.checkoutcheckbox);

        final String DBimage, DBname, DBdescription, DBprice, DBtype, DBstoke, DBuserkey, DBmkey, DBqty;

        DBimage = count.get(position).getpImage();
        DBname = count.get(position).getpName();
        DBdescription = count.get(position).getpDescription();
        DBprice = count.get(position).getpPrice();
        DBtype = count.get(position).getpType();
        DBstoke = count.get(position).getpStoke();
        DBuserkey = count.get(position).getpUserKey();
        DBmkey = count.get(position).getpMkey();
        DBqty = count.get(position).getPqty();

        Picasso.get().load(DBimage).into(image);
        name.setText(DBname);
        type.setText(DBtype);
        price.setText(DBprice);
        qty.setText(DBqty);


        return convertView;
    }
}
