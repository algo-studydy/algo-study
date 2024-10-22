import java.util.*;

class Solution {
    public static String[] video;
    public static String[] p;
    public static String[] start;
    public static String[] end;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        video = video_len.split(":");
        p = pos.split(":");
        start = op_start.split(":");
        end = op_end.split(":");

        for(String cmd : commands){
            checkOpeningRange();

            if (cmd.equals("next")) command(true);
            else command(false);

            checkOpeningRange();
        }

        return p[0] + ":" + p[1];
    }

    // 현재 시간이 오프닝 시간 사이에 있는지 확인하는 함수
    public static void checkOpeningRange(){
        // pos, op_start, op_end의 시간을 초 단위로 변환
        int pos = Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
        int op_start = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
        int op_end = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

        // 현재 시간이 오프닝 시작, 끝 시간 사이에 있을 경우
        if(op_start <= pos && pos <= op_end){
            func(pos, op_end);
        }
    }

    // 커맨드에 맞는 동작 수행
    public static void command(boolean cmd){
        // 현재 재생 위치를 초 단위로 변환
        int pos = Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);

        // next면 10초 앞으로, prev면 10초 뒤로
        if(cmd) pos += 10;
        else pos -= 10;

        int video_len = Integer.parseInt(video[0]) * 60 + Integer.parseInt(video[1]);

        // 현재 재생 위치가 비디오 길이보다 큰 경우
        if(pos > video_len){
            p[0] = video[0];
            p[1] = video[1];
        }
        // 현재 재생 위치가 비디오 길이보다 작은 경우
        else if(pos < 10) p[0] = p[1] = "00";
        else func(pos, pos);
    }

    public static void func(int pos, int time){
        // 현재 시간의 분 단위가 10보다 작은지 검사
        if(pos / 60 < 10) p[0] = "0" + time / 60;  // "0m"
        else p[0] = "" + time / 60;  // "mm"

        // 현재 시간의 초 단위가 10보다 작은지 검사
        if(pos % 60 < 10) p[1] = "0" + time % 60;
        else p[1] = "" + time % 60;
    }
}