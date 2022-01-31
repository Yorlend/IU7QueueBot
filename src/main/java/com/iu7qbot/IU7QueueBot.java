package com.iu7qbot;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;


public class IU7QueueBot extends LongPollBot
{

    @Override
    public void onMessageNew(MessageNew messageNew) {
        try {
            Message message = messageNew.getMessage();
            if (message.getText().equalsIgnoreCase("!queue")) {
                String response = "Received command: " + message.getText();
                vk.messages.send()
                        .setPeerId(message.getPeerId())
                        .setMessage(response)
                        .execute();
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

    public static void main( String[] args ) throws VkApiException
    {
        QueueDBHandler.connect();
        // new IU7QueueBot().startPolling();
    }
}
