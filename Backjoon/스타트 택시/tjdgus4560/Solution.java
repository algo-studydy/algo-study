import java.util.*;

public class Main {
    public static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N; //map 크기
    static int M; //손님 수
    static int fuel; // 현재연료
    static int[][] map;
    static boolean[][] visited;
    static Point taxi;
    static ArrayList<Point> start; //손님 위치
    static ArrayList<Point> end; //목적지 위치
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        fuel = sc.nextInt();
        map = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        taxi = new Point(sc.nextInt()-1, sc.nextInt()-1);
        start = new ArrayList<>();
        end = new ArrayList<>();
        for(int i=0; i<M; i++){
            start.add(new Point(sc.nextInt()-1, sc.nextInt()-1));
            end.add(new Point(sc.nextInt()-1, sc.nextInt()-1));
        } // 입력 끝


        while(true){
            //1. 택시 위치부터 4방탐색bfs로 가장 가까운 손님 탐색
            int customerNum = searchCustomer();

            if( customerNum == -1){
                fuel=-1;
                break;
            }else{
                service(customerNum); // bfs로 목적지까지 거리 탐색
                M--;
                if(M==0)break;
            }

            if(fuel < 0) break;

        }

        System.out.println(fuel < 0 ? -1 : fuel);
    }


    private static int searchCustomer() {
        // 현재 택시 위치에 손님이 있는 경우 처리
        for(int i=0; i<start.size(); i++){
            Point cur = start.get(i);
            if(cur.r == -1 && cur.c == -1) continue;
            if(cur.r == taxi.r && cur.c == taxi.c){
                return i;
            }
        }

        Queue<Point> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.add(taxi);
        visited[taxi.r][taxi.c] = true;

        int count=0;
        ArrayList<Integer> mans = new ArrayList<>(); //탈수있는 모든 손님번호 저장용

        while(!q.isEmpty()){
            int size = q.size();

            for(int s=0; s<size; s++){
                Point p = q.poll();

                for(int d=0; d<4; d++){
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if(nr>=0 && nr<N && nc>=0 && nc<N){ // 외곽선 탐색
                        if(map[nr][nc] != 1 && !visited[nr][nc]){ // 벽여부, 방문여부에 따른 탐색
                            visited[nr][nc] = true;
                            q.add(new Point(nr, nc));

                            for(int i=0; i<start.size(); i++){
                                Point cur = start.get(i);
                                if(cur.r==-1 && cur.c==-1) continue;
                                if(cur.r==nr && cur.c==nc){
                                    mans.add(i);
                                }
                            }
                        }
                    }
                }
            }

            //현재 레벨에서 손님 여러명 태우기 가능시 행번호가깝고 열번호 가까운순 태우기
            if(!mans.isEmpty()){
                mans.sort( (a,b) ->{
                    Point p1 = start.get(a);
                    Point p2 = start.get(b);
                    if(p1.r != p2.r) return p1.r - p2.r;
                    return p1.c - p2.c;
                });
                fuel-=count+1;
                if(fuel < 0) return -1; //손님을 찾았으나 연료부족
                taxi = start.get(mans.get(0)); // 택시위치 손님위치로 보내기
                return mans.get(0); //태울 손님 번호 return
            }
            count++;
        }

        return -1; //손님 못찾음
    }

    private static void service(int num) {
        Queue<Point> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.add(taxi);
        visited[taxi.r][taxi.c] = true;

        Point endP = end.get(num);

        int count=0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int s=0; s<size; s++){
                Point p = q.poll();

                for(int d=0; d<4; d++){
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if(nr>=0 && nr<N && nc>=0 && nc<N){ // 외곽선 탐색
                        if(map[nr][nc] != 1 && !visited[nr][nc]){ // 벽여부, 방문여부에 따른 탐색
                            visited[nr][nc] = true;
                            q.add(new Point(nr, nc));

                            if(endP.r == nr && endP.c == nc){
                                fuel-=count+1;
                                if(fuel < 0){ //목적지 도착했으나 연료부족
                                    fuel= -1;
                                    return;
                                }

                                fuel += (count+1) * 2; //연료2배 채우기
                                taxi = end.get(num); //택시위치 갱신

                                //손님 제거
                                start.set(num, new Point(-1, -1));
                                end.set(num, new Point(-1, -1));
                                return;
                            }
                        }
                    }
                }
            }

            count++;
        }

        fuel= -1; //목적지 못찾음
    }
}
