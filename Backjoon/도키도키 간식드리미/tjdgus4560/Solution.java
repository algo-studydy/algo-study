import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int count = 1;
        for(int i=0; i<n; i++){
            while(!stack.isEmpty() && stack.peek() == count){
                stack.pop();
                count++;
            }
            if(arr[i] == count){
                count++;
            }else if(!stack.isEmpty() && stack.peek() < arr[i]){
                System.out.println("Sad");
                return;
            }else{
                stack.push(arr[i]);
            }
        }

        System.out.println("Nice");
    }
}
