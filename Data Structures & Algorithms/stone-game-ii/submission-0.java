class Solution {
    int[] piles, prefix;
    int n;
    Integer[][][] dp;
    private int helper(int start, int alice, int m){
        if(start == n){
            return 0;
        }

        if(dp[alice][start][m] != null)return dp[alice][start][m];

        int score = alice == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for(int i = start; i < Math.min(n, start+(2*m)); i++){
            
            int sum = alice == 0 ? prefix[i+1] - prefix[start] : 0;

            score = alice == 0 ? Math.max(score, sum + helper(i+1, 1, Math.max((i-start)+1,m))) : 
                            Math.min(score, helper(i+1, 0, Math.max((i-start)+1, m)));
        }

        return dp[alice][start][m] = score;
    }

    
    public int stoneGameII(int[] piles) {
        this.piles = piles;
        this.n = piles.length;

        this.prefix = new int[n+1];

        this.dp = new Integer[2][n][2*n];

        for(int i=0; i<n; i++){
            prefix[i+1] = prefix[i] + piles[i]; 
        }

        return helper(0, 0, 1);

    }
}