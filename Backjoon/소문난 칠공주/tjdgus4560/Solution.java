import java.util.*;

public class Main {
    static char[][] map = new char[5][5];
    static boolean[] selected = new boolean[25]; //조합으로 선택된 7명 담을 배열
    static int answer = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        re(0, 0);
        System.out.println(answer);
    }

    // depth는 현재까지 뽑은 개수, start는 다음에 고를 index
    private static void re(int depth, int start) {
        if (depth == 7) {
            if (bfs()) {
                answer++;
            }
            return;
        }

        /**
         * 2차원 배열에서 n개를 선택하는 조합을 만들때
         * 5*5 크기의 배열에서 (1,0)번을 6번으로 들고가는 1차원으로 생각하는
         * 테크닉
         */
        for (int i = start; i < 25; i++) {
            selected[i] = true;
            re(depth + 1, i + 1);
            selected[i] = false;
        }
    }

    // 뽑은 7개가 인접했는지, S가 4명 이상인지 판단
    private static boolean bfs() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            if (selected[i]) {
                list.add(i);
            }
        }


        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[7];
        queue.add(list.get(0));
        visited[0] = true;

        int connected = 1;
        int sCount = 0;

        if (map[list.get(0) / 5][list.get(0) % 5] == 'S') {
            sCount++;
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int r = cur / 5;
            int c = cur % 5;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int next = nr * 5 + nc;

                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5){
                    for (int i = 0; i < 7; i++) {
                        if (!visited[i] && list.get(i) == next) {
                            visited[i] = true;
                            queue.add(next);
                            connected++;

                            if (map[nr][nc] == 'S') {
                                sCount++;
                            }
                        }
                    }
                }
            }
        }

        //연결이 7개 되어있고 다솜파 가4명 이상이면 true
        if(connected == 7 && sCount>=4){
            return true;
        }else{
            return false;
        }

    }
}
