import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            for(int i=0; i<N; i++){
                char[] c = br.readLine().toCharArray();
                for(int j=0; j<N; j++){
                    arr[i][j] = c[j]-'0';
                }
            } //입력끝

            System.out.println(re(0, 0, N));
        }
    }

    public static int re(int r, int c, int size) {
        int total = 0; //현재 쿠키조각의 총합
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                total += arr[i][j];
            }
        }

        if (size == 1) return total; //종료조건

        int half = size / 2;
        int x = total % 4; //먹을조각번호

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (i == x) continue;
            if (i == 0) sum += re(r, c, half); //0
            else if (i == 1) sum += re(r, c + half, half); //1
            else if (i == 2) sum += re(r + half, c, half); //2
            else sum += re(r + half, c + half, half); //3
        }

        return sum;
    }
}
