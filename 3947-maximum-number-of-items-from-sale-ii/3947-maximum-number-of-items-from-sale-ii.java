class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Step 1: Find minimum price and factor frequencies
        int minPrice = Integer.MAX_VALUE;

        int[] freq = new int[n + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            freq[factor]++;
            minPrice = Math.min(minPrice, price);
        }

        // Step 2: Count items whose factors are divisible
        int[] count = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int multiple = f;
                 multiple <= n;
                 multiple += f) {

                count[f] += freq[multiple];
            }
        }

        // Step 3: Sort items by price
        Arrays.sort(items, (a, b) ->
          Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long enhanced = 0;

        // Step 4: Purchase useful copies greedily
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // More expensive than two cheapest items
            if ((long) price > 2L * minPrice) {
                break;
            }

            // Maximum useful copies for this item
            int freeLimit = count[factor] - 1;

            if (freeLimit == 0) {
                continue;
            }

            // Number of copies affordable
            long canBuy = remaining / price;

            long take = Math.min(
              (long) freeLimit,
              canBuy
            );

            enhanced += take;
            remaining -= take * price;
        }

        // Step 5: Buy cheapest items with remaining budget
        return (int) (2L * enhanced + remaining / minPrice);
    }
}