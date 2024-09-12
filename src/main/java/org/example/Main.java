package org.example;

import org.example.TaskApp.AppInterface;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        AppInterface test1= new AppInterface("TODO");
        test1.Run();
        System.out.println(test1.getUserHashMap().toString());

    }
}