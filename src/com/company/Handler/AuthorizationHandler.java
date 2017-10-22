package com.company.Handler;

import com.company.Server.Server;

public class AuthorizationHandler extends Handler {
    @Override
    public boolean check(String user, String password) {
        if (Server.hasAuthorization(user,password)){
            System.out.println("Correct password! Authorization is completed!");
            return super.checkNext(user,password);
        }
        System.out.println("Wrong password! Authorization doesn't complete!");
        return false;
    }
}
