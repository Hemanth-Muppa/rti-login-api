package com.example.rtilogin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NetUser")
public class NetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;

    private String userName;
    private String mobile;
    private String userType;
    private String activeIdle; // 'Y' or 'N'
    private String activationKey;
    private String activationKeyConf;
    private int loginAttempts;
    private LocalDateTime lastSuccessfulLogin;
    private LocalDateTime lastUnSuccessfulLogin;

    // Getters and setters
    public Long getUserCode() { return userCode; }
    public void setUserCode(Long userCode) { this.userCode = userCode; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public String getActiveIdle() { return activeIdle; }
    public void setActiveIdle(String activeIdle) { this.activeIdle = activeIdle; }
    public String getActivationKey() { return activationKey; }
    public void setActivationKey(String activationKey) { this.activationKey = activationKey; }
    public String getActivationKeyConf() { return activationKeyConf; }
    public void setActivationKeyConf(String activationKeyConf) { this.activationKeyConf = activationKeyConf; }
    public int getLoginAttempts() { return loginAttempts; }
    public void setLoginAttempts(int loginAttempts) { this.loginAttempts = loginAttempts; }
    public LocalDateTime getLastSuccessfulLogin() { return lastSuccessfulLogin; }
    public void setLastSuccessfulLogin(LocalDateTime lastSuccessfulLogin) { this.lastSuccessfulLogin = lastSuccessfulLogin; }
    public LocalDateTime getLastUnSuccessfulLogin() { return lastUnSuccessfulLogin; }
    public void setLastUnSuccessfulLogin(LocalDateTime lastUnSuccessfulLogin) { this.lastUnSuccessfulLogin = lastUnSuccessfulLogin; }
}
