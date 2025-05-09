import java.util.*;

public class BOJ1715카드정렬하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            pq.offer(sc.nextInt());
        }

        while(pq.size() > 1){
            int n1 = pq.poll();
            int n2 = pq.poll();

            int cost = n1 + n2;
            sum += cost;
            pq.offer(cost);
        }
        System.out.println(sum);
    }
}
