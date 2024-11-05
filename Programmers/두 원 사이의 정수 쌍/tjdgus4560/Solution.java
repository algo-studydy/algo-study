import java.util.*;
class Solution {
    public long solution(int r1, int r2) {
        long answer = (long)(r2-r1+1)*4L;

        for(int y = 1 ; y < r2 ; y ++){
            int smallx = (int)Math.sqrt((long) r1 * r1 - (long) y * y);
            int bigx = (int)Math.sqrt((long) r2 * r2 - (long) y * y);
            int n;
            if(y<r1){
                n = bigx - smallx;
            }else{
                n= bigx;
            }
            answer = answer + (n*4);
            //x좌표가 정수일때
            if(y<r1 && Math.sqrt((long)r1*r1-(long)y*y)%1 ==0) answer+=4L;
        }

        return answer;
    }
}