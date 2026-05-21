import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, M, K;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 블럭 크기
        K = Integer.parseInt(st.nextToken())-1; // K 부터 K + M - 1
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void solve() {
        int idx = N-1;
        // 행은 내려가면서 1을 만나기 전이 가장 놓을 수 있는 맨 윗자리
        for(int i=0;i<N;i++) {
            for(int j=K;j<K+M;j++) {
                if(map[i][j] == 1) idx = Math.min(idx, i-1);
            }
        }

        // 현 자리 기준 열 방향으로 ~ M 만큼 뻗어나갈 수 있냐를 검증
        while(idx >= 0) {
            if (check(idx, K)) {
                for (int j = K; j < K + M; j++) {
                    map[idx][j] = 1;
                }
                return;
            }
            idx--;
        }
    }

    private static boolean check(int r, int c) {
        // 1. 우선 범위 안에 있냐
        if(c + M > N) return false;
        // 2. 범위 안에 있으면 그 자리가 전부 0인가
        for(int j=c;j<c+M;j++) {
            if(map[r][j] != 0) return false;
        }

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
