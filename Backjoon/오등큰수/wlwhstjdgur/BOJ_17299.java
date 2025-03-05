package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17299 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		int N = Integer.parseInt(st.nextToken());
		int[] dataArr = new int[N];
		int[] cntArr = new int[1000001];
		int[] answerArr = new int[N];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			dataArr[i] = number;
			cntArr[number]++;
		}

		for (int i = 0; i < N; i++) {
			if (stack.empty()) {
				stack.push(i);
			} else {
				// 지금 인덱스의 개수가 이전수보다 커지면 이전것들 다 해당 데이터로 치환
				while (!stack.empty() && cntArr[dataArr[stack.peek()]] < cntArr[dataArr[i]]) {
						answerArr[stack.peek()] = dataArr[i];
						stack.pop();
				}
				stack.push(i);
			}
		}
		while (!stack.empty()) {
			answerArr[stack.peek()] = -1;
			stack.pop();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(answerArr[i]+ " ");
		}
		System.out.println(sb.toString());
	}
}
