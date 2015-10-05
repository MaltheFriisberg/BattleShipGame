/*
 * Author : Malthe Friisberg : https://github.com/MaltheFriisberg
 */
package BattleShip5;


import BattleShip4.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mal
 */
public final class ObjectFactory {
    
    ObjectFactory() {
        
    }
    
    public static GameState buildGameState(byte[] arr) {
        // http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
        ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        ObjectInput in = null;
        GameState s = null;
        try {
          in = new ObjectInputStream(bis);
            //System.out.println("building GameState obj from server");
          s = (GameState)in.readObject(); 
          in.close();
          bis.close();
            //System.out.println("serial version = " + s.serialVersion + "\nname =" + s.name);

        } catch(IOException ex) {
           System.out.println("could not read the bytes from stream"); 
           ex.printStackTrace(System.out);
        } catch(ClassNotFoundException ex) {
            System.out.println("could not build object");
        }
        
        finally {
          try {
            bis.close();
          } catch (IOException ex) {
              System.out.println("could not close the ByteArrayInputStream bis");
          }
          try {
            if (in != null) {
              in.close();
            }
          } catch (IOException ex) {
            // ignore close exception
              System.out.println("could not close the ObjectInput in");
          }
        }
        return s;
    }
    public static byte[] GameStatetoBytes(GameState gs) {
    // http://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
    byte[] yourBytes = null;    
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;
    try {
    out = new ObjectOutputStream(bos);   
    out.writeObject(gs);
    yourBytes = bos.toByteArray();
  
    }   catch (IOException ex) {
            Logger.getLogger(ObjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
    return yourBytes; 
    }
    
}
