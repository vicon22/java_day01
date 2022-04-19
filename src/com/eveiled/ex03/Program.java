package com.eveiled.ex03;

import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User john = new User( "John", 1000);
        User tom = new User("Tom", 1000);
        User barbara = new User( "Barbara", 1000);
        User homelessNick = new User("Homeless Nick", -480);


        Transaction tr1 = new Transaction(john, tom, 300);
        Transaction tr2 = new Transaction(barbara, tom, 150);
        Transaction tr3 = new Transaction(john, barbara, -350);
        Transaction tr4 = new Transaction(john, barbara, -3500);
        Transaction tr5 = new Transaction(john, tom, 3000);
        Transaction tr6 = new Transaction(john, tom, 550);
        Transaction tr7 = new Transaction(john, tom, -1500);
        Transaction tr8 = new Transaction(homelessNick, tom, 400);
        Transaction tr9 = new Transaction(tom, homelessNick, -400);

        TransactionsList transactionsList = new TransactionsLinkedList();

        System.out.println("1 " + tr1.getTransactionsInfo());
        System.out.println("2 " + tr2.getTransactionsInfo());
        System.out.println("3 " + tr3.getTransactionsInfo());
        System.out.println("4 " + tr4.getTransactionsInfo());
        System.out.println("5 " + tr5.getTransactionsInfo());
        System.out.println("6 " + tr6.getTransactionsInfo());
        System.out.println("7 " + tr7.getTransactionsInfo());
        System.out.println("8 " + tr8.getTransactionsInfo());
        System.out.println("9 " + tr9.getTransactionsInfo());


        transactionsList.add(tr1);
        transactionsList.add(tr2);
        transactionsList.add(tr3);
        transactionsList.add(tr4);
        transactionsList.add(tr5);
        transactionsList.add(tr6);
        transactionsList.add(tr7);
        transactionsList.add(tr8);
        transactionsList.add(tr9);


        System.out.println("\n==============================================Implements method remove()==============================================");
        transactionsList.remove(tr1.getId());
        transactionsList.remove(tr5.getId());
        transactionsList.remove(tr9.getId());
        try{
            UUID uuid = UUID.randomUUID();
            System.out.println("Create a random UUID: " + uuid);
            transactionsList.remove(uuid);
        }catch (TransactionNotFoundException e){
            System.out.println(e);
        }
        System.out.println("----------------------------------------------");
        try{
            transactionsList.remove(tr1.getId());
        }catch (TransactionNotFoundException e){
            System.out.println(e);
        }

        try{
            transactionsList.remove(tr5.getId());
        }catch (TransactionNotFoundException e){
            System.out.println(e);
        }

        try{
            transactionsList.remove(tr9.getId());
        }catch (TransactionNotFoundException e){
            System.out.println(e);
        }

        System.out.println("\n==============================================Implements method toArray()==============================================");
        int i = 0;
        Transaction[] arrTr = transactionsList.toArray();
        for (Transaction transaction: arrTr) {
            i++;
            System.out.println(i + " " + transaction.getTransactionsInfo());
        }

        System.out.println("\n==============================================Implements Iterable==============================================");
        i = 0;
        for (Transaction transaction: transactionsList) {
            i++;
            System.out.println(i + " " + transaction.getTransactionsInfo());
        }
        System.out.println("==============================================");
    }
}
