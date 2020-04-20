package top.itlq.algorithms;

import java.util.Arrays;

public class ShortestPathToZero {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(
                new ShortestPathToZero().updateMatrix(
                        new int[][]{
                                {1,0,1,1,0,0,1,0,0,1},
                                {0,1,1,0,1,0,1,0,1,1},
                                {0,0,1,0,1,0,0,1,0,0},
                                {1,0,1,0,1,1,1,1,1,1},
                                {0,1,0,1,1,0,0,0,0,1},
                                {0,0,1,0,1,1,1,0,1,0},
                                {0,1,0,1,0,1,0,0,1,1},
                                {1,0,0,0,1,1,1,1,0,1},
                                {1,1,1,1,1,1,1,0,1,0},
                                {1,1,1,1,0,1,0,0,1,1},
                        }
                )
        ));
    }

    public int[][] updateMatrix(int[][] matrix) {
        int[][] re = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0,len = matrix[0].length;j<len;j++){
                if(i == 9 && j == 1){
                    System.out.println();
                }
                dfsForShortest(i, j, matrix, re);
            }
        }
        return re;
    }

    // 找
    int dfsForShortest(int i, int j, int[][] matrix, int[][] shortests){
        if(i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length){
            return 10000;
        }
        if(matrix[i][j] == 0){
            return 0;
        }else{
//            if(shortests[i][j] > 0){
//                // 之前已经计算出的节点
//                return shortests[i][j];
//            }else
            if(shortests[i][j] == -1){
                return 10000;
            } else{
                // 标记当前计算的节点为 -1，本次递归不再参与计算
                int lastCompute = shortests[i][j];
                shortests[i][j] = -1;
                shortests[i][j] = Math.min(
                        Math.min(dfsForShortest(i+1, j, matrix, shortests), dfsForShortest(i-1, j, matrix, shortests)),
                        Math.min(dfsForShortest(i, j+1, matrix, shortests), dfsForShortest(i, j-1, matrix, shortests))
                ) + 1;
                if(lastCompute > 0 && lastCompute < shortests[i][j]){
                    shortests[i][j] = lastCompute;
                }
                return shortests[i][j];
            }
        }
    }
}
