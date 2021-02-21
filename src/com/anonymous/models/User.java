package com.anonymous.models;

import java.util.ArrayList;
import java.util.List;

public class User implements Runnable {

    private String uid;

    private double quantumTime;
    private double processCount;

    private List<Process> processList;

    public User(String uid, int processCount) {

        this.uid = uid;
        this.processCount = processCount;

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
//            try {
//                thread.join();
//
//            } catch (InterruptedException e) {
//                System.err.println(e.getLocalizedMessage());
//            }
        });
    }

    public String getUid() {
        return uid;
    }

    public void addProcess(Process process) {

        processList.add(process);

    }

    public List<Process> getProcessList() {
        return processList;
    }

    public void setQuantumTime(double quantumTime) {
        this.quantumTime = quantumTime;

        processList.forEach(process -> process.setQuantumTime(quantumTime / processCount));
    }

    @Override
    public String toString() {
        return String.format("User %s, Process Count : %d", uid, processList.size());
    }
}
