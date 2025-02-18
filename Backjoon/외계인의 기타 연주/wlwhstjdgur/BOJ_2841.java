package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2841 {
	public static void main(String[] args) throws IOException {
		// 몇번째 줄을 눌렀던간에 일단 프렛끼리만 먼저 구분해놔야된다.
		// 스택을 쓰되 프렛단위의 배열이 필요할것
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int cnt = 0;
		Stack<Integer>[] pretArr = new Stack[P + 1]; // 스택 배열 선언
		for (int i = 0; i < P; i++) {
			pretArr[i] = new Stack<Integer>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int guitarString = Integer.parseInt(st.nextToken());
			int pret = Integer.parseInt(st.nextToken());
			// 비어있으면 넣고
			// 안 비어있을때 자기보다 작은 프렛이 나올때까지 꺼내며 카운트
			if (pretArr[guitarString].empty()) {
				pretArr[guitarString].add(pret);
				cnt++;
			} else {
				if (pretArr[guitarString].peek() == pret) {
				} else if (pretArr[guitarString].peek() < pret) {
					pretArr[guitarString].add(pret);
					cnt++;
				} else if (pretArr[guitarString].peek() > pret) {
					// 기타 줄이 들어있고 자기보다 큰 프렛을 잡고있었다면 작은수가 나올때까지 빼야함
					while (true) {
						if (pretArr[guitarString].empty()) {
							pretArr[guitarString].add(pret);
							cnt++;
						} else {
							if (pretArr[guitarString].peek() == pret) {
								break;
							} else if (pretArr[guitarString].peek() > pret) {
								pretArr[guitarString].pop();
								cnt++;
							} else {
								pretArr[guitarString].add(pret);
								cnt++;
							}
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
