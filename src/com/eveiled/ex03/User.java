package com.eveiled.ex03;

public class User {

    private int identifier;
    private String name;
    private int balance;
    private TransactionsLinkedList linkedList;

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

    public void setIdentifier(int identifier)
    {
        this.identifier = identifier;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public TransactionsLinkedList getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(TransactionsLinkedList linkedList) {
        this.linkedList = linkedList;
    }
}
