package com.iu7qbot;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.iu7qbot.dao.schedule.ScheduleDAO;
import com.iu7qbot.dao.schedule.ScheduleDAOImpl;

public class CleanScheduler {
    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final static ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

    private static ScheduledFuture<?> taskHandle;

    public static void run() {
        Runnable task = () -> {
            System.out.println("Schedule has been reset.");
            try {
                scheduleDAO.resetSchedule();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };

        var localZoneId = ZoneId.of("Europe/Moscow");
        var now = ZonedDateTime.now(localZoneId);
        var nineOClock = LocalTime.of(21, 0, 0).atDate(now.toLocalDate()).atZone(localZoneId);

        long remaining = ChronoUnit.MINUTES.between(now, nineOClock);
        if (remaining < 0) {
            remaining += 24 * 60;
        }

        if (taskHandle == null) {
            taskHandle = scheduler.scheduleAtFixedRate(task, remaining, 24 * 60, TimeUnit.MINUTES);
        }
    }
}
