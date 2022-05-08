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

    public void findEfficientPath(int[] bot, int[] station, String product) {

        int[] node = new int[2];

        double totalTime, accessTime, movementTime;
        totalTime = Integer.MAX_VALUE;
        List<int[]> nodesIndexesTraveled = new ArrayList<int[]>();
        List<int[]> nodesIndexesTraveledtemp = new ArrayList<int[]>();
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
                    totalTime =  movementTime + accessTime;;
                    nodesIndexesTraveledtemp.clear();
                    nodesIndexesTraveledtemp.addAll(nodesIndexesTraveled);
                }

            }
        }
        results = new Results(node, totalTime, nodesIndexesTraveledtemp.size() - 1, nodesIndexesTraveledtemp);

    }

    void reset() {
        grid.getMap().keySet().forEach(k -> {
            List<int[]> temp = new ArrayList<int[]>();
            temp.add(k);
            grid.getMap().get(k).setVisited(false);
            grid.getMap().get(k).setDistanceFromSource(1000);
            grid.getMap().get(k).getNodesIndexesTraveled().clear();
            grid.getMap().get(k).setNodesIndexesTraveled(temp);
        });
    }

    public double getShortestDistance(int[] fromNode, int[] toNode) {
        int[] nextNode = fromNode;
        boolean found =false;
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
            nextNode = this.getNodeShortestDistanced();
            if(!grid.getNode(toNode).isNotVisited()) {
                break;
            }
        }


        return grid.getNode(toNode).getDistanceFromSource();
    }

    int[] getNodeShortestDistanced() {
        int[] storedNodeIndex = {0, 0};
        double storedDist = 1000;
        NodeCoord =new int[2];
        for (int j = 0; j < grid.getMaxY(); j++) {
            for (int i = 0; i < grid.getMaxX(); i++) {
                NodeCoord[0] = i;
                NodeCoord[1] = j;

                double currentDist = grid.getNode(NodeCoord).getDistanceFromSource();
                if (grid.getNode(NodeCoord).isNotVisited() && currentDist < storedDist) {
                    storedDist = currentDist;
                    storedNodeIndex = new int[]{i, j};
                }
            }
        }
        System.out.print(storedNodeIndex[0]+" "+storedNodeIndex[1]+"      ");
        return storedNodeIndex;
    }
}