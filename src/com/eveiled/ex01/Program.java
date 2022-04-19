package com.eveiled.ex01;

public class Program {

    public static void main(String[] args) {

        User john = new User( "John", 1000);
        User tom = new User("Tom", 1000);
        User barbara = new User( "Barbara", 1000);

        System.out.printf("Is it the same object? %s\n", UserIdsGenerator.getInstance() == UserIdsGenerator.getInstance());
        System.out.println("Calling method 'generateId':");
        System.out.println(UserIdsGenerator.getInstance().generateId());
        System.out.println(UserIdsGenerator.getInstance().generateId());

        User lonelyMonica = new User("lonely Monica", 1000);
        User relaxedGeorge = new User( "relaxed George", 1000);

        System.out.println(john.getName() + " " + john.getIdentifier());
        System.out.println(tom.getName() + " " + tom.getIdentifier());
        System.out.println(barbara.getName() + " " + barbara.getIdentifier());
        System.out.println(lonelyMonica.getName() + " " + lonelyMonica.getIdentifier());
        System.out.println(relaxedGeorge.getName() + " " + relaxedGeorge.getIdentifier());
    }
}
