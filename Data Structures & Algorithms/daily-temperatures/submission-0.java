class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        Stack<Integer> stk = new Stack<Integer>();

        stk.push(n-1);
        ans[n-1] = n-1;
        
        for(int i=n-2; i>=0; i--){
            while(!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i]){
                stk.pop();
            }

            int nextGreater = stk.isEmpty() ? i : stk.peek();   

            ans[i] = nextGreater;

            stk.push(i);
        }

        for(int i=0; i<n; i++){
            ans[i] = ans[i] - i;
        }

        return ans;
    }
}
