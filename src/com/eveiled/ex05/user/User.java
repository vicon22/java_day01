package com.eveiled.ex05.user;

import com.eveiled.ex05.transaction.Transaction;
import com.eveiled.ex05.transaction.TransactionsLinkedList;

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
        linkedList = new TransactionsLinkedList();
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

    public TransactionsLinkedList getTransactionsList() {
        return linkedList;
    }

    public void addTransaction(Transaction transaction) {
        this.linkedList.add(transaction);
    }
}

