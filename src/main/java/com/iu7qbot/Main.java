package com.iu7qbot;

import api.longpoll.bots.exceptions.VkApiException;

public class Main {
    public static void main( String[] args ) {
        try {
            new IU7QueueBot().startPolling();
        } catch (VkApiException e) {
            e.getMessage();
        }
    }
}
