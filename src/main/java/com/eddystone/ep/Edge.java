package com.eddystone.ep;

public class Edge {

    private Node parentNode;
    private Node childNode;

    private Edge(Node a, Node b){
        this.parentNode = a;
        this.childNode = b;
    }

    public static Edge create(Node parentNode, Node childNode){
        return new Edge(parentNode, childNode);

    }

    public Node parentNode(){
        return this.parentNode;
    }

    public Node childNode(){
        return this.childNode;
    }
}
