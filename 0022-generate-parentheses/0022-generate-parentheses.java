class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> resuList = new ArrayList<>();
        backtrack(resuList, "", 0, 0, n);
        return resuList;
    }

    private void backtrack(List<String> result, String path, int open, int close, int n) {
        if (path.length() == 2 * n) {
            result.add(path);
            return;
        }

        if (open < n)
            backtrack(result, path + "(", open + 1, close, n);

        if (close < open)
            backtrack(result, path + ")", open, close + 1, n);
    }
}