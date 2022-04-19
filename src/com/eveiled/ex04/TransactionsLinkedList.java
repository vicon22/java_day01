package com.eveiled.ex04;

import java.util.Iterator;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{

    private int size = 0;
    private Node first;
    private Node last;

    @Override
    public void add(Transaction transaction) {
        Node current = new Node(transaction);
        if (first == null){
            first = current;
            last = current;
        }else{
            last.next = current;
            current.prev = last;
            last = current;
        }
        size++;
    }

    @Override
    public void remove(UUID id) {
        Node tmp = first;
        Node removingNode = null;

        while (tmp != null){
            if (tmp.item.getId().compareTo(id) == 0){
                removingNode = tmp;
            }
            tmp = tmp.next;
        }
        if (removingNode == null){
            throw new TransactionNotFoundException("Transaction with UUID: " + id + " not found");
        }
        else{
            if (removingNode == first)
            {
                first = first.next;
                size--;
            }
            else if (removingNode == last)
            {
                last = last.prev;
                last.next = null;
                size--;
            }
            else
            {
                Node prev = removingNode.prev;
                Node next = removingNode.next;
                if (prev != null && next != null) {
                    prev.next = next;
                    next.prev = prev;
                }
                size--;
            }
        }
    }

    @Override
    public Transaction[] toArray() {

        Transaction[] transactions = new Transaction[size];
        Node tmp = first;

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                transactions[i] = tmp.item;
                tmp = tmp.next;
            }
        }
        return (transactions);
    }

    @Override
    public Iterator<Transaction> iterator() {
        return new Iterator<Transaction>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Transaction next()  throws IndexOutOfBoundsException {
                Transaction result = current.item;
                current = current.next;
                return result;
            }
        };
    }

    private class Node{

        Transaction item;
        Node next = null;
        Node prev = null;

        public Node(Transaction item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Node(Transaction item) {
            this.item = item;
        }
    }
}
