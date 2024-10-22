import java.util.*;

class Solution {
    static String str;
    static int sLength;
    static int answer;
    
    static void initializeData(String s) {
        str = s;
        sLength = s.length();
        answer = 1;
    }
        
    static void solve() {
        for (int i = 0; i < sLength; i++) {
            if((sLength - i) <= answer) continue;
            for (int offset = sLength; offset > i; offset--) {
                if ((offset - i) <= answer) break;
                
                if (isPalindrome(i, offset)) {
                    answer = Math.max(answer, offset - i);
                }
            }
        }
    }
    
    static boolean isPalindrome(int startIdx, int endIdx) {
        int size = endIdx - startIdx;
        int p = 0;
        
        for (int i = startIdx; i < startIdx + size / 2; i++) {
            if (str.charAt(i) != str.charAt(endIdx - 1 - p)) {
                return false;
            }
            p++;
        }
        
        return true;
    }
    
    public int solution(String s) {
        initializeData(s);
        solve();
        return answer;
    }
}