package com.anonymous.models;

public class Process implements Runnable {

    enum ProcessState {Ready, Started, Resumed, Finished}

    int pid;

    int serviceTime;
    int readyTime;

    @Override
    public void run() {

    }

    public static void execute() {

    }

}
