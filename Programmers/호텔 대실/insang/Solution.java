import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 시간을 분 단위로 변환해 저장
        int[][] times = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            String[] startSplit = book_time[i][0].split(":");
            String[] endSplit = book_time[i][1].split(":");

            int start = Integer.parseInt(startSplit[0]) * 60 + Integer.parseInt(startSplit[1]);
            int end = Integer.parseInt(endSplit[0]) * 60 + Integer.parseInt(endSplit[1]) + 10;

            times[i][0] = start;
            times[i][1] = end;
        }

        // 시작 시간 기준으로 오름차순 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        // 3. 우선순위 큐 (가장 빨리 비는 방의 종료 시간이 맨 앞으로 옴)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] time : times) {
            // System.out.println(Arrays.toString(time));
            // 가장 빨리 비는 방의 종료 시간이 현재 예약 시작 시간보다 작거나 같으면
            // 해당 방을 그대로 사용할 수 있음 (기존 종료 시간 제거)
            if (!pq.isEmpty() && pq.peek() <= time[0]) {
                pq.poll();
            }

            // 새 종료 시간(혹은 갱신된 종료 시간) 추가
            pq.offer(time[1]);
        }

        // pq의 크기 = 최소 객실 수
        return pq.size();
    }
}