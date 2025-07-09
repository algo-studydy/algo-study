import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static List<Integer> odd = new ArrayList<>(); // 양수
    static List<Integer> even = new ArrayList<>(); // 음수
    static int zero = 0, one = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if(a < 0) even.add(a);
            else if(a == 0) zero++;
            else if(a == 1) one++;
            else odd.add(a);
        }

        even.sort((a, b) -> a - b); // 순정렬
        odd.sort((a, b) -> b - a); // 역정렬

//        for(int i : even) {
//            System.out.print(i + " ");
//        }
//
//        System.out.println();
//        for(int i : odd) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        System.out.println("zero : " + zero);
//        System.out.println("one : " + one);

        int ans = 0;
        int peek = 0;
        while(!even.isEmpty()) {
            if(peek == 0) {
                peek = even.get(0);
                even.remove(0);
            } else {
                peek *= even.get(0);
                even.remove(0);
                ans += peek;
                peek = 0;
            }
        }
        if(zero == 0 && peek != 0) ans += peek;

        peek = 0;
        while(!odd.isEmpty()) {
            if(peek == 0) {
                peek = odd.get(0);
                odd.remove(0);
            } else {
                peek *= odd.get(0);
                odd.remove(0);
                ans += peek;
                peek = 0;
            }
        }

        ans += one;
        if(peek != 0) ans += peek;

        System.out.println(ans);
    }
    // 양수, 음수를 따로 나눠서 리스트에 저장
    // 양수는 큰 수를 기반으로 정렬, 음수는 작은 수를 기반으로 정렬
    // 각 리스트가 1개가 남을때까지 계속 곱해서 결과값에 더해준다.
    // 각 리스트의 남은 1개의 값들은 더해준다.
    // ex) -1 2 1 3 -> 6
}