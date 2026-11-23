class ProductOfNumbers {

    private List<Integer> nums;

    public ProductOfNumbers() {
        nums = new ArrayList<>();
        nums.add(1);
    }
    
    public void add(int num) {
        if(num == 0) {
            nums = new ArrayList<>();
            nums.add(1);
        } else {
            int preProd = nums.get(nums.size() - 1);
            nums.add(preProd * num);
        }
    }
    
    public int getProduct(int k) {
        int len = nums.size();
        if(k >= len) {
            return 0;
        }

        return nums.get(len - 1) / nums.get(len - k - 1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */