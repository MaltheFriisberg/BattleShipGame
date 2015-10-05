/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShip5;

import BattleShip4.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mal
 * Responsibility : Listen to the server socket, recieve all new datagrams
 */
public class ConnectorThread implements Runnable {
    
    private BattleShipServer server;
    private DatagramSocket socket;
    private byte[] buffer;
    private DatagramPacket inpacket;
    public ConnectorThread(BattleShipServer server, DatagramSocket socket) {
        this.server = server;
        this.socket = socket;
        buffer = new byte[2048];
        inpacket = new DatagramPacket(buffer, buffer.length);
    }
    
    

    @Override
    public void run() {
        try {
            socket.receive(inpacket);
        } catch (IOException ex) {
            Logger.getLogger(ConnectorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
