import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        ArrayList<Integer> lis = new ArrayList<>();

        // 전체 입력을 한바퀴 돌면서 O(N)
        for(int i = 0 ; i < n ; i++){
            int num = arr[i];
            int pos = binarysearch(lis, num);
            // 각 원소를 lis 리스트에 이분탐색으로 적절히 넣는다 O(logN)
            if(pos >= lis.size()){
                lis.add(num);
            }else{
                lis.set(pos, num);
            }
        }

        System.out.println(lis.size());
    }

    public static int binarysearch(ArrayList<Integer> lis, int target){
        int left = 0;
        int right = lis.size() - 1;

        while(left <= right){
            int mid = (left + right) / 2;
            if(lis.get(mid) >= target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
