package top.itlq.algorithms.chapter02;

public class BinarySearch {
    public static void main(String[]args){

    }
    public static int binarySearch(int[]arr, int aim){
        int left = 0, right = arr.length - 1, center = 0;
        while (left <= right){
            center = (left + right) >>> 1;
            if(arr[center] > aim){
                right = center - 1;
            }else if(arr[center] < aim){
                left = center + 1;
            }else{
                return center;
            }
        }
        return - left - 1 ;
    }
}
