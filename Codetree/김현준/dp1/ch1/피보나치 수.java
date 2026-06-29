package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        memo = new int[46];
        Arrays.fill(memo, -1);

        System.out.println(Fibo(N));
    }

    private static int Fibo(int idx) {
        if(memo[idx] != -1) {
            return memo[idx];
        }

        if(idx <= 2) {
            memo[idx] = 1;
        }
        else memo[idx] = Fibo(idx - 1) + Fibo(idx - 2);

        return memo[idx];
    }
}
