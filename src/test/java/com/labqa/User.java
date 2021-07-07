package com.labqa;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 07.07.2021
 */

public class User {

    private String userLogin;
    private String userDomain;
    private String userPassword;
    private boolean isRememberAuthSession;

    public User() {
        this.userLogin = "djanticisco";
        this.userDomain = "mail.ru";
        this.userPassword = "Only.test.2021";
        this.isRememberAuthSession = true;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isRememberAuthSession() {
        return isRememberAuthSession;
    }

    public String getUserEmail() {
        return userLogin + "@" + userDomain;
    }
}
