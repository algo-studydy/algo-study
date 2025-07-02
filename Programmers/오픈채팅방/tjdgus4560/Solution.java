import java.util.*;
import java.io.*;


class Solution {
    public String[] solution(String[] record) {

        Map<String, String> hmap = new HashMap<>();

        for(int i=0; i<record.length; i++){
            String[] s = record[i].split(" ");

            // 떠나는게 아니면 닉네임을 해시맵에 저장
            if(!s[0].equals("Leave")){
                hmap.put(s[1], s[2]);
            }
        }

        List<String> li = new ArrayList<>();

        for(int i=0; i<record.length; i++){
            String[] s = record[i].split(" ");

            if(s[0].equals("Enter")){
                String name = hmap.get(s[1]);
                li.add(name+"님이 들어왔습니다.");
            }else if(s[0].equals("Leave")){
                String name = hmap.get(s[1]);
                li.add(name+"님이 나갔습니다.");
            }
        }


        String[] answer = li.toArray(new String[li.size()]);
        return answer;
    }
}