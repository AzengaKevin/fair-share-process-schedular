package com.anonymous.models;

import java.util.ArrayList;
import java.util.List;

public class User implements Runnable {

    private String uid;

    private List<Process> processList;

    public User(String uid, int processCount) {

        this.uid = uid;

        processList = new ArrayList<>(processCount);

    }

    @Override
    public void run() {
        executeProcesses();
    }

    private void executeProcesses() {

        processList.forEach(process -> {
            Thread thread = new Thread(process);
            thread.start();
            try {
                thread.join();

            } catch (InterruptedException e) {
                System.err.println(e.getLocalizedMessage());
            }
        });
    }

    public String getUid() {
        return uid;
    }

    public void addProcess(Process process) {

        processList.add(process);

    }

    @Override
    public String toString() {
        return String.format("User %s, Process Count : %d", uid, processList.size());
    }
}
