package top.itlq.algorithms.chapter01;

/**
 * 选择问题
 */
public class Exercise1 {
    public static void main(String...args){
        int[] arr = new int[]{3,1,5,7,2,9};
        System.out.println(getMinK(arr, 6));
    }

    public static int getMinK(int[] arr, int k){
        if(k > arr.length || k <= 0){
            throw new RuntimeException("k must <= arr.length()");
        }
        // 从小到大的前k个数
        int[] sortedArr = new int[k];
        // 遍历所有arr里的数，将每个尝试放入上述数组
        for(int i=0;i<arr.length;i++){
            int len = i;
            // 如果还没放到k，那么可以放到i位置
            if(i < k){
                sortedArr[i] = arr[i];
            }else{
                // 如果已经放了k个，比较新的和最后一个，如果大于直接舍弃，如果小于直接替换掉最后一个；
                if(sortedArr[k - 1] < arr[i]){
                    continue;
                }else{
                    sortedArr[k - 1] = arr[i];
                    len = k - 1;
                }
            }
            // 将最新要放入的往前移以维持顺序
            for(int j = len;j > 0;j--){
                if(sortedArr[j] < sortedArr[j - 1]){
                    int temp = sortedArr[j];
                    sortedArr[j] = sortedArr[j - 1];
                    sortedArr[j - 1] = temp;
                }else{
                    break;
                }
            }
        }
        return sortedArr[k - 1];
    }
}
