package com.eveiled.ex05;

import com.eveiled.ex05.transaction.Transaction;
import com.eveiled.ex05.transactionServise.TransactionsService;
import com.eveiled.ex05.user.User;

import java.util.Scanner;
import java.util.UUID;

public class Menu {

    private TransactionsService transactionsService;

    public void message()
    {
        System.out.println(
                "1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n" +
                "5. Finish execution"
        );
    }

    public void devMessage()
    {
        System.out.println(
                "1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n" +
                "5. DEV - remove a transfer by ID\n" +
                "6. DEV - check transfer validity\n" +
                "7. Finish execution"
        );
    }

    public Menu(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    public void startDevMode()
    {
        Scanner scanner = new Scanner(System.in);
        message();
        int i = scanner.nextInt();

        System.out.println("---------------------------------------------------------");
        devMessage();
        i = scanner.nextInt();
        while (i != 7)
        {
            if (i == 1) {
                AddAUser(transactionsService);
            }
            if (i == 2) {
                UserBalance(transactionsService);
            }
            if (i == 3) {
                PerformATransfer(transactionsService);
            }
            if (i == 4) {
                AllUsersTransactions(transactionsService);
            }
            if (i == 5) {
                RemoveTransfer(transactionsService);
            }
            if (i == 6) {
                checkTransferValidity(transactionsService);
            }
            System.out.println("---------------------------------------------------------");
            devMessage();
            i = scanner.nextInt();
        }
    }

    public void start()
    {
        Scanner scanner = new Scanner(System.in);
        message();
        int i = scanner.nextInt();
        while(true) {
            switch (i) {
                case (1):
                    AddAUser(transactionsService);
                    break;
                case (2):
                    UserBalance(transactionsService);
                    break;
                case (3):
                    PerformATransfer(transactionsService);
                    break;
                case (4):
                    AllUsersTransactions(transactionsService);
                    break;
                case (5):
                    System.exit(0);
            }
            System.out.println("---------------------------------------------------------");
            message();
            i = scanner.nextInt();
        }
    }

    private static void AddAUser(TransactionsService transactionsService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user name and a balance");
        String name = scanner.next();
        int balance = scanner.nextInt();
        User user = new User(name, balance);
        transactionsService.addUser(user);
        System.out.println("User with id = " + user.getIdentifier() + " is added");
    }

    private static void UserBalance(TransactionsService transactionsService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user id");
        int id = scanner.nextInt();
        int balance = transactionsService.getUserBalance(id);
        System.out.println("User with id = " + id + " have balance with " + balance + " amount");
    }

    private static void PerformATransfer(TransactionsService transactionsService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int senderId = scanner.nextInt();
        int recipientId = scanner.nextInt();
        int transferAmount = scanner.nextInt();
        transactionsService.makeTransaction(senderId, recipientId, transferAmount);
        System.out.println("The transfer is completed");

    }

    private static void AllUsersTransactions(TransactionsService transactionsService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user ID");
        int userId = scanner.nextInt();
        User user = transactionsService.retrieveUserById(userId);
        Transaction[] usersTransaction = transactionsService.getUsersTransactions(user);
        for (Transaction transaction: usersTransaction)
        {
            System.out.println(transaction.getTransactionsInfo());
        }
    }

    private static void RemoveTransfer(TransactionsService transactionsService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user ID and a transfer ID");
        int userId = scanner.nextInt();
        UUID trId = UUID.fromString(scanner.next());
        System.out.println(trId);
        transactionsService.removeUsersTransaction(userId, trId);
    }

    private static void checkTransferValidity(TransactionsService transactionsService) {
        Transaction[] invalidTransactions = transactionsService.getInvalidTransactions();
        if (invalidTransactions.length != 0 && invalidTransactions[0] != null)
        {
            System.out.println("notAll transactions are valid " + invalidTransactions.length + " "+ invalidTransactions[0]);
        }
        else if (invalidTransactions.length == 0){
            System.out.println("No one transaction was transferred");
        }
        else {
            System.out.println("All transactions are valid");
        }
    }
}