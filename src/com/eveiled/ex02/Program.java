package com.eveiled.ex02;

import java.lang.reflect.Field;

public class Program {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        int index;
        User testUser;

        User u0 = new User( "u0", 1000);
        User u1 = new User("u1", 1000);
        User u2 = new User( "u2", 1000);
        User u3 = new User( "u3", 10000);
        User u4 = new User("u4", 3000);
        User u5 = new User( "u5", 7000);
        User u6 = new User( "u6", 10000);
        User u7 = new User("u7", 3000);
        User u8 = new User( "u8", 7000);
        User u9 = new User( "u9", 10000);
        User u10 = new User("u10", 3000);
        User u11 = new User( "u11", 7000);

        UsersList usersList = new UsersArrayList();

        usersList.add(u0);
        usersList.add(u1);
        usersList.add(u2);
        usersList.add(u3);
        usersList.add(u4);
        usersList.add(u5);
        usersList.add(u6);
        usersList.add(u7);
        usersList.add(u8);
        usersList.add(u9);
        findOutSizeOfArray(usersList);
        usersList.add(u10);
        findOutSizeOfArray(usersList);
        usersList.add(u11);

        System.out.println("size of usersList: " + usersList.size());


        index = 10;
        System.out.println("Retrieve by " + index +  " Index: " + usersList.get(index).getName());


        testUser = u7;
        System.out.println(testUser + " has id " + testUser.getIdentifier());
        System.out.println("Retrieve by " + testUser.getIdentifier() + " id: " + usersList.getById(testUser.getIdentifier()).getName());

        try{
            System.out.println("\nLets throw UserNotFoundException by non-existent ID");
            usersList.getById(100);
        }catch (UserNotFoundException e){
            System.out.println(e);
        }

        try{
            System.out.println("\nLets throw UserNotFoundException by index over bounds");
            usersList.get(100);
        }catch (UserNotFoundException e){
            System.out.println(e);
        }

        for (User user : usersList) {
            System.out.println(user);
        }

    }

    private static void findOutSizeOfArray(UsersList usersList) throws NoSuchFieldException, IllegalAccessException {
        Field field = usersList.getClass().getDeclaredField("LENGTH_OF_ARRAY");
        field.setAccessible(true);
        System.out.println("LENGTH_OF_ARRAY: " + field.get(usersList));
        field.setAccessible(false);
    }


}

