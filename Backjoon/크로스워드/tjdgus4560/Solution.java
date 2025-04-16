import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        char[][] arr = new char[r][c];

        for(int i=0; i<r; i++){
            String s = sc.next();
            for(int j=0; j<c; j++){
                arr[i][j] = s.charAt(j);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>();
        StringBuilder sb;

        // 가로 단어 추출
        for(int i = 0; i < r; i++) {
            sb = new StringBuilder();
            for(int j = 0; j < c; j++) {
                if(arr[i][j] == '#') {
                    if(sb.length() >= 2) pq.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(arr[i][j]);
                }
            }
            if(sb.length() >= 2) pq.add(sb.toString());
        }

        // 세로 단어 추출
        for(int i = 0; i < c; i++) {
            sb = new StringBuilder();
            for(int j = 0; j < r; j++) {
                if(arr[j][i] == '#') {
                    if(sb.length() >= 2) pq.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(arr[j][i]);
                }
            }
            if(sb.length() >= 2) pq.add(sb.toString());
        }


        System.out.println(pq.poll());
    }
}
