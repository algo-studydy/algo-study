import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        Long[] query = new Long[q];
        List<Long> list = new ArrayList<>();
        HashMap<Long, Integer> hMap = new HashMap<>();
    
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            long value = Long.parseLong(st.nextToken());
            list.add(value);
            hMap.put(value, i);
        }
        for (int i = 0; i < q; i++) {
            query[i] = Long.parseLong(br.readLine());
        }

        Collections.sort(list);
        for (int i = 0; i < n; i++) {
            hMap.replace(list.get(i), i);
        }
        /*
        for (int i = 0; i < n; i++) {
            System.out.print("["+i+"]"+hMap.get(list.get(i)) +" ");
        }*/ 

        for (int i = 0; i < q; i++) {
            Long qValue = query[i];
            Object value = hMap.get(qValue);
            int queryIdx = value == null? -1 : (int) value;
            int beforeCount = queryIdx - 0;
            int afterCount = n - 1 - queryIdx;
            int NoC = 0;
            
            if (queryIdx != -1 && beforeCount != 0 && afterCount != 0) NoC = beforeCount * afterCount;
            sb.append(NoC).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}
