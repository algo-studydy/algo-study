import java.util.*;
public class PROG오픈채팅방 {
    public static String[] solution(String[] record) {
        // uid별 닉네임 저장 map
        Map<String, String> map = new HashMap<>(); // uid : nickname

        // 닉네임 정보 갱신
        for (String str : record) {
            String[] info = str.split(" ");
            String cmd = info[0];
            String uid = info[1];
            // Enter, Change는 닉네임 갱신
            if (cmd.equals("Enter") || cmd.equals("Change")) {
                String nickname = info[2];
                map.put(uid, nickname);
            }
        }

        // 메시지 저장
        List<String> messages = new ArrayList<>();
        for (String str : record) {
            String[] info = str.split(" ");
            String cmd = info[0];
            String uid = info[1];
            if (cmd.equals("Enter")) {
                messages.add(map.get(uid) + "님이 들어왔습니다.");
            }
            else if (cmd.equals("Leave")) {
                messages.add(map.get(uid) + "님이 나갔습니다.");
            }
        }
        String[] answer = new String[messages.size()];

        for(int i = 0; i < answer.length; i++){
            answer[i] = messages.get(i);
        }
        return answer;
    }
}
