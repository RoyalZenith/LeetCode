package code.gaurav;

import java.util.*;

public class avoidFloodInTheCity {
    public int[] avoidFlood(int[] rains) {
        // store ele with location
        Map<Integer,Integer> mp = new HashMap<>();
        // store empty location
        Set<Integer> st = new TreeSet<>();
        int[] ans = new int[rains.length];
        for(int i=0;i<rains.length;i++){
            int val = rains[i];
            if(mp.containsKey(val)){
                if(st.isEmpty()){
                    return new int[0];
                }
                int idx = upper(st,mp.get(val)-1);
                if(idx == -1){
                    return new int[0];
                }
                ans[idx] = rains[i];
                st.remove(idx);
                mp.put(rains[i],i+1);
                ans[i] = -1;
            }else if(rains[i] == 0){
                st.add(i);
            }else{
                mp.put(rains[i],i+1);
                ans[i] = -1;
            }
        }
        for (Integer i:st){
            ans[i] = 1;
        }
        return ans;
    }
    public static int upper(Set<Integer> st, int val){
        for(int i:st){
            if(i>val){
                return i;
            }
        }
        return -1;
    }

}
