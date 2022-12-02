/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ChatRoom extends Thread {
    ServerSocket serverSocket;
    ArrayList<Client> clients;
    
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
                Client client = new Client(socket);
                clients.add(client);
                client.start();
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
        }
    }
    
    private void sendMessageToAll(Message message){
        for(Client client : clients){
            client.prtStrm.println(message.getName() +": "+ message.getMessage());
        }
    }
}
