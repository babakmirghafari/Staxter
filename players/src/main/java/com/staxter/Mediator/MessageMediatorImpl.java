package com.staxter.Mediator;

import com.staxter.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MessageMediatorImpl implements MessageMediator {
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[46m";

    private List<Player> playerList;

    public MessageMediatorImpl (){
        this.playerList=new ArrayList<Player>();
    }
    @Override
    public void sendMessage(final String message, Player receivePlayer,Player sendPlayer,Integer counter) {
        receivePlayer.receiveMessage(message,receivePlayer,counter);
        this.senResponse(message+" - "+counter,receivePlayer,sendPlayer);
        System.out.println("**********************");
    }

    public void senResponse(String message, Player receivePlayer,Player sendPlayer) {
        System.out.println(receivePlayer.name+" send Response to "+sendPlayer.name+" for Message:\t\033[32m"+ message+"\033[0m");
    }

    @Override
    public void addPlayer(Player... players) {
        Stream.of(players).forEach(p->{
            playerList.add(p);
        });
    }

    public void startCommunication(Player initiator,Integer counter){
        System.out.println("\033[31;1m.................Communication Start.................\033[0m");
        playerList.stream().filter(p->p!=initiator).forEach(receivePlayer->{
            System.out.println("\033[33;1;2mStart Communicating with"+ receivePlayer.name+"......................\033[0m");
            IntStream.range(1,counter+1).boxed().forEach(i->{
                initiator.sendMessage("Hi "+receivePlayer.name,i,receivePlayer,initiator);
            });
        });

        System.out.println("\033[31;1m.................Communication Finish Successfully.................\033[0m");
    }
}
