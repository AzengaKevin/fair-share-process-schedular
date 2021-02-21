package com.anonymous;

import com.anonymous.models.Process;
import com.anonymous.models.User;
import com.anonymous.utils.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static List<String> outputList = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();

    private static final String inputFilename = "input.txt";
    private static final String outputFilename = "output.txt";

    public static void main(String[] args) {

        try {

            //Get file contents
            List<String> inputLinesList = FileHandler.readFile(inputFilename);

            //inputLinesList.forEach(System.out::println);

            int quantumTime = Integer.parseInt(inputLinesList.remove(0));

            User tempUserInstance = null;

            for (String line : inputLinesList) {
                String[] parts = line.split(" ");

                if (Character.isAlphabetic(parts[0].trim().charAt(0))) {

                    if (tempUserInstance != null) {

                        userList.add(tempUserInstance);

                    }

                    tempUserInstance = new User(parts[0].trim(), Integer.parseInt(parts[1]));

                } else {

                    if (tempUserInstance != null) {

                        tempUserInstance.addProcess(
                                new Process(
                                        tempUserInstance.getUid(),
                                        Integer.parseInt(parts[0]),
                                        Integer.parseInt(parts[1])
                                )
                        );
                    }
                }
            }

            //Add the last user instance to the list
            userList.add(tempUserInstance);

            //Get the size of of the user
            int userCount = userList.size();

            //Set quantum for user and in turn the processes
            userList.forEach(user -> user.setQuantumTime((double) quantumTime / userCount));

            //Debug Users List
            //userList.forEach(System.out::println);

            //Debug Processes
            //userList.forEach(user -> user.getProcessList().forEach(System.out::println));
            for (int i = 0; i < 2; i++) {
                userList.forEach(user -> {
                    Thread thread = new Thread(user);

                    thread.start();

                    try {
                        thread.join();

                    } catch (InterruptedException e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                });
            }

            if (FileHandler.writeToFile(outputList, outputFilename, false)) {
                System.out.println("Operation successful, check output.txt");
            }else{
                System.err.println("Oops !, something went wrong, debug or page someone");
            }


        } catch (Exception e) {

            System.err.println(e.getLocalizedMessage());

            if (e instanceof NumberFormatException) {
                System.err.println("Invalid file format");
            }
        }

    }
}
