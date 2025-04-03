package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {
	static int M,N,H;
	static int[][][] field;
	static int[][][] cnt;
	static int[] dx = { 0,0,1,-1,0,0 };
	static int[] dy = { 1,-1,0,0,0,0 };
	static int[] dz = { 0,0,0,0,1,-1 };
	static int maxcnt = 0;
	static Queue<pair> q = new LinkedList<>();
	static class pair{
		int x;
		int y;
		int z;
		public pair(int z, int x,int y) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	static void BFS() {
		while(!q.isEmpty()) {
			int currentX = q.peek().x;
			int currentY = q.peek().y;
			int currentZ = q.peek().z;
			q.poll();
			for(int i=0;i<6;i++) {
				int nextX = currentX+dx[i];
				int nextY = currentY+dy[i];
				int nextZ = currentZ+dz[i];
				//경계 안넘고
				if(0<=nextX&&nextX<N&&0<=nextY&&nextY<M&&0<=nextZ&&nextZ<H){
					if(field[nextZ][nextX][nextY]==0) {
						q.offer(new pair(nextZ,nextX,nextY));
						cnt[nextZ][nextX][nextY] = cnt[currentZ][currentX][currentY] + 1;
						field[nextZ][nextX][nextY]=1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		M= Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		field = new int[H][N][M];
		cnt = new int[H][N][M];
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				st = new StringTokenizer(bf.readLine());
				for(int k=0;k<M;k++) {
					field[i][j][k] = Integer.parseInt(st.nextToken());
					if(field[i][j][k]==1) {
						q.offer(new pair(i,j,k));
						cnt[i][j][k]=0;
					}
				}
			}
		}
		BFS();
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<M;k++) {
					if(field[i][j][k] ==0) {
						System.out.println(-1);
						return;
					}
					if(maxcnt<=cnt[i][j][k]) {
						maxcnt=cnt[i][j][k];
					}
				}
			}
		}
		System.out.println(maxcnt);
	}
}
