package com.example.yallaouting.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    @SerializedName("ID")
    private int id;
    @Expose
    @SerializedName("phoneNumber")
    private String phonenumber;
    @Expose
    @SerializedName("Password")
    private String password;
    @Expose
    @SerializedName("LastName")
    private String lastname;
    @Expose
    @SerializedName("FirstName")
    private String firstname;
    @Expose
    @SerializedName("UserName")
    private String username;
    @Expose
    @SerializedName("GenderId")
    private int genderid;
    @Expose
    @SerializedName("Gender")
    private Object Gender;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGenderid() {
        return genderid;
    }

    public void setGenderid(int genderid) {
        this.genderid = genderid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
