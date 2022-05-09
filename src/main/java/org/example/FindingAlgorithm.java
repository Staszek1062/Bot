package org.example;

import java.util.ArrayList;
import java.util.List;

public class FindingAlgorithm {
    private final Grid grid;
    Results results;
    int[] NodeCoord;

    public FindingAlgorithm(Grid grid) {
        this.grid = grid;
    }
    /**
     * Takes coordinates of bot and station. Calculate the least time-consuming path for bot-product-station
     * @param bot The coordinates of bot.
     * @param station The coordinates of station.
     * @param product The product name.
     */
    public void findEfficientPath(int[] bot, int[] station, String product) {

        int[] node = new int[2];

        double totalTime, accessTime, movementTime;
        totalTime = Integer.MAX_VALUE;
        List<int[]> nodesIndexesTraveled = new ArrayList<>();
        List<int[]> nodesIndexesTraveledtemp = new ArrayList<>();
        for (int[] k : grid.getMap().keySet()) {

            nodesIndexesTraveled.clear();

            if (grid.getMap().get(k).getStack().getVector().contains(product)) {
                if(grid.getMap().get(k).getIndex()=='O')
                continue;

                this.reset();

                movementTime = this.getShortestDistance(k, station);
                nodesIndexesTraveled.addAll(grid.getNode(station).getNodesIndexesTraveled());
                this.reset();

                movementTime = movementTime + this.getShortestDistance(bot, k);

                grid.getNode(k).getNodesIndexesTraveled().remove(0);
                nodesIndexesTraveled.addAll(grid.getNode(k).getNodesIndexesTraveled());



                if (totalTime > movementTime) {
                    accessTime = switch (grid.getMap().get(k).getIndex()) {
                        case 'H' -> 4 + 3 * grid.getMap().get(k).getStack().getVector().indexOf(product);
                        case 'B' -> 2 + 2 * grid.getMap().get(k).getStack().getVector().indexOf(product);
                        case 'S' -> 1 + grid.getMap().get(k).getStack().getVector().indexOf(product);
                        default -> 1000000;
                    };
                    node = k;
                    totalTime =  movementTime + accessTime;
                    nodesIndexesTraveledtemp.clear();
                    nodesIndexesTraveledtemp.addAll(nodesIndexesTraveled);
                }

            }
        }
        results = new Results(node, totalTime, nodesIndexesTraveledtemp.size() - 1, nodesIndexesTraveledtemp);

    }
    /**
     * Resets parameters of every node.
     */
    void reset() {
        grid.getMap().keySet().forEach(k -> {
            List<int[]> temp = new ArrayList<>();
            temp.add(k);
            if(grid.getNode(k).getIndex()!='O')
            grid.getMap().get(k).setVisited(false);
            grid.getMap().get(k).setDistanceFromSource(1000);
            grid.getMap().get(k).getNodesIndexesTraveled().clear();
            grid.getMap().get(k).setNodesIndexesTraveled(temp);
        });
    }
    /**
     * Takes 2 coordinates and calculate the shortest time needed to move to node.
     * @param fromNode The coordinates of start node.
     * @param toNode The coordinates end node.
     * @return Returns time needed to move start-move.
     */
    public double getShortestDistance(int[] fromNode, int[] toNode) {
        int[] nextNode = fromNode;
        int[] searchGrid = new int[4];
        searchGrid[0]=Math.min(Math.max(fromNode[0],toNode[0])+3,grid.getMaxX());
        searchGrid[1]=Math.min(Math.max(fromNode[1],toNode[1])+3,grid.getMaxY());
        searchGrid[2]=Math.max(Math.min(fromNode[0],toNode[0])-3,0);
        searchGrid[3]=Math.max(Math.min(fromNode[1],toNode[1])-3,0);

        grid.getNode(nextNode).setDistanceFromSource(0);

        for (int[] k : grid.getMap().keySet()) {

            for (NodeLink o : grid.getNode(nextNode).getNodeLinks()) {
                if (o != null && grid.getNode(nextNode).isNotVisited()) {

                    double approximation = o.length() + grid.getNode(nextNode).getDistanceFromSource();

                    if (approximation < grid.getNode(o.toNodeIndex()).getDistanceFromSource()) {
                        grid.getNode(o.toNodeIndex()).setDistanceFromSource(approximation);
                        grid.getNode(o.toNodeIndex()).setNodesIndexesTraveled(grid.getNode(o.fromNodeIndex()).getNodesIndexesTraveled());
                    }
                }
            }

            grid.getNode(nextNode).setVisited(true);
            nextNode = this.getNodeShortestDistanced(searchGrid);
            if(!grid.getNode(toNode).isNotVisited()) {
                break;
            }
        }


        return grid.getNode(toNode).getDistanceFromSource();
    }
    /**
     * Method check shortest unvisited node.
     * @return The coordinates of next shortest node.
     */
    private int[] getNodeShortestDistanced(int[] searchGrid) {
        int[] storedNodeIndex = {0, 0};
        double storedDist = Integer.MAX_VALUE;
        NodeCoord =new int[2];

        for (int j = searchGrid[3]; j < searchGrid[1]; j++) {

            for (int i = searchGrid[2]; i < searchGrid[0]; i++) {

                NodeCoord[0] = i;
                NodeCoord[1] = j;

                double currentDist = grid.getNode(NodeCoord).getDistanceFromSource();
                if (grid.getNode(NodeCoord).isNotVisited() && currentDist < storedDist) {
                    storedDist = currentDist;
                    storedNodeIndex = new int[]{i, j};
                }
            }
        }

        return storedNodeIndex;
    }
}