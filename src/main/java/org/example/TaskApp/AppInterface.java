package org.example.TaskApp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AppInterface {
    Set<String> userSet = new HashSet<String>();
    HashMap<String, User> userHashMap= new HashMap<>();
    String appName;
    InputScanner inputScanner;

    public AppInterface(String appName) {
        this.appName = appName;
        this.userSet = new HashSet<>();
        this.userHashMap= new HashMap<>();
        this.inputScanner= new InputScanner();
        System.out.println("Hi This is task App!");
    }

    public HashMap<String, User> getUserHashMap() {
        return userHashMap;
    }

    public boolean Run(){
        Scanner inputUser = new Scanner(System.in);
        boolean runTime= true;

        while(runTime){
            String inputVariable = new String();
            int inputInt;
            System.out.println("Please choose :");
            System.out.println("1. Log in ");
            System.out.println("2. Register");
            System.out.println("3. Quit");
            String optionInput = inputScanner.getStringInput("Input the number for option: ");

            try {
                int parseInput = Integer.parseInt(optionInput);
            }catch (NumberFormatException e){
                System.out.println("Invalid input please just use number");
                continue;
            }
            inputInt = Integer.parseInt(optionInput);
            switch (inputInt){
                case 1:
                    System.out.println("");
                    //if true use userInterface ( User) pass User class
                    User userUsed = this.loginInterface();
                    try {
                        if(userUsed == null){
                            break;
                        }
                        this.userInterface(userUsed);
                    }catch (NullPointerException e){
                        System.out.println("Failed");
                    }
                    break;
                case 2:
                    System.out.println("Let's register your account");
                    this.registerAccount();
                    break;
                case 3:
                    System.out.println("3");
                    runTime=false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }


        }
        return false;
    }

    public void registerAccount(){
        boolean runTime = true;
        while(runTime){
            String userNameInput = inputScanner.getStringInput("Input username:");
            if(userSet.contains(userNameInput)){
                System.out.println("The username has been registered please use other username");
                continue;
            }
            else{
                boolean passRun= true;
                String passwordInput = "";
                while(passRun) {
                     passwordInput = inputScanner.getStringInput("Input password");
                    if (passwordInput.length() < 6) {
                        System.out.println("The password length must be longer than 6 characters");
                        continue;
                    } else {
                        passRun = false;
                        break;
                    }
                }
                userSet.add(userNameInput);
                User userRegister = new User(userNameInput,passwordInput);
                userHashMap.put(userNameInput,userRegister);
                System.out.println("Register is completed! please Log in to use your account");
                runTime=false;
                break;

                }



            }
    }

    public User loginInterface(){
        boolean runLoginInterface = true;
        while(runLoginInterface){
            String userNameInput = inputScanner.getStringInput("Input your username here:");
            String passwordInput = inputScanner.getStringInput("Input your password here:");
            if(logInAuthentication(userNameInput,passwordInput)){
                return userHashMap.get(userNameInput);

            }
            else{
                String continueInput = inputScanner.getStringInput("do you want to continue (y/n):");
                if(continueInput.equals("y")){
                    continue;
                }else{
                    break;
                }
            }
        }
        return null;
    }
    public boolean logInAuthentication(String username, String password) {
        if (!this.userSet.contains(username)) {
            System.out.println("Username has not been registered!");
            return false;
        }
        String passwordToCompare = this.userHashMap.get(username).getPassWord();
        if (passwordToCompare.equals(password)) {
            return true;
        } else {
            return false;

        }
    }

    public void userInterface(User username){
        boolean runUserInterface = true;
        while(runUserInterface){
            System.out.println("Choose what you want to do");
            System.out.println("1. Show Task");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark As Complete");
            System.out.println("5. Quit");
            String optionInput = inputScanner.getStringInput("Please choose the option");
            try {
                int parseInput = Integer.parseInt(optionInput);
            }catch (NumberFormatException e){
                System.out.println("Invalid input please just use number");
                continue;
            }
            int intOptionInput =Integer.parseInt(optionInput); ;
            switch (intOptionInput){
                case 1:
                    username.printTask();
                    break;
                case 2:
                    System.out.println("Let's add task");
                    boolean addTaskRun = true;
                    while(addTaskRun){
                        String inputTask =inputScanner.getStringInput("Task name you want to add or type exit if you want to get out:");
                        if(inputTask.isBlank()){
                            System.out.println("please Insert task name");
                            continue;
                        } else if(inputTask.equals("exit")){
                            System.out.println("Exit the add task menu");
                            addTaskRun=false;
                            break;
                        }
                        else{
                            username.addTask(inputTask);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Delete task-----");
                    this.deleteTaskInterface(username);
                    break;
                case 4:
                    System.out.println("Mark Task As Complete");
                    this.markCompleteTaskInterface(username);
                    break;

                default:
                    System.out.println("Wrong input");
                    runUserInterface = false;
                    break;
            }

        }
    }

    public void deleteTaskInterface(User username){
        boolean deleteTaskRun = true;
        while(deleteTaskRun){
            if(username.taskLength()==0){
                System.out.println("There is no task to be deleted.");
                break;
            }
            username.printTask();
            String inputTask =inputScanner.getStringInput("Please give number of task you want to delete or type exit if you want to get out:");
            try {
                int intInputTask = Integer.parseInt(inputTask);
                if (intInputTask<1 || intInputTask>username.taskLength()) {
                    System.out.println("Wrong input, please input the right task");
                    continue;
                }
                else{
                    username.deleteTask(intInputTask-1);
                    continue;
                }
            }catch (NumberFormatException e){
                System.out.println("log : not a number input");
            }finally {
                if(inputTask.isBlank()){
                    System.out.println("please Insert task name or type exit");
                    continue;
                } else if(inputTask.equals("exit")){
                    System.out.println("Exit the add task menu");
                    deleteTaskRun=false;
                    break;
                }
            }

        }
    }

    public void markCompleteTaskInterface(User username){
        boolean markCompleteTaskRun = true;
        while(markCompleteTaskRun){
            if(username.taskLength()==0){
                System.out.println("There is no task to be marked.");
                break;
            }
            username.printTask();
            String inputTask =inputScanner.getStringInput("Please give number of task you want to mark as complete or type exit if you want to get out:");
            try {
                int intInputTask = Integer.parseInt(inputTask);
                if (intInputTask<1 || intInputTask>username.taskLength()) {
                    System.out.println("Wrong input, please input the right option using number in the task list");
                    continue;
                }
                else{
                    username.markCompleteTask(intInputTask-1);
                    continue;
                }
            }catch (NumberFormatException e){
                System.out.println("log : not a number input");
            }finally {
                if(inputTask.isBlank()){
                    System.out.println("please Insert task name or type exit");
                    continue;
                } else if(inputTask.equals("exit")){
                    System.out.println("Exit the add task menu");
                    markCompleteTaskRun=false;
                    break;
                }
            }

        }
    }

}


