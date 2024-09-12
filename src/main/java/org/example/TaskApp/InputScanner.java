package org.example.TaskApp;

import java.util.Scanner;

public class InputScanner {
    private Scanner scanner;

    public InputScanner() {
        this.scanner = new Scanner(System.in);
    }

    public String getStringInput(String printString){
        System.out.println(printString);
        return scanner.nextLine();
    }
}
