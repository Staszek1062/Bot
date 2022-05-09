package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final List<int[]> nodesIndexesTraveled= new ArrayList<>();

    private  double distanceFromSource = 1000;
    private boolean visited =true;
    private int nodesTraveled=1;

    private final NodeLink[] nodeLinks;
    private Stack stack;
    private char index;

    /**
     * Create new node
     * @param stack The stack inserted into node.
     * @param nodeLinks The links .
     * @param index The file results.
     * */
    public Node(Stack stack, NodeLink[] nodeLinks, char index) {
        this.stack = stack;
        this.nodeLinks = nodeLinks;
        this.index=index;



    }

    public List<int[]> getNodesIndexesTraveled() {
        return nodesIndexesTraveled;
    }
    public void setNodesIndexesTraveled(List<int[]> nodesIndexes) {
        this.nodesIndexesTraveled.addAll(nodesIndexes);
    }

    public double getDistanceFromSource() {
        return this.distanceFromSource;
    }
    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
        this.nodesTraveled++;
    }

    public boolean isNotVisited() {return !visited;}
    public void setVisited(boolean visited) {this.visited = visited;}

    public NodeLink[] getNodeLinks() {
        return this.nodeLinks;
    }

    public Stack getStack() {
        return this.stack;
    }
    public void setStack(Stack stack) {this.stack = stack;}

    public char getIndex() {
        return this.index;
    }
    public void setIndex(char index) {
        this.index = index;
    }

}



