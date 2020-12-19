package com.mercado.mercadocom.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mercado.mercadocom.Activity.Checkout_Activity;
import com.mercado.mercadocom.Adapters.cartAdapter;
import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.Database.MobUser_Table;
import com.mercado.mercadocom.Interface.cartInterface;
import com.mercado.mercadocom.Model.ProductModel;
import com.mercado.mercadocom.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mycart_Fragment extends Fragment implements cartInterface {

    public static cartInterface cartInterface;
    ListView listView;
    List<MobUser> UserList;
    Button cartCheckout;
    cartAdapter cartAdapter;
    SharedPreferences sharedPreferences;
    List<ProductModel> mProductmodel;
    public ProgressDialog progressDialog;

    public Mycart_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mycart, container, false);

        try {

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading your Orders...");

            cartCheckout = view.findViewById(R.id.cartCheckOut);
            listView = view.findViewById(R.id.cartListView);

            cartInterface = this;

            progressDialog.show();

            UserList = SQLite.select().from(MobUser.class).queryList();
            cartAdapter = new cartAdapter(getActivity(), UserList);
            listView.setAdapter(cartAdapter);

            progressDialog.dismiss();

            cartCheckout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UserList.isEmpty()) {
                        Toast.makeText(getContext(), "Add Some Products", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getContext(), Checkout_Activity.class);
                        startActivity(intent);
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void addProduct(String s, String getpName, String getmDescription, String getpPrice, String getpType, String getpStoke, String getpUserKey, String getmKey, int position) {
    }

    @Override
    public void add(int position) {

        try {
            String q = UserList.get(position).getPqty();
            int qq = Integer.parseInt(q);
            qq = qq + 1;

            SQLite.update(MobUser.class).set(MobUser_Table.pqty.eq(String.valueOf(qq))).where(MobUser_Table.pMkey.eq(UserList.get(position).getpMkey())).execute();
            UserList.get(position).setPqty(String.valueOf(qq));
            cartAdapter.notifyDataSetChanged();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int position) {
        try {
            String q = UserList.get(position).getPqty();
            int qq = Integer.parseInt(q);
            qq = qq - 1;
            if (qq <= 1) {
                qq = 1;
                Toast.makeText(getActivity(), "Qty less then zero", Toast.LENGTH_SHORT).show();
            }
            SQLite.update(MobUser.class).set(MobUser_Table.pqty.eq(String.valueOf(qq))).where(MobUser_Table.pMkey.eq(UserList.get(position).getpMkey())).execute();
            UserList.get(position).setPqty(String.valueOf(qq));
            cartAdapter.notifyDataSetChanged();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cart(int position) {

    }

    @Override
    public void removeitem(int position) {
        try {
            UserList.get(position).delete();
            UserList.remove(position);
            Toast.makeText(getActivity(), "Item Removed", Toast.LENGTH_SHORT).show();
            cartAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
