package com.iu7qbot;

import com.iu7qbot.schedulers.CleanScheduler;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;


public class IU7QueueBot extends LongPollBot {

    @Override
    public void onMessageNew(MessageNew messageNew) {
        Message message = messageNew.getMessage();
        String textMessage = message.getText();

        String response = "";

        try {
            var sender = vk.users.get()
                .setUserIds(message.getFromId())
                .execute()
                .getResponseObject()
                .get(0);
        
            String surname = sender.getLastName();
            String name = sender.getFirstName();

            response = BotController.handleMessage(textMessage, name, surname);
    
        } catch (VkApiException e) {
            response = "Хто ты?!";
        } catch (Exception e) {
            response = "Хто я?";
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

    public static void main(String[] args) {
        CleanScheduler.run();

        while (true) {
            try {
                new IU7QueueBot().startPolling();
            } catch (VkApiException e) {
                e.printStackTrace();
                if (!e.getMessage().contains("{\"failed\":2}")) {
                    System.out.println();
                    break;
                }
            }

            // sleep for 10 secs
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
