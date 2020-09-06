package com.staxter.Mediator;

import com.staxter.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class responsible to implement Mediator methods.All players use this class to communicate between each other.
 */
public class MessageMediatorImpl implements MessageMediator {


    private List<Player> playerList;

    /**
     * after instantiate this class all Players load into {@link #playerList} and ready to communicate to each other.
     */
    public MessageMediatorImpl (){
        this.playerList=new ArrayList<Player>();
    }

    /**
     * @param message== this message send from initiator
     * @param receivePlayer== The player , who receive message
     * @param sendPlayer==initiator
     * @param counter== number of send and receive message between initiator and receiver players.
     */
    @Override
    public void sendMessage(final String message, Player receivePlayer,Player sendPlayer,Integer counter) {
        /**
         * before receive player want to inform initiator about receive message,initiator sent message .
         * before this line of code initiator sent it's message {@link com.staxter.Player.PlayerImpl#sendMessage(String, Integer, Player, Player)}
         */
        receivePlayer.receiveMessage(message,receivePlayer,counter);
        /**
         * after receive message from receive player , should be send response to initiator,with this method we can do that.
         */
        this.senResponse(message+" - "+counter,receivePlayer,sendPlayer);
        /**
         * when a round completed (initiator sent message,message received,respond sent),this line write to console.
         */
        System.out.println("**********************");
    }

    /**
     *
     * @param message== message that should be send(original message concatenated counter)
     * @param receivePlayer== The receiver player, who responsible to send respond after receive message.
     * @param sendPlayer== initiator.
     *
     *                  Don't care about \033[32m and \033[0m. only use for change color.
     */
    @Override
    public void senResponse(String message, Player receivePlayer,Player sendPlayer) {
        System.out.println(receivePlayer.name+" send Response to "+sendPlayer.name+" for Message:\t\033[32m"+ message+"\033[0m");
    }

    /**
     *
     * @param players== array of players, who wants to involve in our communication channel.one of this players , play initiator role and other
     */
    @Override
    public void addPlayer(Player... players) {
        Stream.of(players).forEach(p->{
            playerList.add(p);
        });
    }

    /**
     *
     * @param initiator== initiator player
     * @param counter=Number of round, that we want to communicate between objects.
     */
    public void startCommunication(Player initiator,Integer counter){
        System.out.println("\033[31;1m.................Communication Start.................\033[0m");
        /**
         * because of all  players are in player list, we have to filter initiator from this list.When we didn't filter initiator,
         * initiator receive message , that sent from itself.for avoid that, we filter initiator from player list and let other player
         * receive message
         */
        playerList.stream().filter(p->p!=initiator).forEach(receivePlayer->{
            System.out.println("\033[33;1;2mStart Communicating with"+ receivePlayer.name+"......................\033[0m");
            /**
             * create number of round that we want to have send and receive.This loop create for each receiver player. after a player
             * receive all messages and send response to all of them , next player start it's rounds.
             */
            IntStream.range(1,counter+1).boxed().forEach(i->{
                initiator.sendMessage("Hi "+receivePlayer.name,i,receivePlayer,initiator);
            });
        });
        System.out.println("\033[31;1m.................Communication Finish Successfully.................\033[0m");
    }
}
