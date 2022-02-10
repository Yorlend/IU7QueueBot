package com.iu7qbot;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.iu7qbot.dao.queue.Queue;
import com.iu7qbot.dao.queue.QueueDAO;
import com.iu7qbot.dao.queue.QueueDAOImpl;
import com.iu7qbot.dao.schedule.ScheduleDAO;
import com.iu7qbot.dao.schedule.ScheduleDAOImpl;
import com.iu7qbot.func.SqlCheckedConsumer;
import com.iu7qbot.func.SqlCheckedFunction;

public class BotActions {
    private final static QueueDAO queueDAO = new QueueDAOImpl();
    private final static ScheduleDAO scheduleDAO = new ScheduleDAOImpl();

    private final static String errorMessage = "Я поломався. Тыкни Клима, чтобы починил( Ему понравится (нет).";
    private final static String duplicate = "Вы уже в этой очереди.";

    public static String generateHelp() {
        return "Список команд:\n" +
            "1. /help -> выводит информацию о командах.\n" +
            "2. /info [CG/OOP/MZYAP] -> выводит очередь.\n" + 
            "3. /today [CG/OOP/MZYAP] -> очередь на сегодня.\n" +
            "4. /queue [CG/OOP/MZYAP] -> запись в очередь.\n" +
            "5. /done [CG/OOP/MZYAP] -> готов сдавать лабу.\n" +
            "6. /def [CG/OOP/MZYAP] -> защитился.";
    }

    public static String showQueue(String type) {
        return customShow(type, queueDAO::findStudents);
    }

    public static String showSchedule(String type) {
        return customShow(type, scheduleDAO::getSchedule);
    }
    
    public static String enrollQueue(String task, String surname, String name) {
        Queue student = new Queue(task, surname, name);
        return customEnroll(student, queueDAO::insertStudent);
    }

    public static String enrollSchedule(String task, String surname, String name) {
        Queue student = new Queue(task, surname, name);
        return customEnroll(student, scheduleDAO::insertSchedule);
    }

    public static String popQueue(String task, String surname, String name) {
        try {
            Queue student = new Queue(task, surname, name);
            queueDAO.removeStudent(student);

            return "Успешно удалён.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return errorMessage;
        }
    }

    private static String customEnroll(Queue student, SqlCheckedConsumer<Queue> consumer) {
        try {
            consumer.accept(student);

            return "Успешно добавлен.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return duplicate;
        }
    }

    private static String customShow(String type, SqlCheckedFunction<String, List<Queue>> func) {
        try {
            var students = func.apply(type);
            var sb = new StringBuilder();

            int i = 1;
            for (var student : students) {
                sb.append(String.format("%d: %s\n", i++, student));
            }

            return sb.toString();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return errorMessage;
        }
    }
}
