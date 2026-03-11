import java.util.*;

class Solution {

    public int solution(String[][] book_time) {
        int[][] arr = new int[book_time.length][2];
        // 1. 시간을 분으로 변환 후 정렬
        for(int i=0;i<book_time.length;i++) {
            arr[i][0] = toMin(book_time[i][0]);
            arr[i][1] = toMin(book_time[i][1]);
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        // 2. PQ에
        for(int i=0;i<arr.length;i++) {
            int start = arr[i][0];
            int end = arr[i][1] + 10;
            if(!PQ.isEmpty() && PQ.peek() <= start) {
                PQ.poll();
            }

            PQ.offer(end);
        }

        int answer = PQ.size();
        return answer;
    }

    private static int toMin(String s) {
        String[] a = s.split(":");
        int sum = 0;
        sum += Integer.parseInt(a[0]) * 60 + Integer.parseInt(a[1]);
        return sum;
    }
}