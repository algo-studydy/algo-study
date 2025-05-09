import java.util.*;

public class BOJ2212센서 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] sensor = new int[n];
        int[] dist = new int[n-1];
        int answer = 0;

        if(n < k) {
            System.out.println(0);
            return;
        }

        for(int i = 0; i < n; i++){
            sensor[i] = sc.nextInt();
        }
        Arrays.sort(sensor);

        // 센서 좌표의 총 길이 = 센서의 최대 좌표 - 최소 좌표 (ex. 7 - 1)
        int length = sensor[n-1] - sensor[0];

        // 현재 좌표와 다음 좌표값의 차이 저장
        for(int i = 0; i < n-1; i++){
            dist[i] = sensor[i+1] - sensor[i];
        }

        // 차이가 큰 순서로 k개의 좌표값을 누적
        Arrays.sort(dist);
        for(int i = 0; i < k-1; i++){
            answer += dist[n-2-i];
        }
        System.out.println(length - answer);
    }
}
