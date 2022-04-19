package com.eveiled.ex05.transaction;

import java.util.UUID;

public interface TransactionsList extends Iterable<Transaction>{

    void add(Transaction transaction);
    void remove(UUID id);
    Transaction[] toArray();

}

