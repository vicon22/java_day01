package com.eveiled.ex02;

import java.util.Iterator;

public class UsersArrayList implements UsersList {

    private int LENGTH_OF_ARRAY = 10;
    private User[] usersArray = new User[LENGTH_OF_ARRAY];
    private int numberOfUsers = 0;

    @Override
    public void add(User user) {
        if (numberOfUsers >= LENGTH_OF_ARRAY) {
            usersArray = increaseArray(usersArray);
        }
        usersArray[numberOfUsers] = user;
        numberOfUsers++;
    }

    @Override
    public User getById(int id) {

        User findUser = null;
        for (int i = 0; i < numberOfUsers; i++) {
            if (usersArray[i].getIdentifier() == id)
                findUser = usersArray[i];
        }
        if (findUser == null){
            throw new UserNotFoundException("non-existent ID");
        }
        return (findUser);
    }

    @Override
    public User get(int index) {
        User findUser = null;
        if (index < numberOfUsers)
            findUser = usersArray[index];
        if (findUser == null){
            throw new UserNotFoundException("Index " + index + " out of bounds for length " + LENGTH_OF_ARRAY);
        }
        return (findUser);
    }

    @Override
    public int size() {
        return numberOfUsers;
    }


    private User[] increaseArray(User[] array){

        User[] expandedArray;

        LENGTH_OF_ARRAY = LENGTH_OF_ARRAY * 2;
        expandedArray = new User[LENGTH_OF_ARRAY];
        for (int i = 0; i < array.length; i++) {
            expandedArray[i] = array[i];
        }

        return (expandedArray);
    }

    @Override
    public Iterator<User> iterator() {
        return new Iterator<User>() {

            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < numberOfUsers;
            }

            @Override
            public User next() {
                return usersArray[currentIndex++];
            }
        };
    }
}
