import java.util.*;

public class PROG지게차와크레인 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"ABC", "DEF", "GHI"}, new String[]{}));
    }

    static char[][] map;
    static int r, c, answer;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static int solution(String[] s, String[] re) {
        r = s.length;
        c = s[0].length();
        answer = r * c;

        map = new char[r+2][c+2];

        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], ' ');
        }

        // 공백(' ')으로 감싼 형태로 map 배열을 채움
        for (int i = 0; i < r; i++) {
            char[] temp = s[i].toCharArray();
            for (int j = 0; j < c; j++) {
                map[i + 1][j + 1] = temp[j];
            }
        }

        for(int i = 0; i <= r+1; i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();

        // 잉땅 윙땅
        for(String e : re){
            // 길이가 1이면 외부와 연결된 컨테이너만 꺼낼 수 있음
            if(e.length() == 1){
                connect(e);
            }
            // 외부와 연결되지 않아도 꺼낼 수 있음
            else unConnect(e);

            for(int i = 1; i <= r; i++){
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println();
        }

        return answer;
    }
    // 외부와 연결된 컨테이너만 제거
    private static void connect(String e) {
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){
                if(map[i][j] == e.charAt(0)){
                    // 4방탐색 후 외부와 연결된 컨테이너는 큐에 담음
                    for(int d = 0; d < 4; d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(map[nr][nc] == ' '){
                            q.offer(new int[] {i, j});
                            break;
                        }
                    }
                }
            }
        }

        // 외부와 연결된 컨테이너 일괄 처리
        while(!q.isEmpty()){
            int[] cur = q.poll();
            map[cur[0]][cur[1]] = ' ';
            func(cur[0], cur[1]);
            answer--;
        }
    }

    // 외부와 연결되지 않은 컨테이너도 제거
    private static void unConnect(String e) {
        for(int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == e.charAt(0)) {
                    // 외부와 연결된 컨테이너인지 판별하는 변수
                    boolean flag = false;

                    // 4방탐색 후 외부와 연결된 컨테이너라면 flag -> true
                    for(int d = 0; d < 4; d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(map[nr][nc] == ' '){
                            flag = true;
                        }
                    }

                    // 해당 컨테이너가 외부와 연결되어 있다면 ' '로, 연결되지 않았다면 '0'으로 변경
                    if(flag) {
                        map[i][j] = ' ';
                        func(i, j);

                    }
                    else map[i][j] = '0';
                    answer--;
                }
            }
        }
    }

    static void func(int r, int c){
        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(map[nr][nc] == '0') {
                map[nr][nc] = ' ';
                func(nr, nc);
            }
        }
    }
}

