package com.mercado.mercadocom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mercado.mercadocom.Model.DeliveryAddressModel;
import com.mercado.mercadocom.R;

import java.util.List;

public class DeliveryAddressAdapter extends BaseAdapter
{
    Context context;
    List<DeliveryAddressModel> deliveryAddressModels;

    public DeliveryAddressAdapter(Context context, List<DeliveryAddressModel> deliveryAddressModels) {
        this.context = context;
        this.deliveryAddressModels = deliveryAddressModels;
    }

    @Override
    public int getCount() {
        return deliveryAddressModels.size();
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
        convertView = inflater.inflate(R.layout.deliveryaddressess, null);

        TextView StoredAddressaddressLine1, StoredAddressaddressLine2, StoredAddresscity, StoredAddressdistrict, StoredAddresspincode, StoredAddresscontactname, StoredAddresscontactnumber, StoredAddresscontactalternatenumber;


        StoredAddressaddressLine1 = convertView.findViewById(R.id.StoredAddressaddressLine1);
        StoredAddressaddressLine2 = convertView.findViewById(R.id.StoredAddressaddressLine2);
        StoredAddresscity = convertView.findViewById(R.id.StoredAddresscity);
        StoredAddressdistrict = convertView.findViewById(R.id.StoredAddressdistrict);
        StoredAddresspincode = convertView.findViewById(R.id.StoredAddresspincode);
        StoredAddresscontactname = convertView.findViewById(R.id.StoredAddresscontactname);
        StoredAddresscontactnumber = convertView.findViewById(R.id.StoredAddresscontactnumber);
        StoredAddresscontactalternatenumber = convertView.findViewById(R.id.StoredAddresscontactalternatenumber);

        StoredAddressaddressLine1.setText(deliveryAddressModels.get(position).getHouse_number());
        StoredAddressaddressLine2.setText(deliveryAddressModels.get(position).getArea());
        StoredAddresscity.setText(deliveryAddressModels.get(position).getCity());
        StoredAddressdistrict.setText(deliveryAddressModels.get(position).getDistrict());
        StoredAddresspincode.setText(deliveryAddressModels.get(position).getPincode());
        StoredAddresscontactname.setText(deliveryAddressModels.get(position).getcName());
        StoredAddresscontactnumber.setText(deliveryAddressModels.get(position).getcNumber());
        StoredAddresscontactalternatenumber.setText(deliveryAddressModels.get(position).getcAltNumber());

        return convertView;

    }
}
