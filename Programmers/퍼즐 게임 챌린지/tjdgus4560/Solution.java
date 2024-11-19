import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = binarysearch(diffs, times, limit);
        return answer;
    }

    public int binarysearch(int[] diffs, int[] times, long limit){
        int low = 1;
        int high = 100000;

        while(low <= high){
            int level = (low + high) / 2;

            long solve_time = solve(diffs, times, level);
            if(solve_time > limit){
                low = level + 1;
            }else{
                high = level - 1;
            }
        }
        return low;
    }

    // 문제 풀이 시간 계산 함수
    public long solve(int[] diffs, int[] times, int level){
        long time = times[0]; // diffs[0]=1 고정이므로 첫문제는 항상 풀이시간만큼만 소요
        int time_prev = times[0];

        for(int i = 1; i < diffs.length ; i++){
            int diff = diffs[i];
            int time_cur = times[i];

            //바로 풀 수 있는경우
            if(diff <= level){
                time += time_cur;
            }else{
                // 몇번 틀리고 풀 경우
                time += (long)(diff - level) * (long)(time_cur + time_prev) + time_cur;
            }

            time_prev = time_cur;
        }
        return time;
    }
}