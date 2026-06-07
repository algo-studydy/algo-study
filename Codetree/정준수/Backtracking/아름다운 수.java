import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ct0602 {
    static int N, ans;
    static ArrayList<Integer> list;

    static void checkBeauty() {
        boolean isBeauty = true;

        for (int i=0;i<N;i++) {
            int number = list.get(i);
            int count = 1;

            while (true) {
                i += 1;
                if (i >= N) break;
                if(list.get(i) != number) break;

                count += 1;
            }

            if (count % number != 0) {
                isBeauty = false;
                break;
            }
            i -= 1;
        }
        ans += isBeauty ? 1 : 0;
    }

    static void go(int count) {
        if (count == N) {
            checkBeauty();
            return;
        }

        for (int number=1;number<=4;number++) {
            list.add(number);
            go(count + 1);
            list.remove(count);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        ans = 0;

        go(0);

        System.out.print(ans);
    }
}
