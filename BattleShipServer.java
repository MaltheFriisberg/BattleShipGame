/*
 * Author : Malthe Friisberg : https://github.com/MaltheFriisberg
 */
package BattleShip5;



import BattleShip4.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mal
 */
public class BattleShipServer {
    public static void main(String[] args) {
        BattleShipServer s = new BattleShipServer();
    }
    private int numPlayers = 2;
    private DatagramSocket socket;
    private byte[] buffer; 
    private DatagramPacket inpacket;
    private String ping = "Ping";
    private String pong = "Pong";
    private boolean open;
    public GameState p;
    
    
    
    public BattleShipServer() {
        open = true;
        buffer = new byte[2048];
        inpacket = new DatagramPacket(buffer, buffer.length);
        System.out.println("opening port");
        openPort();
        
        do {
            syncPlayers();
        } while(open);
      
    }
    private void openPort() {
        try {
            socket = new DatagramSocket(5820);
            
        } catch (SocketException ex) {
            Logger.getLogger(BattleShipServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void syncPlayers() {
          
            
            try {
                
                System.out.println("waiting for incoming datagrams");
                socket.receive(inpacket); //Blocking
                
                InetAddress clientAddress = inpacket.getAddress();
                int clientport = inpacket.getPort();
                String message = new String(inpacket.getData(), 0, inpacket.getLength());
                //System.out.println("SERVER recieved : "+message);
                
                
                p = (GameState)ObjectFactory.buildGameState(inpacket.getData());
                p.toBytes();
                System.out.println("GameState Recieved "+p);
                
                DatagramPacket outPacket = new DatagramPacket(
                p.getBytes(), p.length(), clientAddress, clientport);
                socket.send(outPacket);
            } catch (IOException ex) {
                Logger.getLogger(BattleShipServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    public void close() {
        this.open = false;
    }
}
