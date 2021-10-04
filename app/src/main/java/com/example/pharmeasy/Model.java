package com.example.pharmeasy;

public class Model {
    String name ,product_price,imageURL,about_manufacture,expiry_date,product_description,sid;
public  Model(){}
    public String getName() {
        return name;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout_manufacture() {
        return about_manufacture;
    }

    public void setAbout_manufacture(String about_manufacture) {
        this.about_manufacture = about_manufacture;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
