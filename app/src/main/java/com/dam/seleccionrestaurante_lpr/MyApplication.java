package com.dam.seleccionrestaurante_lpr;

import android.app.Application;

public class MyApplication extends Application {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
