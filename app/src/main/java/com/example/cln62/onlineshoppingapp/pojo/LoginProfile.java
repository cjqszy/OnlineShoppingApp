package com.example.cln62.onlineshoppingapp.pojo;

public class LoginProfile {

    String id;
    String fname;
    String lname;
    String email;
    String mobile;
    String appapikey;
    String password;

    public LoginProfile(String id, String fname, String lname, String email, String mobile, String appapikey, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.appapikey = appapikey;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
