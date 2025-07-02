import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> adjlist;
    static int[] cnt; //칭찬 받은거 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 직원수
        int m = sc.nextInt(); // 칭찬 수

        adjlist = new ArrayList<>();

        for(int i=0; i<=n; i++){
            adjlist.add(new ArrayList<>());
        }

        for(int i=1; i<=n; i++){
            int num = sc.nextInt(); //상사 번호
            if(num == -1) continue;
            adjlist.get(num).add(i);
        }

        cnt = new int[n+1];
        for(int i=0; i<m; i++){
            int a = sc.nextInt(); //칭찬을 받은 직원 번호
            int b = sc.nextInt(); //칭찬 수치

            cnt[a] += b;
        }

        dfs(1);

        for(int i=1; i<=n; i++){
            System.out.print(cnt[i]+" ");
        }
    }

    private static void dfs(int num) {
        for(int i=0; i<adjlist.get(num).size(); i++){
            cnt[adjlist.get(num).get(i)] += cnt[num];
            dfs(adjlist.get(num).get(i));
        }
    }
}
