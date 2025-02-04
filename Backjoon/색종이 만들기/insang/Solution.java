import java.util.*;

public class Main {
    static int white, blue;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        func(0, 0, n);
        System.out.println(white);
        System.out.println(blue);

    }

    private static void func(int r, int c, int size) {
        if(check(r, c, size)) {
            if(map[r][c] == 1) blue++;
            else white++;
            return;
        }

        func(r, c + size/2, size/2);  // 1사분면 탐색
        func(r, c, size/2);  // 2사분면 탐색
        func(r + size/2, c, size/2);  // 3사분면 탐색
        func(r + size/2, c + size/2, size/2);  // 4사분면 탐색
    }

    // 탐색 영역이 모두 같은 색인지 판별하여 true 또는 false 리턴
    private static boolean check(int r, int c, int size) {
        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                // 탐색 영역의 가장 처음 색과 나머지 영역의 색을 비교
                if(map[r][c] != map[i][j]) return false;
            }
        }
        return true;
    }
}