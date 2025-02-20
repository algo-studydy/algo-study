package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
	static int N,M;
	static int[][] room;
	static boolean[][] checkRoom;
	//0,1,2,3 순서로 (북,동,남,서) 
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int direction;
	static int currentX;
	static int currentY;
	static int cnt;
	static void Clean(int x,int y) {
		checkRoom[x][y]=true;
		cnt++;
	}
	static int MoveBackDir(int dir) {
		if(dir==0) {
			//북->남
			return 2;
		}
		else if(dir==1) {
			//동->서
			return 3;
		}
		else if(dir==2) {
			//남->북
			return 0;
		}
		else if(dir==3) {
			//서->동
			return 1;
		}
		return 5;
	}
	
	static boolean CaseCheck(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
				if (room[nextX][nextY] == 0) { // 벽이 아니여야함
					if (checkRoom[nextX][nextY] == false) {
						// 청소가 안된곳이 하나라도 있다면
						// case 3번
						return true;
					}
				}
				else {//벽이면 패스
					continue;
				}

			}
		}
		//반복문 다돌아서 온 경우 청소된곳이 없다는 듯이므로
		//case 2번
		return false;

	}
	
	
	static void Move(int x,int y,int dir) {
		//case1. 청소
		if(checkRoom[x][y]==false) {			
			Clean(x,y);
		}
		if(CaseCheck(x, y)==false) {
			//case 2번
			//1. 바라보는 방향 유지하며 후진
			if(0<=x+dx[MoveBackDir(dir)] && x+dx[MoveBackDir(dir)]<N&&0<=y+dy[MoveBackDir(dir)] && y+dy[MoveBackDir(dir)]<M) {
				//이동가능하면
				if(room[x+dx[MoveBackDir(dir)]][y+dy[MoveBackDir(dir)]]==0) {					
					Move(x+dx[MoveBackDir(dir)],y+dy[MoveBackDir(dir)],dir);
				}
				//2. 벽이라 불가능하다면 작동멈춤
				else {
					return;
				}
			}
		}
		else {
			//case 3번
			//1. 반시계 회전
			if(dir==0) {
				dir=3;
			}
			else {
				dir--;
				
			}
			//2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
			if(0<=x+dx[dir] && x+dx[dir]<N&&0<=y+dy[dir] && y+dy[dir]<M) {
				//이동가능하면
				if(checkRoom[x+dx[dir]][y+dy[dir]]==false) {					
					Move(x+dx[dir],y+dy[dir],dir);
				}
				else {
					Move(x,y,dir);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		checkRoom = new boolean[N][M];
		st = new StringTokenizer(bf.readLine());
		currentX = Integer.parseInt(st.nextToken());
		currentY = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<M;j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j]==1) {
					checkRoom[i][j]=true;
				}
				else {
					checkRoom[i][j]=false;					
				}
			}
		}
		
		Move(currentX,currentY,direction);
		System.out.println(cnt);
	}
}
