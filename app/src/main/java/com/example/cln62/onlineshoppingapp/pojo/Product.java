package com.example.cln62.onlineshoppingapp.pojo;

public class Product {

    public static String PRODUCT_NAME_KEY = "pname";
    public static String PRODUCT_IMAGE_KEY = "image";

    String id;
    String pname;
    String quantity;
    String prize;
    String description;
    String image;

    public Product(String id, String pname, String quantity, String prize, String description, String image) {
        this.id = id;
        this.pname = pname;
        this.quantity = quantity;
        this.prize = prize;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


