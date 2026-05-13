import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] blocks = new int[n];
        for (int i = 0; i < n; i++) {
            blocks[i] = sc.nextInt();
        }
        int s1 = sc.nextInt() - 1;
        int e1 = sc.nextInt();
        int s2 = sc.nextInt() - 1;
        int e2 = sc.nextInt();
        // Please write your code here.

        for (int i=s1; i<e1; i++) {
            blocks[i] = 0;
        }

        int[] tmp = new int[n];
        int idx = 0;
        for (int i=0; i<n; i++) {
            if (blocks[i] != 0) tmp[idx++] = blocks[i];
        }
        blocks = tmp;

        for (int i=s2; i<e2; i++) {
            blocks[i] = 0;
        }

        tmp = new int[n];
        idx = 0;
        for (int i=0; i<n; i++) {
            if (blocks[i] != 0) tmp[idx++] = blocks[i];
        }
        blocks = tmp;

        int count = 0;
        for (int i=0; i<n; i++) {
            if (blocks[i] == 0) break;
            count++;
        }

        System.out.println(count);
        for (int i=0; i<count; i++) {
            System.out.println(blocks[i]);
        }

    }
}
