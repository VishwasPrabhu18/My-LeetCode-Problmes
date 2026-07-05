class Solution {
    private final String[] letterMap = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits.isEmpty()) {
            return result;
        }

        backtracking(0, digits, new StringBuilder(), result);

        return result;
    }

    private void backtracking(int idx, String digits, StringBuilder curr, List<String> res) {
        if (idx == digits.length()) {
            res.add(curr.toString());
            return;
        }

        String letter = letterMap[digits.charAt(idx) - '0'];

        for (char ch : letter.toCharArray()) {
            curr.append(ch);
            backtracking(idx + 1, digits, curr, res);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}