class Solution {
    public int solution(String s) {
        int sLength = s.length();
        int answer = sLength;

        if (sLength == 1) return 1;

        for (int size = 1; size <= sLength / 2; size++) {
            StringBuilder compressed = new StringBuilder();
            String prev = s.substring(0, size);
            int count = 1;

            int index = size;

            while (index + size <= sLength) {
                String curr = s.substring(index, index + size);

                if (prev.equals(curr)) {
                    count++;
                } else {
                    if (count > 1) {
                        compressed.append(count);
                    }
                    compressed.append(prev);
                    prev = curr;
                    count = 1;
                }
                index += size;
            }

            if (count > 1) {
                compressed.append(count);
            }
            compressed.append(prev);

            if (index < sLength) {
                compressed.append(s.substring(index));
            }
            answer = Math.min(answer, compressed.length());
        }

        return answer;
    }
}
