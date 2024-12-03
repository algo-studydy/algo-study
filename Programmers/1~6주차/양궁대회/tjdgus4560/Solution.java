class Solution {
    static int[] answer;
    static int max;

    public int[] solution(int n, int[] info) {
        answer = new int[11];
        max = 0;

        dfs(info, new int[11], n, 0);

        if (max == 0) {
            return new int[] {-1};  // 우승할 방법이 없는 경우
        }

        return answer;
    }

    public void dfs(int[] apeach, int[] ryan, int arrows, int idx) {
        // 마지막 과녁에 도달했거나 화살을 다 쏜 경우
        if (idx == 11 || arrows == 0) {
            // 남은 화살은 0점에 맞추기
            ryan[10] += arrows;

            int apeachScore  = 0;
            int ryanScore  = 0;
            for(int i = 0; i < 11 ; i++){
                if(apeach[i] < ryan[i]){
                    ryanScore += 10 - i;
                }else if(apeach[i] > 0){
                    apeachScore += 10 - i;
                }
            }
            // 만약 점수차가 더크다면 업데이트
            int score = ryanScore - apeachScore ;
            if(max < score){
                max = score;
                answer = ryan.clone();
            } else if (max == score) {
                // 더 낮은 점수에 더 많은 화살을 맞힌 경우로 업데이트
                for(int i = 10 ; i >= 0 ; i--){
                    if(answer[i] < ryan[i]){
                        answer = ryan.clone();
                        break;
                    }else if(answer[i] > ryan[i]){
                        break;
                    }
                }
            }

            ryan[10] -= arrows;

            return;
        }

        // 라이언이 이 점수를 가져가는 경우
        if(apeach[idx] < arrows) {
            ryan[idx] = apeach[idx] + 1;
            arrows = arrows - ryan[idx];
            dfs(apeach, ryan, arrows, idx + 1);
            arrows = arrows + ryan[idx];
            ryan[idx] = 0;
        }

        // 라이언이 이 점수를 포기하는 경우
        dfs(apeach, ryan, arrows, idx + 1);
    }
}