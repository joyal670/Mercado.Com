package com.mercado.mercadocom.Model;

public class DeliveryAddressModel
{
    String house_number;
    String area;
    String city;
    String district;
    String pincode;
    String cName;
    String cNumber;
    String cAltNumber;

    public DeliveryAddressModel()
    {

    }

    public DeliveryAddressModel(String house_number, String area, String city, String district, String pincode, String cName, String cNumber, String cAltNumber) {
        this.house_number = house_number;
        this.area = area;
        this.city = city;
        this.district = district;
        this.pincode = pincode;
        this.cName = cName;
        this.cNumber = cNumber;
        this.cAltNumber = cAltNumber;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public String getcAltNumber() {
        return cAltNumber;
    }

    public void setcAltNumber(String cAltNumber) {
        this.cAltNumber = cAltNumber;
    }
}
