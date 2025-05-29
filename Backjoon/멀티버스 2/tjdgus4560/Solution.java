import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 우주의 갯수
        int m = Integer.parseInt(st.nextToken()); // 행성의 갯수

        int[][] arr = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 좌표압축
        for(int i=0; i<n; i++){

            // 1. 중복제거, 정렬을 위해 treeset 사용
            Set<Integer> set = new TreeSet<>();
            for(int num : arr[i]){
                set.add(num);
            }

            //2. map으로 인덱스 저장
            Map<Integer, Integer> map = new HashMap<>();
            int idx=0;
            for(int num : set){
                map.put(num,idx++);
            }

            // 압축된 번호로 저장
            for(int j=0; j<m; j++){
                arr[i][j] = map.get(arr[i][j]);
            }
        }

        int ans =0;
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(Arrays.equals(arr[i], arr[j])) ans++;
            }
        }

        System.out.println(ans);
    }
}
