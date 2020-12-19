package com.mercado.mercadocom.Interface;

public interface cartInterface {

    public void addProduct(String s, String getpName, String getmDescription, String getpPrice, String getpType, String getpStoke, String getpUserKey, String getmKey, int position);

    void add(int position);

    void remove(int position);

    void cart(int position);

    void removeitem(int position);
}
