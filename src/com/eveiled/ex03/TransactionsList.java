package com.eveiled.ex03;

import java.util.UUID;

public interface TransactionsList extends Iterable<Transaction>{

    void add(Transaction transaction);
    void remove(UUID id);
    Transaction[] toArray();


}
