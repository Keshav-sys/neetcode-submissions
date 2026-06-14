class Solution {
    class Edge{
        int to, wt;
        public Edge(int to, int wt){
            this.to = to;
            this.wt = wt;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dis = new int[n+1];
        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();

        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<Edge>());
        }

        for(int[] edge : times){
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
        }


        
        Arrays.fill(dis, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1]));

        pq.add(new int[]{k, 0}); // node, minWeightSoFar

        while(!pq.isEmpty()){
            int[] node = pq.remove();

            if(dis[node[0]] > node[1]){
                dis[node[0]] = node[1];

            for(Edge edge : graph.get(node[0])){
                int nbr = edge.to;
                int wtSoFar = node[1] + edge.wt;

                if(dis[nbr] > wtSoFar){
                    pq.add(new int[]{nbr, wtSoFar});
                }
            }
            }
            
        }

        int max = 0;
        for(int i=1; i<=n; i++){
            int time = dis[i];
            if(time == Integer.MAX_VALUE)return -1;

            max = Math.max(max, time);
        }

        return max;
    }
}
