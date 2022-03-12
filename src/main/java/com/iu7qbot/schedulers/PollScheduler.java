package com.iu7qbot.schedulers;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.iu7qbot.IU7QueueBot;

import api.longpoll.bots.exceptions.VkApiException;

public class PollScheduler {
    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static IU7QueueBot qbot;

    private static ScheduledFuture<?> taskHandle;

    public static void run() {
        Runnable task = () -> {
            if (qbot != null) {
                qbot.stopPolling();
            }
            
            qbot = new IU7QueueBot();

            System.out.println("Polling server has been reset.");
            try {
                qbot.startPolling();
            } catch(VkApiException e) {
                e.printStackTrace();
            }
        };

        long remaining = 0;

        if (taskHandle == null) {
            taskHandle = scheduler.scheduleAtFixedRate(task, remaining, 24 * 60, TimeUnit.MINUTES);
        }
    }
}
