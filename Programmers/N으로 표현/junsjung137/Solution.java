import java.util.*;

class Solution {
    static int N, number;
    
    public int solution(int N, int number) {
        int answer = 0;
        Solution.n = n;
        Solution.number = number;
        
        go(0);
        
        return answer;
    }
    
    static int go(int value) {
        if (value == number) {
            
        }
        
        int next = value
        go(next + N);
        go(next - N);
        go(next * N);
        go(next / N);
    }
}