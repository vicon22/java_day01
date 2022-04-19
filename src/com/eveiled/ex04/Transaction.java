package com.eveiled.ex04;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private int transferAmount;
    private Status status;
    private Category category;

    private enum Category {
        DEBIT , CREDIT
    }
    private enum Status {
        SUCCESS , FAIL
    }

    public Transaction(User recipient, User sender, int transferAmount){
        identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;

        if (transferAmount < 0){
            this.category = Category.CREDIT;
        }
        else {
            this.category = Category.DEBIT;
        }
        if (this.sender.getBalance() < 0 || this.sender.getBalance() < this.transferAmount) {
            status = Status.FAIL;
        }
        else {
            if (category == Category.DEBIT)
                sender.setBalance(sender.getBalance() - transferAmount);
            else
                recipient.setBalance(recipient.getBalance() - transferAmount);
            status = Status.SUCCESS;
        }
    }

    public Transaction(UUID identifier, User recipient, User sender, int transferAmount){
        this.identifier = identifier;
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;

        if (transferAmount < 0){
            this.category = Category.CREDIT;
        }
        else {
            this.category = Category.DEBIT;
        }
        if (this.sender.getBalance() < 0 || this.sender.getBalance() < this.transferAmount) {
            status = Status.FAIL;
        }
        else {
            if (category == Category.DEBIT)
                sender.setBalance(sender.getBalance() - transferAmount);
            else
                recipient.setBalance(recipient.getBalance() - transferAmount);
            status = Status.SUCCESS;
        }
    }

    public UUID getId() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public Status getStatus() {
        return status;
    }

    public String getCategory() {
        return category.toString();
    }

    public String getTransactionsInfo() {
        return ("Transaction{" +
                "identifier=" + identifier +
                ", recipient=" + recipient.getName() +
                ", sender=" + sender.getName() +
                ", amount=" + transferAmount +
                ", category=" + category +
                ", status=" + status +
                '}');
    }
}