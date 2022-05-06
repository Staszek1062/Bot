package org.example;





import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Grid {
    HashMap<int[], recordNode> map;
    Stack stack;
    int stackSize;
    int size;
    int maxX;
    int maxY;
    int[] node;
    NodeLink[] nodeLinks;;
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
        boolean full;
        for (int i=0;i<Index.length;i++) {
            node =new int[2];
            node[0]  = i%maxX;
            node[1]  = i/maxX;
            map.put(node, new recordNode(new Stack(stackSize),nodeLinks, Index[i]));
        }
    }
    public recordNode getNode(int[] node) {

        for (int[] e : map.keySet()) {
            if (Arrays.equals(e, node)) {
                return map.get(e);
            }
        }
        return null;
    }

    public NodeLink[] createLinks (int[] node,double[][] IndexVal){
        nodeLinks = new NodeLink[4];
        double lenght;

        this.node=node;
        this.node[0]-=1;
        lenght=  Math.max(IndexVal[node[0]][node[1]],IndexVal[this.node[0]][this.node[1]]);
        nodeLinks[0]= new NodeLink(node,this.node,lenght);

        return nodeLinks;
    }


    public void fillGrid(String[][] itemPlacement) {
        int[] itemXY = new int[2];
        int itemZ;
        String itemName;

        for (int i = 0; i < itemPlacement.length; i++) {
            itemName = itemPlacement[i][0];
            itemXY[0] = Integer.parseInt(itemPlacement[i][1]);
            itemXY[1] = Integer.parseInt(itemPlacement[i][2]);
            itemZ = Integer.parseInt(itemPlacement[i][3]);

            getNode(itemXY).stack().getVector().setElementAt(itemName,itemZ);
        }
    }

}
