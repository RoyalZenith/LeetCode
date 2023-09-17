package code.gaurav;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ShortestPathVisitingAllNodes {
    //Leetcode : 847
    /*
        BFS to check all level
     */
        public int shortestPathLength(int[][] graph) {
            int len = graph.length;
            Queue<int[]> q = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            for(int i=0;i<len;i++){
                q.offer(new int[]{1<<i,i,0});
                visited.add((1<<i)*12 + i);
            }
            int finalMask = (1<<len)-1;
            while(!q.isEmpty()){
                int size = q.size();
                while(size-- >0){
                    var temp = q.poll();
                    if(temp[0] == finalMask){
                        return temp[2];
                    }
                    for(int node:graph[temp[1]]){
                        int newMask = temp[0] | (1<<node) ;
                        int hashvalue = newMask * 12 + node;
                        if(!visited.contains(hashvalue)){
                            visited.add(hashvalue);
                            q.offer(new int[]{newMask,node,temp[2]+1});
                        }
                    }
                }

            }
            return -1;

        }
    }
