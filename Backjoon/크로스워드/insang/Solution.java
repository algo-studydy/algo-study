import java.util.*;

public class BOJ1706크로스워드 {
    static char[][] map;
    static List<String> words;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        map = new char[r][c];
        words = new ArrayList<>();

        for(int i = 0; i < r; i++){
            String word = sc.next();
            for(int j = 0; j < c; j++){
                map[i][j] = word.charAt(j);
            }
        }

        // 0: 가로, 1: 세로
        search(r, c, 0);
        search(c, r, 1);

        // 리스트 오름차순 정렬 후, 첫 번째 요소 출력
        Collections.sort(words);
        System.out.println(words.get(0));
    }

    private static void search(int r, int c, int d) {
        // 가로 or 세로 방향으로 단어 탐색
        for(int i = 0; i < r; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < c; j++){
                // 가로 or 세로 방향으로 한줄을 모두 sb에 저장
                if(d == 0) sb.append(map[i][j]);
                else sb.append(map[j][i]);
            }
            // #을 기준으로 split
            String[] word = sb.toString().split("#");
            for(String w : word){
                // 나눠진 단어가 2글자 이상이면 리스트에 add
                if(w.length() >= 2) words.add(w);
            }
        }
    }
}
