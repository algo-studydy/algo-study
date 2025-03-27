import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int A;
    static int B;
    static int C;
    static boolean[][] visited;
    static PriorityQueue<Integer> result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        visited = new boolean[A+1][B+1];
        result = new PriorityQueue<>();

        dfs(0,0,C);

        while(!result.isEmpty()){
            System.out.print(result.poll()+" ");
        }
    }

    private static void dfs(int a, int b, int c) {
        if(visited[a][b]) return;
        visited[a][b] = true;

        if(a==0 && !result.contains(c)){
            result.offer(c);
        }
        int move=0;
        // A > B
        move = Math.min(a, B-b);
        dfs(a-move, b+move, c);

        // A > C
        move = Math.min(a, C-c);
        dfs(a-move, b, c+move);

        // B > A
        move = Math.min(b, A-a);
        dfs(a+move, b-move, c);

        // B > C
        move = Math.min(b, C - c);
        dfs(a, b-move, c+move);

        // C > A
        move = Math.min(c, A-a);
        dfs(a+move, b, c-move);

        // C > B
        move = Math.min(c, B-b);
        dfs(a, b+move, c-move);
    }

}
