package com.eddystone.ep;

public class SimpleNode implements Node {

    public String id;

    public SimpleNode(String id){
        this.id = id;
    }

    @Override
    public boolean apply() {
        System.out.println(id);
        return true;
    }
}
