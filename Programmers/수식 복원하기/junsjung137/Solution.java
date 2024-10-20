import java.io.*;
import java.util.*;

class Solution {
    static int n;
    static String[] expressions;
    static final int[] bases = new int[] {2, 3, 4, 5, 6, 7, 8, 9};
    static int[] tryBase;
    static List<String> ans;
    
    static void initializeData(String[] expressions) {
        n = expressions.length;
        Solution.expressions = expressions;
        tryBase = new int[8];
        ans = new ArrayList<>();
    }
    
    static void solve() {
        for (int i = 0; i < n; i++) {
            String[] expression = expressions[i].split(" ");
            int value1 = Integer.valueOf(expression[0]);
            int sign = "-".equals(expression[1]) ? -1 : 1;
            int value2 = Integer.valueOf(expression[2]);
            int value3 = 0;
            boolean completeExpression = !"X".equals(expression[expression.length - 1]);
            
            if (!completeExpression) {
                ans.add(expressions[i]);
            }
            else {
                value3 = Integer.valueOf(expression[expression.length - 1]);
            }
            
            for (int j = 0; j < bases.length; j++) {
                if (tryBase[j] == -1) continue;
                if(!isBaseNumber(bases[j], value1, value2, value3)) {
                    tryBase[j] = -1;
                    continue;
                }
                if(completeExpression) {
                    if(!expression[expression.length - 1].equals(operation(bases[j], sign, value1, sign * value2))) {
                        tryBase[j] = -1;
                        continue;
                    }
                }
                tryBase[j] = 1;
            }
        }

        int size = ans.size();
        for (int i = 0; i < size; i++) {
            String value3 = "";
            for (int j = 0; j < bases.length; j++) {
                if (tryBase[j] == -1) continue;
                
                String[] expression = ans.get(i).split(" ");
                int sign = "-".equals(expression[1]) ? -1 : 1;
                int value1 = Integer.valueOf(expression[0]);
                int value2 = Integer.valueOf(expression[2]);
                String result = operation(bases[j], sign, value1, sign * value2);
                
                if (value3 != "" && !value3.equals(result)) {
                    value3 = "?";
                    break;
                }
                value3 = result;
            }
            
            ans.set(i, ans.get(i).replace("X", value3));
        }
    }
    
    static boolean isBaseNumber(int base, int value1, int value2, int value3) {
        while ((value1 % 10) != 0 || (value2 % 10) != 0 || (value3 % 10) != 0) {
            int q1 = value1 % 10;
            int q2 = value2 % 10;
            int q3 = value3 % 10;
            
            if (q1 >= base || q2 >= base || q3 >= base) return false;
            
            value1 /= 10;
            value2 /= 10;
            value3 /= 10;
        }
        
        return true;
    }
    
    static String operation(int base, int sign, int value1, int value2) {
        if (value1 + value2 == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        int v1 = Math.abs(value1);
        int v2 = Math.abs(value2);
        int decimal1 = 0;
        int decimal2 = 0;
        int sum = 0;
        int weight = 1;
        
        while ((v1 % 10) != 0 || (v2 % 10) != 0) {
            decimal1 += (v1 % base) * weight;
            decimal2 += (v2 % base) * weight;
            
            v1 /= 10;
            v2 /= 10;
            weight *= base;
        }
        
        sum = Math.abs(decimal1 + sign < 0 ? -decimal2 : decimal2);

        while (sum % base != 0 || sum / base != 0) {
            sb.append(sum % base);
            
            sum /= base;
        }
        
        if (value1 + value2 < 0) sb.append("-");
        
        return sb.reverse().toString();
    }
    
    public String[] solution(String[] expressions) {
        initializeData(expressions);
        solve();
        
        return ans.toArray(new String[ans.size()]);
    }
}