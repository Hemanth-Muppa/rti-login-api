package com.example.rtilogin.dto;

public class LoginResponse {
    private String result;
    private String resulttype;
    private Long sessionuser;
    private String sessiontype;
    private String jwtToken;

    public LoginResponse(String result, String resulttype, Long sessionuser, String sessiontype, String jwtToken) {
        this.result = result;
        this.resulttype = resulttype;
        this.sessionuser = sessionuser;
        this.sessiontype = sessiontype;
        this.jwtToken = jwtToken;
    }

    public LoginResponse(String result, String resulttype, Long sessionuser, String sessiontype) {
        this(result, resulttype, sessionuser, sessiontype, null);
    }

    // Getters and setters
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getResulttype() { return resulttype; }
    public void setResulttype(String resulttype) { this.resulttype = resulttype; }
    public Long getSessionuser() { return sessionuser; }
    public void setSessionuser(Long sessionuser) { this.sessionuser = sessionuser; }
    public String getSessiontype() { return sessiontype; }
    public void setSessiontype(String sessiontype) { this.sessiontype = sessiontype; }
    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }
}
