class Solution
{
    public int solution(String s)
    {
        int answer = 0;


        for(int i = s.length(); i > 0  ; i--){
            // 문자열 크기만큼의 팰린드롬 탐색 -> 크기를 하나씩 줄여가면서 탐색
            for (int j = 0; j+i <= s.length() ; j++){
                if(check(s,j,j+i-1)){
                    answer = i;
                    return answer;
                }
            }
        }

        return answer;
    }

    private boolean check(String s, int start, int end) {
        while(start <= end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }
}