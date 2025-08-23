package com.springboot.webapp.Login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password){
        boolean isValidUserName = username.equals("papu");
        boolean isValidPassword = password.equals("gameon");

        return isValidUserName && isValidPassword;
    }
}