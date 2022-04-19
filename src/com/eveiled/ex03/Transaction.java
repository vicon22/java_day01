package com.eveiled.ex03;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;

    public UUID getId() {
        return identifier;
    }

    private enum Category {
        DEBIT , CREDIT
    }
    private Category category;

    private int transferAmount;
    private enum Status {
        SUCCESS , FAIL
    }
    private Status status;

    public Transaction(User recipient, User sender, int transferAmount){
        identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;

        if (transferAmount < 0){
            category = Category.CREDIT;
        }
        else {
            category = Category.DEBIT;
        }
        if (recipient.getBalance() < 0 || sender.getBalance() < 0){
            status = Status.FAIL;
        }
        else if (this.sender.getBalance() < 0 || this.sender.getBalance() < this.transferAmount || (category == Category.CREDIT && this.recipient.getBalance() < -1 * this.transferAmount)) {
            status = Status.FAIL;
        } else {
            sender.setBalance(sender.getBalance() - transferAmount);
            recipient.setBalance(recipient.getBalance() + transferAmount);
            status = Status.SUCCESS;
        }
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
