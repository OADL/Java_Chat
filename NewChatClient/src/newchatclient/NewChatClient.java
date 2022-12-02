/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author DELL
 */
public class NewChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("Enter your name: ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine().trim();
            new Client(name);
        } catch (IOException exception) {
            System.err.println("Couldn't get your name.");
        }
    }
    
}
