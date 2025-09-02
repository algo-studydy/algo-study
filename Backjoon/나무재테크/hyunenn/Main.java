
import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static StringTokenizer st;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static List<Integer>[][] trees;
    static List<Integer>[][] deadTrees;
    static int[][] fields;
    static int[][] foods;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fields = new int[N+1][N+1];
        deadTrees = new List[N+1][N+1];
        trees = new List[N+1][N+1];
        foods = new int[N+1][N+1];
        // 기존 초기 양분 세팅
        for(int i=1;i<=N;i++) {
            Arrays.fill(fields[i], 5);
        }
        // 나무 모을 배열 세팅
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                trees[i][j] = new ArrayList<>();
                deadTrees[i][j] = new ArrayList<>();
            }
        }
        // 겨울에 추가될 양분
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                foods[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 시작할때 심을 나무 위치와 값
        for(int m=0;m<M;m++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int tree = Integer.parseInt(st.nextToken());
            trees[r][c].add(tree);
        }

        // 매번, 나무들을 나이 순으로 정렬해야함
        for(int k=0;k<K;k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    trees[i][j].sort((a, b) -> a - b);
                }
            }
            spring();
            summer();
            autumn();
            winter();
        }

        int ans = 0;
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                ans += trees[i][j].size();
            }
        }
        System.out.println(ans);
    }

    public static void spring() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                List<Integer> tmp = new ArrayList<>();
                while(!trees[i][j].isEmpty()) {
                    int tree = trees[i][j].get(0);
                    // 여기서, 현재 나무의 나이가 땅의 양분보다 크면 그 즉시 남은 나무들은
                    // 복사하고 즉사 시키고 종료
                    if(tree > fields[i][j]) {
                        for(int k : trees[i][j]) {
                            deadTrees[i][j].add(k);
                        }
                        trees[i][j].clear();
                    }
                    // 아니라면, 새로운 리스트에 나이 + 1을 저장해둔다.
                    else {
                        fields[i][j] -= tree;
                        tmp.add(tree + 1);
                        trees[i][j].remove(0);
                    }
                }
                // 끝난 이후, 나무들을 다시 복사해서 사용한다.
                trees[i][j] = tmp;
            }
        }
    }

    public static void summer() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                // 죽은 나무들의 /2 만큼 양분을 추가한다.
                for(int k=0;k<deadTrees[i][j].size();k++) {
                    int tree = deadTrees[i][j].get(k) / 2;
                    fields[i][j] += tree;
                }
                deadTrees[i][j].clear();
            }
        }
    }

    public static void autumn() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                // 나무가 5의 배수 이상인 경우에만 8방 탐색으로, 나무 1을 모든 방향에 퍼뜨림
                for(int h=0;h<trees[i][j].size();h++) {
                    int tree = trees[i][j].get(h);
                    if(tree % 5 == 0) {
                        for(int k=0;k<8;k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if(inRange(nr, nc)) {
                                trees[nr][nc].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void winter() {
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                fields[i][j] += foods[i][j];
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r > 0 && r <= N && c > 0 && c <= N;
    }


    /**
     * 봄에는 나무가 자신의 나이만큼 양분을 먹고 , 1증가 한다. 단, 한 칸에는 여러개의 나무가 존재할 수 있다.
     * 나이가 어린 나무부터 양분을 먹는다. 땅에 양분이 부족해지면, 자신의 나이만큼 못먹는 나무는 즉사
     *
     * 여름에는 봄에 죽은 나무가 양분으로 변함. 각각 죽은 나무마다 나이를 2로 나눈 값이 양분으로 추가됨, 소숫점 아래 제거
     *
     * 가을에는 나무가 번식하는데, 5의 배수인 경우에만 번식하고, 8방 탐색으로 가능한 지역에 나이가 1인 나무 생성
     *
     * 겨울에는 주어진 입력의 값들의 양분이 추가된다.
     */
}
