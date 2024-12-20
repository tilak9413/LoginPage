package com.example.LoginPage.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class LoginEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private String password;

    @Version
    private Long version;

    @OneToMany(mappedBy = "loginEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<KycEntity> kycEntities;

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<KycEntity> getKycEntities() {
        return kycEntities;
    }

    public void setKycEntities(List<KycEntity> kycEntities) {
        this.kycEntities = kycEntities;
    }
}
