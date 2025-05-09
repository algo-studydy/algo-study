import java.util.*;
public class PROG튜플 {
    public static int[] solution(String s) {
        ArrayList<int[]> nums = new ArrayList<>();

        int length = s.length();

        // s2 = 1,2,3},{2,1},{1,2,4,3},{2
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < length - 2; i++){
            sb.append(s.charAt(i));
        }
        String s2 = sb.toString();

        // ss = [1,2,3, 2,1, 1,2,4,3, 2]
        String[] ss = s2.split("\\},\\{");

        int size = 0;  // 가장 긴 튜플의 길이
        for(String str : ss){
            // 저장된 문자열을 숫자로 변환하여 리스트에 저장
            String[] temp = str.split(",");
            int[] strToInt = new int[temp.length];
            size = Integer.max(size, temp.length);

            for(int i = 0; i < temp.length; i++){
                strToInt[i] = Integer.parseInt(temp[i]);
            }
            nums.add(strToInt);
        }

        // 튜플의 길이를 기준으로 오름차순 정렬
        nums.sort(Comparator.comparingInt(a -> a.length));

        int[] answer = new int[size];
        boolean[] check = new boolean[100001];

        int idx = 0;
        for(int[] n : nums){
            for(int i = 0; i < n.length; i++){
                if(!check[n[i]]){
                    answer[idx] = n[i];
                    check[n[i]] = true;
                    idx++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("{{20,111},{111}}")));
    }
}
