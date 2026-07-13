class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][][] dp = new int[2][n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int M = 1; M <= n; M++) {
                int total = 0;
                dp[1][i][M] = 0;
                dp[0][i][M] = Integer.MAX_VALUE;

                for (int X = 1; X <= 2 * M; X++) {
                    if (i + X > n)
                        break;
                    total += piles[i + X - 1];

                    dp[1][i][M] = Math.max(dp[1][i][M], total + dp[0][i + X][Math.max(M, X)]);
                    dp[0][i][M] = Math.min(dp[0][i][M], dp[1][i + X][Math.max(M, X)]);
                }
            }
        }
        return dp[1][0][1];
    }
}