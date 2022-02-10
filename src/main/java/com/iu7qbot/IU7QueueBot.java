package com.iu7qbot;

import java.util.function.Function;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;


public class IU7QueueBot extends LongPollBot
{

    @Override
    public void onMessageNew(MessageNew messageNew) {
        Message message = messageNew.getMessage();
        String textMessage = message.getText();

        String[] args = textMessage.split("\\s+");
        String response = BotActions.generateHelp();

        try {
            var sender = vk.users.get()
                .setUserIds(message.getFromId())
                .execute()
                .getResponseObject()
                .get(0);
        
            String surname = sender.getFirstName();
            String name = sender.getLastName();

            if (args.length > 1) {
                String command = args[0];
                if (command.equals("/info")) {
                    response = typeChecker(args, BotActions::showQueue);
                } else if (command.equals("/today")) {
                    response = typeChecker(args, BotActions::showSchedule);
                } else if (command.equals("/queue")) {
                    response = typeChecker(args, t ->
                        BotActions.enrollQueue(t, surname, name));
                } else if (command.equals("/done")) {
                    response = typeChecker(args, t ->
                        BotActions.enrollSchedule(t, surname, name));
                } else if (command.equals("/def")) {
                    response = typeChecker(args, t ->
                        BotActions.popQueue(t, surname, name));
                }
            }
        } catch (VkApiException e) {
            response = "Хто ты?!";
        }

        vk.messages.send()
            .setPeerId(message.getPeerId())
            .setMessage(response)
            .executeAsync();
    }

    @Override
    public String getAccessToken() {
        return System.getenv("QBOT");
    }

    @Override
    public int getGroupId() {
        return Integer.parseInt(System.getenv("GID"));
    }

    private String typeChecker(String[] args, Function<String, String> func) {
        if (args.length < 2 || !args[1].matches("CG|OOP|MZYAP")) {
            return "Выбери очередь (CG/OOP/MZYAP)";
        } else {
            return func.apply(args[1]);
        }
    }

    public static void main(String[] args) throws VkApiException {
        new IU7QueueBot().startPolling();
    }
}
