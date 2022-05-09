package org.example;





import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * Class that creates grid(x,y) and fill it.
 */
public class Grid {


    final HashMap<int[], Node> map;
    final FindingAlgorithm findingAlgorithm = new FindingAlgorithm(this);
    final int maxY;
    final int maxX;
    final int size;
    final int stackSize;
    int[] coordinates;

    NodeLink[] nodeLinks;
    double [][] IndexVal;

    /**
     * Takes list of ints and initializes grid,map,stack sizes.
     * @param setupCoordinates The list of ints parameters for setup.
     */
    public Grid(List<Integer> setupCoordinates) {
        stackSize = setupCoordinates.get(2);
        size = setupCoordinates.get(0) * setupCoordinates.get(1);
        maxX = setupCoordinates.get(0);
        maxY = setupCoordinates.get(1);
        map = new HashMap<>(size);


    }
    /**
     * Takes array of chars and creates HashMap of
     * keys(coordinates) and values(Node)
     * @see  Grid
     * @param Index The array of chars indexes of every node.
     */
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
            coordinates = new int[2];
            coordinates[0] = i % maxX;
            coordinates[1] = i / maxX;
            map.put(coordinates, new Node(new Stack(stackSize), createLinks(coordinates, IndexVal), Index[i]));
        }
    }
    /**
     * Takes products in list and places them in designated Nodes.
     * @param productPlacement The list of strings each string contains product and his position.
     * @see  Grid#createGrid(char[])
     */
    public void fillGrid(List<String[]> productPlacement) {
        int[] itemXY = new int[2];
        int itemZ;
        String itemName;

        for (String[] strings : productPlacement) {
            if(strings[0]==null) break;
            itemName = strings[0];
            itemXY[0] = Integer.parseInt(strings[1]);
            itemXY[1] = Integer.parseInt(strings[2]);
            itemZ = Integer.parseInt(strings[3]);

            getNode(itemXY).getStack().getVector().setElementAt(itemName, itemZ);
        }
    }
    /**
     * Takes coordinates and movementValues and creates links for every node to neighbour nodes.
     * @param coordinates The coordinates.
     * @param moveValues The time needed to travel each node.
     * @see  Grid#fillGrid(List)
     * @return The array of Links to neighbour nodes.
     */
    public NodeLink[] createLinks (int[] coordinates,double[][] moveValues) {
        this.coordinates = coordinates;
        nodeLinks = new NodeLink[4];
        double lenght;

        if (coordinates[0] < maxX-1) {
            int[] nextCoordinates= new int[2];
            nextCoordinates[0] = this.coordinates[0]+1;
            nextCoordinates[1] = this.coordinates[1];
            lenght = Math.max(moveValues[this.coordinates[0]][this.coordinates[1]], moveValues[nextCoordinates[0]][nextCoordinates[1]]);
            nodeLinks[0] = new NodeLink(this.coordinates, nextCoordinates, lenght);

        }

        if (coordinates[1] < maxY-1) {
            int[] nextCoordinates= new int[2];
            nextCoordinates[0] = this.coordinates[0];
            nextCoordinates[1] = this.coordinates[1]+1;
            lenght = Math.max(moveValues[this.coordinates[0]][this.coordinates[1]], moveValues[nextCoordinates[0]][nextCoordinates[1]]);
            nodeLinks[1] = new NodeLink(this.coordinates, nextCoordinates, lenght);


        }
        if (coordinates[0] - 1 >= 0) {
            int[] nextCoordinates= new int[2];
            nextCoordinates[0] = this.coordinates[0] - 1;
            nextCoordinates[1] = this.coordinates[1];
            nodeLinks[2] = new NodeLink(this.coordinates, nextCoordinates,this.getNode(nextCoordinates).getNodeLinks()[0].length());


        }
        if (coordinates[1] - 1 >= 0) {
            int[] nextCoordinates= new int[2];
            nextCoordinates[0] = coordinates[0];
            nextCoordinates[1] = coordinates[1]-1;
            nodeLinks[3] = new NodeLink(this.coordinates, nextCoordinates,this.getNode(nextCoordinates).getNodeLinks()[1].length());

        }
        return nodeLinks;
    }
    public HashMap<int[], Node> getMap() {
        return map;
    }
    public Node getNode(int[] node) {

        for (int[] e : map.keySet()) {
            if (Arrays.equals(e, node)) {
                return map.get(e);
            }
        }
        return null;
    }


    public int getMaxY() {
        return maxY;
    }

    public int getMaxX() {
        return maxX;
    }

}
