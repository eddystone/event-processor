package com.eddystone.ep;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.HashSet;
import java.util.ArrayList;

public class GraphTest extends TestCase {

    private Graph simpleGraph;
    SimpleNode s5, s7, s3, s8, s11, s10, s2, s9;

    public void setUp(){
        simpleGraph = new Graph();
        s5 = new SimpleNode("5");
        s7 = new SimpleNode("7");
        s3 = new SimpleNode("3");
        s8 = new SimpleNode("8");
        s11 = new SimpleNode("11");
        s10 = new SimpleNode("10");
        s2 = new SimpleNode("2");
        s9 = new SimpleNode("9");

        simpleGraph.addEdge(s5, s11);
        simpleGraph.addEdge(s7, s11);
        simpleGraph.addEdge(s7, s8);
        simpleGraph.addEdge(s3, s8);
        simpleGraph.addEdge(s3, s10);
        simpleGraph.addEdge(s8, s9);
        simpleGraph.addEdge(s11, s2);
        simpleGraph.addEdge(s11, s9);
        simpleGraph.addEdge(s11, s10);


    }

    public static Test suite()
    {
        return new TestSuite( GraphTest.class );
    }

    public void testGraph(){

        assertTrue(simpleGraph.edges.size() == 9);
    }

    public void testNodeList(){
        HashSet<Node> nodeSet = Graph.nodeList(simpleGraph.edges);
        assertTrue(nodeSet.size() == 8);
        assertTrue(nodeSet.contains(s5));
        assertTrue(nodeSet.contains(s7));
        assertTrue(nodeSet.contains(s8));
        assertTrue(nodeSet.contains(s2));
        assertTrue(nodeSet.contains(s10));
        assertTrue(nodeSet.contains(s11));
        assertTrue(nodeSet.contains(s9));
        assertTrue(nodeSet.contains(s3));

    }

    public void testSort(){

        try{
            ArrayList<Node> sortedList = Graph.sort(new ArrayList<Node>(), simpleGraph.edges);
            assertTrue(sortedList.size() == 8);
            assertTrue(sortedList.indexOf(s5) < sortedList.indexOf(s11));
            //assertTrue(sortedList.indexOf(s5) < sortedList.indexOf(s8));
            assertTrue(sortedList.indexOf(s5) < sortedList.indexOf(s2));
            assertTrue(sortedList.indexOf(s5) < sortedList.indexOf(s9));
            assertTrue(sortedList.indexOf(s5) < sortedList.indexOf(s10));


            assertTrue(sortedList.indexOf(s3) < sortedList.indexOf(s8));
            assertTrue(sortedList.indexOf(s3) < sortedList.indexOf(s9));
            assertTrue(sortedList.indexOf(s3) < sortedList.indexOf(s10));

            assertTrue(sortedList.indexOf(s7) < sortedList.indexOf(s11));
            assertTrue(sortedList.indexOf(s7) < sortedList.indexOf(s8));
            assertTrue(sortedList.indexOf(s7) < sortedList.indexOf(s2));
            assertTrue(sortedList.indexOf(s7) < sortedList.indexOf(s9));
            assertTrue(sortedList.indexOf(s7) < sortedList.indexOf(s10));

            assertTrue(sortedList.indexOf(s11) < sortedList.indexOf(s2));
            assertTrue(sortedList.indexOf(s11) < sortedList.indexOf(s9));
            assertTrue(sortedList.indexOf(s11) < sortedList.indexOf(s10));

            //assertTrue(sortedList.indexOf(s8) < sortedList.indexOf(s2));
            assertTrue(sortedList.indexOf(s8) < sortedList.indexOf(s9));
            //assertTrue(sortedList.indexOf(s8) < sortedList.indexOf(s10));




        }catch(Exception e){}
    }


}
