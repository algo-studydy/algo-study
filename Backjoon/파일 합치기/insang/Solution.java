import java.util.*;

public class BOJ20055컨베이어벨트위의로봇 {
    static LinkedList<int[]> belt;
    static int n, cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int k = sc.nextInt();  // 내구도가 0인 칸이 k 이상이면 종료

        belt = new LinkedList<>();
        for(int i = 0; i < n*2; i++){
            int durability = sc.nextInt();
            belt.offer(new int[] {durability, 0});
        }
        int answer = 0;
        cnt = 0;  // 내구도가 0인 칸
        while(cnt < k){
            answer++;
            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            moveBelt();
            // 2. 로봇이 다음 방향으로 이동 가능하면 이동
            moveRobot();
            // 3. 올리는 위치의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            putRobot();
        }

        System.out.println(answer);
    }

    private static void moveRobot() {
        // 먼저 올라간 로봇부터 이동시키기 위해 역순으로 순회
        for (int i = n-2; i >= 0; i--) {
            int[] cur = belt.get(i);
            int[] next = belt.get(i + 1);

            // 현재 칸에 로봇이 있고,
            // 다음 칸에 로봇이 없으며,
            // 다음 칸의 내구도가 1 이상이라면 이동 가능
            if (cur[1] == 1 && next[1] == 0 && next[0] > 0) {
                cur[1] = 0;
                next[1] = 1;
                next[0]--;  // 로봇이 이동한 칸의 내구도 감소
                if(next[0] == 0) cnt++;
            }
        }
        belt.get(n - 1)[1] = 0; // 내리는 위치면 내려줌
    }

    private static void putRobot() {
        int[] cur = belt.get(0);
        // 벨트의 내구도가 0보다 크고, 현재 칸에 로봇이 없으면 로봇 올림
        if(cur[0] > 0 && cur[1] == 0) {
            cur[0]--;
            cur[1] = 1;
            if(cur[0] == 0) cnt++;
        }
    }

    private static void moveBelt() {
        // 벨트 회전(Deque 함수 사용)
        belt.offerFirst(belt.pollLast());

        // 회전 후 N-1번 칸에 로봇이 있다면 내림
        belt.get(n-1)[1] = 0;
    }
}
