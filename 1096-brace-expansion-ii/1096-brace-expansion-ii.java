class Solution {

    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {

        this.expression = expression;
        this.index = 0;

        Set<String> result = parse();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parse() {

        Set<String> result = new HashSet<>();

        // Parse the first expression
        result = parseConcat();

        // Handle union: a,b,c
        while (index < expression.length()
                && expression.charAt(index) == ',') {

            index++; // skip ','

            Set<String> next = parseConcat();
            result.addAll(next);
        }

        return result;
    }

    private Set<String> parseConcat() {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()) {

            char ch = expression.charAt(index);

            // End of current brace expression
            if (ch == '}' || ch == ',') {
                break;
            }

            Set<String> current;

            if (ch == '{') {
                index++; // skip '{'

                current = parse();

                index++; // skip '}'

            } else {
                // Single lowercase letter
                current = new HashSet<>();
                current.add(String.valueOf(ch));

                index++;
            }

            // Cartesian product for concatenation
            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : current) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }
}