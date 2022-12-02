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
        Scanner reader = new Scanner(System.in);
        while(true) {
            System.out.print(this.name + ": \r");
            if (reader.hasNext()) {
                prtStrm.println(reader.next().trim());
            }
        }
    }
}
