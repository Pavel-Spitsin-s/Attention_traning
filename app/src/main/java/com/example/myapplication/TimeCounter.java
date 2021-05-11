package com.example.myapplication;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter {
    static int interval;
    static Timer timer;
    static String secs_to_output;

    public static void run(String secs) {
        Scanner sc = new Scanner(System.in);
        secs_to_output = secs;
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                secs_to_output = new Integer(setInterval()).toString();

            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
}