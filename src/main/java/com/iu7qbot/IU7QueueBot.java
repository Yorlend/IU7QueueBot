package com.iu7qbot;

import java.io.IOException;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class IU7QueueBot {
    
    public static void main(String[] args) {
        CleanScheduler.run();
        
        TelegramBot bot = new TelegramBot(System.getenv("TGTOKEN"));

        bot.setUpdatesListener(updates -> {

            for (var update : updates) {
                try {
                    String command = update.message().text();
                    long id = update.message().from().id();
    
                    String response = BotController.handleMessage(command, id);
    
                    SendMessage msg = new SendMessage(update.message().chat().id(), response);
    
                    bot.execute(msg, new Callback<SendMessage, SendResponse>() {
    
                        @Override
                        public void onResponse(SendMessage request, SendResponse response) {
                            
                        }
    
                        @Override
                        public void onFailure(SendMessage request, IOException e) {
                            
                            e.printStackTrace();
                        }
                        
                    });
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

        
    }
} 


// public class IU7QueueBot extends LongPollBot {

//     @Override
//     public void onMessageNew(MessageNew messageNew) {
//         Message message = messageNew.getMessage();
//         String textMessage = message.getText();

//         String response = "";

//         try {
//             var sender = vk.users.get()
//                 .setUserIds(message.getFromId())
//                 .execute()
//                 .getResponseObject()
//                 .get(0);
        
//             String surname = sender.getLastName();
//             String name = sender.getFirstName();

//             response = BotController.handleMessage(textMessage, name, surname);
    
//         } catch (VkApiException e) {
//             response = "Хто ты?!";
//         } catch (Exception e) {
//             response = "Хто я?";
//         }

//         vk.messages.send()
//             .setPeerId(message.getPeerId())
//             .setMessage(response)
//             .executeAsync();
//     }

//     @Override
//     public String getAccessToken() {
//         return System.getenv("QBOT");
//     }

//     @Override
//     public int getGroupId() {
//         return Integer.parseInt(System.getenv("GID"));
//     }

//     public static void main(String[] args) {
//         CleanScheduler.run();

//         while (true) {
//             try {
//                 new IU7QueueBot().startPolling();
//             } catch (VkApiException e) {
//                 e.printStackTrace();
//                 if (!e.getMessage().contains("{\"failed\":2}")) {
//                     System.out.println();
//                     break;
//                 }
//             }

//             // sleep for 10 secs
//             try {
//                 Thread.sleep(10_000);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//                 break;
//             }
//         }
//     }
// }
