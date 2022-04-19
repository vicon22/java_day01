package com.eveiled.ex04;

import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User john = new User( "John", 1000);
        User tom = new User("Tom", 1000);
        User barbara = new User( "Barbara", 1000);
        User nick = new User("Nick", 1000);
        User sasha = new User("Sasha", 1000);

        TransactionsService transactionsService = new TransactionsService();

        System.out.println("========================================= makeTransaction() =========================================");
        transactionsService.addUser(john);
        transactionsService.addUser(tom);
        transactionsService.addUser(barbara);
        transactionsService.addUser(nick);
        transactionsService.addUser(sasha);
        transactionsService.makeTransaction(1, 2, 300);
        transactionsService.makeTransaction(2, 1, 200);
        System.out.println(john.getIdentifier() + " " + john.getName() + " " + john.getBalance());
        System.out.println(tom.getIdentifier() + " " + tom.getName() + " " + tom.getBalance());
        transactionsService.makeTransaction(3, 1, 500);
        transactionsService.makeTransaction(4, 3, 300);
        transactionsService.makeTransaction(5, 4, 100);
        transactionsService.makeTransaction(1, 2, 50);
        try {
            transactionsService.makeTransaction(1, 1, 50);
        }catch (IllegalTransactionException e){
            System.out.println(e);
        }
        try {
            transactionsService.makeTransaction(1, 2, 5000000);
        }catch (IllegalTransactionException e){
            System.out.println(e);
        }
        try {
            transactionsService.makeTransaction(1, 2, -100000);
        }catch (IllegalTransactionException e){
            System.out.println(e);
        }


        System.out.println("========================================= getUserBalance() =========================================");
        System.out.println(transactionsService.getUserBalance(1));

        System.out.println("========================================= getUsersTransactions() =========================================");
        Transaction[] transactions = transactionsService.getUsersTransactions(john);
        for (Transaction transaction: transactions) {
            System.out.println(transaction.getTransactionsInfo());
        }

        System.out.println("========================================= removeUsersTransaction() =========================================");
        User deleteTransactionUser = john;

        transactions = transactionsService.getUsersTransactions(deleteTransactionUser);
        transactionsService.removeUsersTransaction(deleteTransactionUser.getIdentifier(), transactions[0].getId());

        deleteTransactionUser = nick;

        transactions = transactionsService.getUsersTransactions(deleteTransactionUser);
        transactionsService.removeUsersTransaction(deleteTransactionUser.getIdentifier(), transactions[0].getId());

        deleteTransactionUser = sasha;

        transactions = transactionsService.getUsersTransactions(deleteTransactionUser);
        transactionsService.removeUsersTransaction(deleteTransactionUser.getIdentifier(), transactions[0].getId());


        System.out.println("========================================= getInvalidTransactions() =========================================");

        for(Transaction transaction: transactionsService.getInvalidTransactions()){
            System.out.println(transaction);
        }
    }
}