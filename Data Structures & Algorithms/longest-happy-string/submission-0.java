class Solution {
    class Char implements Comparable<Char> {
        int freq;
        char ch;
        Char(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }

        public int compareTo(Char o) {
            return o.freq - this.freq;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Char> pq = new PriorityQueue<Char>();
        StringBuilder sb = new StringBuilder();

        if(a > 0)
            pq.add(new Char(a, 'a'));
        
        if(b > 0)
            pq.add(new Char(b, 'b'));
        
        if(c > 0)
            pq.add(new Char(c, 'c'));

        while (!pq.isEmpty()) {
            Char ele = pq.remove();

            if (sb.length() > 1 && sb.charAt(sb.length() - 1) == ele.ch
                && sb.charAt(sb.length() - 2) == ele.ch) {
                if (pq.size() == 0)
                    break;
                else {
                    Char secEle = pq.remove();
                    secEle.freq--;
                    sb.append(secEle.ch);
                    pq.add(ele);

                    if (secEle.freq > 0)
                        pq.add(secEle);
                }
            } else {
                ele.freq--;
                sb.append(ele.ch);

                if (ele.freq > 0)
                    pq.add(ele);
            }

        }

        return sb.toString();
    }
}