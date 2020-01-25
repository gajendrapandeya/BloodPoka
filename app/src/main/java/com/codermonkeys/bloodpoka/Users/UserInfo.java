package com.codermonkeys.bloodpoka.Users;

import android.app.Application;

import java.io.Serializable;

public class UserInfo extends Application {

    private String name;
    private String address;

    public UserInfo() {
    }

    public UserInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
