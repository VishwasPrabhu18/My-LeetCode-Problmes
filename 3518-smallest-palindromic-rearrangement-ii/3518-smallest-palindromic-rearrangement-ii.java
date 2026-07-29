class Solution {
    private long comb(int totalItems, int itemsToChoose, long maxValue) {
        if (itemsToChoose > totalItems) {
            return 0;
        }
        // Optimization: C(n,r) = C(n,n-r), so use the smaller value for efficiency
        itemsToChoose = Math.min(itemsToChoose, totalItems - itemsToChoose);
        
        long combinations = 1;
        for (int i = 1; i <= itemsToChoose; i++) {
            combinations = combinations * (totalItems - itemsToChoose + i) / i;
            if (combinations >= maxValue) {
                return maxValue;
            }
        }
        return combinations;
    }


    private long countPerms(List<Integer> characterCounts, int remainingPositions, long maxValue) {
        long permutations = 1;
        for (int count : characterCounts) {
            // Calculate how many ways we can place 'count' characters in the remaining positions
            long waysToPlace = comb(remainingPositions, count, maxValue);
            permutations *= waysToPlace;
            if (permutations >= maxValue) {
                return maxValue;
            }
            remainingPositions -= count;
        }
        return permutations;
    }


    public String smallestPalindrome(String inputString, int kthSmallest) {
        // Maximum permutations needed
        final long maxPermutationsNeeded = kthSmallest + 1L;
        
        // Character frequency counter
        int[] characterFrequency = new int[26];
        for (char character : inputString.toCharArray()) {
            characterFrequency[character - 'a']++;
        }

        // Process characters for palindrome construction
        String middleCharacter = "";
        List<Integer> halfPalindromeCharCounts = new ArrayList<>();
        
        for (int i = 0; i < 26; i++) {
            // If character appears odd number of times, it must be in the middle
            if (characterFrequency[i] % 2 == 1) {
                middleCharacter = String.valueOf((char) ('a' + i));
            }
            // Half palindrome count
            halfPalindromeCharCounts.add(characterFrequency[i] / 2);
        }

        // Half length calculation
        int halfLength = 0;
        for (int count : halfPalindromeCharCounts) {
            halfLength += count;
        }

        // Total palindrome count
        long totalPossiblePalindromes = countPerms(halfPalindromeCharCounts, halfLength, maxPermutationsNeeded);
        
        // Check validity of k
        if (kthSmallest > totalPossiblePalindromes) {
            return "";
        }

        // Build palindrome first half
        StringBuilder firstHalf = new StringBuilder();
        for (int position = 0; position < halfLength; position++) {
            for (int charIndex = 0; charIndex < 26; charIndex++) {
                // Skip unavailable characters
                if (halfPalindromeCharCounts.get(charIndex) == 0) {
                    continue;
                }
                
                // Try character
                halfPalindromeCharCounts.set(charIndex, halfPalindromeCharCounts.get(charIndex) - 1);
                
                // Count palindromes with this prefix
                long palindromesWithThisPrefix = countPerms(
                    halfPalindromeCharCounts, 
                    halfLength - position - 1, 
                    maxPermutationsNeeded
                );

                if (kthSmallest > palindromesWithThisPrefix) {
                    // Not the right character, try next
                    kthSmallest -= palindromesWithThisPrefix;
                    halfPalindromeCharCounts.set(charIndex, halfPalindromeCharCounts.get(charIndex) + 1);
                } else {
                    // Found right character
                    firstHalf.append((char) ('a' + charIndex));
                    break;
                }
            }
        }

        // Construct full palindrome
        return firstHalf.toString() + middleCharacter + firstHalf.reverse().toString();
    }
}