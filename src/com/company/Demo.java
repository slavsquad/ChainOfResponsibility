package com.company;

import com.company.Handler.AuthorizationHandler;
import com.company.Handler.Handler;
import com.company.Handler.ThrottlingHandler;
import com.company.Handler.UserExistHandler;
import com.company.Server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo{
        private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private static Server server;

        private static void initializationServer() {//Создание нового сервера и регистрация на нем пользователей
            server = new Server();
            server.registerUser("admin@example.com", "admin_pass");
            server.registerUser("user@example.com", "user_pass");

            // Проверки связаны в одну цепь. Клиент может строить различные цепи,
            // используя одни и те же компоненты.
            // В данном случае: Проверка на превышения числа запросов к серверу -> Проверка на существования пользователя -> Проверка коректности пароля
            // ThrottlingHandler.next = UserExistHandler; UserExistHandler.next = AuthorizationHandler; AuthorizationHandler.next = null
            Handler handler = new ThrottlingHandler(2);
                    handler.linkWith(new UserExistHandler())
                    .linkWith(new AuthorizationHandler());

            // Сервер получает цепочку от клиентского кода.
            server.setHandler(handler);
        }

        public static void main(String[] args) throws IOException {

            initializationServer();

            boolean success;
            do {
                System.out.println();
                System.out.print("user name: ");
                String email = reader.readLine();
                System.out.print("password: ");
                String password = reader.readLine();
                System.out.println();
                success = server.logIn(email, password);
            } while (!success);
        }
}
