class Solution {
    int[] bestHeight, secondBestHeight, totalHeight;
    List<List<Integer>> graph;
    private int dfs1(int node, int par){
        
        for(int nbr : graph.get(node)){
            if(nbr != par){
                int height = dfs1(nbr, node) + 1;
                if(height >= bestHeight[node]){
                    secondBestHeight[node] = bestHeight[node];
                    bestHeight[node] = height;
                }else if(height < bestHeight[node] && height > secondBestHeight[node]){
                    secondBestHeight[node] = height;
                }
            }
        }

        return bestHeight[node];
    }

    private void dfs2(int node, int par, int upHeight) {
        totalHeight[node] = Math.max(bestHeight[node], upHeight);

        for (int nbr : graph.get(node)) {
            if (nbr == par) continue;

            boolean isBestPath = (bestHeight[nbr] + 1 == bestHeight[node]);
            int bestAlternativeDown = isBestPath ? secondBestHeight[node] : bestHeight[node];
            int nextUpHeight = 1 + Math.max(upHeight, bestAlternativeDown);

            dfs2(nbr, node, nextUpHeight);
        }
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        graph = new ArrayList<>();
        bestHeight = new int[n];
        secondBestHeight = new int[n];
        totalHeight = new int[n];


        for(int i=0; i<n ;i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs1(0, -1);
        dfs2(0, -1, 0);

        int minHeight = Integer.MAX_VALUE;
        var ans = new ArrayList<Integer>();

        for(int i=0; i<n; i++){
            int height = totalHeight[i];
            if(height == minHeight){
                ans.add(i);
            }else if(height < minHeight){
                ans = new ArrayList<Integer>();
                minHeight = height;
                ans.add(i);
            }
        }

        return ans;

    }
}