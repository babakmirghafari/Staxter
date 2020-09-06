package com.staxter;

import com.staxter.Mediator.MessageMediator;
import com.staxter.Mediator.MessageMediatorImpl;
import com.staxter.Player.Player;
import com.staxter.Player.PlayerImpl;

public class Main {

    public static void main(String[] args) {

        MessageMediator messageMediator=new MessageMediatorImpl();
        Player initiator=new PlayerImpl(messageMediator,"Initiator");
        Player player1=new PlayerImpl(messageMediator,"Player1");
        Player player2=new PlayerImpl(messageMediator,"Player2");
        Player player3=new PlayerImpl(messageMediator,"Player3");
        Player player4=new PlayerImpl(messageMediator,"Player4");
        Player player5=new PlayerImpl(messageMediator,"Player5");
        messageMediator.addPlayer(initiator,player1,player2,player3,player4,player5);
        messageMediator.startCommunication(initiator,10);

    }
}
