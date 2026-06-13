class Solution {
    private static final String FORWARD = "FORWARD";
    private static final String BACKWARD = "BACKWARD";
    class Asteroid{
        int val;
        String dir;
        public Asteroid(int val, String dir){
            this.val = val;
            this.dir = dir;
        }
    }
    public int[] asteroidCollision(int[] asteroids) {
        var stack = new Stack<Asteroid>();

        for(int asteroid : asteroids){
            int currVal = asteroid;
            String currDir = asteroid < 0 ? BACKWARD : FORWARD;

            if(stack.isEmpty() || stack.peek().dir.equals(BACKWARD) || stack.peek().dir.equals(currDir)){
                stack.push(new Asteroid(currVal, currDir));
            }else{
                //left with case where curr -> backward prev -> forward
                boolean add = true;

                while(!stack.isEmpty() && stack.peek().dir.equals(FORWARD)){
                    if(Math.abs(currVal) > Math.abs(stack.peek().val)){
                        stack.pop();
                    }else if(Math.abs(currVal) < Math.abs(stack.peek().val)){
                        add = false;
                        break;
                    }else{
                        stack.pop();
                        add = false;
                        break;
                    }
                }

                if(add)
                    stack.push(new Asteroid(currVal, currDir));
            }
        }

        Collections.reverse(stack); 

        int[] ans = new int[stack.size()];
        int ansIndex = 0;
        while(!stack.isEmpty()){
            ans[ansIndex++] = stack.pop().val;
        }

        return ans;
    }
}