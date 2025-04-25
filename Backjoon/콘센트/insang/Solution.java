import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer> device = new ArrayList<>();
        int[] outlet = new int[m];
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            device.add(sc.nextInt());
        }
        // 리스트 내림차순 정렬
        Collections.sort(device, Collections.reverseOrder());
        for(int e : device) q.offer(e);

        int time = 0;
        while(true){
            boolean flag = true;  // 충전중인 기기 & 충전해야 할 기기 확인을 위한 변수

            for(int i = 0; i < m; i++){
                // 비어있는 콘센트에 기기 충전 시작
                if(!q.isEmpty() && outlet[i] == 0){
                    outlet[i] = q.poll();
                    // 충전할 기기가 존재한다는 의미
                    flag = false;
                }
            }

            for(int i = 0; i < m; i++){
                // 콘센트에 기기가 꽂혀 있으면 남은 시간 감소
                if(outlet[i] > 0) {
                    outlet[i]--;
                    // 충전중인 기기가 존재한다는 의미
                    flag = false;
                }
            }

            // 모든 콘센트가 비어있고, 충전이 필요한 기기가 없으면 반복문 종료
            if(flag) break;

            // 소요시간 증가
            time++;

        }

        System.out.println(time);
    }
}
