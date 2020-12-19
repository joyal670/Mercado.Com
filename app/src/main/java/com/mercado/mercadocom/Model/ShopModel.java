package com.mercado.mercadocom.Model;

import com.google.firebase.database.Exclude;

public class ShopModel {
    private String mName;
    private String mImageUrl;
    private String mPlace;
    private String mPhone;
    private String mType;
    private String mKey;
    private String mUserKey;

    public ShopModel() {
    }

    public ShopModel(String Name, String ImageUrl, String place, String phone, String type, String userKey) {
        if(Name.trim().equals("")){
            Name = "No name";
        }
        this.mName = Name;
        this.mPhone = phone;
        this.mPlace = place;
        this.mType = type;
        this.mImageUrl = ImageUrl;
        this.mUserKey = userKey;
    }

    public String getmUserKey() {
        return mUserKey;
    }

    public void setmUserKey(String mUserKey) {
        this.mUserKey = mUserKey;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmPlace() {
        return mPlace;
    }

    public void setmPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }

    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
