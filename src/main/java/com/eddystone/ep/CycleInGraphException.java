package com.eddystone.ep;

public class CycleInGraphException extends Exception {

    public String getMessage(){
        return "There is a cycle in the graph";
    }

}
