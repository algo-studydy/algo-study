import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static LinkedList<Integer> list = new LinkedList<>();
    static int[] cal;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cal = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        // 0. 역정렬
        list.sort((a, b) -> b - a);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i) + " ");
//        }

        int totalTime = 0;
        while (!list.isEmpty() || !isCalEmpty()) {
            int time = Integer.MAX_VALUE;
            // 1. 콘센트 중 빈 슬롯에 작업을 할당
            for(int i=0;i<M;i++) {
                if(cal[i] == 0 && !list.isEmpty()) {
                    cal[i] = list.poll();
                }
            }
            // 2. cal 에서 현재 스케줄링이 돌고 있는 최솟값 찾기
            for (int i = 0; i < M; i++) {
                if (cal[i] > 0) time = Math.min(time, cal[i]);
            }
            // 3. 최소 시간을 기준으로, 모든 스케줄링 시간 갱신
            for (int i = 0; i < M; i++) {
                if (cal[i] > 0) cal[i] -= time;
            }
            // 4. 가장 먼저 끝난 스케줄링을 기준으로 전체 시간 갱신
            totalTime += time;

        }
        System.out.println(totalTime);
    }

    static boolean isCalEmpty() {
        for (int i = 0; i < M; i++) {
            if (cal[i] > 0) return false;
        }
        return true;
    }
}
