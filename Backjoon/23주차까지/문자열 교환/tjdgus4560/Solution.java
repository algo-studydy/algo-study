import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            //a 갯수 체크 -> 슬라이딩 윈도우의 크기
            if(s.charAt(i) == 'a') count++;
        }
        //앞뒤가 이어져 있으므로 슬라이딩윈도우의 크기만큼 뒤에 붙이면 일자로 처리가능
        s = s.concat(s.substring(0, count));
        char[] arr = s.toCharArray();
        int cntb=0;
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<arr.length; i++){
            if(i<count){
                if(arr[i] == 'b') cntb++;
            }else{
                ans = Math.min(cntb, ans);
                if(arr[i] == 'b') cntb++;
                if(arr[i-count] == 'b') cntb--;
            }
        }
        ans = Math.min(cntb, ans);

        System.out.println(ans);
    }
}
