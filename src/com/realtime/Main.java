package com.realtime;

import java.io.*;
import java.util.*;
import java.lang.*;


//Driver Class
public class Main {




    public static void main(String[] args) {

        BedControl bedControl;
        BedSimulation bedSimulation;
        ErrorBuffer errorBuffer;

        errorBuffer = new ErrorBuffer();
        bedControl = new BedControl(errorBuffer);
        bedSimulation = new BedSimulation(errorBuffer);
        bedSimulation.setPriority(Thread.MIN_PRIORITY);
        bedSimulation.start();
        bedControl.setPriority(Thread.MAX_PRIORITY);
        bedControl.start();

	//
    }
}



