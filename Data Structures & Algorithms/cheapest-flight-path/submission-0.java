class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        var graph = new ArrayList<List<int[]>>(); // nbr, dist

        for(int i=0; i<n ;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] flight : flights){
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }


        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;

        var queue = new LinkedList<int[]>();

        queue.add(new int[]{src, 0}); //node, min dis to node

        int steps = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int[] curr = queue.removeFirst();

                for(int[] nbr : graph.get(curr[0])){
                    int nbrNode = nbr[0];
                    int nbrDis = nbr[1];

                    if(curr[1] + nbrDis < dis[nbrNode] && steps <= k){
                        dis[nbrNode] = curr[1] + nbrDis;
                        queue.addLast(new int[]{nbrNode, dis[nbrNode]});
                    }
                }

            }
            steps++;
        }

        return dis[dst] == Integer.MAX_VALUE ? -1 : dis[dst];
    }
}