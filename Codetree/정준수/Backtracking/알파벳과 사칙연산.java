import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ct0605 {
    static String inputString;
    static int N;
    static ArrayList<String> list;
    static int ans;
    static String nums = "01234";
    static int[] visited;

    static void checkResult() {
        int value = Integer.parseInt(list.get(0));

        for(int i=2;i<list.size();i+=2){
            String operation = list.get(i-1);
            int num = Integer.parseInt(list.get(i));

            switch (operation) {
                case("+"): value = value + num; break;
                case("-"): value = value - num; break;
                case("*"): value = value * num; break;
            }
        }

        ans = Math.max(ans, value);
    }

    static void go(int idx) {
        if (idx == N) {
            checkResult();
            return;
        }

        char c = inputString.charAt(idx);
        int asciiDec = c - 'a';
        if (asciiDec < 0 || asciiDec > 25) { // 연산자
            list.add(String.valueOf(c));
            go(idx+1);
            list.remove(idx);
        } else { // a~f 알파벳
            if (visited[c - 'a'] > 0) {
                list.add(String.valueOf(visited[c - 'a']));
                go(idx + 1);
                list.remove(idx);
                return;
            }
            for (int i = 1; i <= 4; i++) {
                String numStr = String.valueOf(nums.charAt(i));
                list.add(numStr);
                visited[c - 'a'] = Integer.parseInt(numStr);
                go(idx+1);
                visited[c - 'a'] = 0;
                list.remove(idx);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        inputString = br.readLine();
        N = inputString.length();
        list = new ArrayList<>();
        ans = Integer.MIN_VALUE;
        visited = new int[26];

        go(0);

        System.out.print(ans);
    }
}
