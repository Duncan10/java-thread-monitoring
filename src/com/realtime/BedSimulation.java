package com.realtime;

import java.io.*;
import java.util.*;
import java.lang.*;

//Message Consumer
public class BedSimulation extends Thread{
    private String message;
    private boolean active = true;
    private ErrorBuffer buffer;
    public BedSimulation( ErrorBuffer errBuffer ) {
        buffer = errBuffer;
    };
    public void run() {
        do {
            try {
                message = buffer.consumeMsg();// ggf. blockierendes Einlesen der nächsten Message
                if(message == null){
                    //omit, sleeps 6 sec until next poll cycle
                }
                // Ausgabe der Nachricht
                else{System.out.println("[BedSimulation] :" + message);}
                Thread.sleep(6000);


            } catch (Exception e) {
                e.printStackTrace();
            }

        } while ( active );
    };
}
