import java.util.*;

class Solution {
    static class Point {
        int dia, iron, stone;
        Point(int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }
    }
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        List<Point> list = new ArrayList<>();
        int cnt = 0, dia = 0, iron = 0, stone = 0;
        int ch = 0;
        for(int i=0;i<picks.length;i++) {
            ch += picks[i];
        }

        for(int i=0;i<minerals.length;i++) {
            // 곡괭이 여분 안되면 탐색 종료
            if(ch == 0) break;
            cnt++;
            if(minerals[i].equals("diamond")) dia++;
            else if(minerals[i].equals("iron")) iron++;
            else stone++;

            // 갯수가 5개면, list에 추가하고 cnt 는 0으로 초기화
            if(cnt == 5) {
                list.add(new Point(dia, iron, stone));
                dia = 0; iron = 0; stone = 0; cnt = 0;
                ch--;
            }
        }

        // 남아있는 갯수도 list에 추가해줌
        if(cnt != 0) {
            list.add(new Point(dia, iron, stone));
            cnt = 0;
        }

        // 내림차순 정렬
        list.sort((a, b) -> {
            if(a.dia == b.dia) {
                if(a.iron == b.iron) {
                    return b.stone - a.stone;
                }
                return b.iron - a.iron;
            }
            return b.dia - a.dia;
        });

        int idx = 0;
        for(Point p : list) {
            int sel = -1;

            // System.out.println(idx++ + "번째 : " + p.dia + " " + p.iron + " " + p.stone);

            if(picks[0] > 0) {
                picks[0]--;
                sel = 0;
            }
            else if(picks[1] > 0) {
                picks[1]--;
                sel = 1;
            }
            else if(picks[2] > 0) {
                picks[2]--;
                sel = 2;
            }
            else break;

            answer += cal(sel, p.dia, p.iron, p.stone);
        }

        return answer;
    }

    private static int cal(int sel, int a, int b, int c) {
        // 현재 선택이 다이아 곡괭이라면
        if(sel == 0) return a+b+c;
            // 선택이 철 곡괭이라면
        else if(sel == 1) return a * 5 + b + c;
            // 돌 곡괭이라면
        else return a * 25 + b * 5 + c;
    }
}