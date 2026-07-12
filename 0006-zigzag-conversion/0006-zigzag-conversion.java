class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder[] resRow = new StringBuilder[numRows];
        for(int i=0; i<numRows; i++) {
            resRow[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        for(char c: s.toCharArray()) {
            resRow[currRow].append(c);

            // change direction
            if(currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }

            currRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for(StringBuilder sb: resRow) {
            result.append(sb);
        }

        return result.toString();
    }
}