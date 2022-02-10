package com.iu7qbot;

import java.util.concurrent.CompletableFuture;

import com.iu7qbot.db.QueueDBHandler;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;


public class IU7QueueBot extends LongPollBot
{

    @Override
    public void onMessageNew(MessageNew messageNew) {
        try {
            Message message = messageNew.getMessage();
            String textMessage = message.getText();
            if (textMessage.startsWith("/help")) {
                CompletableFuture<Send.Response> future = vk.messages.send()
                        .setPeerId(message.getPeerId())
                        .setMessage(BotActions.generateHelp())
                        .executeAsync();
            }
            
            if (textMessage.startsWith("/info")) {
                var args = textMessage.split("\\s+");
                String response = "";

                if (args.length < 2 || !args[1].matches("CG|OOP|MZYAP")) {
                    response = "Выбери очередь (CG/OOP/MZYAP)";
                } else {
                    response
                }
            }
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccessToken() {
        return System.getenv("QBOT");
    }

    @Override
    public int getGroupId() {
        return Integer.parseInt(System.getenv("GID"));
    }
}
