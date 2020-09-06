package com.staxter.Player;

import com.staxter.Mediator.MessageMediator;

public class PlayerImpl extends Player {
    public PlayerImpl(MessageMediator messageMediator, String name) {
        super(messageMediator, name);
    }

    /**
     *
     * @param message
     * @param counter
     * @param receivePlayer== base on my design, each player should know target player. this player is initiators player target.
     * @param sendPlayer== this use by receiver player, when want to send response to initiator,should be know , who sent this message.
     */
    @Override
    public void sendMessage(String message,Integer counter,Player receivePlayer,Player sendPlayer) {
        /**
         * initiator say start send message
         */
        System.out.println(this.name+" Sending Message:\t\033[32m"+message+" - "+counter+"\033[0m");
        /**
         * Base on Mediator design pattern , when an object want to send message to other objects , should be use Mediator class.
         * So initiator use messageMediator to send message to other players.
         * {@link com.staxter.Mediator.MessageMediatorImpl#sendMessage(String, Player, Player, Integer)}
         */
        messageMediator.sendMessage(message,receivePlayer,sendPlayer,counter);
    }

    /**
     * With this method receive player , send acknowledge to initiator
     * @param message== original message , that sent from initiator.it will be change in response message.
     * @param receivePlayer
     * @param counter
     */
    @Override
    public void receiveMessage(String message,Player receivePlayer,Integer counter) {
        System.out.println(receivePlayer.name+" Receive Message:\t\033[32m"+message+" - "+counter+"\033[0m");
    }
}
