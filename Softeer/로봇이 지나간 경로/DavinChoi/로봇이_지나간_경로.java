	package Study;
	
	import java.util.*;
	import java.io.*;
	
	public class 로봇이_지나간_경로 {
		static int W;
		static int H;
		static boolean[][] field; 
		static boolean[][] fieldCheck; 
		//북,동,남,서
		static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		static String answer;
		static int minSize = Integer.MAX_VALUE;
		static int[] resultCordi = new int[3]; // 결과 좌표 + 방향 담을 배열
	
		public static void main(String args[]) throws IOException {
			// 입력 받기, 초기화
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			field = new boolean[H][W];
			fieldCheck = new boolean[H][W];
			for (int row = 0; row < H; row++) {
				String[] rowLine = br.readLine().split("");
				for (int col = 0; col < W; col++) {
					String value = rowLine[col];
					if (value.equals("#")) {
						field[row][col] = false;
						fieldCheck[row][col] = false;
					} else {
						field[row][col] = true;
						fieldCheck[row][col] = true;
					}
				}
			}
			// dfs
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					if (!field[row][col]) {
						for (int way = 0; way < 4; way++) {
							int[] resCordi = new int[3]; // 결과 출력을위해 재귀 매개변수로 넘길 좌표 + 방향
							resCordi[0] = row;
							resCordi[1] = col;
							resCordi[2] = way;
							boolean[][] nextCheck = checkCopy(fieldCheck); // 복제
							nextCheck[row][col] = true;
							dfs(row, col, way, nextCheck, "", resCordi);
						}
					}
				}
			}
			
			System.out.println((resultCordi[0] + 1) + " " + (resultCordi[1] + 1));
			if (resultCordi[2] == 0) {
				System.out.println("^");
			} else if (resultCordi[2] == 1) {
				System.out.println(">");
			} else if (resultCordi[2] == 2) {
				System.out.println("v");
			} else if (resultCordi[2] == 3) {
				System.out.println("<");
			}
	
			System.out.println(answer);
		}
	
		public static void dfs(int row, int col, int way, boolean[][] beforeCheck, String res, int[] resCordi) {
			if (isAllTrue(beforeCheck) && res.length() < minSize) {
				// 길이가 최소이고 전부 방문하면 종료하고 결과 전용 전역 변수에 넣는다.
				minSize = res.length();
				answer = res;
				resultCordi = resCordi.clone();
				return;
			}
	
			// 직진
			boolean[][] nextCheck = checkCopy(beforeCheck);
			int[] d = dir[way];
			int forwardR = row + d[0];//직진 이동
			int forwardC = col + d[1];
			if (isIn(forwardR, forwardC) && !nextCheck[forwardR][forwardC]) {
				nextCheck[forwardR][forwardC] = true;
				forwardR += d[0];
				forwardC += d[1];
				if (isIn(forwardR, forwardC) && !nextCheck[forwardR][forwardC]) {
					nextCheck[forwardR][forwardC] = true;// 한번더 가능하면 이동
				} else {// 불가능한 경우 다시 뒤로이동
					forwardR -= d[0];
					forwardC -= d[1];
				}
				dfs(forwardR, forwardC, way, nextCheck, res + "A", resCordi);
			}
			// 오른쪽 이동
			nextCheck = checkCopy(beforeCheck);
			int nextR = (way + 1) % 4; // 오른쪽 방향 회전
			int[] rWay = dir[nextR];
			int nextRrow = row + rWay[0];
			int nextRcol = col + rWay[1];
			if (isIn(nextRrow, nextRcol) && !nextCheck[nextRrow][nextRcol]) {
				nextCheck[nextRrow][nextRcol] = true;
				dfs(nextRrow, nextRcol, nextR, nextCheck, res + "R", resCordi);
			}
	
			// 왼쪽이동
			nextCheck = checkCopy(beforeCheck);
			int nextL;
			if (way == 0) {
				nextL = 3;
			} else if (way == 1) {
				nextL = 0;
			} else if (way == 2) {
				nextL = 1;
			} else {
				nextL = 2;
			}
			int[] lWay = dir[nextL];
			int nextLRow = row + lWay[0];// 왼쪽 방향으로 이동
			int nextLCol = col + lWay[1];
			if (isIn(nextLRow, nextLCol) && !nextCheck[nextLRow][nextLCol]) {
				nextCheck[nextLRow][nextLCol] = true;
				dfs(nextLRow, nextLCol, nextL, nextCheck, res + "L", resCordi);// 가능하면 dfs 재귀
			}
		}
	
		// 방문 전부 체크
		public static boolean isAllTrue(boolean[][] checkTarget) {
	
			for (boolean[] rowTarget : checkTarget) {
				for (boolean b : rowTarget) {
					if (!b) {
						return false;
					}
				}
			}
			return true;
	
		}
	
		public static boolean[][] checkCopy(boolean[][] inCheck) {
			boolean[][] re = new boolean[H][W];
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					re[row][col] = inCheck[row][col];
				}
			}
			return re;
		}
	
		//경계선 벗어나는지 체크
		public static boolean isIn(int row, int col) {
			return row >= 0 && row < H && col >= 0 && col < W;
		}
	}
