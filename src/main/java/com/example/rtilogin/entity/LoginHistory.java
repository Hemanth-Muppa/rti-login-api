package com.example.rtilogin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LoginHistory")
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime loginDateTime;
    private String ip;
    private String browser;
    private String os;
    private String userName;
    private Long uCode;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getLoginDateTime() { return loginDateTime; }
    public void setLoginDateTime(LocalDateTime loginDateTime) { this.loginDateTime = loginDateTime; }
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    public String getBrowser() { return browser; }
    public void setBrowser(String browser) { this.browser = browser; }
    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }
    
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }
    public Long getUCode() { return uCode; }

    public void setUCode(Long uCode) { this.uCode = uCode; }
}
