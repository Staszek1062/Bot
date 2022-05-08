package org.example;





import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Grid {


    final HashMap<int[], Node> map;
    final FindingAlgorithm findingAlgorithm = new FindingAlgorithm(this);
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
        IndexVal = new double[maxX][maxY];
        for (int j = 0; j < maxY; ++j) {
            for (int i = 0; i < maxX; ++i) {
                IndexVal[i][j] = switch (Index[(j * maxX) + i]) {
                    case 'H' -> 0.5;
                    case 'B' -> 1;
                    case 'S' -> 2;
                    default -> 1000000;
                };
            }
        }
        for (int i = 0; i < Index.length; i++) {
            nodeCoord = new int[2];
            nodeCoord[0] = i % maxX;
            nodeCoord[1] = i / maxX;
            map.put(nodeCoord, new Node(new Stack(stackSize), createLinks(nodeCoord, IndexVal), Index[i]));
        }
    }
    public void fillGrid(List<String[]> itemPlacement) {
        int[] itemXY = new int[2];
        int itemZ;
        String itemName;

        for (String[] strings : itemPlacement) {
            if(strings[0]==null) break;
            itemName = strings[0];
            itemXY[0] = Integer.parseInt(strings[1]);
            itemXY[1] = Integer.parseInt(strings[2]);
            itemZ = Integer.parseInt(strings[3]);

            getNode(itemXY).getStack().getVector().setElementAt(itemName, itemZ);
        }
    }
    public NodeLink[] createLinks (int[] nodeCoord,double[][] IndexVal) {
        this.nodeCoord = nodeCoord;
        nodeLinks = new NodeLink[4];

        double lenght;

        if (nodeCoord[0] < maxX-1) {
            int[] nextToNodeCoord= new int[2];
            nextToNodeCoord[0] = this.nodeCoord[0]+1;
            nextToNodeCoord[1] = this.nodeCoord[1];
            lenght = Math.max(IndexVal[this.nodeCoord[0]][this.nodeCoord[1]], IndexVal[nextToNodeCoord[0]][nextToNodeCoord[1]]);
            nodeLinks[0] = new NodeLink(this.nodeCoord, nextToNodeCoord, lenght);

        }

        if (nodeCoord[1] < maxY-1) {
            int[] nextToNodeCoord= new int[2];
            nextToNodeCoord[0] = this.nodeCoord[0];
            nextToNodeCoord[1] = this.nodeCoord[1]+1;
            lenght = Math.max(IndexVal[this.nodeCoord[0]][this.nodeCoord[1]], IndexVal[nextToNodeCoord[0]][nextToNodeCoord[1]]);
            nodeLinks[1] = new NodeLink(this.nodeCoord, nextToNodeCoord, lenght);


        }
        if (nodeCoord[0] - 1 >= 0) {
            int[] nextToNodeCoord= new int[2];
            nextToNodeCoord[0] = this.nodeCoord[0] - 1;
            nextToNodeCoord[1] = this.nodeCoord[1];
            nodeLinks[2] = new NodeLink(this.nodeCoord, nextToNodeCoord,this.getNode(nextToNodeCoord).getNodeLinks()[0].length());


        }
        if (nodeCoord[1] - 1 >= 0) {
            int[] nextToNodeCoord= new int[2];
            nextToNodeCoord[0] = nodeCoord[0];
            nextToNodeCoord[1] = nodeCoord[1]-1;
            nodeLinks[3] = new NodeLink(this.nodeCoord, nextToNodeCoord,this.getNode(nextToNodeCoord).getNodeLinks()[1].length());


        }



        return nodeLinks;
    }
    public Node getNode(int[] node) {

        for (int[] e : map.keySet()) {
            if (Arrays.equals(e, node)) {
                return map.get(e);
            }
        }
        return null;
    }


    public HashMap<int[], Node> getMap() {
        return map;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxX() {
        return maxX;
    }

}
