class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        double[] posSpeed = new double[target+1];

        for(int pos=0; pos<position.length; pos++){
            double sub = target - position[pos];
            double time = (double)(sub/speed[pos]);

            posSpeed[position[pos]] = time;
             //System.out.println("Time at pos :: "+position[pos]+" is "+time);
        }

        Stack<Double> stk = new Stack<Double>();

        int fleet = 0;

        for(int i=0; i<=target; i++){
            if(posSpeed[i] > 0){
                stk.push(posSpeed[i]);
            }
        }

        while(!stk.isEmpty()){
            double time = stk.pop();

            while(!stk.isEmpty() && stk.peek() <= time){
                stk.pop();
            }

            fleet++;
        }

        return fleet;
    }
}
