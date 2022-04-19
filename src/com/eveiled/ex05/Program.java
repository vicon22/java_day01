package com.eveiled.ex05;

import com.eveiled.ex05.transactionServise.TransactionsService;

public class Program {

    public static void main(String[] args) {

        Menu menu = new Menu(new TransactionsService());

        if(args.length != 0 && args[0].equals("--profile=dev")) {
            menu.startDevMode();
        }else{
            menu.start();
        }
    }
}

