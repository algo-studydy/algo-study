import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;

        for(int i = 1; i < scores.length; i++){  // 완호가 인센티브를 받지 못하면 return
            if(scores[0][0] < scores[i][0] && scores[0][1] < scores[i][1]){
                return -1;
            }
        }

        int[] wanho = {scores[0][0], scores[0][1]};

        // scores 배열 요소의 합을 기준으로 내림차순 정렬
        Arrays.sort(scores, (o1, o2) -> Integer.compare(o2[0] + o2[1], o1[0] + o1[1]));

        int nextMax = scores[0][0] + scores[0][1];  // scores 배열의 가장 큰 점수로 초기화
        int rank = 1;  // 완호의 석차
        int cur = 0;  // 현재 배열의 인덱스

        for(int[] s : scores){
            if(s[0] == wanho[0] && s[1] == wanho[1]) return rank;  // 현재 배열이 완호의 점수면 return

            if(nextMax >= s[0] + s[1]){
                nextMax = s[0] + s[1];  // 다음으로 큰 점수 갱신
                rank++;

                for(int i = 0; i < cur; i++){  // 현재 배열의 인덱스 -1 까지만 scores 배열 순회
                    if(s[0] < scores[i][0] && s[1] < scores[i][1]){  // 인센티브 대상이 아니면 등수에서 제외
                        rank--;
                        break;
                    }
                } cur++;
            }
        }


        return rank;
    }
}