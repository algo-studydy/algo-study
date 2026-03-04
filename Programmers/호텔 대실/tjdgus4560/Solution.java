import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (String[] book : book_time) {
            int start = toMinute(book[0]);
            int end = toMinute(book[1]) + 10; // 청소시간 포함

            if (!pq.isEmpty()){
                if(pq.peek() <= start) {
                    pq.poll();
                }
            }
            pq.offer(end);
        }

        return pq.size();
    }

    private int toMinute(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}