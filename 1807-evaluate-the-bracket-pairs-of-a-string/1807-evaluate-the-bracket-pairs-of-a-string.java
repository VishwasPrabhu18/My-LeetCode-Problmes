class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> knowledgeMap = new HashMap<>();

        for (List<String> list : knowledge) {
            knowledgeMap.put(list.get(0), list.get(1));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '(') {
                sb.append(s.charAt(i));
                continue;
            }

            int j = i + 1;

            while (s.charAt(j) != ')') {
                j++;
            }

            String key = s.substring(i + 1, j);
            sb.append(knowledgeMap.getOrDefault(key, "?"));
            i = j;
        }
        return sb.toString();
    }
}