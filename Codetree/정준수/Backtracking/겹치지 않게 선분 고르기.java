import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0604 {
    static class Segment {
        int r, l;

        Segment(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return String.format("l: "+l+", r: "+r);
        }
    }

    static final int MAX_LENGTH = 1_000 + 1;
    static int N;
    static ArrayList<Integer> pickList;
    static Segment[] lineList;
    static int ans;

    static void checkCount() {
        int lineCount = pickList.size();
        int removedCount = 0;

        boolean[] map =new boolean[MAX_LENGTH];
        for (int i=0;i<pickList.size();i++) {
            Segment s = lineList[pickList.get(i)];

            int l = s.l;
            int r = s.r;
            boolean isCollision = false;
            for (int idx=l;idx<=r;idx++) {
                if (map[idx]) isCollision = true;

                map[idx] = true;
            }
            if (isCollision) removedCount += 1;
        }

        ans = Math.max(ans, lineCount - removedCount);
    }

    static void go(int idx) {
        if (idx == N) {
            checkCount();
            return;
        }

        // 선분 선택
        pickList.add(idx);
        go(idx + 1);

        // 선분 미선택
        pickList.remove(pickList.size() - 1);
        go(idx + 1);
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lineList = new Segment[N];
        pickList = new ArrayList<>();
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            lineList[i] = new Segment(l, r);
        }

        go(0);

        System.out.print(ans);
    }
}
