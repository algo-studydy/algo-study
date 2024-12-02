import java.util.*;

class Solution {
    private static final int[][] vector = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}}; // d, l, r, u 순서
    private static final char[] DIRECTIONS = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int initialRemainingDistance = Math.abs(x - r) + Math.abs(y - c);

        if (initialRemainingDistance > k || (k - initialRemainingDistance) % 2 != 0) {
            return "impossible";
        }

        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Node(x, y, 0, new StringBuilder()));
        visited.add(x + "," + y + "," + 0);
        String result = "impossible";

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int currX = current.x;
            int currY = current.y;
            int count = current.count;
            StringBuilder path = current.path;

            int remainingDistance = Math.abs(currX - r) + Math.abs(currY - c);

            if (remainingDistance > k - count || (k - count - remainingDistance) % 2 != 0) {
                continue;
            }

            if (count == k) {
                if (currX == r && currY == c) {
                    result = path.toString();
                    return result;
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = currX + vector[i][0];
                int ny = currY + vector[i][1];

                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }

                String state = nx + "," + ny + "," + (count + 1);
                if (visited.contains(state)) {
                    continue;
                }

                visited.add(state);
                StringBuilder newPath = new StringBuilder(path);
                newPath.append(DIRECTIONS[i]);
                queue.offer(new Node(nx, ny, count + 1, newPath));
            }
        }

        return result;
    }

    static class Node {
        int x, y, count;
        StringBuilder path;

        Node(int x, int y, int count, StringBuilder path) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.path = path;
        }
    }
}
