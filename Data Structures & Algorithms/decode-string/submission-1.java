class Solution {
    public String decodeString(String s) {
        
        var stack = new Stack<String>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(ch == ']'){
                StringBuilder sb = new StringBuilder();
                
                while(!stack.isEmpty() && !stack.peek().equals("[")){
                    String str = stack.pop();
                    sb.insert(0, str);
                }

                stack.pop(); // pop [

                StringBuilder num = new StringBuilder();

                while(!stack.isEmpty() && (stack.peek().equals("0") || 
                stack.peek().equals("1") || 
                stack.peek().equals("2") || 
                stack.peek().equals("3") || 
                stack.peek().equals("4") || 
                stack.peek().equals("5") || 
                stack.peek().equals("6") ||
                stack.peek().equals("7") ||
                stack.peek().equals("8") ||
                stack.peek().equals("9"))){
                    num.insert(0, stack.pop());
                }

                int timesFactor = Integer.parseInt(num.toString());

                String pushString = sb.toString();
                for(int in=0; in<timesFactor; in++){
                    stack.push(pushString);
                }
            }else{
                stack.push(ch+"");
            }
        }

        StringBuilder ans = new StringBuilder();
        for (String str : stack) {
            ans.append(str);
        }
        
        String result = ans.toString(); 
        return result;
    }
}