import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] vMS = video_len.split(":");
        String[] pMS = pos.split(":");
        String[] sMS = op_start.split(":");
        String[] eMS = op_end.split(":");
        int videoSize = msToSecond(vMS);
        int posSize = msToSecond(pMS);
        int startSize = msToSecond(sMS);
        int endSize = msToSecond(eMS);
        
        if (posSize >= startSize && posSize <= endSize) posSize = endSize;
        
        for (int i = 0; i < commands.length; i++) {
            switch(commands[i]) {
                    case("next"):
                        posSize += 10;
                        break;
                    case("prev"):
                        posSize -= 10;
                        break;
            }
            
            if (posSize > videoSize) {
                posSize = videoSize;
                continue;
            }

            if (posSize < 0) posSize = 0;
        }
        
        int min = posSize / 60;
        int second = posSize % 60;
        StringBuilder sb = new StringBuilder();
        if (min < 10) sb.append("0");
        sb.append(min).append(":");
        if (second < 10) sb.append("0");
        sb.append(second);
        return sb.toString();
    }
    
    static int msToSecond(String[] ms) {
        return Integer.parseInt(ms[0]) * 60 + Integer.parseInt(ms[1]);
    }
}