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
                """
                        1. Add a user
                        2. View user balances
                        3. Perform a transfer
                        4. View all transactions for a specific user
                        5. DEV - remove a transfer by ID
                        6. DEV - check transfer validity
                        7. Finish execution"""
        );
    }

    public Menu(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    public void startDevMode()
    {
        Scanner scanner = new Scanner(System.in);
        devMessage();
        int i = scanner.nextInt();
        while (i != 7)
        {
            if (i == 1) {
                AddAUser();
            }
            if (i == 2) {
                UserBalance();
            }
            if (i == 3) {
                PerformATransfer();
            }
            if (i == 4) {
                AllUsersTransactions();
            }
            if (i == 5) {
                RemoveTransfer();
            }
            if (i == 6) {
                checkTransferValidity();
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
                    AddAUser();
                    break;
                case (2):
                    UserBalance();
                    break;
                case (3):
                    PerformATransfer();
                    break;
                case (4):
                    AllUsersTransactions();
                    break;
                case (5):
                    System.exit(0);
            }
            System.out.println("---------------------------------------------------------");
            message();
            i = scanner.nextInt();
        }
    }

    private void AddAUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user's name and a balance");
        boolean rewrite = true;
        String name = null;
        int balance = 0;
        while (rewrite) {
            try {
                name = scanner.next();
                balance = scanner.nextInt();
                rewrite = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Try enter correct user's name and a balance again");
            }
        }
        User user = new User(name, balance);
        transactionsService.addUser(user);
        System.out.println("User with id = " + user.getIdentifier() + " is added");
    }

    private void UserBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user id");
        boolean rewrite = true;
        int id = 0;
        int balance = 0;
        while (rewrite) {
            try {
                id = scanner.nextInt();
                balance = transactionsService.getUserBalance(id);
                rewrite = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Try enter correct user id again");
            }
        }
        System.out.println("User with id = " + id + " have balance with " + balance + " amount");
    }

    private void PerformATransfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        boolean rewrite = true;
        int senderId = 0;
        int recipientId = 0;
        int transferAmount = 0;
        while (rewrite) {
            try {
                senderId = scanner.nextInt();
                recipientId = scanner.nextInt();
                transferAmount = scanner.nextInt();
                transactionsService.makeTransaction(senderId, recipientId, transferAmount);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Enter correct sender ID, recipient ID and transfer amount");
            }
        System.out.println("The transfer is completed");
        }
    }

    private void AllUsersTransactions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user ID");
        int userId = 0;
        Transaction[] usersTransaction = null;
        boolean rewrite = true;

        while (rewrite) {
            try {
                userId = scanner.nextInt();
                User user = transactionsService.retrieveUserById(userId);
                usersTransaction = transactionsService.getUsersTransactions(user);
            } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Enter correct user ID");
                }
            }
        for (Transaction transaction: usersTransaction) {
            System.out.println(transaction.getTransactionsInfo());
        }
    }

    private void RemoveTransfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user ID and a transfer ID");
        boolean rewrite = true;
        while (rewrite) {
            try {
        int userId = scanner.nextInt();
        UUID trId = UUID.fromString(scanner.next());
        transactionsService.removeUsersTransaction(userId, trId);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Enter a user ID and a transfer I");
            }
        }
        System.out.println("Transfer removed");
    }

    private void checkTransferValidity() {
        Transaction[] invalidTransactions = transactionsService.getInvalidTransactions();
        if (invalidTransactions.length != 0 && invalidTransactions[0] != null) {
            System.out.println("notAll transactions are valid " + invalidTransactions.length + " "+ invalidTransactions[0]);
        }
        else if (invalidTransactions.length == 0) {
            System.out.println("No one transaction was transferred");
        }
        else {
            System.out.println("All transactions are valid");
        }
    }
}