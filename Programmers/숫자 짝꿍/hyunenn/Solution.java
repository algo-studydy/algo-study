import java.util.*;

class Solution {
    static int[] A, B;
    public String solution(String X, String Y) {
        A = new int[10];
        B = new int[10];
        String answer = "";
        // 1. 현재 입력된 숫자의 갯수 체크
        for(int i=0;i<X.length();i++) {
            int curr = X.charAt(i) - '0';
            A[curr]++;
        }
        for(int i=0;i<Y.length();i++) {
            int curr = Y.charAt(i) - '0';
            B[curr]++;
        }

        // 2. 탐색을 통해, 겹치는 갯수만큼 PQ에 전달
        PriorityQueue<String> PQ = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<10;i++) {
            int min = Math.min(A[i], B[i]);
            while(min > 0) {
                PQ.offer(i+"");
                min--;
            }
        }

        // 3. PQ가 비었다면 짝꿍이 없으므로 -1
        if(PQ.isEmpty()) return "-1";
        // 3-1. PQ에 들어있는 값을 역정렬순으로 뽑아서 정답처리
        StringBuilder sb = new StringBuilder();
        while(!PQ.isEmpty()) {
            String s = PQ.poll();
            sb.append(s);
        }
        // 3-2. 만약에 "00" , "000" 과 같이 0은 모두 0처리
        if(sb.charAt(0) == '0') return "0";
        return sb.toString();
    }
}