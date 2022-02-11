package com.iu7qbot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.iu7qbot.dao.schedule.ScheduleDAO;

public class CleanScheduler {
    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final static ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

    public static void run() {
        var task = () -> {
            System.out.println("Schedule has been reset.");
            scheduleDAO.resetSchedule();
        };

        LocalDateTime now = LocalDateTime.now();
        var nineOClock = LocalTime.of(21, 0, 0).atDate(now.toLocalDate());
        Duration delta = Duration.between(now, nineOClock);

        long remaining = ChronoUnit.MINUTES.between(now, nineOClock);
        if (remaining < 0) {
            remaining += 24 * 60;
        }

        var taskHandle = scheduler.scheduleAtFixedRate(task, remaining, 24 * 60, TimeUnit.MINUTES);
    }
}
