package com.staxter.Player;

import com.staxter.Mediator.MessageMediator;

public class PlayerImpl extends Player {
    public PlayerImpl(MessageMediator messageMediator, String name) {
        super(messageMediator, name);
    }

    @Override
    public void sendMessage(String message,Integer counter,Player receivePlayer,Player sendPlayer) {
        System.out.println(this.name+" Sending Message:\t\033[32m"+message+" - "+counter+"\033[0m");
        messageMediator.sendMessage(message,receivePlayer,sendPlayer,counter);
    }

    @Override
    public void receiveMessage(String message,Player receivePlayer,Integer counter) {
        System.out.println(receivePlayer.name+" Receive Message:\t\033[32m"+message+" - "+counter+"\033[0m");
    }
}
