class Solution {
    class UnionFind{
        int[] par;
        int[] rank;
        int maxRank;
        public UnionFind(int n){
            this.par = new int[n];
            this.rank = new int[n];
            for(int i=0;i<n;i++){
                par[i] = i;
                rank[i] = 1;
            }  
            this.maxRank = 1;
        }

        public int findPar(int u){
            if(par[u] == u)return u;

            return par[u] = findPar(par[u]);
        }

        public boolean union(int u, int v){
            int upu = findPar(u);
            int upv = findPar(v);

            if(upu == upv)return false;

            if(rank[upu] > rank[upv]){
                par[upv] = upu;
                rank[upu] += rank[upv];
                this.maxRank = Math.max(this.maxRank, rank[upu]);
            }else{
                par[upu] = upv;
                rank[upv] += rank[upu];
                this.maxRank = Math.max(this.maxRank, rank[upv]);
            }

            return true;
        }
    }
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        
        int[][] nEdges = new int[edges.length][4];
        
        for(int i=0; i<edges.length; i++){
            nEdges[i][0] = edges[i][0];
            nEdges[i][1] = edges[i][1];
            nEdges[i][2] = edges[i][2];
            nEdges[i][3] = i;
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> criticalEdges = new ArrayList<>();
        List<Integer> pseudoCriticalEdges = new ArrayList<>();
        
        Arrays.sort(nEdges,(a, b) -> {return a[2] - b[2];});

        UnionFind uf = new UnionFind(n);
        int mstWt = 0;

        for(int[] nEdge : nEdges){
            if(uf.union(nEdge[0], nEdge[1])){
                mstWt += nEdge[2];
            }
        }

        
        for(int[] nEdge : nEdges){
            //Try without curr edge
            int wt = 0;
            uf = new UnionFind(n);
            for(int[] nEdge1 : nEdges){
                if(nEdge1[3] != nEdge[3] && uf.union(nEdge1[0], nEdge1[1])){
                    wt += nEdge1[2];
                }
            }

            //System.out.println("for edge "+nEdge[3]+" without wt is "+wt);
            
            if(uf.maxRank != n || wt > mstWt){
                criticalEdges.add(nEdge[3]);
                continue;
            }

            //Try with curr edge
            wt = nEdge[2];
            uf = new UnionFind(n);
            uf.union(nEdge[0], nEdge[1]);
            for(int[] nEdge1 : nEdges){
                if(uf.union(nEdge1[0], nEdge1[1])){
                    wt += nEdge1[2];
                }
            }

            //System.out.println("for edge "+nEdge[3]+" with wt is "+wt);

            if(wt == mstWt){
                pseudoCriticalEdges.add(nEdge[3]);
            }

        }

        ans.add(criticalEdges);
        ans.add(pseudoCriticalEdges);

        return ans;

    }
}