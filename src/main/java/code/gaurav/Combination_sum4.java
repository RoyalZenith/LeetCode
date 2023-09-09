package code.gaurav;

import java.util.Map;

//LeetCode 377.
public class Combination_sum4 {

        public int combinationSum4(int[] nums, int target) {
            //using map
            // Map<Integer, Integer> mp = new HashMap<>();
            // return  genAllPossibleCom(nums,target,mp);
            //using dp
            Integer[] dp = new Integer[target+1];
            return genAllPossibleCom2(nums,target,dp);
        }
        //using map
        public static int genAllPossibleCom(int[] nums, int target, Map<Integer,Integer> mp){
            if(target == 0){
                return 1;
            }
            if(mp.containsKey(target)){
                return mp.get(target);
            }
            int ans = 0;
            for(int i=0;i<nums.length;i++){
                if(target - nums[i] >= 0){
                    ans = ans + genAllPossibleCom(nums,target-nums[i],mp);
                }
            }
            mp.put(target,ans);
            return ans;
        }
        //using dp
        public static int genAllPossibleCom2(int[] nums, int target, Integer[] dp){
            if(target == 0){
                return 1;
            }
            if(dp[target] != null){
                return dp[target];
            }
            int ans = 0;
            for(int num:nums){
                if(target-num >= 0){
                    ans += genAllPossibleCom2(nums,target-num,dp);
                }
            }
            return dp[target] = ans;

        }

}
