package code.gaurav;

import java.util.*;

public class ConsistentHashing {
        Map<String, List<Integer>> serverData = new HashMap<>(); // mapping of server and their user list
        TreeMap<Integer, String> server = new TreeMap<>(); // mapping of hashval and server name
        Map<String, Integer> mapServer = new HashMap<>(); //mapping of server name and hashVal
        public ArrayList<Integer> solve(ArrayList<String> A, ArrayList<String> B, ArrayList<Integer> C) {
            serverData = new HashMap<>(); // mapping of server and their user list
            server = new TreeMap<>(); // mapping of hashval and server name
            mapServer = new HashMap<>(); //mapping of server name and hashVal
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < A.size(); i++) {
                String type = A.get(i);
                int hashValue = userHash(B.get(i), C.get(i));
                switch (type) {
                    case "ADD":
                        ans.add(addNewServer(B.get(i), hashValue));
                        break;
                    case "REMOVE":
                        ans.add(removeServer(B.get(i)));
                        break;
                    case "ASSIGN":
                        ans.add(assignServer(hashValue));
                        break;
                    default:
                        System.out.println("Invalid input type");
                        break;
                }
            }

            return ans;
        }

        private Integer addNewServer(String serverName, int hashValue) {
            //update in map
            server.put(hashValue,serverName);
            mapServer.put(serverName,hashValue);
            // update in serveData map
            serverData.put(serverName,new ArrayList<>());
            if(serverData.size() > 0){
                int nextNumber;
                if(server.ceilingKey(hashValue+1) == null){
                    nextNumber = server.firstKey();
                }else{
                    nextNumber = server.ceilingKey(hashValue+1);
                }
                String nextServer = server.get(nextNumber);
                List<Integer> list = serverData.get(nextServer);
                serverData.put(nextServer,new ArrayList<>());
                for(int i:list){
                    assignServer(i);
                }
            }
            return serverData.get(serverName).size();
        }
        private Integer removeServer(String serverName) {
            int hashValue = mapServer.get(serverName);
            if(server.get(hashValue).equals(serverName)){
                server.remove(hashValue);
            }
            List<Integer> list = serverData.get(serverName);
            serverData.remove(serverName);
            for(int i:list){
                assignServer(i);
            }
            return list.size();
        }
        private Integer assignServer(int hashValue) {
            int nextNumber;
            if(server.ceilingKey(hashValue) == null){
                nextNumber = server.firstKey();
            }else{
                nextNumber = server.ceilingKey(hashValue);
            }
            serverData.get(server.get(nextNumber)).add(hashValue);
            return hashValue;
        }


        public int userHash(String username, int hashKey) {
            long p = hashKey;
            long n = 360;
            long hashCode = 0;
            long p_pow = 1;
            for (int i = 0; i < username.length(); i++) {
                char character = username.charAt(i);
                hashCode = (hashCode + (character - 'A' + 1) * p_pow) % n;
                p_pow = (p_pow * p) % n;
            }
            return (int)hashCode;
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

