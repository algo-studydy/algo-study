import java.util.*;

class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static char[][] map;
    static char[][] copyMap;
    static int n, m;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();
        map = new char[n][m];
        copyMap = new char[n][m];
        for(int i=0;i<n;i++) {
            String line = storage[i];
            for(int j=0;j<m;j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for(int i=0;i<requests.length;i++) {
            // 지도 복사
            for(int a=0;a<n;a++) {
                for(int b=0;b<m;b++) {
                    copyMap[a][b] = map[a][b];
                }
            }
            // 알파벳이 1개
            String alpa = requests[i];
            if(alpa.length() == 1) {
                removeOne(alpa.charAt(0));
            }
            // 알파벳이 2개 이상이면서, 같은 알파벳이 두 번 반복
            else if(alpa.length() >= 2 && alpa.charAt(0) == alpa.charAt(1)) {
                removeAll(alpa.charAt(0));
            }

            // 지도 옮겨담기
            for(int a=0;a<n;a++) {
                for(int b=0;b<m;b++) {
                    map[a][b] = copyMap[a][b];
                }
            }

            // System.out.println(i+"번째 시도 : ");
            // printMap();

            // 갯수 세기
            answer = countMap();
        }

        return answer;
    }

    static int countMap() {
        int cnt = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j] != '0') cnt++;
            }
        }
        return cnt;
    }
    // r = m, c = n
    // 알파벳 1개면 접근 가능한 값들만 꺼내고, 같은 알파벳이 2개 반복되면
    // 관련된 모든 값 제거

    static void printMap() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void removeOne(char c) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                // bfs를 돌려서 나갈 수 있으면 지운다
                if(map[i][j] == c) {
                    if(bfs(i, j)) copyMap[i][j] = '0';
                }
            }
        }
    }

    static void removeAll(char c) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j] == c) copyMap[i][j] = '0';
            }
        }
    }

    static boolean bfs(int r, int c) {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        Q.offer(new Point(r, c));

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 지도밖으로 나갈 수 있다면 true
                if(nr < 0 || nr > n-1 || nc < 0 || nc > m-1)
                    return true;
                // 0이면 빈자리니 이동 가능
                if(!v[nr][nc] && map[nr][nc] == '0') {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                }
            }
        }
        return false;
    }
}