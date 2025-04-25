import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        Integer[] arr = new Integer[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr, Collections.reverseOrder()); //내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int time = 0;
        for(int i=0; i<n; i++){
            if(pq.size() >= m){
                time = pq.poll(); //전부 다 찬거이므로 현재 물리고있는 것중에 pq의 앞에있는 시간만큼은 무조건 소모
            }
            pq.add(arr[i] + time); //새로운 기기를 pq에 넣을때 현재까지 걸린 시간을 더해서 추가
        }

        // 가장 마지막까지 남아있는 기기의 걸린시간이 정답
        while(!pq.isEmpty()){
            time = pq.poll();
        }
        System.out.println(time);
    }
}
