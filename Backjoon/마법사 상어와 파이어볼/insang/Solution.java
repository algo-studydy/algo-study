import java.util.*;

public class BOJ20056마법사상어와파이어볼 {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        // 각 칸마다 파이어볼 정보를 저장하는 리스트
        ArrayList<int[]>[][] map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        // 파이어볼의 좌표 관리용 리스트
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();
            map[r][c].add(new int[]{m, s, d});
            list.offer(new int[]{r, c});
        }

        for (int move = 0; move < K; move++) {
            // 1. 모든 파이어볼 이동 (이동 결과 newMap에 저장)
            ArrayList<int[]>[][] newMap = new ArrayList[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newMap[i][j] = new ArrayList<>();
                }
            }
            // 다음 턴에 사용할 위치 리스트
            LinkedList<int[]> newList = new LinkedList<>();

            while (!list.isEmpty()) {
                int[] cur = list.poll();
                int r = cur[0], c = cur[1];
                for (int[] ball : map[r][c]) {
                    int m = ball[0], s = ball[1], d = ball[2];
                    int nr = r + dr[d] * s;
                    int nc = c + dc[d] * s;

//                    if(nr > N-1) nr %= N;
//                    else if(nr < 0) nr = N - (Math.abs(nr) % N);
//
//                    if(nc > N-1) nc %= N;
//                    else if(nc < 0) nc = N - (Math.abs(nc) % N);
                    nr = (nr % N + N) % N;
                    nc = (nc % N + N) % N;

                    newMap[nr][nc].add(new int[]{m, s, d});
                }
                map[r][c].clear();  // 이동한 파이어볼을 기존 위치에서 제거
            }

            // 2. 2개 이상 있는 칸에서 합치고 4방향으로 분할
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int cnt = newMap[r][c].size();

                    // 파이어볼이 1개면 그대로 복사
                    if (cnt == 1) {
                        map[r][c].add(newMap[r][c].get(0));
                        newList.add(new int[]{r, c});
                    }
                    // 같은 칸에 2개 이상의 파이어볼 존재
                    else {
                        int sumM = 0, sumS = 0;
                        boolean allOdd = true, allEven = true;
                        for (int[] ball : newMap[r][c]) {
                            sumM += ball[0];
                            sumS += ball[1];
                            if (ball[2] % 2 == 0) allOdd = false;
                            else allEven = false;
                        }

                        int nm = sumM / 5;
                        if (nm == 0) continue; // 질량 0 파이어볼 소멸
                        int ns = sumS / cnt;
                        int[] dirs = (allOdd || allEven) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                        for (int d : dirs) {
                            map[r][c].add(new int[]{nm, ns, d});
                            newList.add(new int[]{r, c});
                        }
                    }
                }
            }
            // 다음 이동을 위해 파이어볼 위치 갱신
            list = newList;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                for (int[] a : map[i][j]) answer += a[0];
            }
        }

        System.out.println(answer);
    }
}
