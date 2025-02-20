import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Main  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int P = sc.nextInt();
        Stack<Integer>[] stacks = new Stack[N];
        for(int i=0; i<N; i++){
            stacks[i] = new Stack<>();
        }
        int count=0;

        for(int i=0; i<N; i++){
            int n = sc.nextInt();
            int p = sc.nextInt();
            if(stacks[n-1].isEmpty()){
                stacks[n-1].add(p);
                count++;
            }else{
                if(stacks[n-1].peek() < p){
                    stacks[n-1].add(p);
                    count++;
                } else if (stacks[n-1].peek() == p) {
                    continue;
                } else{
                    while(!stacks[n-1].isEmpty() && stacks[n-1].peek() > p){
                        stacks[n-1].pop();
                        count++;
                    }if(stacks[n-1].isEmpty()){
                        stacks[n-1].add(p);
                        count++;
                    } else if (stacks[n-1].peek() != p) {
                        stacks[n-1].add(p);
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
