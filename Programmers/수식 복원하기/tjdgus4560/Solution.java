class Solution {
    public String[] solution(String[] expressions) {
        String[] answer = {};

        ArrayList<String> includeX = new ArrayList<>();
        ArrayList<String> notIncludeX = new ArrayList<>();
        boolean[] posibleJinSu = {false, false, true, true, true, true, true, true, true, true};

        // X수식, 아닌수식 분리
        for (String expression : expressions) {
            String[] tmp = expression.split(" ");
            if(tmp[tmp.length-1].equals("X")){
                includeX.add(expression);
            }else{
                notIncludeX.add(expression);
            }
        }
        //사용가능한 진법 추리기
        int max = 0;
        for(String expression : expressions){
            for(int i = 0; i < expression.length(); i++){
                int n = Character.getNumericValue(expression.charAt(i));
                if(n > 0){
                    max = Integer.max(n,max);
                }
            }
        }
        for(int i = 0; i<=max ;i++){
            posibleJinSu[i] = false;
        }

        // 진수추론
        ArrayList<Integer> check = new ArrayList<>();
        for(String expression : notIncludeX){
            String[] tmp = expression.split(" ");
            for(int i = 2; i <= 9; i++){
                if(tmp[4].equals(calculate(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[2]),tmp[1],i))){
                    check.add(i);
                }
            }
        }
        if(check.size() == 1){

        }


        return answer;
    }
}