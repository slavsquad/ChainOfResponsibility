package com.company.Handler;

public abstract class Handler {
    private Handler next;

    // Помогает строить цепь из объектов-проверок.
    public Handler linkWith(Handler next){
        this.next = next;
        return next;
    }

    //Подклассы реализуют в этом методе конкретные проверки.
    public abstract boolean check(String user, String password);

    // Запускает проверку в следующем объекте или завершает проверку, если мы в
    // последнем элементе цепи.
    protected boolean checkNext(String user, String password){
        if (next==null){
            return true;
        } else{
            return next.check(user,password);
        }
    }
}
