package org.example;

public class objectNode{
    private  double distanceFromSource = Integer.MAX_VALUE;
    boolean visited =false;
    Stack stack;
    NodeLink[] nodeLinks;
    char index;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }




    public char getIndex() {
        return this.index;
    }

    public void setIndex(char index) {
        this.index = index;
    }

    public objectNode(Stack stack, NodeLink[] nodeLinks, char index) {
        this.stack = stack;
        this.nodeLinks = nodeLinks;
        this.index=index;

    }
    public Stack getStack() {
        return this.stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public NodeLink[] getNodeLinks() {
        return this.nodeLinks;
    }

    public double getDistanceFromSource() {
        return this.distanceFromSource;
    }

    public objectNode setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
        return this;
    }



}



