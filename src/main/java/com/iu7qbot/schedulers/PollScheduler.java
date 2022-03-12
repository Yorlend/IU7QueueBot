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
    private IU7QueueBot qbot;

    private static ScheduledFuture<?> taskHandle;

    public PollScheduler(IU7QueueBot qbot) {
        this.qbot = qbot;
    }

    public void run() {
        Runnable task = () -> {
            System.out.println("Polling server has been reset.");
            try {
                qbot.startPolling();
            } catch(VkApiException e) {
                e.printStackTrace();
            }
        };

        var localZoneId = ZoneId.of("Europe/Moscow");
        var now = ZonedDateTime.now(localZoneId);
        var threeOClock = LocalTime.of(3, 0, 0).atDate(now.toLocalDate()).atZone(localZoneId);

        // long remaining = ChronoUnit.MINUTES.between(now, threeOClock);
        long remaining = 0;
        if (remaining < 0) {
            remaining += 24 * 60;
        }

        if (taskHandle == null) {
            taskHandle = scheduler.scheduleAtFixedRate(task, remaining, 24 * 60, TimeUnit.MINUTES);
        }
    }
}