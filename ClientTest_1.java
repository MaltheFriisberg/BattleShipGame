/*
 * Author : Malthe Friisberg : https://github.com/MaltheFriisberg
 */
package BattleShip5;

import BattleShip4.*;



/**
 *
 * @author Mal
 */
public class ClientTest_1 {
    public static void main(String[] args) {
        BattleShipClient c = new BattleShipClient(5820, "kurt");
        
        /*GameState p = new GameState();
        ObjectFactory f = new ObjectFactory();
        p.setTurn(2);
        p.setClientLastTurn("Kurt");
        byte[] arr = f.GameStatetoBytes(p);
        System.out.println(arr.length);
        
        GameState s = f.buildGameState(arr);
        System.out.println(s.turn + " "+s.getClientLastTurn()+" "+s.getBytes()); */
        
    }
    
}
