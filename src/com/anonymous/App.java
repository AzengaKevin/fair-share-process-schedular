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

            for (int i = 0; i < inputLinesList.size(); i++) {
                String line = inputLinesList.get(i);

                String[] parts = line.split(" ");

                if (Character.isAlphabetic(parts[0].trim().charAt(0))) {

                    if (tempUserInstance != null) {

                        userList.add(tempUserInstance);
                        tempUserInstance = null;

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

            userList.add(tempUserInstance);

            userList.forEach(System.out::println);

        } catch (Exception e) {

            System.err.println(e.getLocalizedMessage());

            if (e instanceof NumberFormatException) {
                System.err.println("Invalid file format");
            }
        }

    }
}
