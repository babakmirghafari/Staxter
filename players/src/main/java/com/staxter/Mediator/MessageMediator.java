package com.staxter.Mediator;

import com.staxter.Player.Player;

/**
 * This interface create for as Mediator interface.This interface implement with an implementor class for
 * provide send,receive and start communication between players objects.
 */
public interface MessageMediator {

    public void sendMessage(String message, Player receivePlayer,Player sendPlayer, Integer counter);
    public void senResponse(String message, Player receivePlayer,Player sendPlayer);
    public void addPlayer(Player... players);
    public void startCommunication(Player initiator, Integer counter);
}
