import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        String[] s = number.split("");
        int[] num = new int[number.length()];
        for(int i=0;i<number.length();i++) {
            num[i] = Integer.parseInt(s[i]);
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<number.length();i++) {
            while(!list.isEmpty() && k > 0 && (list.get(list.size() - 1) < num[i])) {
                list.remove(list.size() - 1);
                k--;
            }
            list.add(num[i]);
        }

        while(!list.isEmpty() && k > 0) {
            k--;
            list.remove(list.size() - 1);
        }

        StringBuilder sb = new StringBuilder();

        for(int i : list) {
            sb.append(i);
        }

        return sb.toString();
    }
}