import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int L = sc.nextInt(); //경사로 크기

        int[][] arr = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                arr[i][j] = sc.nextInt();
            }
        }



        int ans =0;
        // 가로줄 검사
        for(int i=0; i<N; i++) {
            int downCount=0; //내려가는 발판
            int upCount=1; //올라갈수 있는 발판
            int high=arr[i][0]; // 이전높이 저장용
            boolean possible = true;

            for(int j=1; j<N; j++) {
                int curr = arr[i][j]; //현재 높이

                if(curr == high) {
                    // down경사로 제작중
                    if(downCount > 0) {
                        if(downCount == L) {
                            downCount = 0;
                        }else{
                            downCount++;
                            if(downCount == L) {
                                downCount = 0;
                            }
                        }
                    } else {
                        upCount++;
                    }
                } else if(curr == high + 1) { // 올라가는 경우
                    //down경사로 만드는 도중이거나 up경사로 만드는 칸 부족 실패
                    if(downCount > 0 || upCount < L) {
                        possible = false;
                        break;
                    }
                    high = curr;
                    upCount = 1;
                    downCount = 0;
                } else if(curr == high - 1) { // 내려가는 경우
                    // down경사로 만드는 도중 실패
                    if(downCount > 0) {
                        possible = false;
                        break;
                    }
                    if(L != 1)downCount = 1;
                    upCount = 0;
                    high = curr;
                } else { // 높이 차가 2 이상
                    possible = false;
                    break;
                }

            }

            //끝까지 왔는데 down경사로 다 못만듬
            if(downCount > 0 && downCount < L) possible = false;
            if(possible) ans++;
        }

        //세로줄 검사(가로줄과 동일한로직)

        for(int i=0; i<N; i++) {
            int downCount=0; //내려가는 발판
            int upCount=1; //올라갈수 있는 발판
            int high=arr[0][i]; // 이전높이 저장용
            boolean possible = true;

            for(int j=1; j<N; j++) {
                int curr = arr[j][i]; //현재 높이

                if(curr == high) {
                    // down경사로 제작중
                    if(downCount > 0) {
                        if(downCount == L) {
                            downCount = 0;
                        }else{
                            downCount++;
                            if(downCount == L) {
                                downCount = 0;
                            }
                        }
                    } else {
                        upCount++;
                    }
                } else if(curr == high + 1) { // 올라가는 경우
                    //down경사로 만드는 도중이거나 up경사로 만드는 칸 부족 실패
                    if(downCount > 0 || upCount < L) {
                        possible = false;
                        break;
                    }
                    high = curr;
                    upCount = 1;
                    downCount = 0;
                } else if(curr == high - 1) { // 내려가는 경우
                    // down경사로 만드는 도중 실패
                    if(downCount > 0 ) {
                        possible = false;
                        break;
                    }
                    if(L != 1)downCount = 1;
                    upCount = 0;
                    high = curr;
                } else { // 높이 차가 2 이상
                    possible = false;
                    break;
                }

            }

            //끝까지 왔는데 down경사로 다 못만듬
            if(downCount > 0 && downCount < L) possible = false;
            if(possible) ans++;
        }

        //정답
        System.out.println(ans);
    }
}
