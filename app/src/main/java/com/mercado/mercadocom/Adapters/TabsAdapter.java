package com.mercado.mercadocom.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mercado.mercadocom.Fragments.AllOrders_Fragment;
import com.mercado.mercadocom.Fragments.DeliveredOrders_Fragment;
import com.mercado.mercadocom.Fragments.OnGoingOrders_Fragment;


public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                AllOrders_Fragment f1 = new AllOrders_Fragment();
                return f1;
            case 1:
                DeliveredOrders_Fragment f2 = new DeliveredOrders_Fragment();
                return f2;
            case 2:
                OnGoingOrders_Fragment f3 = new OnGoingOrders_Fragment();
                return f3;

            default:
                return null;
        }
    }
}