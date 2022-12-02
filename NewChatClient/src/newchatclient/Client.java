/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchatclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author DELL
 */
public class Client {
    Socket server;
    Receiver receiver;
    Sender sender;

    public Client(String name) {
        try {
            server = new Socket("127.0.0.1", 8080);
            receiver = new Receiver(name,new DataInputStream(server.getInputStream()));
            PrintStream prtStrm = new PrintStream(server.getOutputStream());
            sender = new Sender(name, prtStrm);
            prtStrm.println(name);
            receiver.start();
            sender.start();
        } catch (IOException ex) {
            System.err.println("Couldn't connect to server.");
            throw new RuntimeException();
        }
    }
}
