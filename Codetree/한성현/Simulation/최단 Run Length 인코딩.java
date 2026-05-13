import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        // Please write your code here.
        char[] c = A.toCharArray();
        int size = c.length;


        int min = Integer.MAX_VALUE;

        for(int i=0; i<size; i++){
            char tmp = c[size-1];
            for(int j=size-1; j>0; j--){
                c[j] = c[j-1];
            }
            c[0] = tmp;

            StringBuilder sb = new StringBuilder();
            char cur = c[0];
            sb.append(cur);
            int curSize = 1;
            for(int j=1; j<size; j++){
                if(cur != c[j]){
                    sb.append(curSize);
                    cur = c[j];
                    sb.append(cur);
                    curSize=1;
                }else{
                    curSize++;
                }
            }
            sb.append(curSize);

            min = Math.min(sb.length(), min);

        }

        System.out.println(min);
    }
}