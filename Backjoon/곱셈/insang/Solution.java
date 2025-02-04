import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();

        System.out.println(pow(a, b, c));
    }

    public static long pow(long a, long b, long c) {
        // 지수가 1일 경우 a % c리턴
        if(b == 1) {
            return a % c;
        }

        // 지수의 절반에 해당하는 a^(b / 2) 을 구한다.
        long num = pow(a, b / 2, c);


        // 지수가 홀수라면 앞서 구한 지수의 절반 연산에 a을 한번 더 곱함
        if(b % 2 == 1) {
            return (num * num % c) * a % c;
        }
        return num * num % c;
    }
}