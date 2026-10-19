class Solution {

    public String encode(List<String> strs) {
        StringBuilder s = new StringBuilder();
        for(String str : strs){
            s.append(str.length()+"#");
            s.append(str);
        }

        return s.toString();
    }

    public List<String> decode(String str) {
        
        List<String> ans = new ArrayList<String>();

        int index = 0;

        while(index < str.length()){
            int startIndex = index;
            while(str.charAt(index) != '#'){
                index++;
            }

            int size = Integer.parseInt(str.substring(startIndex, index));
            
            ans.add(str.substring(index+1, index+1+size));

            index = index+size+1;
        }

        return ans;
    }
}
