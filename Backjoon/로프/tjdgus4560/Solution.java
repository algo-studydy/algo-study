import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double[] arr = new double[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextDouble();
        }

        Arrays.sort(arr);


        double max = arr[n-1];

        int tmp=1; //while반복시 이전까지 진행된 i번째배열 검사하던거 저장용
        /*
        n 무게가 올때
        로프가 n/i 만큼 버텨야함
        오름차순 으로 정렬된 배열 역순으로 탐색 들수있으면 다음무게
         */
        L : while(true){
            for(int i=tmp; i<=n; i++){
                double w = max/i; //i개의 로프를 이용했을때 들어야하는 무게
                if(arr[n-i]>=w){
                    max+=1;
                    tmp = Math.max(tmp, i);
                    continue L;
                }
            }
            break;
        }

        System.out.println((int)max-1);
    }
}
