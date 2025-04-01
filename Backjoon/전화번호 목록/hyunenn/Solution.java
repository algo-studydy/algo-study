
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 전화번호목록 {
    static int T, N;
    static HashSet<String> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            N = Integer.parseInt(br.readLine());
            set = new HashSet<>();
            String[] arr = new String[N];
            boolean flag = false;
            // 값을 모두 1개씩 넣고
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
                set.add(arr[i]);
            }
            // 이제 처음 값부터, 인덱스를 순차 탐색하면서 최소 ~ 최대 범위안에 set 에 있는 값이 있나 탐색
            for (int i = 0; i < N; i++) {
                String s = "";
                for (int j = 0; j < arr[i].length() - 1; j++) {
                    s += arr[i].charAt(j);
                    if(set.contains(s)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }

            if (flag) System.out.println("NO");
            else System.out.println("YES");
        }
    }
}
