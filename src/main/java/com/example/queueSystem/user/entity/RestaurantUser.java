package com.example.queueSystem.user.entity;

public class RestaurantUser extends User{
    private String userName;
    private String password;

    public RestaurantUser(Integer phoneNumber, String email, String userName, String password) {
        super(phoneNumber, email);
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
