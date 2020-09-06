package com.staxter;

public class MessageCreator {

    public static void writeMessage(String message, Integer counter, Object player) {
        System.out.println(message.concat(counter.toString()));
    }
}
