package code.gaurav;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinCostToConnectAllPoints {
    // min spanning tree concept to connect all points
    public static int minCostConnectPoints(int[][] points) {
            // vis , priority_queue, source destination
            // min spanning tree
            boolean[] vis = new boolean[points.length];
            int dist = 0;
            PriorityQueue<Pair<Integer,Integer>> pq = new PriorityQueue<>((p1,p2) -> p1.num1-p2.num1);
            pq.add(new Pair<>(0,0));

            while(!pq.isEmpty()){
                var temp = pq.poll();
                if(vis[temp.num2]){
                   continue;
                }
                dist = dist + temp.num1;
                vis[temp.num2] = true;
                for(int i=0;i<points.length;i++){
                    if(!vis[i]){
                        int distance = Math.abs(points[i][0] - points[temp.num2][0]) + Math.abs(points[i][1] - points[temp.num2][1]);
                        pq.add(new Pair<>(distance,i));
                    }
                }
            }
            return dist;
    }

    public static class Pair<T, T1> {
        public int num1;
        public int num2;
        public Pair(int num1,int num2){
            this.num1 = num1;
            this.num2 = num2;
        }

    }

    public static void main(String[] args) {
        //0,0],[2,2],[3,10],[5,2],[7,0]
        System.out.println(minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
    }
}
