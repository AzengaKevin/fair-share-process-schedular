package com.anonymous.models;

public class Process implements Runnable {

    int pid;

    int serviceTime;
    int readyTime;
    int time;
    private String user;
    private double quantumTime;

    private static int processCount = 0;

    public Process(String user, int readyTime, int serviceTime) {

        processCount++;

        pid = processCount;

        this.user = user;

        this.readyTime = readyTime;

        this.serviceTime = serviceTime;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {

        System.out.printf("Time %d, User %s, Process %d, Started\n", 1, user, pid);
        System.out.printf("Time %d, User %s, Process %d, Ready\n", 1, user, pid);

        try {

            Thread.sleep((long) quantumTime * 1000);

            System.out.printf("Time %d, User %s, Process %d, Paused\n", 1, user, pid);

        } catch (InterruptedException e) {

            System.err.println(e.getLocalizedMessage());

        }

    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPid() {
        return pid;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getReadyTime() {
        return readyTime;
    }

    public int getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }

    public double getQuantumTime() {
        return quantumTime;
    }

    public void setQuantumTime(double quantumTime) {
        this.quantumTime = quantumTime;
    }

    public static int getProcessCount() {
        return processCount;
    }

    @Override
    public String toString() {
        return String.format("Process(id = %d, ready = %s, service = %s, quantum = %.2f)", pid, readyTime, serviceTime, quantumTime);
    }
}
