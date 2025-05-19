import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        long w, cnt;
        Pair(long w, long cnt) {
            this.w = w;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair p) {
            return Long.compare(this.w, p.w);
        }
    }
    static int N;
    static List<Pair> list = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long peoples = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            peoples += cnt;
            list.add(new Pair(w,cnt));
        }

        // 정렬 기준이 앞 뒤로
        Collections.sort(list);

        long sum = 0;
        long start = 0;
        for(Pair p : list) {
            sum += p.cnt;
            if(sum >= (peoples+1) / 2) {
                start = p.w;
                break;
            }
        }

        System.out.println(start);
    }
    // 5 6 5 9 -> 25 / 2 ->
    /*
    1. 마을 정보를 위치 기준으로 정렬
    2. 전체 인구 수 totalPeople = 모든 사람 수 합
    3. 왼쪽부터 누적합 peopleSum을 구하면서,
        peopleSum >= totalPeople / 2 이 되는 위치에 우체국을 세운다
     */
}
