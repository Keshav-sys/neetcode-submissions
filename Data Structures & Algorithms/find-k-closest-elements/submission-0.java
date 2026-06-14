class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        int l = 0, r = n-1;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        int left = l - 1, right = l;

        while(right - left - 1 < k){
            if(left < 0)right++;
            else if(right >= n)left--;
            else if(Math.abs(x-arr[left]) <= Math.abs(x-arr[right])){
                left--;
            }else{
                right++;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();

        for(int i=left+1; i<right; i++){
            ans.add(arr[i]);
        }

        return ans;
    }
}