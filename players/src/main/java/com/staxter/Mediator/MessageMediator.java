package com.staxter.Mediator;

import com.staxter.Player.Player;

/**
 * This interface create for as Mediator interface.This interface implement with an implementor class for
 * provide send,receive and start communication between players objects.
 */
public interface MessageMediator {

    /**
     * This method responsible for send message between objects.
     * @param message== this message send from sender
     * @param receivePlayer== The player , who receive message
     * @param sendPlayer==initiator
     * @param counter== number of send and receive message between send and receiver player.
     */
    public void sendMessage(String message, Player receivePlayer,Player sendPlayer, Integer counter);

    /**
     * when receiver player , receive message from sender player, should respond to it. with this method we can send response message
     * @param message== message that should be send(original message concatenated counter)
     * @param receivePlayer== The receiver player, who responsible to send respond after receive message.
     * @param sendPlayer== initiator.
     */
    public void senResponse(String message, Player receivePlayer,Player sendPlayer);

    /**
     * With this method we can add multiple players into our communication channel.
     * @param players== array of players, who wants to involve in our communication channel.one of this players , play initiator role and other
     *               players receive message.
     */
    public void addPlayer(Player... players);

    /**
     * With this method we can start communication play.
     * @param initiator== initiator player,this player in implementor class will be filter and remove for list or receiver.
     * @param counter=Number of round, that we want to communicate between objects.
     */
    public void startCommunication(Player initiator, Integer counter);
}
