import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            pq.add(sc.nextInt());
        }

        int ans =0;
        while(pq.size() != 1){
            int a = pq.poll();
            int b = 0;
            if(!pq.isEmpty()) b = pq.poll();
            ans += a+b;
            pq.add(a+b);
        }
        System.out.println(ans);
    }
}
