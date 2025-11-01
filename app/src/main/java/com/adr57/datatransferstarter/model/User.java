package com.adr57.datatransferstarter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User /*implements Parcelable */ {
    private String name;
    private String email;
    private int age;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // TODO: Implement Parcelable methods


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}