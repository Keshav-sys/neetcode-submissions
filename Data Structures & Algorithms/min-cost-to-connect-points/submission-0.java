class Solution {
    class Pair implements Comparable<Pair> {
        int e1,e2,weight;

        public Pair(int e1,int e2,int weight){
            this.e1 = e1;
            this.e2 = e2;
            this.weight = weight;
        }

        public int compareTo(Pair o){
            return this.weight - o.weight; 
        }
    }

    private int dist(int i,int j,int[][] points){
        return Math.abs(points[i][0]-points[j][0]) + Math.abs(points[i][1]-points[j][1]);
    }
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        int n = points.length;
        pq.offer(new Pair(0,0,0));
        Set<Integer> visited = new HashSet<Integer>();
        int cost = 0;
        while(!pq.isEmpty() && visited.size() < n){
            Pair p = pq.poll();
            if(visited.contains(p.e2))continue;
            visited.add(p.e2);
            
            cost += p.weight;
            for(int i=0;i<n;i++){
                if(!visited.contains(i)){
                    int dist = dist(p.e2,i,points);
                    pq.offer(new Pair(p.e2,i,dist));
                }
            }
        } 
        return cost;
    }
}
