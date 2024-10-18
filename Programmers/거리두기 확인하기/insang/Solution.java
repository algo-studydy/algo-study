class Solution {
    static int[] dr = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dc = {1, 0, -1, 0, 1, 1, -1, -1};
    static String[][] map;

    public int[] solution(String[][] places) {
        int[] answer = {0, 0, 0, 0, 0};

        for(int i = 0; i < 5; i++){
            map = new String[5][5];

            for(int j = 0; j < 5; j++){
                map[j] = places[i][j].split("");
            }

            if(func()) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    public static boolean func() {
        // 모든 좌표를 돌며 응시자(P)가 위치한 좌표에서 8방 탐색 진행(상하좌우, 대각선)
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(map[i][j].equals("P")){
                    // 상하좌우 탐색
                    if(!check1(i, j)) return false;

                    // 대각선 탐색
                    if(!check2(i, j)) return false;
                }
            }
        }
        return true;
    }

    public static boolean check1(int r, int c){
        for(int i = 0; i < 4; i++){
            for(int j = 1; j <= 2; j++){
                int nr = r + dr[i] * j;
                int nc = c + dc[i] * j;

                if(nr >= 0 && nr < 5 && nc >= 0 && nc < 5){
                    if(map[nr][nc].equals("X")) break;
                    if(map[nr][nc].equals("P")) return false;
                }
            }
        }
        return true;
    }

    public static boolean check2(int r, int c){
        for(int i = 4; i < 8; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nr < 5 && nc >= 0 && nc < 5){
                if(!map[nr][nc].equals("P")) continue;

                if(!map[nr][c].equals("X") || !map[r][nc].equals("X")) {
                    return false;
                }
            }
        }
        return true;
    }
}