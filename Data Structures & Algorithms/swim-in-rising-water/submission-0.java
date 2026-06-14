class Solution {
     private boolean dfs(int[][] grid,int n, int m, int i, int j, int limit, boolean[][] vis, int[][] dirs){
        if(i == n-1 && j == m-1){
            return true;
        }

        vis[i][j] = true;
        
        for(int[] dir : dirs){
            int nRow = i + dir[0];
            int nCol = j + dir[1];
            if(nRow < n && nRow >=0 && nCol >=0 && nCol < m && !vis[nRow][nCol] && grid[nRow][nCol] <= limit){
                boolean isPossible = dfs(grid, n, m, nRow, nCol, limit, vis, dirs);
                if(isPossible)return true;
            }
        }

        //vis[i][j] = false;

        return false;

    }
    public int swimInWater(int[][] grid) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, n = grid.length, m = grid[0].length;

        for(int i=0; i<n; i++){
            for(int j=0; j<m ;j++){
                max = Math.max(max, grid[i][j]);
            }
        }

        min = Math.max(grid[0][0], grid[n-1][m-1]);
        
        
        //System.out.println(min+" "+max);

        int[][] dirs = { {-1,0}, {0,1}, {1,0}, {0,-1} };
        int ans = 0;
        while(min <= max){
            int mid = min + (max - min)/2;

            if(dfs(grid, n, m, 0, 0, mid, new boolean[n][m], dirs)){
                max = mid-1;
                ans = mid;
            }else{
                min = mid+1;
            }
        }

        return ans;
    }
}
