class Solution {
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 1; i < n; i++) {
           str = buildString(str);
            System.out.println(str);
        }
        return str;
    }

    private String buildString(String str) {
        StringBuilder res = new StringBuilder();

        int count = 1;

        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                res.append(count).append(str.charAt(i));
                count = 1;
            }
        }

        // Add the final group
        res.append(count).append(str.charAt(str.length() - 1));

        return res.toString();
    }
}