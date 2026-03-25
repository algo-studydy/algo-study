class Solution {
    public int solution(int[][] signals) {
        int answer = 0;
        int time = 1, max = 1;
        int[] rotate = new int[signals.length];
        for(int i=0;i<signals.length;i++) {
            rotate[i] = signals[i][0] + signals[i][1] + signals[i][2];
        }
        for(int i=0;i<signals.length;i++) {
            max = lcm(max, rotate[i]);
        }
        System.out.println(max);
        while(time < max) {
            boolean flag = true;
            for(int i=0;i<signals.length;i++) {
                int green = signals[i][0];
                int yellow = signals[i][1];

                int phase = (time - 1) % rotate[i];

                if(phase < green || phase >= green + yellow) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                answer = time; break;
            }
            time++;
        }

        return answer == 0 ? -1 : answer;
    }
    private static int lcm(int a, int b) {
        if(a%b == 0) return a;
        else if(b%a == 0) return b;
        return a * b;
    }
}