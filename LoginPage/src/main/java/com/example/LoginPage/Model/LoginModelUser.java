package com.example.LoginPage.Model;

public class LoginModelUser {

    private Integer kyc_id; // Reference to the KYC entity
    private Integer userId; // User ID
    private String userName; // Username of the user
    private String password; // Password of the user

    // Getter and Setter for kyc_id
    public Integer getKyc_id() {
        return kyc_id;
    }

    public void setKyc_id(Integer kyc_id) {
        this.kyc_id = kyc_id;
    }

    // Getter and Setter for userId
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Getter and Setter for userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
