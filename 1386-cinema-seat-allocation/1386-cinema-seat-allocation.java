class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map to store reserved seats in each row
        Map<Integer, Set<Integer>> reservedMap = new HashMap<>();
        for (int[] seat : reservedSeats) {
            reservedMap.computeIfAbsent(seat[0], k -> new HashSet<>()).add(seat[1]);
        }

        int maxGroups = 0;

        // Check only rows that have reserved seats
        for (Map.Entry<Integer, Set<Integer>> entry : reservedMap.entrySet()) {
            Set<Integer> reserved = entry.getValue();

            boolean left = !(reserved.contains(2) || reserved.contains(3) || reserved.contains(4)
                    || reserved.contains(5));
            boolean middle = !(reserved.contains(4) || reserved.contains(5) || reserved.contains(6)
                    || reserved.contains(7));
            boolean right = !(reserved.contains(6) || reserved.contains(7) || reserved.contains(8)
                    || reserved.contains(9));

            if (left && right) {
                // Can place 2 groups
                maxGroups += 2;
            } else if (left || middle || right) {
                // Can place 1 group
                maxGroups += 1;
            }
            // else 0 groups in this row
        }

        // Rows without reserved seats can have 2 groups
        int emptyRows = n - reservedMap.size();
        maxGroups += emptyRows * 2;

        return maxGroups;
    }
}