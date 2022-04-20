package com.eveiled.ex04;

import java.util.UUID;

public class TransactionsService {

    UsersList usersList = new UsersArrayList();
    TransactionsList transactionsList = new TransactionsLinkedList();

    public void addUser(User newUser) {
        usersList.add(newUser);
    }

    public int getUserBalance(int id) {
        return (usersList.getById(id).getBalance());
    }

    public void makeTransaction(int id_sender, int id_recipient, int transferAmount)
    {
        if (transferAmount <= 0){
            throw new IllegalTransactionException("transferAmount has to be positive!");
        }
        if (usersList.getById(id_recipient).getBalance() < transferAmount){
            throw new IllegalTransactionException("transferAmount exceed user’s residual balance!");
        }
        if (id_sender == id_recipient){
            throw new IllegalTransactionException("User can't sent money to yourself!");
        }

        Transaction debit = new Transaction(usersList.getById(id_recipient), usersList.getById(id_sender),  transferAmount);
        Transaction credit = new Transaction(debit.getId(), usersList.getById(id_recipient), usersList.getById(id_sender), -1 * transferAmount);

        transactionsList.add(debit);
        transactionsList.add(credit);
        usersList.getById(id_sender).addTransaction(debit);
        usersList.getById(id_recipient).addTransaction(credit);
    }

    public Transaction[] getUsersTransactions(User user)
    {
        TransactionsList list = usersList.getById(user.getIdentifier()).getTransactionsList();
        return  list.toArray();
    }

    public void removeUsersTransaction(int users_id, UUID transactions_id)
    {
        User user = usersList.getById(users_id);
        user.getTransactionsList().remove(transactions_id);
        transactionsList.remove(transactions_id);
    }

    public Transaction[] getInvalidTransactions()
    {
        Transaction[] allTransactions = transactionsList.toArray();
        Transaction[] invalidTransactions = new Transaction[allTransactions.length];
        int j = 0;
        for (int i = 0; i < allTransactions.length; i++) {
            if (allTransactions[i].getCategory().equals("DEBIT")) {
                if ((i + 1 >= allTransactions.length) || allTransactions[i + 1].getId() != allTransactions[i].getId()) {
                    invalidTransactions[j] = allTransactions[i];
                    j++;
                }
                else
                    i++;
            }
            else {
                if ((i - 1 < 0) || allTransactions[i - 1].getId() != allTransactions[i].getId()) {
                    invalidTransactions[j] = allTransactions[i];
                    j++;
                }
                else
                    i++;
            }
        }
        return getCorrectArray(invalidTransactions);
    }

    private Transaction[] getCorrectArray(Transaction[] invalidTransactions){

        Transaction[] correctArray;

        int size = 0;
        while (invalidTransactions[size] != null){
            size++;
        }
        correctArray = new Transaction[size];

        for (int i = 0; i < size; i++) {
            correctArray[i] = invalidTransactions[i];
        }

        return correctArray;
    }

}
