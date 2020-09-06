package com.staxter;

import com.staxter.Mediator.MessageMediator;
import com.staxter.Mediator.MessageMediatorImpl;
import com.staxter.Player.Player;
import com.staxter.Player.PlayerImpl;

public class Main {

    public static void main(String[] args) {

        /**
         * create Mediator for communicate Objects to each other{@link MessageMediatorImpl}
         */
        MessageMediator messageMediator=new MessageMediatorImpl();
        /**
         * Creat initiator player {@link PlayerImpl}
         */
        Player initiator=new PlayerImpl(messageMediator,"Initiator");
        /**
         * Create receiver Players
         */
        Player player1=new PlayerImpl(messageMediator,"Player1");
        Player player2=new PlayerImpl(messageMediator,"Player2");
        Player player3=new PlayerImpl(messageMediator,"Player3");
        Player player4=new PlayerImpl(messageMediator,"Player4");
        Player player5=new PlayerImpl(messageMediator,"Player5");
        /**
         * Add players who wants to communicate to each other in Mediator.
         * {@link MessageMediatorImpl#addPlayer(Player...)}
         */
        messageMediator.addPlayer(initiator,player1,player2,player3,player4,player5);
        /**
         * Start communication with 10 round and define initiator player as a initiator player.
         * {@link MessageMediatorImpl#startCommunication(Player, Integer)}
         */
        messageMediator.startCommunication(initiator,10);

    }
}
