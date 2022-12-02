/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchatclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author DELL
 */
public class Receiver extends Thread {
    
    DataInputStream dataIS;

    public Receiver(DataInputStream dataIS){
        this.dataIS = dataIS;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                String message = dataIS.readLine();
                System.out.println(message);
            } catch (IOException exception) {
                System.err.println("Couldn't receiver message.");
            }  
        }
    }  
}
