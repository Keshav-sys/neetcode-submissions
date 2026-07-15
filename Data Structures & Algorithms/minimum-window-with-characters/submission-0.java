class Solution {
    public String minWindow(String s, String t) {
      int ns = s.length(),nt = t.length(),len = Integer.MAX_VALUE,si = 0,ei = 0,gsi = 0,count = nt;
        int freq[] = new int[128];
        for(int i=0;i<nt;i++){
            freq[t.charAt(i)]++;
        }
        
        while(ei < ns){
            if(freq[s.charAt(ei)] > 0){
                count--;
            }
            freq[s.charAt(ei)]--;
            ei++;
            while(count == 0){
                if(ei - si < len){
                    len = ei - si;
                    gsi = si;
                }
                if(freq[s.charAt(si)] == 0){
                    count++;
                }
                freq[s.charAt(si)]++;
                si++;
            }
        }
        return len == Integer.MAX_VALUE ? "":s.substring(gsi,gsi+len);
    }
}