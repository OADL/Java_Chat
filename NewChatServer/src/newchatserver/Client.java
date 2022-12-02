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
    private int id;
    private Socket socket;
    private DataInputStream dataIS;
    private PrintStream prtStrm;
    private String name;
    
    public Client(int id, Socket socket) {
        try {
            this.id = id;
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

    public int getClientId() {
        return id;
    }

    public boolean isClosed() {
        return this.socket.isClosed();
    }
    
    @Override
    public void run(){
        try {
            while(true){
                String message = dataIS.readLine();
                System.out.println(name+": "+ message);
                ChatQueue.messages.add(new Message(id,name,message));
            }
        } catch (IOException ex) {
            System.err.println("Client "+ this.name + " is disconnected.");
            interrupt();
        }
    }

    public void sendMessage(String message) {
        if(isAlive()){
            this.prtStrm.println(message);
        }
    }
}
