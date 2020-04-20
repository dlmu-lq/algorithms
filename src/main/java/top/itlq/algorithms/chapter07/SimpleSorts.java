package top.itlq.algorithms.chapter07;

import java.util.Arrays;

/**
 * 简单的排序方法
 * 插入排序
 * 冒泡排序
 */
public class SimpleSorts {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertSort(new Integer[]{4,2,8,1,9,0,-4})));
        System.out.println(Arrays.toString(bubbleSort(new Integer[]{4,2,8,1,9,0,-4})));
    }

    /**
     * 插入排序，假设前面已有序，将当前元素找到在前面有序元素中的位置，将这个位置后的元素向后移移位
     * @param arr
     * @param <T>
     * @return
     */
    static <T extends Comparable<T>> T[] insertSort(T[] arr){
        int j;
        for(int i=1;i<arr.length;i++){
            T current = arr[i];
            for(j=i;j>0 && current.compareTo(arr[j - 1])<0;j--){ // 当前要插入的与上一个对比
                arr[j] = arr[j-1];
            }
            arr[j] = current;
        }
        return arr;
    }

    /**
     * 冒泡排序，每次遍历，找到最大值放到最后
     * @param arr
     * @param <T>
     * @return
     */
    static <T extends Comparable<T>> T[] bubbleSort(T[] arr){
        for(int i=0;i<arr.length - 1;i++){
            for(int j=0;j<arr.length - i - 1;j++){
                if(arr[j].compareTo(arr[j+1]) > 0){
                    T tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        return arr;
    }
}
