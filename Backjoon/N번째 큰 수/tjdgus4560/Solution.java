import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //내림차순 pq 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                pq.add(sc.nextInt());
            }
        }

        for(int i=0; i<n-1; i++){
            pq.poll();
        }

        System.out.println(pq.peek());
    }
}
