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

    public int[] findRedundantConnection(int[][] edges) {
        int n = 0;
        for(int[] edge : edges){
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }

        UnionFind uf = new UnionFind(n+1);

        for(int[] edge : edges){
            if(!uf.union(edge[0], edge[1]))return edge;
        }

        return new int[0];
    }
}
