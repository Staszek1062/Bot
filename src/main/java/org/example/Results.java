package org.example;

import java.util.List;

public record Results(int[] productCoords, double time, int nodesTraveled, List<int[]> nodesIndexesTraveled) {
}
