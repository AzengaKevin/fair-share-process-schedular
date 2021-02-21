package com.anonymous.utils;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {

    /**
     * Reads the content of a file line by line
     *
     * @param filename the path of the file to be read
     * @return List<String> of the lines of the file if available else and empty list
     */
    public static List<String> readFile(String filename) {

        try {

            BufferedReader in = new BufferedReader(
                    new FileReader(
                            new File(filename)
                    )
            );

            return in.lines().map(String::trim).collect(Collectors.toList());

        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }

        return Collections.emptyList();
    }

    /**
     * Write content to file
     *
     * @param contentInLines the lines of content to be written to the file
     * @param filename       the file to write to
     * @param append         whether you should append to the file
     * @return Whether content was successfully written to the file
     */
    public static boolean writeToFile(List<String> contentInLines, String filename, boolean append) {

        try (
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(
                                        new File(filename), append
                                )
                        )
                );
        ) {

            contentInLines.forEach(out::println);

            return true;

        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());

            return false;
        }
    }

}
