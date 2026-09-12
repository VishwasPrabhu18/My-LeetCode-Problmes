class Solution {

    static class Interval {
        int left;
        int right;
        int weight;
        int index;

        Interval(int left, int right, int weight, int index) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.index = index;
        }
    }

    static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.left != b.left) {
                return Integer.compare(a.left, b.left);
            }
            return Integer.compare(a.right, b.right);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = findNext(arr, i);
        }

        Result[][] dp = new Result[n + 1][5];

        // IMPORTANT:
        // If we can choose 0 intervals, the answer is empty
        // regardless of where we are.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new Result(0, new ArrayList<>());
        }

        // No intervals remaining
        for (int k = 1; k <= 4; k++) {
            dp[n][k] = new Result(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: skip this interval
                Result skip = dp[i + 1][k];

                // Option 2: take this interval
                Result afterTake = dp[next[i]][k - 1];

                List<Integer> takeIndices =
                    new ArrayList<>(afterTake.indices);

                takeIndices.add(arr[i].index);

                Collections.sort(takeIndices);

                Result take = new Result(
                    (long) arr[i].weight + afterTake.score,
                    takeIndices
                );

                dp[i][k] = better(take, skip);
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int findNext(Interval[] arr, int i) {

        int target = arr[i].right;

        int left = i + 1;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].left > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private Result better(Result a, Result b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        return compareLexicographically(a.indices, b.indices) <= 0
            ? a
            : b;
    }

    private int compareLexicographically(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}