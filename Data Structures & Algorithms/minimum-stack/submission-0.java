class MinStack {
    Stack<Integer> stack, minOrderStack;
    public MinStack() {
        stack = new Stack<>();
        minOrderStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if(minOrderStack.isEmpty() || (!minOrderStack.isEmpty() && minOrderStack.peek() >= val)){
            minOrderStack.push(val);
        }
    }
    
    public void pop() {
        int val = stack.pop();
        if(minOrderStack.peek() == val){
            minOrderStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minOrderStack.peek();
    }
}
