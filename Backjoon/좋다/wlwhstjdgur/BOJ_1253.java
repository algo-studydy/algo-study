package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		//
		int result = 0;
        for(int i = 0 ; i < N ; i++){
            int left = 0;
            int right = N-1;
            while(true){
                if(left == i) left++;
                else if(right == i) right--;
                if(left >= right) break;

                // 합이 더 크다면 더 작은 수와 왼쪽수 더함  right 값 변경
                if(arr[left] + arr[right] > arr[i]) right--;
                else if(arr[left] + arr[right] < arr[i]) left++;
                else{
                    result++;
                    break;
                }
            }
        }
        System.out.println(result);
	}
}
