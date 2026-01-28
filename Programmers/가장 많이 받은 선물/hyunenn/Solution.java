class Solution {
    static int[][] map;
    static int[] points;
    static int[] cal;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        map = new int[n][n];
        points = new int[n];
        cal = new int[n];
        // 선물 지도 만들기
        for(int i=0;i<gifts.length;i++) {
            String[] str = gifts[i].split(" ");
            int r = 0; int c = 0;
            for(int j=0;j<friends.length;j++) {
                if(friends[j].equals(str[0])) r = j;
            }
            for(int j=0;j<friends.length;j++) {
                if(friends[j].equals(str[1])) c = j;
            }

            map[r][c]++;
        }

        // 선물 지수 나타내기
        for(int i=0;i<n;i++) {
            int sum = 0;
            for(int j=0;j<n;j++) {
                if(i == j) continue;
                sum += map[i][j];
            }
            points[i] = sum;
        }

        for(int j=0;j<n;j++) {
            int sum = 0;
            for(int i=0;i<n;i++) {
                if(j == i) continue;
                sum += map[i][j];
            }
            points[j] -= sum;
        }

        // 각각 받을 선물 갯수 체크
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(i == j) continue;
                if(map[i][j] > map[j][i]) cal[i]++;
                else if(map[i][j] < map[j][i]) cal[j]++;
                else {
                    if(points[i] > points[j]) cal[i]++;
                    else if(points[i] < points[j]) cal[j]++;
                }
            }
        }

        // 이제, 최대치 값 찾기
        for(int i=0;i<n;i++) {
            answer = Math.max(answer, cal[i]);
        }
        return answer;
    }
}

/*
    1. 선물을 많이 준 사람이 선물을 받는다.
    2. 선물을 서로 준 기록이 없거나, 같다면
    선물 지수(자신이 선물 준 수 - 받은 선물 수)가 큰 사람이 받는다.
    2-2. 선물 지수도 같다면, 서로 주고 받지 않는다.

*/
