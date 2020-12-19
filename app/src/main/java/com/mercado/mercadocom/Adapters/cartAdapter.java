package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.Fragments.Mycart_Fragment;
import com.mercado.mercadocom.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class cartAdapter extends BaseAdapter {
    private Context context;
//    List<PriceModel> count;
    List<MobUser> count;


    public cartAdapter(Context context, List<MobUser> count)
    {
        this.context = context;
        this.count=count;
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.cart_listview_items, null);


        ImageButton add, remove;
        ImageView image;
        TextView name, price, qty, type, desc;
        Button itembtn;

        add = convertView.findViewById(R.id.addqty);
        remove = convertView.findViewById(R.id.removeqty);
        qty = convertView.findViewById(R.id.cartqty);
        image = convertView.findViewById(R.id.cartitemImage);
        name = convertView.findViewById(R.id.cartname);
        type = convertView.findViewById(R.id.carttype);
        price = convertView.findViewById(R.id.mycartprice);
        itembtn = convertView.findViewById(R.id.cartitemremovebtn);
        desc = convertView.findViewById(R.id.cartdesc);

        //qty.setText(count.get(position).getQty()+"");

        List<MobUser> UserList = SQLite.select().from(MobUser.class).queryList();

        String DBimage,DBname,DBprice,DBqty,DBtype,DBdesc;

            DBimage = count.get(position).getpImage();
            DBname = count.get(position).getpName();
            DBtype = count.get(position).getpType();
            DBprice = count.get(position).getpPrice();
            DBqty = count.get(position).getPqty();
            DBdesc = count.get(position).getpDescription();


            Picasso.get().load(DBimage).into(image);
            name.setText(DBname);
            type.setText(DBtype);
            qty.setText(DBqty);
            price.setText(DBprice);
            desc.setText(DBdesc);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mycart_Fragment.cartInterface.add(position);
//                notifyDataSetChanged();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mycart_Fragment.cartInterface.remove(position);
            }
        });

            ImageView ll_outer = convertView.findViewById(R.id.cartitemImage);
                ll_outer.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Mycart_Fragment.cartInterface.cart(position);
                }
            });

                itembtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mycart_Fragment.cartInterface.removeitem(position);
                    }
                });

        return convertView;
    }
}
