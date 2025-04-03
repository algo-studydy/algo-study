import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        //LCS[i][j] : LCS[][]는 A,B 문자열을 각각 행과 열에 매치하여 글자를 하나씩 비교해가며 LCS 크기를 저장하는 배열
        // 0열또는 0행은 0으로 초기화 하여 정수0을 참조만 하는 용도
        int[][] LCS = new int[A.length()+1][B.length()+1];
        int max=0;
        for(int i=1; i<=A.length(); i++){
            for(int j=1; j<=B.length(); j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    // 비교하는 문자가 같으면 LCS[i-1][j-1]값을 끌고와 + 1
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                    max = Math.max(LCS[i][j], max);
                }else{
                    // 비교하는 문자가 다르면 LCS[i-1][j], LCS[i][j-1] 중 큰것을 끌고와 최장길이를 유지
                    LCS[i][j] = Math.max(LCS[i-1][j],LCS[i][j-1]);
                }
            }
        }

        System.out.println(max);
    }
}
