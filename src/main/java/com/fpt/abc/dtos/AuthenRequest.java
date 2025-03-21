package com.fpt.abc.dtos;

public class AuthenRequest {
    String accountEmail;
    String accountPassword;

    public AuthenRequest(String accountEmail, String accountPassword) {
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
    }

    public AuthenRequest() {
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
