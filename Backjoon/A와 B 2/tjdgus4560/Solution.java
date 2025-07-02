import java.util.Scanner;

public class Main {
    static boolean ans;
    static String t;
    static String s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.nextLine();
        t = sc.nextLine();

        re(t);
        System.out.println(ans ? 1 : 0);
    }

    private static void re(String cur) {
        if(cur.length() == s.length()) {
            if(cur.equals(s)) ans = true;
            return;
        }

        if(cur.endsWith("A")) {
            re(cur.substring(0, cur.length() - 1));
        }

        if(cur.startsWith("B")) {
            StringBuilder sb = new StringBuilder(cur.substring(1)).reverse();
            re(sb.toString());
        }
    }
}
