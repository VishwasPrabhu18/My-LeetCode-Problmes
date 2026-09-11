class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < digits.length; i++) {

            // No leading zero
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < digits.length; j++) {

                // Same copy cannot be reused
                if (j == i) {
                    continue;
                }

                for (int k = 0; k < digits.length; k++) {

                    // Same copy cannot be reused
                    if (k == i || k == j) {
                        continue;
                    }

                    // Last digit must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int number = digits[i] * 100
                               + digits[j] * 10
                               + digits[k];

                    numbers.add(number);
                }
            }
        }

        return numbers.size();
    }
}