package com.iu7qbot;

import api.longpoll.bots.exceptions.VkApiException;

public class Main {
    public static void main( String[] args ) throws VkApiException {
        new IU7QueueBot().startPolling();
    }
}
