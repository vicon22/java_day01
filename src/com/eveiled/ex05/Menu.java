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
                case (1) -> AddAUser();
                case (2) -> UserBalance();
                case (3) -> PerformATransfer();
                case (4) -> AllUsersTransactions();
                case (5) -> System.exit(0);
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
                rewrite = false;
                System.out.println("The transfer is completed");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Enter correct sender ID, recipient ID and transfer amount");
            }
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
                rewrite = false;
            } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Enter correct user ID");
                }
            }
        for (Transaction transaction: usersTransaction) {
            System.out.println("Transfer To " + transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier() + ") " + transaction.getTransferAmount() + " with id = "
            + transaction.getId());
        }
    }

    private void RemoveTransfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a user ID and a transfer ID");
        boolean rewrite = true;
        Transaction transaction = null;
        while (rewrite) {
            try {
                int userId = scanner.nextInt();
                UUID trId = UUID.fromString(scanner.next());
                User user = transactionsService.retrieveUserById(userId);
                Transaction[] transactions = transactionsService.getUsersTransactions(user);
                for (Transaction tr: transactions) {
                    if (tr.getId().compareTo(trId) == 0){
                        transaction = tr;
                    }
                }
                if (transaction == null){
                    throw new Exception("non-existent transaction ID");
                }
                transactionsService.removeUsersTransaction(userId, trId);
                rewrite = false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Enter a user ID and a transfer I");
            }
        }
        System.out.println("Transfer To " + transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier() + ") " + transaction.getTransferAmount() + " removed");
    }

    private void checkTransferValidity() {

        try {
            System.out.println("Check results: ");
            Transaction[] invalidTransactions = transactionsService.getInvalidTransactions();
            if (invalidTransactions.length != 0 && invalidTransactions[0] != null) {
                for (Transaction transaction : invalidTransactions) {

                    System.out.println(transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier()
                            + ") has an unacknowledged transfer id = " + transaction.getId() + " from "
                            + transaction.getSender().getName()  + "(id = " + transaction.getSender().getIdentifier()
                            + ") for " + transaction.getTransferAmount());
                }

            } else if (invalidTransactions.length == 0) {
                System.out.println("No one transaction was transferred");
            } else {
                System.out.println("All transactions are valid");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}