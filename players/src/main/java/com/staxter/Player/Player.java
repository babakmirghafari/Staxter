package com.staxter.Player;

import com.staxter.Mediator.MessageMediator;

/***
 * Each player have 2 properties:
 * 1- all of players can communicate to each other with {@link MessageMediator}, so each player should be define it's messageMediator
 * 2- a name, with this name can introduce itself in messageMediator.
 */
public abstract class Player {

    protected MessageMediator messageMediator;
    public String name;

    /**
     * properties initiated when a player instantiated.
     * @param messageMediator
     * @param name
     */
    public Player(MessageMediator messageMediator, String name){
        this.messageMediator=messageMediator;
        this.name=name;
    }

    /**
     * initiator use this method to send message , they use messageMediator to connect to other players.
     * @param message
     * @param counter
     * @param receivePlayer== base on my design, each player should know target player. this player is initiators player target.
     * @param sendPlayer== this use by receiver player, when want to send response to initiator,should be know , who sent this message.
     */
    public abstract void sendMessage(String message,Integer counter,Player receivePlayer,Player sendPlayer);

    /**
     * receiver players use this method to send confirm to initiator.
     * @param message== original message , that sent from initiator.it will be change in response message.
     * @param receivePlayer
     * @param counter
     */
    public abstract void receiveMessage(String message,Player receivePlayer,Integer counter);
}
