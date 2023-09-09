package code.gaurav;

import java.util.ArrayList;
import java.util.List;

//Leetcode 118.

public class pascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<numRows;i++){
            List<Integer> temp = new ArrayList<>();
            for(int j=0;j<=i;j++){
                if(j==0 || j == i){
                    temp.add(1);
                }else{
                    int val = ans.get(i-1).get(j-1)+ans.get(i-1).get(j);
                    temp.add(val);
                }
            }
            ans.add(temp);
        }
        return ans;
    }
}


