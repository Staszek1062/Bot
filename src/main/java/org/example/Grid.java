package org.example;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Grid {
     final HashMap<int[], objectNode> map;

    final int maxY;
    final int maxX;
    final int size;
    final int stackSize;
    int[] nodeCoord;

    NodeLink[] nodeLinks;
    double [][] IndexVal;


    public Grid(List<Integer> cord) {
        stackSize = cord.get(2);
        size = cord.get(0) * cord.get(1);
        maxX = cord.get(0);
        maxY = cord.get(1);
        map = new HashMap<>(size);


    }

    public void createGrid(char[] Index) {
        IndexVal= new double[maxX][maxY];
        for(int j = 0; j < maxY; ++j) {
            for (int i = 0; i < maxX; ++i) {
                IndexVal[i][j] = switch (Index[(j*maxX)+i]) {
                    case 'H' -> 0.5;
                    case 'B' -> 1;
                    case 'S' -> 2;
                    default -> 1000000;
                };
            }
        }




        for (int i=0;i<Index.length;i++) {
            nodeCoord =new int[2];
            nodeCoord[0]  = i%maxX;
            nodeCoord[1]  = i/maxX;
            map.put(nodeCoord, new objectNode(new Stack(stackSize),createLinks(nodeCoord,IndexVal), Index[i]));

        }
    }
    public double getShortestDistance(int[] fromNode, int[] toNode){
        boolean visited =false;
        this.getNode(toNode).setDistanceFromSource(0);
        int[] nextNode={0,0};
        nodeCoord =new int[2];
        for(int j=0;j<maxY;j++){
            for(int i=0;i<maxX;i++){
                nodeCoord[0]=i;
                nodeCoord[1]=j;
                for(NodeLink o:this.getNode(nodeCoord).getNodeLinks()){
                    if(o != null) {
                        double distance = o.length();

                        if(!visited){
                            double approximation =distance+this.getNode(nodeCoord).getDistanceFromSource();
                            if(approximation<this.getNode(toNode).getDistanceFromSource()){
                                this.getNode(toNode).setDistanceFromSource(approximation);
                            }
                        }
                    }
                }
                this.getNode(nodeCoord).setVisited(true);
                nextNode = this.getNodeShortestDistanced();
            }
        }
        return 0;
    }
    private int[] getNodeShortestDistanced() {
        int[] storedNodeIndex = {0,0};
        double storedDist = Integer.MAX_VALUE;
        nodeCoord =new int[2];
        for(int j=0;j<maxY;j++){
            for(int i=0;i<maxX;i++) {
                nodeCoord[0] = i;
                nodeCoord[1] = j;

                double currentDist = this.getNode(nodeCoord).getDistanceFromSource();
                if (!this.getNode(nodeCoord).isVisited() && currentDist < storedDist) {
                    storedDist = currentDist;
                    storedNodeIndex = new int[]{i, j};
                }
            }
        }
        return storedNodeIndex;
    }
    public objectNode getNode(int[] node) {

        for (int[] e : map.keySet()) {
            if (Arrays.equals(e, node)) {
                return map.get(e);
            }
        }
        return null;
    }


    public NodeLink[] createLinks (int[] nodeCoord,double[][] IndexVal) {
        nodeLinks = new NodeLink[4];
        double lenght;
        if (nodeCoord[0] - 1 >= 0) {
            this.nodeCoord = nodeCoord;
            this.nodeCoord[0] = nodeCoord[0] - 1;
            this.nodeCoord[1] = nodeCoord[1];
            nodeLinks[2] = this.getNode(this.nodeCoord).getNodeLinks()[0];
        }
        if (nodeCoord[1] - 1 >= 0) {
            this.nodeCoord = nodeCoord;
            this.nodeCoord[0] = nodeCoord[0];
            this.nodeCoord[1] = nodeCoord[1]-1;
            nodeLinks[3] = this.getNode(this.nodeCoord).getNodeLinks()[1];
        }
        if (nodeCoord[0] < maxX) {
            this.nodeCoord[0] = nodeCoord[0]+1;
            this.nodeCoord[1] = nodeCoord[1];
            lenght = Math.max(IndexVal[nodeCoord[0]][nodeCoord[1]], IndexVal[this.nodeCoord[0]][this.nodeCoord[1]]);
            nodeLinks[0] = new NodeLink(nodeCoord, this.nodeCoord, lenght);
        }

        if (nodeCoord[1] < maxY) {
            this.nodeCoord[0] = nodeCoord[0];
            this.nodeCoord[1] = nodeCoord[1]+1;
            lenght = Math.max(IndexVal[nodeCoord[0]][nodeCoord[1]], IndexVal[this.nodeCoord[0]][this.nodeCoord[1]]);
            nodeLinks[1] = new NodeLink(nodeCoord, this.nodeCoord, lenght);
        }

        return nodeLinks;
    }



    public void fillGrid(String[][] itemPlacement) {
        int[] itemXY = new int[2];
        int itemZ;
        String itemName;

        for (String[] strings : itemPlacement) {
            itemName = strings[0];
            itemXY[0] = Integer.parseInt(strings[1]);
            itemXY[1] = Integer.parseInt(strings[2]);
            itemZ = Integer.parseInt(strings[3]);

            getNode(itemXY).getStack().getVector().setElementAt(itemName, itemZ);
        }
    }

}
