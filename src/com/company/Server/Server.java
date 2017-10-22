package com.company.Server;

import com.company.Handler.Handler;

import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, String> users = new HashMap<>();
    private Handler handler;

    public boolean logIn(String user, String password){
        if (handler.check(user,password)){
            System.out.println("Access is allowed!!!");
            return true;
        } else{
            System.out.println("Access denied!!!");
            return false;
        }
    }

    public void registerUser(String user, String password){
        System.out.println(String.format("User %s has registered.",user));
        users.put(user,password);
    }

    public static boolean hasUser(String user){
        return users.containsKey(user);
    }

    public static boolean hasAuthorization(String user, String password){
        if (hasUser(user)){
            String userPassword = users.get(user);
            if (userPassword.equals(password)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Клиент подаёт готовую цепочку в сервер. Это увеличивает гибкость и
     * упрощает тестирование класса сервера.
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
