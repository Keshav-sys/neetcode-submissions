class FreqStack {
    HashMap<Integer, Stack<Integer>> stackMap;
    HashMap<Integer, Integer> freqMap;
    int maxFreq;
    public FreqStack() {
        stackMap = new HashMap<>();
        freqMap = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        int freq = freqMap.getOrDefault(val, 0);
        freq++;
    
        Stack stk = stackMap.getOrDefault(freq, new Stack<Integer>());

        stk.push(val);

        stackMap.put(freq, stk);
        freqMap.put(val, freq);

        maxFreq = Math.max(maxFreq, freq);
    }
    
    public int pop() {
        Stack stk = stackMap.get(maxFreq);
        
        int ele = (Integer) stk.pop();

        freqMap.put(ele, freqMap.get(ele)-1);
        
        if(stk.isEmpty()){
            maxFreq--;
        }

        return ele;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */