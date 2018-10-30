package com.example.cln62.onlineshoppingapp.pojo;

public class SignupProfile {
    String fname;
    String lname;
    String email;
    String address;
    String mobile;
    String password;

    public SignupProfile(String fname, String lname, String email, String address, String mobile, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
