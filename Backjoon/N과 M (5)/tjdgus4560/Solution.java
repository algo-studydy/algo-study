import java.util.*;

public class Main{
    static int N;
    static int M;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> li;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        visited = new boolean[N];
        li = new ArrayList<>();
        re();
    }

    private static void re() {
        if(li.size() ==M){
            for(int n : li){
                System.out.print(n+" ");
            }
            System.out.println();
            return;
        }

        for(int i=0; i<N; i++){
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            li.add(arr[i]);
            re();
            li.remove(li.size() - 1);
            visited[i] = false;
        }
    }
}
