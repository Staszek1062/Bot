package org.example;

public record NodeLink(int[] fromNodeIndex, int[] toNodeIndex, double length) {




    public int[] getNeighbourIndex(int[] nodeIndex) {
        return this.fromNodeIndex.equals(nodeIndex)  ? this.toNodeIndex : this.fromNodeIndex;
    }

}
