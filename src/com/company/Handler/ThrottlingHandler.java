package com.company.Handler;

public class ThrottlingHandler extends Handler {
    private int requestsPerMinute;
    private int requests;
    private long currentTime;

    public ThrottlingHandler(int requestsPerMinute) {
        this.requestsPerMinute = requestsPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * Обратите внимание, вызов checkNext() можно вставить как в начале этого
     * метода, так и в середине или в конце.
     *
     * Это даёт еще один уровень гибкости по сравнению с проверками в цикле.
     * Например, элемент цепи может пропустить все остальные проверки вперёд и
     * запустить свою проверку в конце.
     */
    @Override
    public boolean check(String user, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000){
            requests = 0;
            currentTime = System.currentTimeMillis();
        }

        requests++;
        if (requests > requestsPerMinute){
            //Thread.currentThread().stop(); //Exit on server
            System.out.println("Request limit exceeded! Try it in a minute...");
            return false;
        }

        return super.checkNext(user,password);
    }
}
