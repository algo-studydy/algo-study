import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18869멀티버스2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());  // 우주의 개수
        int n = Integer.parseInt(st.nextToken());  // 행성의 개수

        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 0; i < m; i++){
            Integer[] arr = new Integer[n];
            TreeSet<Integer> temp = new TreeSet<>();

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int size = Integer.parseInt(st.nextToken());
                // 원본 배열과 정렬을 위한 배열
                arr[j] = size;
                temp.add(size);
            }

            // 배열의 구성이 같은게 존재하는지 정렬 후 set을 통해 확인(값이 들어가면 중복x)
            HashMap<Integer, Integer> map = new HashMap<>();
            int rank = 0;
            for(Integer size : temp){
                map.put(size, rank++);
            }

            int[] num = new int[n];
            for(int j = 0; j < n; j++){
                num[j] = map.get(arr[j]);
            }

            list.add(num);
        }

        int answer = 0;
        for(int i = 0; i < list.size()-1; i++){
            for(int j = i+1; j < list.size(); j++){
                boolean flag = true;
                for(int k = 0; k < n; k++){
                    if(list.get(i)[k] != list.get(j)[k]){
                        flag = false;
                        break;
                    }
                }
                if(flag) answer++;
            }
        }
        System.out.println(answer);
    }
}
