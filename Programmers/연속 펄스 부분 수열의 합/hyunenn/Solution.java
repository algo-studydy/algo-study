class Solution {
    static long[] a, b;
    public long solution(int[] sequence) {
        long answer = 0;
        a = new long[sequence.length];
        b = new long[sequence.length];
        a[0] = sequence[0];
        b[0] = sequence[0] * -1;
        int c = -1; int d = 1;

        for(int i=1;i<sequence.length;i++) {
            a[i] = Math.max(sequence[i] * c, a[i-1] + sequence[i] * c);
            b[i] = Math.max(sequence[i] * d, b[i-1] + sequence[i] * d);
            c *= -1; d *= -1;
        }

        for(int i=0;i<sequence.length;i++) {
            answer = Math.max(a[i], answer);
            answer = Math.max(b[i], answer);
        }
        return answer;
    }
}