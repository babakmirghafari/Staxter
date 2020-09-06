package com.staxter.Player;

import com.staxter.Mediator.MessageMediator;

public abstract class Player {

    protected MessageMediator messageMediator;
    public String name;

    public Player(MessageMediator messageMediator, String name){
        this.messageMediator=messageMediator;
        this.name=name;
    }

    public abstract void sendMessage(String message,Integer counter,Player receivePlayer,Player sendPlayer);
    public abstract void receiveMessage(String message,Player receivePlayer,Integer counter);
}
