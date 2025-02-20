import java.util.*;
import java.io.*;

public class Main {
    static int H, W;
    static char[][] grid;
    static int[] dRow = {-1,0,1,0};
    static int[] dCol = {0,1,0,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        grid = new char[H][W];
        for(int i = 0; i < H; i++){
            grid[i] = br.readLine().toCharArray();
        }
        ArrayList<int[]> endpoints = new ArrayList<>();
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(grid[i][j]=='#'){
                    int cnt = 0;
                    for(int d = 0; d < 4; d++){
                        int nr = i + dRow[d], nc = j + dCol[d];
                        if(nr>=0 && nr<H && nc>=0 && nc<W && grid[nr][nc]=='#'){
                            cnt++;
                        }
                    }
                    if(cnt==1) endpoints.add(new int[]{i,j});
                }
            }
        }
        int[] start = endpoints.get(0);
        ArrayList<int[]> path = new ArrayList<>();
        path.add(start);
        int pr = -1, pc = -1;
        int cr = start[0], cc = start[1];
        while(true){
            boolean ok = false;
            for(int d = 0; d < 4; d++){
                int nr = cr + dRow[d], nc = cc + dCol[d];
                if(nr>=0 && nr<H && nc>=0 && nc<W && grid[nr][nc]=='#' && (nr!=pr || nc!=pc)){
                    pr = cr; pc = cc;
                    cr = nr; cc = nc;
                    path.add(new int[]{cr,cc});
                    ok = true;
                    break;
                }
            }
            if(!ok) break;
        }
        ArrayList<Integer> dirs = new ArrayList<>();
        for(int i = 1; i < path.size(); i++){
            int[] prev = path.get(i-1);
            int[] cur = path.get(i);
            int dir = 0;
            if(cur[0]-prev[0]==-1 && cur[1]-prev[1]==0) dir = 0;
            else if(cur[0]-prev[0]==0 && cur[1]-prev[1]==1) dir = 1;
            else if(cur[0]-prev[0]==1 && cur[1]-prev[1]==0) dir = 2;
            else if(cur[0]-prev[0]==0 && cur[1]-prev[1]==-1) dir = 3;
            dirs.add(dir);
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < dirs.size()){
            int curDir = dirs.get(i);
            int j = i;
            while(j < dirs.size() && dirs.get(j)==curDir) j++;
            int cnt = j - i;
            for(int k = 0; k < cnt/2; k++){
                sb.append("A");
            }
            if(j < dirs.size()){
                int nextDir = dirs.get(j);
                if((curDir+3)%4==nextDir) sb.append("L");
                else sb.append("R");
            }
            i = j;
        }
        char initDir = '^';
        int initDir = dirs.get(0);
        if(initDir==0) initSym = '^';
        else if(initDir==1) initSym = '>';
        else if(initDir==2) initSym = 'v';
        else if(initDir==3) initSym = '<';
        System.out.println((start[0]+1) + " " + (start[1]+1));
        System.out.println(initSym);
        System.out.println(sb.toString());
    }
}