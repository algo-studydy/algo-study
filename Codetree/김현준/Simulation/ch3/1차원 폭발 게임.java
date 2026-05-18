
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if(M == 1) {
            System.out.println(0);
            return;
        }

        while(true) {
            // 겹치는 갯수가 M개가 되면 터트린다?
            int cnt = 1;
            boolean exploded = false;
            for(int i=0;i<arr.length-1;i++) {
                if(arr[i] == arr[i+1]) cnt++;
                else {
                    if(cnt >= M) {
                        exploded = true;
                        // i=2 , cnt = 2,
                        for(int j=i;j>=i-(cnt-1);j--) {
                            arr[j] = 0;
                        }
                    }
                    cnt = 1;
                }
            }

            if(cnt >= M) {
                for(int j=arr.length-1;j>=arr.length-1 - (cnt-1);j--) {
                    exploded = true;
                    arr[j] = 0;
                }
            }

            if(!exploded) break;

            // 0이 되었으면 밑으로 내린다
            int idx = arr.length-1;
            int[] tmp = new int[arr.length];
            for(int i=arr.length-1;i>=0;i--) {
                if(arr[i] != 0) tmp[idx--] = arr[i];
            }

            arr = tmp;
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<arr.length;i++) {
                if(arr[i] != 0) list.add(arr[i]);
            }
            arr = new int[list.size()];
            for(int i=0;i<list.size();i++) {
                arr[i] = list.get(i);
            }
        }


        System.out.println(arr.length);
        for(int i=0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }


}

/**
 * 4 2
 * 1
 * 2
 * 2
 * 1
 *
 * -> 0
 */