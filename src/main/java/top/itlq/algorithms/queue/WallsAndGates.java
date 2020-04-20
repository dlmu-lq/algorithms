package top.itlq.algorithms.queue;

import java.util.Arrays;

/**
 * 墙与门 https://www.cnblogs.com/yaphse-19/p/12029080.html
 * 这个递归算法可能有问题，不能使用之前找过点的已知最短路，好像因为会有特例可能不通过
 * todo 应该使用广度优先算法分别找到每个点的最短路，
 * todo 结合https://leetcode-cn.com/explore/learn/card/queue-stack/220/conclusion/892/题 试试广度优先加动态规划是否能通过；
 */
public class WallsAndGates {
    public void wallAndGates(int[][] grid){
        for(int i=0;i<grid.length;i++){
            for(int j=0, len=grid[0].length;j<len;j++){
                if (grid[i][j] == 2147483647 || grid[i][j] < -1){
                    grid[i][j] = nearestToGate(i, j, grid);
                }
            }
        }
    }

    // 递归找到当前节点离门最近距离（带限制条件，不能走回头路）
    private int nearestToGate(int i, int j, int[][] grid){
        if(i < 0 || j < 0 || i > grid.length-1 || j > grid[0].length-1 || grid[i][j] < 0){
            return -1;
        }
        if(grid[i][j] == 0){
            return 0;
        }else if(grid[i][j] == 2147483647){
            int temp = grid[i][j];
            // 将当前节点标记为 -2,代表不能走；
            grid[i][j] = -2;
            int[] dis = {
                    nearestToGate(i+1, j, grid),
                    nearestToGate(i-1, j, grid),
                    nearestToGate(i, j+1, grid),
                    nearestToGate(i, j-1, grid)
            };
            int minPath = -2;
            for(int v:dis){
                if(v >= 0 && (minPath == -2 || minPath > v + 1)){
                    minPath = v + 1;
                }
            }
            // 如果暂未找到，需要恢复当前节点值，因为可能带有限制条件的未找到，轮到自己时下一步或许可以找到；
            grid[i][j] = minPath == -2 ? temp : minPath;
            return minPath;
        }else{
            return grid[i][j];
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {2147483647, -1, 0, 2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647},
        };
        new WallsAndGates().wallAndGates(grid);
        System.out.println(Arrays.deepToString(grid));
    }
}
