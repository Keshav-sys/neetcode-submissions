class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] pse = new int[n];
        int[] nse = new int[n];

        Stack<Integer> stack = new Stack<Integer>();

        stack.push(0);
        pse[0] = -1;

        for(int i=1;i<n;i++){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                nse[stack.peek()] = i;
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            nse[stack.peek()] = n;
            stack.pop();
        }



        int max = 0;
        for(int i=0;i<n;i++){
            int area = heights[i]*(nse[i]-pse[i]-1);
            max = Math.max(area, max);
        }

        return max;
    }
}