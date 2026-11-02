class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            char ch = nums[i].charAt(i);

            if (ch == '0') {
                result.append('1');
            } else {
                result.append('0');
            }
        }

        return result.toString();
    }
}