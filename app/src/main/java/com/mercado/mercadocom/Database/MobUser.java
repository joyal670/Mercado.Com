package com.mercado.mercadocom.Database;

import com.mercado.mercadocom.Database.dbflow.MyDatabse;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabse.class)
public class MobUser extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String pImage;

    @Column
    String pName;

    @Column
    String pDescription;

    @Column
    String pPrice;

    @Column
    String pType;

    @Column
    String pStoke;

    @Column
    String pUserKey;

    @Column
    String pMkey;

    @Column
    String pqty;

    public void InsertData(String pImage, String pName, String pDescription, String pPrice, String pType, String pStoke, String pUserKey, String pMkey, String pqty)
    {
        this.pImage = pImage;
        this.pName = pName;
        this.pDescription = pDescription;
        this.pPrice = pPrice;
        this.pType = pType;
        this.pStoke = pStoke;
        this.pUserKey = pUserKey;
        this.pMkey = pMkey;
        this.pqty = pqty;
    }

    public String getPqty() {
        return pqty;
    }

    public void setPqty(String pqty) {
        this.pqty = pqty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpStoke() {
        return pStoke;
    }

    public void setpStoke(String pStoke) {
        this.pStoke = pStoke;
    }

    public String getpUserKey() {
        return pUserKey;
    }

    public void setpUserKey(String pUserKey) {
        this.pUserKey = pUserKey;
    }

    public String getpMkey() {
        return pMkey;
    }

    public void setpMkey(String pMkey) {
        this.pMkey = pMkey;
    }
}
