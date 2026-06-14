class StockSpanner {
    int day = 1;
    class Stock{
        int value, day;
        Stock(int value, int day){
            this.value = value;
            this.day = day;
        }
    }

    Stack<Stock> stk;
    public StockSpanner() {
        stk = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        if(!stk.isEmpty()){
            while(!stk.isEmpty() && stk.peek().value <= price){
                stk.pop();
            }

            if(stk.size() != 0)span = day - stk.peek().day;
            else span = day;
        }

        stk.push(new Stock(price, day));
        day++;

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */