import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        // a+b경우 저장
        List<Integer> sumList = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sumList.add(arr[i]+arr[j]);
            }
        }

        Collections.sort(sumList);

        // k를 큰 값부터 내려가며 탐색
        for(int i=N-1; i>=0; i--){
            int k = arr[i];
            for(int j=0; j<N; j++){
                int c = arr[j];
                int target = k - c;

                // 이분탐색으로 target 존재 여부 확인
                if(bSearch(sumList, target)){
                    System.out.println(k);
                    return;
                }
            }
        }
    }


    static boolean bSearch(List<Integer> list, int target){
        int left=0;
        int right=list.size()-1;

        while(left<=right){
            int mid=(left+right)/2;
            if(list.get(mid)==target){
                return true;
            }
            if(list.get(mid)<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return false;
    }
}
