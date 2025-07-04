import java.util.*;

public class Main {
    public static class Node {
        int s; // 시작위치
        int e; // 끝위치
        int d; // 거리

        public Node(int s, int e, int d) {
            this.s = s;
            this.e = e;
            this.d = d;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 지름길 개수
        int D = sc.nextInt(); // 고속도로 길이

        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int d = sc.nextInt();
            if (e <= D && e - s > d) { // 유효한 지름길만 추가
                list.add(new Node(s, e, d));
            }
        }

        list.sort((a, b) -> a.s - b.s); //시작 기준 정렬

        int[] dp = new int[D+1];
        dp[0] = 0;
        // 기본 도로 세팅
        for(int i=1; i<=D; i++){
            dp[i] = dp[i-1] + 1;
        }

        int idx = 0;
        for(int i=0; i<=D; i++){
            if(i>0) dp[i] = Math.min(dp[i], dp[i-1]+1);

            while (idx < list.size() && list.get(idx).s == i){
                Node node = list.get(idx);
                dp[node.e] = Math.min(dp[node.e], dp[i] + node.d);
                idx++;
            }
        }



        System.out.println(dp[D]);

    }
}
