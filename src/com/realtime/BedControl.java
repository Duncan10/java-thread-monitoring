package com.realtime;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.concurrent.ThreadLocalRandom;

//Message Producer
public class BedControl extends Thread{
    private int errNumber = 0;
    private int sleepTime;
    private boolean active = true;
    private String OutMessage;
    private ErrorBuffer buffer;
    public BedControl(ErrorBuffer errBuffer) {
        buffer = errBuffer;
    };

    public String produceErrorMsg(){


        String errMsg = "Error Nr: " + errNumber + " spawned!";
        System.out.println("[BedControl] " + errMsg);
        //errNumber increment with overflow protection
        errNumber = (errNumber+1)%Integer.MAX_VALUE;
        return errMsg;
    }

    public void run() {
        do {
            try {
                sleepTime = ThreadLocalRandom.current().nextInt(2, 10 + 1);
                System.out.println("[BedControl] random time to next Error: " + sleepTime + "sec!");
                Thread.sleep(sleepTime*1000);
                OutMessage = produceErrorMsg();
                buffer.produceMsg(OutMessage);// ggf. blockierende Weitergabe der Nr.
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Bestimmung, ob noch weiter aktiv
        } while ( active );
    };
}
