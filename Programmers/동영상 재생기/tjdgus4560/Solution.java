import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int videoLenInt = minToSec(video_len);
        int posInt = minToSec(pos);
        int opStartInt = minToSec(op_start);
        int opEndInt = minToSec(op_end);

        for(String command : commands) {
            // 오프닝 건너뛰기
            if(posInt >= opStartInt && posInt < opEndInt) {
                posInt = opEndInt;
            }
            if(command.equals("next")){
                posInt = posInt + 10;
                posInt = endCheck(posInt, videoLenInt);
            }else if(command.equals("prev")){
                posInt = posInt - 10;
                posInt = startCheck(posInt);
            }
        }
        // 오프닝 건너뛰기
        if(posInt >= opStartInt && posInt < opEndInt) {
            posInt = opEndInt;
        }
        answer = secToMin(posInt);
        return answer;
    }

    // mm:ss to int
    public int minToSec(String mm) {
        String[] tmp = mm.split(":");
        int m = Integer.parseInt(tmp[0]);
        int s = Integer.parseInt(tmp[1]);
        return m * 60 + s;
    }

    // 시간끝체크
    public int endCheck(int pos, int video_len){
        if(pos >= video_len){
            return video_len;
        }else{
            return pos;
        }
    }

    // 시간끝체크
    public int startCheck(int pos){
        if(pos < 0){
            return 0;
        }else{
            return pos;
        }
    }

    // int to "mm:ss"
    public String secToMin(int sec) {
        int m = sec / 60;
        int s = sec % 60;
        return String.format("%02d:%02d", m, s);
    }
}