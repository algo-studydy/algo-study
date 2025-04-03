package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_5052 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < n ; i++) {
            int m = Integer.parseInt(br.readLine());
            List<String> list = new ArrayList<>();
            for (int j = 0 ; j < m ; j++) {
                String s = br.readLine();
                list.add(s);
            }
            Collections.sort(list);
            boolean b = false;
            for (int j = 0 ; j < m  - 1; j++) {
                if (list.get(j + 1).startsWith(list.get(j))) {
                    b = true;
                    break;
                }
            }
            if (b) sb.append("NO").append("\n");
            else sb.append("YES").append("\n");
        }
        System.out.println(sb);
    }
}
