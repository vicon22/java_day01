package com.eveiled.ex03;

public class UserIdsGenerator {
    private int id = 0;

    private static UserIdsGenerator instance;
    private UserIdsGenerator(){}
    public static UserIdsGenerator getInstance()
    {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }
    public int generateId(){
        id++;
        return (id);
    }
}