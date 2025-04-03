package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int minNum = Integer.MAX_VALUE;
		int smallAns = 0;
		int bigAns = 0;
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		//정렬후 양쪽 끝단에서 더하고 더했을때 0이랑 가장 가까운것 검출(절댓값 작은거)
		//작으면 왼쪽 끝을 당긴다
		//크면 오른쪽 끝을 당긴다
		//중간에 합이 0이면 반복문 종료
		int left=0;
		int right=N-1;
		while(left<right) {
			int sum = arr[left]+arr[right];
			int result = Math.abs(sum);
			if(result<minNum) {
				minNum=result;
				smallAns = arr[left];
				bigAns = arr[right];
				if(sum==0) {
					break;
				}
			}
			if(sum<0) {
				left++;
			}
			else {
				right--;
			}
			
		}
		System.out.println(smallAns+" "+bigAns);
	}
}
