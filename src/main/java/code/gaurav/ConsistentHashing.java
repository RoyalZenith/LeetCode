package code.gaurav;

import java.util.*;

public class ConsistentHashing {
    public ArrayList<Integer> solve(ArrayList<String> A, ArrayList<String> B, ArrayList<Integer> C) {
        List<Set<Integer>> machines = new ArrayList<>();
        Map<String,Integer> mp = new HashMap<>();
        for(int i=0;i<360;i++){
            machines.add(i,null);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<A.size();i++){
            int hashValue= userHash(B.get(i),C.get(i));
            switch(A.get(i)){
                case "ADD":
                    if(mp.containsValue(hashValue)){
                        mp.put(B.get(i),hashValue);
                        break;
                    }
                    mp.put(B.get(i),hashValue);
                    ans.add(addNewServer(machines,hashValue));
                    break;
                case "REMOVE":
                    ans.add(removeServer(machines,mp.get(B.get(i))));
                    mp.remove(B.get(i));
                    break;
                case "ASSIGN":
                    ans.add(assignKey(machines,hashValue));
                    break;
                default:
                    System.out.println("not a valid input");
                    break;
            }

        }
        return ans;

    }
    public int userHash(String username, int hashKey){
        final long p = hashKey;
        final long n = 360;
        long hashCode = 0;
        long p_pow = 1;
        for (int i = 0; i < username.length(); i++) {
            char character = username.charAt(i);
            hashCode = (hashCode + (character - 'A' + 1) * p_pow) % n;
            p_pow = (p_pow * p) % n;
        }
        return (int)hashCode;
    }

    public  int addNewServer(List<Set<Integer>> machines, int hashValue){
        int nextAvailableMachine = getNextAvailableMachine(machines,hashValue);
        if(nextAvailableMachine == -1){
            machines.set(hashValue,new HashSet<>());
            return 0;
        }
        Set<Integer> keys = machines.get(nextAvailableMachine);
//        int left = getPrevAvailableMachine(machines,hashValue);
//        int right = nextAvailableMachine;

        machines.set(hashValue, new HashSet<>());

        //update the new machine added key from nextAvailableMachine
        boolean flag = nextAvailableMachine > hashValue;
        int left = Math.min(nextAvailableMachine,hashValue);
        int right = Math.max(nextAvailableMachine,hashValue);
        for(int key:keys){
            boolean flag2 = key > left && key< right;
            if(flag && !flag2){
                machines.get(hashValue).add(key);
            }
            if (!flag && flag2) {
                machines.get(hashValue).add(key);
            }
        }
        for(int key:machines.get(hashValue)){
            machines.get(nextAvailableMachine).remove(key);
        }
//        machines.set(hashValue, keys);
        //remove the key from the machine
//        machines.set(nextAvailableMachine, null);
        return machines.get(hashValue).size();

    }

    public int removeServer(List<Set<Integer>>  machines, int hashValue){
        // return 0;
        int nextAvailableMachine = getNextAvailableMachine(machines,hashValue);
        if(nextAvailableMachine == -1){
            machines.set(hashValue, null);
            return 0;
        }
        Set<Integer> keys = machines.get(hashValue);
        //update the keys to nextAvailableMachine from curr machine
        if(keys==null){
            return 0;
        }
        int cnt = 0;
        for(int key:keys){
            cnt++;
            machines.get(nextAvailableMachine).add(key);
        }
//        machines.set(nextAvailableMachine, keys);

        //remove the key from the curr machine
        machines.set(hashValue,null);

        return cnt;
    }
    public int assignKey(List<Set<Integer>>  machines, int hashValue){
        int nextAvailableMachine = getNextAvailableMachine(machines,hashValue);
        if(nextAvailableMachine == -1){
            return -1;
        }
        machines.get(nextAvailableMachine).add(hashValue);
        return hashValue;
    }
    public synchronized int getNextAvailableMachine(List<Set<Integer>>  machines, int hashValue){
        for(int i=hashValue+1;i<360;i++){
            if(machines.get(i) != null){
                return i;
            }
        }
        for(int i=0;i<hashValue;i++){
            if(machines.get(i) != null){
                return i;
            }
        }
        return -1;

    }

    public int getPrevAvailableMachine(List<Set<Integer>> machines, int hashValue) {
        for(int i=hashValue-1;i>=0;i--){
            if(machines.get(i) != null){
                return i;
            }
        }
        for(int i=359;i>hashValue;i--){
            if(machines.get(i) != null){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ConsistentHashing consistentHashing = new ConsistentHashing();
//        ArrayList<String> A = new ArrayList<>(Arrays.asList("ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"));
//        ArrayList<String> B = new ArrayList<>(Arrays.asList("INDIA","GYQF","SSAH","DVTQ","RUSSIA","ZIVQ","VBWW","ACDW","CHINA","YNXC","MWUN","NECZ","GERMANY","OOHQ","RSTZ","WRJJ","INDIA","YLDR","XDFH","SCCV","RUSSIA","QECH","WPCA","ZLVQ","CHINA","RQPJ","PFWJ"));
//        System.out.println(consistentHashing.solve(A,B,new ArrayList<>(Arrays.asList(947,613,821,701,193,683,19,467,503,347,433,887,971,587,509,283,727,359,443,883,499,487,853,223,137,13,739))));
        ArrayList<String> A = new ArrayList<>(Arrays.asList("ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","ADD","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN","ASSIGN","REMOVE","ASSIGN","ASSIGN"));
        ArrayList<String> B = new ArrayList<>(Arrays.asList("INDIA","GYQF","SSAH","DVTQ","RUSSIA","ZIVQ","VBWW","ACDW","CHINA","YNXC","MWUN","NECZ","GERMANY","OOHQ","RSTZ","WRJJ","INDIA","YLDR","XDFH","SCCV","RUSSIA","QECH","WPCA","ZLVQ","CHINA","RQPJ","PFWJ"));
        System.out.println(consistentHashing.solve(A,B,new ArrayList<>(Arrays.asList(947,613,821,701,193,683,19,467,503,347,433,887,971,587,509,283,727,359,443,883,499,487,853,223,137,13,739))));
//        System.out.println(consistentHashing.userHash("RUSSIA",193)+" "+ consistentHashing.userHash("CHINA",503));
    }
}

