package com.example.cln62.onlineshoppingapp.network;

import com.example.cln62.onlineshoppingapp.pojo.SignupProfile;

public interface NetworkContract {

    void signup(SignupProfile sp);

    void login(String mobile, String password);
}
