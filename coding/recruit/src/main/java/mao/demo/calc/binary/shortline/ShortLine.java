package mao.demo.calc.binary.shortline;

import java.util.*;

public class ShortLine {
    public static void main(String[] args) {
        int n = 7;
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        //int n = 12;
        //int[][] roads = {{1,0,2348},{2,1,2852},{2,0,5200},{3,1,12480},{2,3,9628},{4,3,7367},{4,0,22195},{5,4,5668},{1,5,25515},{0,5,27863},{6,5,836},{6,0,28699},{2,6,23499},{6,3,13871},{1,6,26351},{5,7,6229},{2,7,28892},{1,7,31744},{3,7,19264},{6,7,5393},{2,8,31998},{8,7,3106},{3,8,22370},{8,4,15003},{8,6,8499},{8,5,9335},{8,9,5258},{9,2,37256},{3,9,27628},{7,9,8364},{1,9,40108},{9,5,14593},{2,10,45922},{5,10,23259},{9,10,8666},{10,0,51122},{10,3,36294},{10,4,28927},{11,4,25190},{11,9,4929},{11,8,10187},{11,6,18686},{2,11,42185},{11,3,32557},{1,11,45037}};
        System.out.println(new Solution().countPaths(n, roads));
    }
}

class Solution {
    public Integer min = Integer.MAX_VALUE;
    public Integer count = 0;

    public Integer n;
    public int[][] roads;

    public int countPaths(int n, int[][] roads) {
        this.n = n;
        this.roads = roads;
        // roads = doubleRoads(roads);

        List<int[]> starts = getStartNode(roads);
        for (int[] start : starts) {

            calc(start, new HashMap());
        }

        int result = (int) (this.count % (Math.pow(10, 9) + 7));
        // return result;
        return result;

    }

    private int[][] doubleRoads(int[][] roads) {
        int[][] roadsNew = new int[roads.length * 2][3];
        int count = 0;
        for (int[] road : roads) {
            int[] newRoad = road.clone();
            int temp = newRoad[1];
            newRoad[1] = newRoad[0];
            newRoad[0] = temp;
            roadsNew[count] = newRoad;
            count++;
            roadsNew[count] = road;
            count++;
        }
        return roadsNew;
    }

    public void calc(int[] node, Map<Integer, Integer> line) {
        line.put(node[0], node[2]);
        int sum = 0;
        for (int v : line.values()) {
            sum = v + sum;
        }
        if (node[1] == this.n - 1) {
            line.put(node[1], 0);
            if (sum < this.min) {
                this.count = 0;
                this.min = sum;
            }
            if (sum == this.min) {
                this.count++;
            }
            return;
        }
        List<int[]> nextNodes = getNextNodes(roads, node);

        for (int[] nextNode : nextNodes) {
            if (!line.keySet().contains(nextNode[1]) && sum + nextNode[2] <= this.min) {
                HashMap map = new HashMap();
                map.putAll(line);
                calc(nextNode, line);
            }
        }
    }

    public List<int[]> getNextNodes(int[][] roads, int[] node) {
        List<int[]> nodes = new ArrayList();
        for (int[] road : roads) {
            if (road[0] == node[1]) {
                nodes.add(road);
            }
        }
        return nodes;
    }

    public List<int[]> getStartNode(int[][] roads) {
        List<int[]> nodes = new ArrayList();

        for (int[] road : roads) {
            if (road[0] == 0) {
                nodes.add(road);
            }
        }
        return nodes;
    }
}