import java.util.Scanner;

public class Main {
    static int[][] paper;
    static int white = 0, blue = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        paper = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                paper[r][c] = sc.nextInt();
            }
        }

        divideAndCount(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void divideAndCount(int sr, int sc, int size) {
        if (isUniform(sr, sc, size)) {
            if (paper[sr][sc] == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }

        int half = size / 2;
        divideAndCount(sr, sc, half); // 1번 영역
        divideAndCount(sr, sc + half, half); // 2번 영역
        divideAndCount(sr + half, sc, half); // 3번 영역
        divideAndCount(sr + half, sc + half, half); // 4번 영역
    }

    private static boolean isUniform(int sr, int sc, int size) {
        int color = paper[sr][sc];
        for (int r = sr; r < sr + size; r++) {
            for (int c = sc; c < sc + size; c++) {
                if (paper[r][c] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
