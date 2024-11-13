import java.util.*;

class Solution {
    static int answer;
    static int[][][] map;
    static final int size = 101;
    static int n;
    static int m;
    static int x;
    static Deque<Robot> robots;
    static HashMap<Integer, int[]> hMap;
    
    public int solution(int[][] points, int[][] routes) {
        Solution.answer = 0;
        map = new int[size][size][2];
        n = points.length;
        m = routes[0].length;
        x = routes.length;
        robots = new ArrayDeque<>();
        hMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int[] point = points[i];
            
            hMap.put(i + 1, point);
        }

        for (int i = 0; i < x; i++) {
            int[] route = routes[i];
            int[] point = hMap.get(route[0]);
            map[point[0]][point[1]][0] += 1;
            if (map[point[0]][point[1]][0] == 2) answer += 1;
            robots.add(new Robot(1, point[0], point[1], route));
        }
        /*
        for (Robot r : robots) {
            System.out.println(r);
        }
        */
        
        simulation();
        
        return answer;
    }
    
    static void simulation() {
        int finished = 0;
        int time = 0;
        Deque<int[]> dQ = new ArrayDeque<>();
        while (finished != x) {
            int remain = robots.size();

            while (remain-- > 0) {
                Robot r = robots.poll();
                
                int tNum = r.route[r.dest];
                int[] point = hMap.get(tNum);
                int rX = r.x;
                int rY = r.y;
                int tX = point[0];
                int tY = point[1];
                int[] delta = getDelta(rX, rY, tX, tY);
                int nextX = rX + delta[0];
                int nextY = rY + delta[1];
                int now = time % 2;
                int future = (time + 1) % 2;
                
                map[rX][rY][now] -= 1;
                map[nextX][nextY][future] += 1;
                if (map[nextX][nextY][future] == 2) answer += 1;
                if (nextX == tX && nextY == tY) {
                    r.dest += 1;

                    if (r.dest == m) {
                        finished += 1;
                        dQ.add(new int[] {nextX, nextY});
                        continue;
                    }
                }
                
                r.x = nextX;
                r.y = nextY;
                robots.add(r);
            }
            while (!dQ.isEmpty()) {
                int[] point = dQ.poll();
                map[point[0]][point[1]][(time + 1) % 2] -= 1;
            }
            time += 1;
        }
    }
    
    static int[] getDelta(int rX, int rY, int tX, int tY) {
        int[] delta = new int[2];
        
        if (rX == tX) {
            delta[1] += rY < tY ? 1 : -1;
        }
        else {
            delta[0] += rX < tX ? 1 : -1;
        }
        
        return delta;
    }
    
    static class Robot {
        int x, y;
        int dest;
        int[] route;
        
        Robot(int dest, int x, int y, int[] route) {
            this.dest = dest;
            this.x = x;
            this.y = y;
            this.route = route;
        }
        
        @Override
        public String toString() {
            return "(x, y) = " + x + ", " + y +
                " dest = " + dest +
                " route = " + Arrays.toString(route);
        }
    }
}