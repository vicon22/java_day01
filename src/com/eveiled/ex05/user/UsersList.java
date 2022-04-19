package com.eveiled.ex05.user;

public interface UsersList extends Iterable<User> {

    void add(User user);
    User getById(int id);
    User get(int index);
    int size();
}
