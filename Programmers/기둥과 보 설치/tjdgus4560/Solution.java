import java.util.*;

class Solution {
    static List<int[]> answer;

    public int[][] solution(int n, int[][] build_frame) {
        answer = new ArrayList<>();

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2]; // 0: 기둥, 1: 보
            int b = frame[3]; // 0: 삭제, 1: 설치

            if (b == 1) { // 설치
                answer.add(new int[]{x, y, a});
                if (!isValid()) {
                    answer.remove(answer.size() - 1); //설치실패 복구
                }
            } else { // 삭제
                int idx = -1;
                for (int i = 0; i < answer.size(); i++) {
                    //삭제할 구조물 찾기
                    if (answer.get(i)[0] == x && answer.get(i)[1] == y && answer.get(i)[2] == a) {
                        idx = i;
                        break;
                    }
                }
                if (idx != -1) {
                    int[] removed = answer.remove(idx);
                    if (!isValid()) {
                        answer.add(removed); //삭제실패 복구
                    }
                }
            }
        }

        answer.sort(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                if (o1[1] != o2[1]) return o1[1] - o2[1];
                return o1[2] - o2[2];
            }
        });

        return answer.toArray(new int[0][]);
    }

    // 현재 구조물이 모두 조건을 만족하는지 검사
    public boolean isValid() {
        for (int[] tmp : answer) {
            int x = tmp[0];
            int y = tmp[1];
            int a = tmp[2];

            if (a == 0) { // 기둥
                // 높이가0 or 바로아래 기둥 존재 or 한칸왼쪽에서 시작하는 보 존재 or 현재자리에서 시작하는 보 존재
                if (y == 0 ||
                        exists(x, y - 1, 0) ||
                        exists(x - 1, y, 1) ||
                        exists(x, y, 1)) {
                    continue;
                }
                return false;
            } else { // 보
                // 바로아래 기둥존재 or 오른쪽끝의 아래 기둥 존재 or 양쪽끝 보 존재
                if (exists(x, y - 1, 0) ||
                        exists(x + 1, y - 1, 0) ||
                        (exists(x - 1, y, 1) && exists(x + 1, y, 1))) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    // 해당 좌표에 해당 구조물이 존재하는지 확인
    public boolean exists(int x, int y, int a) {
        for (int[] tmp : answer) {
            if (tmp[0] == x && tmp[1] == y && tmp[2] == a) {
                return true;
            }
        }
        return false;
    }
}