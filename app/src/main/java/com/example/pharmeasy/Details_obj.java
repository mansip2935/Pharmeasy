package com.example.pharmeasy;

public class Details_obj {

  String Name, About_manufacture  , Product_description  , Product_price ,Product_quantity,Expiry_date  ,ImageURL,sid;

    public String getExpiry_date() {
        return Expiry_date;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setExpiry_date(String setExpiry_date) {
        this.Expiry_date = setExpiry_date;
    }

    public String getName() {
        return Name;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAbout_manufacture() {
        return About_manufacture;
    }

    public void setAbout_manufacture(String about_manufacture) {
        About_manufacture = about_manufacture;
    }

    public String getProduct_description() {
        return Product_description;
    }

    public void setProduct_description(String product_description) {
        Product_description = product_description;
    }

    public String getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(String product_price) {
        Product_price = product_price;
    }

    public String getProduct_quantity() {
        return Product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        Product_quantity = product_quantity;
    }


}
