import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();

        for(int i=0; i<10; i++){
            xMap.put(i,0);
            yMap.put(i,0);
        }
        for(int i=0; i<X.length(); i++){
            int xNum = X.charAt(i) - '0';

            xMap.put(xNum, xMap.get(xNum) +1);
        }

        for(int i=0; i<Y.length(); i++){
            int yNum = Y.charAt(i) - '0';

            yMap.put(yNum, yMap.get(yNum) +1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=9; i>=0; i--){
            int t = Math.min(xMap.get(i), yMap.get(i));
            for(int j=0; j<t; j++){
                sb.append(i);
            }
        }

        if(sb.length() == 0)  sb.replace(0,sb.length(),"-1");
        if(sb.charAt(0) == '0')  sb.replace(0,sb.length(),"0");
        return sb.toString();
    }
}