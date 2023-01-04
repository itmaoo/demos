package mao.demo.calc.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra算法 狄克斯特拉算法
 */
public class ShortLineExample {
        long inf = Long.MAX_VALUE >> 2;
        int mod = (int) (1e9 + 7), n;
        int[] cnt;

        public int countPaths(int n, int[][] roads) {
            this.n = n;
            List<int[]>[] lists = new List[n];
            //初始化
            for (int i = 0; i < n; i++)
                lists[i] = new ArrayList<>();
            //无向图--》建图
            for (int[] road : roads) {
                int x = road[0], y = road[1], w = road[2];
                lists[x].add(new int[]{y, w});
                lists[y].add(new int[]{x, w});
            }
            //跑一遍dij
            dijkstra(lists);
            int c = 0;
            for (long d:cnt) {
                System.out.print((c++)+ "-" +d+"|");
            }
            return cnt[n - 1];
        }

        private void dijkstra(List<int[]>[] lists) {
            long[] dist = new long[n];
            Arrays.fill(dist, inf);
            dist[0] = 0;
            cnt = new int[n];
            boolean[] used = new boolean[n];
            //从点0的方式只有一种
            cnt[0] = 1;
            //按照距离，从小到大进行排序
            PriorityQueue<long[]> pri = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
            pri.add(new long[]{0L, 0L});
            while (!pri.isEmpty()) {
                long[] p = pri.poll();
                int x = (int) p[0];
                if (used[x]) continue;

                used[x] = true;
                for (int[] its : lists[x]) {
                    int y = its[0], w = its[1];
                    if (dist[y] > dist[x] + w) {
                        dist[y] = dist[x] + w;
                        cnt[y] = cnt[x];
                        pri.add(new long[]{y, dist[x] + w});
                    } else if (dist[y] == dist[x] + w)
                        //当前点的距离，已经被添加到堆中一次了
                        cnt[y] = (cnt[y] + cnt[x]) % mod;
                }
            }
            int c = 0;
            for(long l:dist){
                System.out.print((c++)+"-"+l+"|");
            }
            System.out.println();
        }
    }
