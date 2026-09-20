class Solution {
    public int reverseDegree(String s) {
        int START_ALPHA_IDX = 26;
        int degree = 0;
        for (int i = 0; i < s.length(); i++) {
            int charAlpha = START_ALPHA_IDX - (s.charAt(i) - 'a');
            degree += charAlpha * (i+1);
        }
        return degree;
    }
}