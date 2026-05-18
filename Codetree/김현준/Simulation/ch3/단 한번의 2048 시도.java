
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = new int[4][4];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<4;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String dir = br.readLine();
        switch (dir) {
            case "U": move(0); engraft(0); move(0); break;
            case "R": move(1); engraft(1); move(1); break;
            case "D": move(2); engraft(2); move(2); break;
            case "L": move(3); engraft(3); move(3); break;
        }

        printMap();
    }

    // U: 0, R: 1, D: 2, L: 3
    public static void engraft(int d) {
        int[][] tmp = new int[4][4];
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                tmp[i][j] = map[i][j];
            }
        }

        for(int j=0;j<4;j++) {
            if(d == 0) {
                for(int i=0;i<3;i++) {
                    if(map[i][j] == map[i+1][j]) {
                        tmp[i][j] += tmp[i+1][j];
                        tmp[i+1][j] = 0; i++;
                    }
                }
            }
            else if(d == 2) {
                for(int i=3;i>=1;i--) {
                    if(map[i][j] == map[i-1][j]) {
                        tmp[i][j] += tmp[i-1][j];
                        tmp[i-1][j] = 0; i--;
                    }
                }
            }
        }

        for(int i=0;i<4;i++) {
            if(d == 1) {
                for(int j=3;j>=1;j--) {
                    if(map[i][j] == map[i][j-1]) {
                        tmp[i][j] += tmp[i][j-1];
                        tmp[i][j-1] = 0; j--;
                    }
                }
            }
            else if(d == 3) {
                for(int j=0;j<3;j++) {
                    if(map[i][j] == map[i][j+1]) {
                        tmp[i][j] += tmp[i][j+1];
                        tmp[i][j+1] = 0; j++;
                    }
                }
            }
        }

        map = tmp;
//        System.out.println("여기는 합친 부분");
//        printMap();
//        System.out.println();
    }

    public static void move(int dir) {
        int[][] tmp = new int[4][4];

        if(dir == 0) {
            for(int j=0;j<4;j++) {
                int idx = 0;
                for(int i=0;i<4;i++) {
                    if(map[i][j] != 0) tmp[idx++][j] = map[i][j];
                }
            }
        }
        else if(dir == 1) {
            for(int i=0;i<4;i++) {
                int idx = 3;
                for(int j=3;j>=0;j--) {
                    if(map[i][j] != 0) tmp[i][idx--] = map[i][j];
                }
            }
        }
        else if(dir == 2) {
            for(int j=0;j<4;j++) {
                int idx = 3;
                for(int i=3;i>=0;i--) {
                    if(map[i][j] != 0) tmp[idx--][j] = map[i][j];
                }
            }
        }
        else if(dir == 3) {
            for(int i=0;i<4;i++) {
                int idx = 0;
                for(int j=0;j<4;j++) {
                    if(map[i][j] != 0) tmp[i][idx++] = map[i][j];
                }
            }
        }

        map = tmp;
    }

    public static void printMap() {
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}