package com.eveiled.ex04;

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
