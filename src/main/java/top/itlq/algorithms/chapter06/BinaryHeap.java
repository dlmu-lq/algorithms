package top.itlq.algorithms.chapter06;

/**
 * 二叉堆
 * 一个完全二叉树；
 * 可以由数组实现；
 * 需要实现 找到最小 O(1)；
 * 删除最小 O(logN);
 */
public class BinaryHeap<T extends Comparable<? super T>>{
    public BinaryHeap(){

    }
    public BinaryHeap(int capacity){

    }
    public BinaryHeap(T[] items){

    }

    public void insert(T item){

    }
    public T findMin(){
        return null;
    }
    public T deleteMin(){
        return null;
    }
    public boolean isEmpty(){
        return true;
    }
    public void makeEmpty(){

    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private T[] array;

    private void percolateDown(int hole){

    }
    private void buildHeap(){

    }
    private void enlargeArray(int newSize){
        
    }
}
