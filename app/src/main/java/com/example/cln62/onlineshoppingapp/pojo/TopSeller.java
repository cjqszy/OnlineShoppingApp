package com.example.cln62.onlineshoppingapp.pojo;

public class TopSeller {
    private String id;
    private String sellername;
    private String sellerdeal;
    private String sellerrating;
    private String sellerlogo;

    public TopSeller(String id, String sellername, String sellerdeal, String sellerrating, String sellerlogo) {
        this.id = id;
        this.sellername = sellername;
        this.sellerdeal = sellerdeal;
        this.sellerrating = sellerrating;
        this.sellerlogo = sellerlogo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getSellerdeal() {
        return sellerdeal;
    }

    public void setSellerdeal(String sellerdeal) {
        this.sellerdeal = sellerdeal;
    }

    public String getSellerrating() {
        return sellerrating;
    }

    public void setSellerrating(String sellerrating) {
        this.sellerrating = sellerrating;
    }

    public String getSellerlogo() {
        return sellerlogo;
    }

    public void setSellerlogo(String sellerlogo) {
        this.sellerlogo = sellerlogo;
    }
}
