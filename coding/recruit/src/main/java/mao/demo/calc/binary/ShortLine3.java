package mao.demo.calc.binary;

import mao.demo.calc.binary.ShortLineExample;

import java.util.*;

public class ShortLine3 {
    public static void main(String[] args) {
        // int n = 2;
        // int[][] roads = {{1,0,10}};

        // int n = 7;
        // int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        //int n = 12;
        //int[][] roads = {{1,0,2348},{2,1,2852},{2,0,5200},{3,1,12480},{2,3,9628},{4,3,7367},{4,0,22195},{5,4,5668},{1,5,25515},{0,5,27863},{6,5,836},{6,0,28699},{2,6,23499},{6,3,13871},{1,6,26351},{5,7,6229},{2,7,28892},{1,7,31744},{3,7,19264},{6,7,5393},{2,8,31998},{8,7,3106},{3,8,22370},{8,4,15003},{8,6,8499},{8,5,9335},{8,9,5258},{9,2,37256},{3,9,27628},{7,9,8364},{1,9,40108},{9,5,14593},{2,10,45922},{5,10,23259},{9,10,8666},{10,0,51122},{10,3,36294},{10,4,28927},{11,4,25190},{11,9,4929},{11,8,10187},{11,6,18686},{2,11,42185},{11,3,32557},{1,11,45037}};

        int n = 27;
        int[][] roads = {{1, 0, 2942}, {1, 2, 5815}, {3, 2, 5383}, {2, 4, 15128}, {4, 0, 23885}, {3, 4, 9745}, {1, 4, 20943}, {5, 1, 19596}, {0, 5, 22538}, {3, 5, 8398}, {3, 6, 7826}, {7, 2, 1814}, {8, 7, 19465}, {8, 1, 27094}, {8, 5, 7498}, {4, 8, 6151}, {8, 3, 15896}, {8, 2, 21279}, {8, 0, 30036}, {9, 1, 21739}, {9, 2, 15924}, {4, 9, 796}, {5, 9, 2143}, {10, 3, 4692}, {10, 7, 8261}, {11, 0, 23427}, {6, 11, 1461}, {11, 3, 9287}, {0, 12, 39285}, {12, 1, 36343}, {10, 12, 20453}, {8, 12, 9249}, {12, 7, 28714}, {12, 6, 17319}, {9, 12, 14604}, {5, 12, 16747}, {13, 0, 45790}, {13, 5, 23252}, {12, 13, 6505}, {1, 13, 42848}, {7, 14, 38944}, {14, 8, 19479}, {14, 4, 25630}, {0, 14, 49515}, {2, 14, 40758}, {9, 14, 24834}, {12, 14, 10230}, {14, 13, 3725}, {14, 10, 30683}, {14, 5, 26977}, {6, 14, 27549}, {7, 15, 42945}, {15, 12, 14231}, {5, 15, 30978}, {14, 15, 4001}, {15, 11, 30089}, {15, 13, 7726}, {15, 8, 23480}, {15, 9, 28835}, {4, 15, 29631}, {0, 15, 53516}, {15, 6, 31550}, {2, 15, 44759}, {5, 16, 573}, {7, 16, 12540}, {16, 1, 20169}, {4, 17, 34351}, {17, 14, 8721}, {18, 7, 48566}, {18, 15, 5621}, {18, 16, 36026}, {17, 18, 901}, {2, 18, 50380}, {19, 2, 38766}, {12, 19, 8238}, {16, 19, 24412}, {20, 17, 1672}, {1, 20, 56966}, {20, 7, 49337}, {11, 20, 36481}, {20, 9, 35227}, {3, 20, 45768}, {20, 2, 51151}, {20, 5, 37370}, {16, 20, 36797}, {19, 20, 12385}, {0, 20, 59908}, {6, 20, 37942}, {20, 12, 20623}, {8, 20, 29872}, {20, 10, 41076}, {18, 20, 771}, {4, 20, 36023}, {14, 20, 10393}, {13, 20, 14118}, {21, 3, 46941}, {21, 18, 1944}, {8, 21, 31045}, {21, 6, 39115}, {14, 21, 11566}, {21, 15, 7565}, {21, 7, 50510}, {21, 16, 37970}, {13, 22, 5713}, {8, 23, 38014}, {12, 23, 28765}, {18, 23, 8913}, {20, 23, 8142}, {23, 15, 14534}, {6, 23, 46084}, {23, 9, 43369}, {13, 24, 23356}, {24, 21, 8065}, {6, 24, 47180}, {6, 25, 51342}, {22, 25, 21805}, {4, 25, 49423}, {25, 14, 23793}, {7, 25, 62737}, {13, 25, 27518}, {25, 17, 15072}, {25, 24, 4162}, {25, 8, 43272}, {16, 25, 50197}, {23, 25, 5258}, {25, 15, 19792}, {25, 20, 13400}, {25, 10, 54476}, {25, 5, 50770}, {25, 11, 49881}, {21, 25, 12227}, {25, 3, 59168}, {23, 26, 1769}, {26, 22, 18316}};//int n = 4;
        // int[][] roads = {{1,0,2942},{1,2,5815},{3,2,5383}};

        // int n = 13; int[][] roads = {{1,0,2942},{1,2,5815},{3,2,5383},{2,4,15128},{4,0,23885},{3,4,9745},{1,4,20943},{5,1,19596},{0,5,22538},{3,5,8398},{3,6,7826},{7,2,1814},{8,7,19465},{8,1,27094},{8,5,7498},{4,8,6151},{8,3,15896},{8,2,21279},{8,0,30036},{9,1,21739},{9,2,15924},{4,9,796},{5,9,2143},{10,3,4692},{10,7,8261},{11,0,23427},{6,11,1461},{11,3,9287},{0,12,39285},{12,1,36343},{10,12,20453},{8,12,9249},{12,7,28714},{12,6,17319},{9,12,14604},{5,12,16747}};

        // int n = 7;  int[][] roads = {{1,0,2},{1,2,5},{3,2,5},{2,4,15},{4,0,23},{3,4,9},{1,4,20},{5,1,19},{0,5,22},{3,5,8},{3,6,7}};

        System.out.println(roads.length);
        System.out.println(new Solution3().countPaths(n, roads));
        System.out.println(">>>>>>");
        System.out.println(new ShortLineExample().countPaths(n, roads));

    }
}

class Solution3 {
    public Map<Integer, Integer> nodeMin = new HashMap<>();
    public int[] countMin;
    public Integer n;
    public int[][] roads;
    public Map<Integer,List<int[]>> roadNext = new HashMap<>();

    public int countPaths(int n, int[][] roads) {
        //dest默认设置最大
        countMin = new int[n];
        Arrays.fill(countMin, 0);
        countMin[0] = 1;
        this.n = n;
        this.roads = doubleRoads(roads);
        roadMap();
        PriorityQueue<int[]> lines = getStartNode(this.roads);
        nodeMin.put(0, 0);
        calc(lines);
        int result = (int) (countMin[n - 1] % (1e9 + 7));
        return result;
    }

    private void roadMap() {
        for (int[] road : roads) {
            if (roadNext.get(road[0]) == null) {
                roadNext.put(road[0], new ArrayList<>());
            }
            roadNext.get(road[0]).add(road);
        }
    }


    public void calc(PriorityQueue<int[]> lines) {
        while (!lines.isEmpty()) {
            int[] node = lines.poll();
            int sum = node[3] + node[2];
            if (this.nodeMin.containsKey(node[1])) {
                if (sum <= this.nodeMin.get(node[1])) {
                    if (sum < this.nodeMin.get(node[1])) {
                        countMin[node[1]] = 1;
                    } else {
                        countMin[node[1]] = countMin[node[1]] + 1;
                    }
                    this.nodeMin.put(node[1], sum);
                } else {
                    continue;
                }
            } else {
                this.nodeMin.put(node[1], sum);
                countMin[node[1]] = 1;
            }

            if (node[1] == this.n - 1) {
                continue;
            } else {
                for (int[] nextNode : this.roadNext.get(node[1])) {
                    if (nextNode[1] == node[0]) {
                        continue;
                    }
                    if (this.nodeMin.containsKey(nextNode[1]) && this.nodeMin.get(nextNode[1]) < (sum + nextNode[2])) {
                        continue;
                    }
                    int[] nextNode2 = nextNode.clone();
                    nextNode2[3] = sum;
                    lines.add(nextNode2);
                }
            }
        }
    }


    private int[][] doubleRoads(int[][] roads) {
        int[][] roadsNew = new int[roads.length * 2][3];
        int count = 0;
        for (int[] road : roads) {
            int[] newRoad = new int[4];
            newRoad[0] = road[1];
            newRoad[1] = road[0];
            newRoad[2] = road[2];
            newRoad[3] = 0;
            int[] newRoad2 = new int[4];
            newRoad2[0] = road[0];
            newRoad2[1] = road[1];
            newRoad2[2] = road[2];
            newRoad2[3] = 0;
            roadsNew[count] = newRoad;
            count++;
            roadsNew[count] = newRoad2;
            count++;
        }
        return roadsNew;
    }

    public PriorityQueue<int[]> getStartNode(int[][] roads) {
        PriorityQueue<int[]> nodes = new PriorityQueue<int[]>((a,b)->Integer.compare(a[3],b[3]));
        nodes.addAll(this.roadNext.get(0));
        return nodes;
    }
}