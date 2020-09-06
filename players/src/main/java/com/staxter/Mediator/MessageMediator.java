package com.staxter.Mediator;

import com.staxter.Player.Player;

public interface MessageMediator {

    public void sendMessage(String message, Player receivePlayer,Player sendPlayer, Integer counter);
    public void addPlayer(Player... players);
    public void startCommunication(Player initiator, Integer counter);
}
