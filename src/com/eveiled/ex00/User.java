package com.eveiled.ex00;

public class User {

    private final int identifier;
    private String name;
    private int balance;

    public User(int identifier, String name, int balance)
    {
        this.identifier = identifier;
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
