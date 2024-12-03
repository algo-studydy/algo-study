import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binaryString = Long.toBinaryString(numbers[i]);
            int length = binaryString.length();
            int fullTreeLength = 1;

            while (fullTreeLength < length) {
                fullTreeLength = fullTreeLength * 2 + 1;
            }
            
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < fullTreeLength - length; j++) {
                sb.append('0');
            }
            sb.append(binaryString);
            String fullBinaryString = sb.toString();

            if (isRepresentable(fullBinaryString)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }

        return result;
    }

    private boolean isRepresentable(String binaryString) {
        return isRepresentableRecursive(binaryString, 0, binaryString.length() - 1);
    }

    private boolean isRepresentableRecursive(String binaryString, int left, int right) {
        if (left > right) {
            return true;
        }

        int mid = (left + right) / 2;
        char root = binaryString.charAt(mid);

        if (root == '0') {
            for (int i = left; i <= right; i++) {
                if (binaryString.charAt(i) == '1') {
                    return false;
                }
            }
        }

        return isRepresentableRecursive(binaryString, left, mid - 1) &&
               isRepresentableRecursive(binaryString, mid + 1, right);
    }
}
