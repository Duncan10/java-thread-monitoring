package com.realtime;


import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;


public class ErrorBuffer {
    private int count = 0;   //max size of buffer memory
    private String[] bufferMemory = {" "}; //ErrorMessage String Storage
    private boolean writeable = true, readable = false;
    private int readLocation = 0, writeLocation = 0;
    private long lastTime;
    public ErrorBuffer()
    {lastTime = System.currentTimeMillis();
    };
    public synchronized void  produceMsg( String message ) throws InterruptedException {
        while ( !writeable ) {

            try {
                wait(); // warten, bis bis Buffer wieder beschrieben werden kann

            }
            catch(Exception e){}//nicht zu erwartende Ausnahme abfangen
        }; // end while
        bufferMemory[writeLocation] = message;
        readable = true;
        writeable = false;

    } // end produceMsg

    public synchronized String consumeMsg() throws InterruptedException {

        long currentTime = System.currentTimeMillis();
        //check elapsed time
        System.out.println("[ErrorBuffer] Time since last BedSimulation Poll : " + ((currentTime - lastTime) / 1000) + "sec");
        lastTime = currentTime;
        //time check done
        String returnMessage;
        if ( !readable ) {
           try {
            //buffer empty, return null
            return null;
           }
            catch(Exception e) { }// nicht zu erwartende Ausnahme abfangen
        }; // end while
        returnMessage = bufferMemory[readLocation];
        writeable = true;
        readable = false;
        notify(); //BedControl Thread wake up Call
        return returnMessage;
    } // end consumeMsg
} // end ErrorBuffer

