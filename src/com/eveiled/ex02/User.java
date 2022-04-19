package com.eveiled.ex02;

import com.eveiled.ex01.UserIdsGenerator;

public class User {

    private final int identifier;
    private String name;
    private int balance;

    public User(String name, int balance)
    {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
    }

    public int getIdentifier()
    {
        return identifier;
    }

    public int getBalance()
    {
        return balance;
    }

    public String getName()
    {
        return name;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
