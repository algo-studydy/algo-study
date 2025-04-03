import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        long C = sc.nextLong();
        System.out.println(powMod(A,B,C));
    }

    private static long powMod(long base, long exp, long mod) {
        if(exp == 0){
            return 1;
        }
        // (a^b) mod c = [(a^b/2 mod c) × (a^b/2 mod c)] mod c 인 모듈러 연산의 성질 이용
        long half = powMod(base, exp/2, mod);
        long ans = (half * half) % mod;

        if(exp % 2 == 1){
            ans = (ans * base) % mod;
        }

        return ans;
    }
}
