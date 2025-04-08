import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 벨트 내구도
        ArrayList<Integer> belt = new ArrayList<>();
        for(int i=0; i<N*2; i++){
            belt.add(sc.nextInt());
        }

        // 로봇 위치
        ArrayList<Boolean> robot = new ArrayList<>();
        for(int i=0; i<N; i++){
            robot.add(false);
        }

        int step = 0;

        while (true) {
            step++;

            // 1. 벨트와 로봇을 한 칸 회전
            belt.add(0, belt.remove(N*2-1));
            robot.add(0, false);
            robot.remove(robot.size()-1);

            // 내리는 위치 로봇 내리기
            if(robot.get(N-1)){
                robot.set(N-1, false);
            }

            // 2. 로봇 이동
            for(int i=N-2; i>=0; i--){
                if(robot.get(i) && !robot.get(i+1) && belt.get(i+1)>0){
                    robot.set(i, false);
                    if (i + 1 == N - 1) {
                        robot.set(i + 1, false); // 도착했으면 바로 내려감
                    } else {
                        robot.set(i + 1, true);
                    }
                    belt.set(i+1, belt.get(i+1)-1);
                }
            }


            // 3. 로봇 올리기
            if(belt.get(0) > 0 && !robot.get(0)){
                belt.set(0, belt.get(0)-1);
                robot.set(0, true);
            }

            // 4. 종료조건
            int count=0;
            for(int i=0; i<N*2; i++){
                if(belt.get(i) == 0) count++;
            }
            if(count >= K) break;
        }

        System.out.println(step);

    }
}
