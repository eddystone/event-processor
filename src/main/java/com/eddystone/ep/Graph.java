package com.eddystone.ep;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class Graph {

    public ArrayList<Edge> edges = new ArrayList<Edge>();


    public Graph(){}

    public void addEdge(Node parentNode, Node childNode){

        Edge e = Edge.create(parentNode, childNode);
        edges.add(e);
    }

    public static HashSet<Node> nodeList(ArrayList<Edge> edges){

        HashSet<Node> nodes = new HashSet<Node>();
        for(Edge edge : edges){
            nodes.add(edge.parentNode());
            nodes.add(edge.childNode());
        }
        return nodes;
    }

    public static HashSet<Node> parentNodes(ArrayList<Edge> edges){

        HashSet<Node> nodes = new HashSet<Node>();
        for(Edge edge : edges)
            nodes.add(edge.parentNode());
        return nodes;
    }

    public static HashSet<Node> childNodes(ArrayList<Edge> edges){

        HashSet<Node> nodes = new HashSet<Node>();
        for(Edge edge : edges)
            nodes.add(edge.childNode());
        return nodes;
    }

    public static HashSet<Node> orphans(ArrayList<Edge> edges){

        HashSet<Node> c = childNodes(edges);
        return parentNodes(edges)
                .stream()
                .filter(n -> ! c.contains(n))
                .collect(Collectors.toCollection(HashSet<Node>::new));
    }

    public static HashSet<Node> childrenOf(Node node, ArrayList<Edge> edges){

        return edges.stream()
                .filter(e -> e.parentNode() == node)
                .map(e -> e.childNode())
                .collect(Collectors.toCollection(HashSet<Node>::new));
    }

    /*
    Not quite the Khan algorithm
    L ← Empty list that will contain the sorted elements
    S ← Set of all nodes with no incoming edge
    while S is non-empty do
        remove a node n from S
        add n to tail of L
        for each node m with an edge e from n to m do
        remove edge e from the graph
            if m has no other incoming edges then
        insert m into S (this is the bit i've done a bit differenly)
    if graph has edges then
        return error   (graph has at least one cycle)
    else
            return L   (a topologically sorted order)
    */

    public static ArrayList<Node> sort(ArrayList<Node> sortedList, ArrayList<Edge> edges)
            throws CycleInGraphException{

        HashSet<Node> s = orphans(edges);
        if(! s.isEmpty()){

            // first node in orphans list
            Node n = s.iterator().next();
            sortedList.add(n);

            // edges we're removing
            ArrayList<Edge> deadEdges = edges.stream()
                    .filter(e -> e.parentNode() == n)
                    .collect(Collectors.toCollection(ArrayList<Edge>::new));

            // child nodes of edges we're removing
            HashSet<Node> ms = childNodes(deadEdges);

            // edge set for next cycle
            ArrayList<Edge> newEdges = edges.stream()
                    .filter(e -> e.parentNode() != n)
                    .collect(Collectors.toCollection(ArrayList<Edge>::new));

            // other nodes to add to sortedList
            ArrayList<Node> mms = ms.stream()
                    .filter(nn -> !childNodes(newEdges).contains(nn))
                    .filter(nn -> !parentNodes(newEdges).contains(nn))
                    .collect(Collectors.toCollection(ArrayList<Node>::new));

            // add other nodes
            for(Node node: mms)
                sortedList.add(node);

            sort(sortedList, newEdges);
        }
        else if(s.isEmpty() && !edges.isEmpty()){
            throw new CycleInGraphException();
        }
        return sortedList;

    }
}
