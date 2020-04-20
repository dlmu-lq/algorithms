package top.itlq.algorithms;

/**
 * 背包问题
 */
public class BackPack {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // 动态规划，计算经过第i个物品后，背包对应的重量作为索引，对应的价值作为值
        // 状态转移方程：dpNext[n] = Math.max(dpPrevious[n], dpPrevious[n - A[i]])
        int[] dp = new int[m + 1];
        if(A[0] <= m){
            dp[A[0]] = V[0];
        }
        for(int i=1;i<A.length;i++){
            int[] dp2 = new int[m + 1];
            for(int j=0;j<m+1;j++){
                if(j >= A[i]){
                    dp2[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
                }else{
                    dp2[j] = dp[j];
                }
            }
            dp = dp2;
        }
        int re = dp[0];
        for(int i=1;i<dp.length;i++){
            if(re < dp[i]){
                re = dp[i];
            }
        }

        return re;
    }

    public static void main(String[] args) {
        System.out.println(new BackPack().backPackII(10, new int[]{2,3,5,7}, new int[]{1,5,2,4}));
    }
}
