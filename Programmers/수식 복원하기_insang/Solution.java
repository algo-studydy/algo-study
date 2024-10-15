import java.util.*;

class Solution {

    // 문자열 배열에서 가장 큰 수를 찾는 함수
    public int findMax(String[] strs){
        int max = 1;  // 2진법 이상을 찾기 위해 최소값을 1로 설정
        for(int i = 0; i < 5; i += 2){  // 배열의 0, 2, 4번째 인덱스인 숫자를 검사
            if (strs[i].equals("X")){
                continue;
            }
            int cur = Integer.parseInt(strs[i]);
            while(cur > 0){
                int temp = cur % 10;
                max = max < temp ? temp : max;
                cur /= 10;
            }
        }
        return max;
    }

    // a진법 수를 b진법으로 변환하는 함수
    public int aToB(String str, int a, int b){
        int num = Integer.parseInt(str);
        int temp = 0;
        int i = 1;
        while(num > 0){
            temp += num % a * i;
            i *= b;
            num /= a;
        }
        return temp;
    }

    public String[] solution(String[] expressions) {
        int max = 1;
        ArrayList<String> xInList = new ArrayList<>();  // X가 들어간 수식을 저장할 리스트
        ArrayList<String> notInList = new ArrayList<>();  // X가 들어가지 않은 수식을 저장할 리스트
        for(String str : expressions){
            String[] splits = str.split(" ");
            int found = findMax(splits);
            max = max < found ? found : max;

            // X가 들어간 수식과 들어가지 않은 수식을 구분해서 저장
            if (splits[4].equals("X")){
                xInList.add(str);
            } else {
                notInList.add(str);
            }
        }

        // 가능한 진법 후보 리스트 생성
        ArrayList<Integer> possibles = new ArrayList<>();
        for(int i = max + 1; i < 10; i++){
            possibles.add(i);
        }
        for(String str : notInList){
            String[] splits = str.split(" ");
            for(int i = 0; i< possibles.size(); i++){
                int a = 10, b = possibles.get(i);
                int left = aToB(splits[0], a, b);
                if (splits[1].equals("+")){
                    left += aToB(splits[2], a, b);
                } else {
                    left -= aToB(splits[2], a, b);
                }

                // 계산 결과가 오른쪽 숫자와 일치하지 않으면 해당 진법을 제거
                if (left != aToB(splits[4], a, b)){
                    possibles.remove(i);
                    i--;
                }
            }
        }

        // X가 들어간 수식에서 가능한 진법을 순회하며 계산된 결과 갱신
        String[] answer = new String[xIncl.size()];
        for(int i = 0; i< xInList.size(); i++){
            String[] splits = xInList.get(i).split(" ");
            String temp = null;
            for(int j = 0; j < possibles.size(); j++){
                int a = 10, b = possibles.get(j);
                int left = aToB(splits[0], a, b);

                if (splits[1].equals("+")){
                    left += aToB(splits[2], a, b);
                } else {
                    left -= aToB(splits[2], a, b);
                }
                String temp2 = String.format("%s %s %s = %d", splits[0], splits[1], splits[2], aToB("" + left, b, a));
                if (temp == null){
                    temp = temp2;
                } else if (!temp.equals(temp2)){
                    temp = String.format("%s %s %s = ?", splits[0], splits[1], splits[2]);
                    break;
                }
            }
            answer[i] = temp;
        }
        return answer;
    }
}