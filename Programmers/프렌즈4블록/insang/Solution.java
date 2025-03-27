import java.util.*;
class Solution {
    static char[][] map;
    static boolean flag;  // 부서지는 블록이 있는지 판단하는 변수
    static boolean[][] visited;
    static int[] dr = {0, 1, 1};  // 동, 남동, 남
    static int[] dc = {1, 1, 0};
    static Queue<int[]> q = new ArrayDeque<>();

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];

        for(int i = 0; i < m; i++){
            map[i] = board[i].toCharArray();
        }

        // 1회 반복 당, 블록을 부수고 정렬하는 작업 수행
        // 더 이상 부술 수 있는 블록이 없으면 break
        while(true){
            flag = false;
            visited = new boolean[m][n];

            for(int i = 0; i < m-1; i++){
                for(int j = 0; j < n-1; j++){
                    // 지울 수 있는 블록을 모두 큐에 담음
                    if(map[i][j] != '.') check(map[i][j], i, j);
                }
            }

            // 지울 수 있는 블록이 없으면 break
            if(!flag) break;

            // 지울 수 있는 블록을 모두 지움
            while(!q.isEmpty()){
                int[] block = q.poll();
                map[block[0]][block[1]] = '.';
                answer++;  // 정답 증가
            }

            // 블록이 지워지고 남은 빈 자리를 채움
            sort(m, n);
        }
        return answer;
    }

    public static void check(char block, int r, int c){
        int cnt = 1;  // 같은 모양의 블록 개수 카운트

        for(int i = 0; i < 3; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            // 다음 블록이 현재 블록과 같으면 cnt + 1
            if(map[nr][nc] == block){
                cnt++;
            }
            else return;
        }

        // 2x2 형태로 4개의 블록이 붙어 있으면 블록들의 좌표를 큐에 담음
        if(cnt == 4){
            // 중복으로 큐에 담기는 걸 방지하기 위해 방문 배열 사용
            if(!visited[r][c]){
                visited[r][c] = true;
                q.offer(new int[] {r, c});
            }

            for(int i = 0; i < 3; i++){
                if(!visited[r+dr[i]][c+dc[i]]){
                    visited[r+dr[i]][c+dc[i]] = true;
                    q.offer(new int[] {r + dr[i], c + dc[i]});
                }
            }
            // while문이 종료되지 않도록 flag를 true로 변경
            flag = true;
        }
    }

    static void sort(int m, int n){
        // 제일 밑에 줄부터 탐색
        for(int i = m-1; i >= 0; i--){
            for(int j = 0; j < n; j++){
                char block = map[i][j];
                // 현재 좌표가 캐릭터라면
                if(map[i][j] != '.'){
                    int next = 1;
                    int row = i;
                    // map의 제일 아래로 이동 시킴
                    while(i + next < m && map[i+next][j] == '.'){
                        map[i+next][j] = block;
                        map[row][j] = '.';
                        row++;
                        next++;
                    }
                }
            }
        }
    }
}