package com.eveiled.ex00;

public class Program {

    public static void main(String[] args) {

        User john = new User(1, "John", 1000);
        User tom = new User(2, "Tom", 1000);
        User barbara = new User(3, "Barbara", 1000);
        User homelessNick = new User(3, "Homeless Nick", -480);

        System.out.println("==============================================");
        System.out.println(john);
        System.out.println(tom);
        System.out.println(barbara);
        System.out.println(homelessNick);
        //==================================================================
        Transaction tr1 = new Transaction(john, tom, 300);
        Transaction tr2 = new Transaction(barbara, tom, 150);
        Transaction tr3 = new Transaction(john, barbara, -350);
        Transaction tr4 = new Transaction(john, barbara, -3500);
        Transaction tr5 = new Transaction(john, tom, 3000);
        Transaction tr6 = new Transaction(john, tom, 550);
        Transaction tr7 = new Transaction(john, tom, -1500);
        Transaction tr8 = new Transaction(homelessNick, tom, 400);
        Transaction tr9 = new Transaction(tom, homelessNick, -400);
        System.out.println("==============================================");
        System.out.println(tr1);
        System.out.println(tr2);
        System.out.println(tr3);
        System.out.println("----------------------------------------------");
        System.out.println(tr4);
        System.out.println(tr5);
        System.out.println("----------------------------------------------");
        System.out.println(tr6);
        System.out.println(tr7);
        System.out.println("----------------------------------------------");
        System.out.println(tr8);
        System.out.println(tr9);
        System.out.println("==============================================");
        System.out.println(homelessNick);
        System.out.println(john);
        System.out.println(tom);
        System.out.println(barbara);
        System.out.println("==============================================");
    }
}
