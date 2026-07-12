class Solution {
    public int[] arrayRankTransform(int[] arr) {
        if(arr.length == 0) return arr;

        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        Map<Integer, Integer> rank = new HashMap<>();
        int r = 1;

        for(int num: sortedArr) {
            if(!rank.containsKey(num)) {
                rank.put(num, r++);
            }
        }

        for(int i=0; i<arr.length; i++) {
            arr[i] = rank.get(arr[i]);
        }

        return arr;
    }
}