package top.itlq.algorithms.chapter02;

public class MaxSubSum {
    public static void main(String...args){
        int[] arr = new int[]{-1,2,5,-3,4};
        System.out.println(maxSubSum1(arr));
        System.out.println(maxSubSum2(arr, 0, arr.length - 1));
        System.out.println(maxSubSum3(arr));
    }

    /**
     * 简单两层遍历计算每个子序列进行对比
     * @param arr
     * @return
     */
    public static int maxSubSum1(int[] arr){
        int maxSum = 0;
        for(int i=0;i<arr.length;i++){
            int thisSum = 0;
            for(int j=i;j<arr.length;j++){
                thisSum += arr[j];
                if(thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 使用分治策略递归计算，分成两段，分别计算两端最大子序列和包含两端近边界的最大子序列取最大，即是序列的最大子序列
     * @param arr
     * @return
     */
    public static int maxSubSum2(int[] arr, int left, int right){
        if(left == right){
            if(arr[left] > 0){
                return arr[left];
            }else{
                return 0;
            }
        }
        int center = (left + right) >>> 1;
        int maxLeft = maxSubSum2(arr, left, center);
        int maxRight = maxSubSum2(arr, center + 1, right);
        int maxLeftWithBorder = 0, leftWithBorder = 0, maxRightWithBorder = 0, rightWithBorder = 0;
        for(int i=center;i>=left;i--){
            leftWithBorder += arr[i];
            if(leftWithBorder > maxLeftWithBorder){
                maxLeftWithBorder = leftWithBorder;
            }
        }
        for(int i=center + 1;i<=right;i++){
            rightWithBorder += arr[i];
            if(rightWithBorder > maxRightWithBorder){
                maxRightWithBorder = rightWithBorder;
            }
        }
        return Math.max(Math.max(maxLeft, maxRight), maxLeftWithBorder + maxRightWithBorder);
    }

    /**
     * 利用最大子序列的前缀不可能为负数；确定起始位置，新序列位置
     * @param arr
     * @return
     */
    public static int maxSubSum3(int[] arr){
        // maxSum 存储遍历过的最大子序列，thisSum用来探索是否能更大，如果thisSum小于0则证明可以放弃前面所累积的，因为从下一个开始总比`加上前面的`大(此时前面部分最大的已经保存所以不怕丢失)；
        int maxSum = 0, thisSum = 0;
        for(int i=0;i<arr.length;i++){
            thisSum += arr[i];
            if(thisSum < 0) {
                thisSum = 0;
            }else if(thisSum > maxSum){
                maxSum = thisSum;
            }
        }
        return maxSum;
    }
}
