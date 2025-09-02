
import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int next, cnt;
        Point(int next, int cnt) {
            this.next = next;
            this.cnt = cnt;
        }
    }
    static int N, M;
    static List<Integer>[] list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        System.out.println(findBacon());
    }

    public static int findBacon() {
        // 모든 유저들의 베이컨 값들을 구해야 함
        int ans = 0;
        int max = Integer.MAX_VALUE;
        for(int i=1;i<=N;i++) {
            int check = 0;
            Queue<Point> Q = new ArrayDeque<>();
            boolean[] v = new boolean[N+1];
            Q.offer(new Point(i, 1));
            v[i] = true;
            // 탐색할때, 시작하는 현재 값을 방문 처리 해놓고 시작
            while(!Q.isEmpty()) {
                Point p = Q.poll();
                for(int j : list[p.next]) {
                    // 방문하지 않은 경우에만, 케빈 방문 저장
                    if(!v[j]) {
                        v[j] = true;
                        Q.offer(new Point(j, p.cnt + 1));
                        check += p.cnt;
                    }
                }
            }

//            System.out.println(" 현재 노드 : " + i + " , 값 : " + check);
            if(max > check) {
                max = check;
                ans = i;
            }
        }
        return ans;
    }
}
