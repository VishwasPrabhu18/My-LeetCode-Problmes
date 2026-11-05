class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;

        for (int k : arr1) {
            boolean valid = true;

            for (int i : arr2) {
                if (Math.abs(k - i) <= d) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
            }
        }
        
        return count;
    }
}