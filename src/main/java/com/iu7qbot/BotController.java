package com.iu7qbot;

import java.util.function.Function;

public class BotController {
    public static String handleMessage(String message, String senderFirstName, String senderLastName) {
        String[] args = message.split("\\s+");
        String response = "";

        if (message.startsWith("/")) {
            response = BotActions.generateHelp();
        }

        if (args.length > 1) {
            String command = args[0];
            if (command.equals("/info")) {
                response = typeChecker(args, BotActions::showQueue);
            } else if (command.equals("/today")) {
                response = typeChecker(args, BotActions::showSchedule);
            } else if (command.equals("/queue")) {
                response = typeChecker(args, t ->
                    BotActions.enrollQueue(t, senderLastName, senderFirstName));
            } else if (command.equals("/done")) {
                response = typeChecker(args, t ->
                    BotActions.enrollSchedule(t, senderLastName, senderFirstName));
            } else if (command.equals("/def")) {
                response = typeChecker(args, t ->
                    BotActions.popQueue(t, senderLastName, senderFirstName));
            }
        }

        return response;
    }

    private static String typeChecker(String[] args, Function<String, String> func) {
        if (args.length < 2 || !args[1].toLowerCase().matches("cg|oop|asm|ca|evm")) {
            return "Выбери очередь (cg/oop/asm/ca/evm)";
        } else {
            return func.apply(args[1].toUpperCase());
        }
    }
}
