package top.itlq.algorithms.chapter01;

/**
 * N的二进制表示中1的个数
 * 递归方式
 */
public class Exercise5 {
    public static void main(String...args){
        System.out.println(345);
        System.out.println(Integer.toBinaryString(345));
        System.out.println(count1(345));
    }
    public static int count1(int num){
        if(num == 0){
            return 0;
        }else if(num == 1){
            return 1;
        }else if(num % 2 == 0){
            return count1(num / 2);
        }else{
            return count1(num / 2) + 1;
        }
    }
}
