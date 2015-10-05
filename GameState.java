/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShip5;


import BattleShip4.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author Mal
 */
public class GameState implements Serializable {
    
    
    
    public int turn;
   
    public String clientLastTurn; //
    
    public String[][] board = {{ "1", "", "", "", "", "", "", "", "", "", "" },
			{ "2", "", "", "", "", "", "", "", "", "", "" },
			{ "3", "", "", "", "", "", "", "", "", "", "" },
			{ "4", "", "", "", "", "", "", "", "", "", "" },
			{ "5", "", "", "", "", "", "", "", "", "", "" },
			{ "6", "", "", "", "", "", "", "", "", "", "" },
			{ "7", "", "", "", "", "", "", "", "", "", "" },
			{ "8", "", "", "", "", "", "", "", "", "", "" },
			{ "9", "", "", "", "", "", "", "", "", "", "" },
			{ "10", "", "", "", "", "", "", "", "", "", "" },}; 
    private byte[] ObjToByte;
    public String[][] board1;
    
    
    public GameState() {
        //this.map = new HashMap();
        //addToMap();
        toBytes(); 
        
        
    }
    public GameState(byte[] objBytes) {
        
        //addToMap();
        
    }
    /*private void addToMap() {
        //map.put(0, new BoardRow());
        String[] S = {"X", "Y", "X", "Y", "X", "Y", "X", "Y", "X", "Y"};
        for(int i = 1; i <=10; i++) {
            String j = String.valueOf(i);
            this.map.put(j, S);
        }
    }*/
    /*public HashMap<String, String[]> getBoardData() {
        return this.map;
    } */
    public String[][] getBoard() {
        return this.board;
    }
    public void toBytes() {
    // http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;
    byte[] yourBytes = null;
    try {
      out = new ObjectOutputStream(bos);   
      out.writeObject(this);
      yourBytes = bos.toByteArray();
      ObjToByte = yourBytes;
      
    } catch(IOException ex) {
        //byte[] b = new byte[256];
        //ObjToByte = b;
    }
    finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException ex) {
        // ignore close exception
      }
      try {
        bos.close();
      } catch (IOException ex) {
        // ignore close exception
      }
    }
    this.ObjToByte = yourBytes;
    }
    
    public byte[] getBytes() {
        return this.ObjToByte;
    }
    public int length() {
        return this.ObjToByte.length;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getClientLastTurn() {
        return clientLastTurn;
    }

    public void setClientLastTurn(String clientLastTurn) {
        this.clientLastTurn = clientLastTurn;
    }
    @Override
    public String toString() {
        return "turn "+this.turn+" PlayerLastTurn "+this.clientLastTurn;
    }
    
}