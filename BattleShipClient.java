/*
 * Author : Malthe Friisberg : https://github.com/MaltheFriisberg
 */
package BattleShip5;

import BattleShip4.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mal
 */
public class BattleShipClient {
    ClientGUI gui;
    private DatagramSocket socket;
    InetAddress local;
    byte[] buffer;
    private DatagramPacket inpacket;
    private DatagramPacket outPacket;
    private int port;
    private GameState state;
    public String userName;
    public static void main(String[] args) {
        BattleShipClient g = new BattleShipClient(5820, "Hans");
        
    }
    public BattleShipClient(int port, String userName) {
        this.port = port;
        this.userName = userName;
        gui = new ClientGUI();
        gui.getPlayBtn().addActionListener(new PlayBtnListener());
        buffer = new byte[2048];
        inpacket = new DatagramPacket(buffer, buffer.length);
        try {
            local = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(BattleShipClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket = new DatagramSocket();
            
            if(state == null) {
                state = new GameState();
            } else {
                ping();
                recieve();
            }
            //update GUi 
            //gui.updateBoard(state.getBoard());
            
            
            
            //System.out.println(state.length());
           
            
        } catch (SocketException ex) {
            System.out.println("could not setup socket");
        } 
        do {
            recieve();
        } while(true);
    }
    private void ping() {
         String message = "Ping";
            DatagramPacket pingPacket = new DatagramPacket(
                message.getBytes(), message.length(), local, this.port);
        try {
            socket.send(pingPacket);
        } catch (IOException ex) {
            Logger.getLogger(BattleShipClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void recieve() {
        ///ping();
        try {
            this.socket.receive(inpacket);
            this.state = (GameState)ObjectFactory.buildGameState(inpacket.getData());
            System.out.println(userName+" recieved " +state);
                    
            //System.out.println("Client last turn recieved "+state.getClientLastTurn());
        } catch (IOException ex) {
            Logger.getLogger(BattleShipClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendBoardToServer() {
        //System.out.println(this.state);  
        state.toBytes();
        outPacket = new DatagramPacket(
                state.getBytes(), state.length(), local, this.port);
        try {
                socket.send(this.outPacket);
            } catch (IOException ex) {
                System.out.println("couldnt send the gamestate to server \n" + ex);
            }
        }
    class PlayBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int columnShotAt = gui.getTable1().getSelectedColumn();
            int rowShotAt = gui.getTable1().getSelectedRow();
            
            int columnShipPlaced = gui.getTable().getSelectedColumn();
            int rowShipPlaced = gui.getTable().getSelectedRow();
            placeShip(columnShipPlaced, rowShipPlaced);
            
            state.setClientLastTurn(userName);
            System.out.println("hej");
            //Update the gameState
                System.out.println("clientLastTurnX "+state.clientLastTurn);
                System.out.println("placing ship at"+ columnShipPlaced + "row "+ rowShipPlaced);
            System.out.println("Shooting at column "+columnShotAt + " row "+rowShotAt);
            String[][] board = state.getBoard();
            board[columnShipPlaced][rowShipPlaced] = userName;
            board[columnShotAt][rowShotAt] = ""; //Insert empty string where the player shot at.
            state.setBoard(board);
            state.turn++;
            //gui.updateBoard(state.getBoard());
            
            //System.out.println("clientLastTurn "+state.clientLastTurn);
            //send to server
            sendBoardToServer();
            
            
            
        }
        private void placeShip(int columnShipPlaced, int rowShipPlaced) {
            
            //if any player has a ship on the row deny
            //else, place the ship on the coordinates given
        }
        
    }
    
}
