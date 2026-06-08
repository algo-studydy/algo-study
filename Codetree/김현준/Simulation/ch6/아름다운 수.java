
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> list;
    static int N;
    static int ans;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        ans = 0;
        recursive(0);
        System.out.println(ans);
    }

    private static void recursive(int idx) {
        // basis
        if (idx == N) {
            if (isPossible()) {
                ans++;
//                for(int i : list) System.out.print(i + " ");
//                System.out.println();
            }

            return;
        }
        // inductive
        for (int i = 1; i <= 4; i++) {
            list.add(i);
            recursive(idx + 1);
            list.remove(list.size() - 1);
        }
    }

    private static boolean isPossible() {
        int[] arr = new int[5];
        arr[list.get(0)]++;
        // 중간에 값이 변하는 경우에 대한 처리
        for(int i=1;i<list.size();i++) {
            int before = list.get(i-1);
            int curr = list.get(i);
            if(curr != before) {
                if(arr[before] % before != 0) return false;
            }
            arr[curr]++;
        }
        // 마지막에 연속된 값이 왔을 경우를 고려한 제거
        for (int i = 1; i <= 4; i++)
            if (arr[i] % i != 0) return false;
        return true;
    }
}
