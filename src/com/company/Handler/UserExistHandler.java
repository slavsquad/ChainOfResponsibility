package com.company.Handler;

import com.company.Server.Server;

public class UserExistHandler extends Handler{
    @Override
    public boolean check(String user, String password) {
        if (Server.hasUser(user)){
            System.out.println(String.format("User %s has existed!",user));
            return super.checkNext(user,password);
        }
        System.out.println(String.format("User %s doesn't exist on server!",user));
        return false;
    }
}
