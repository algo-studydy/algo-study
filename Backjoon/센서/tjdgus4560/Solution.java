import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=1; i<n; i++){
            pq.add(arr[i] - arr[i-1]);
        }

        int ans = 0;
        for(int i=0; i<n-k; i++){
            ans+=pq.poll();
        }

        System.out.println(ans);
    }
}
