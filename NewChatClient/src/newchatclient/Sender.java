/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Sender extends Thread {
    
    String name;
    PrintStream prtStrm;

    public Sender(String name, PrintStream prtStrm) {
        this.name = name;
        this.prtStrm = prtStrm;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print(this.name + ": \r");
                String message = reader.readLine().trim();
                prtStrm.println(message);
            } catch (IOException exception) {
                System.err.println("Couldn't send message.");
            }
        }
    }

//    @Override
//    public void run() {
//        Scanner reader = new Scanner(System.in);
//        StringBuilder message = new StringBuilder();
//        while(true) {
//            System.out.print(this.name + ": \r");
//            if (reader.hasNext()) {
//                message.append(reader.next()).append(" ");
//            } else if(message.length()>0) {
//                prtStrm.println(message);
//                message = new StringBuilder();
//            }
//        }
//    }
}
