import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int N; // 맵크기
    static int M; // 초기나무수
    static int K; // K년 수행
    static int[][] map; //양분의 수를 나타내는 지도
    static int[][] A; //매 겨울마다 주는 양분의 지도
    static PriorityQueue<Integer>[][] ageMap; // 각 칸에 심어져있는 나무의 나이들을 나타내는 지도
    static PriorityQueue<Integer>[][] deathMap; // 각 칸에 심어져있는 나무의 나이들을 나타내는 지도
    static int[] dr = {0,1,0,-1,-1,-1,1,1};
    static int[] dc = {1,0,-1,0,1,-1,1,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        map = new int[N][N];
        for(int[] arr : map){
            Arrays.fill(arr, 5);
        }
        A = new int[N][N];
        ageMap = new PriorityQueue[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                A[i][j] = sc.nextInt();
                ageMap[i][j] = new PriorityQueue<>();
            }
        }


        for(int i=0; i<M; i++){
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            ageMap[r][c].add(sc.nextInt());
        }

        while (K-->0){
            spring();
            summer();
            fall();
            winter();
        }

        int ans=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                ans += ageMap[i][j].size();
            }
        }
        System.out.println(ans);
    }

    private static void summer() {
        // 여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                while (!deathMap[i][j].isEmpty()){
                    map[i][j] += deathMap[i][j].poll()/2;
                }
            }
        }
    }

    private static void winter() {
        // 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] += A[i][j];
            }
        }
    }

    private static void fall() {
        // 가을에는 나무가 번식한다
        PriorityQueue<Integer>[][] tmp = new PriorityQueue[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                tmp[i][j] = new PriorityQueue<>();
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                while (!ageMap[i][j].isEmpty()){
                    int age = ageMap[i][j].poll();
                    if(age%5 == 0){
                        // 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
                        for(int d=0; d<8; d++){
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if(nr>=0 && nr<N && nc>=0 && nc<N){
                                tmp[nr][nc].add(1);
                            }
                        }
                    }
                    tmp[i][j].add(age);
                }
            }
        }

        ageMap = tmp;
    }

    private static void spring() {
        // 봄에는 나무가 자신의 나이만큼 양분을 먹고

        deathMap = new PriorityQueue[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                deathMap[i][j] = new PriorityQueue<>();
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                PriorityQueue<Integer> tmp = new PriorityQueue<>();
                while (!ageMap[i][j].isEmpty()){
                    int age = ageMap[i][j].poll();
                    if(map[i][j] >= age){
                        // 봄에는 나이만큼 양분을 먹고, 나이가 1 증가한다
                        map[i][j] -= age;
                        tmp.add(age+1);
                    }else{
                        // 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
                        deathMap[i][j].add(age);
                    }
                }
                ageMap[i][j] = tmp;
            }
        }
    }
}
