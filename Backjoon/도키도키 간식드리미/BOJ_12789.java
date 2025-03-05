package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789 {
	public static void main(String[] args) throws IOException {
		// 스택 두개 사용하고
		// 다음 순번이 나올때까지 옮길건데
		// 양쪽다 만족 못하는 경우가 생기면 실패
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		// 오른쪽 줄
		Queue<Integer> rightQueue = new LinkedList<>();
		// 아래 줄
		Stack<Integer> bottomStack = new Stack<Integer>();
		int startNumber = 1;
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			int person = Integer.parseInt(st.nextToken());
			rightQueue.offer(person);
		}

		while (true) {
			if (!rightQueue.isEmpty()) {
				if (rightQueue.peek() == startNumber) {
					rightQueue.poll();
					startNumber++;
					continue;
				}
				else if (!bottomStack.empty() && bottomStack.peek() == startNumber) {
					bottomStack.pop();
					startNumber++;
					continue;
				}
				else {
					bottomStack.push(rightQueue.peek());
					rightQueue.poll();
					continue;
				}
				
			}
			else {
				if (!bottomStack.empty() && bottomStack.peek() == startNumber){
					bottomStack.pop();
					startNumber++;
					continue;
				}
			}
			break;

		}
		if (rightQueue.isEmpty() && bottomStack.empty()) {
			System.out.println("Nice");
		} else {
			System.out.println("Sad");
		}
	}
}
