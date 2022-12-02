/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchatserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author DELL
 */
public class Client extends Thread {
    Socket socket;
    DataInputStream dataIS;
    PrintStream prtStrm;
    String name;
    
    public Client(Socket socket) {
        try {
            this.socket = socket;
            dataIS = new DataInputStream(socket.getInputStream());
            prtStrm = new PrintStream(socket.getOutputStream());
            name = dataIS.readLine();
            System.out.println(name + " is added to server.");
            prtStrm.println("Hi "+ name + ".");
        } catch (IOException exception) {
            System.err.println("Couldn't initialize client.");
        }
    }
    
    public boolean isClosed() {
        return this.socket.isClosed();
    }
    
    @Override
    public void run(){
        while(true){
            try {
                String message = dataIS.readLine();
                System.out.println(name+": "+ message);
                ChatQueue.messages.add(new Message(name,message));
            } catch (IOException ex) {
                System.err.println("Couldn't read from client.");
            }
        }
    }
}
