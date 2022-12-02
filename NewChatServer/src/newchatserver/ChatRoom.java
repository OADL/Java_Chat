/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchatserver;

import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class ChatRoom extends Thread {
    ServerSocket serverSocket;
    ArrayList<Client> clients;
    private int roomCount = 0;

    public ChatRoom() {
        try{
            serverSocket = new ServerSocket(8080); 
            clients = new ArrayList<>();
        } catch (IOException exception) {
            System.err.println("Couldn't create server socket.");
            throw new RuntimeException("Couldn't create server socket: " + exception.getMessage());
        }
    }

    public void open() {
        System.out.println("Server started.");
        start();
        while(true){
            try {
                Socket socket = serverSocket.accept();
                Client client = new Client(roomCount++,socket);
                clients.add(client);
                client.start();
                sendBroadcast(client.getClientName()+" joined the party.");
            } catch (IOException exception) {
                System.err.println("Couldn't connect to client.");
            }
        }
    } 
    
    @Override
    public void run() {
        while (true) {
            if(!ChatQueue.messages.isEmpty()){
                Message message = ChatQueue.messages.remove(0);
                sendMessageToAll(message);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }
    }
    
    private void sendMessageToAll(Message message){
        for(Client client : clients){
            if(message.getId() != client.getClientId())
                client.sendMessage(message.getName() +": "+ message.getMessage());
        }
    }
    private void sendBroadcast(String string){
        for(Client client : clients){
            client.sendMessage(string);
        }
    }
}
