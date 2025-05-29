import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11501주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] price = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            int profit = 0;
            int max = 0;

            // 뒤에서부터 탐색하며 최고 주가 기준으로 차액 계산
            for(int i = n-1; i >= 0; i--){
                // 가장 비싼 주가 갱신
                if (price[i] > max) {
                    max = price[i]; // 현재까지 가장 비싼 주가
                }
                // 가장 비싼 주가보다 현재 주가가 낮으면 판매
                else {
                    profit += max - price[i];
                }
            }
            System.out.println(profit);
        }

    }
}
