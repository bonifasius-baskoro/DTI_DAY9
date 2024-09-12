package org.example.TaskApp;

import java.util.ArrayList;

public class User {
    String ID;
    String userName;

    String passWord;
    TaskList taskList;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.taskList = new TaskList();
    }

    public String getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void printTask() {
        this.taskList.printTask();
    }

    public void addTask(String taskName){
        this.taskList.addTask(taskName);
    }

    public int taskLength(){
        return this.taskList.taskList.size();
    }

    public void deleteTask(int indexTask){
        this.taskList.taskList.remove(indexTask);
        System.out.println("Task sucessfully deleted");
    }




    private class TaskList {
        protected  ArrayList<String> taskList;
        protected ArrayList<String> completeTask;

        protected TaskList() {
            this.taskList = new ArrayList<>();
            this.completeTask= new ArrayList<>();
        }

        protected void printTask(){
            System.out.println("Task:");
            for(int i = 0; i < taskList.size();i++){
                System.out.println(i+1 + ". "+taskList.get(i));
            }
            System.out.println("Task Completed:");
            for(int i = 0; i < completeTask.size();i++){
                System.out.println(i+1 + ". "+completeTask.get(i));
            }
        }
        protected void addTask(String taskName){
            System.out.println("Task Added");
            this.taskList.add(taskName);
        }

    }
}
