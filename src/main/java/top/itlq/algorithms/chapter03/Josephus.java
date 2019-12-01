package top.itlq.algorithms.chapter03;

/**
 * 自定义链表，josephus 问题，N个人围坐，从第一个依次报数；报数M的出局，从下一个再开始报，
 */
public class Josephus {
    public static void main(String...args){
        josephus(41, 2);
        josephus2(41, 2);
    }
    private static void josephus(int n, int m){
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for(int i=1;i<=n;i++){
            myLinkedList.add(i);
        }
        while (myLinkedList.size > 0){
            System.out.println(myLinkedList.removeAndReLink(m));
        }
    }
    private static void josephus2(int n, int m){
        MyCircleLinkedList<Integer> myLinkedList = new MyCircleLinkedList<>();
        for(int i=1;i<=n;i++){
            myLinkedList.add(i);
        }
        while (myLinkedList.size > 0){
            System.out.println(myLinkedList.remove(m));
        }
    }

    /**
     * 自定义双向链表；下面自定义循环链表更好；
     * @param <T>
     */
    private static class MyLinkedList<T>{
        Node<T> beginMarker;
        Node<T> endMarker;
        public int size = 0;
        public MyLinkedList(){
            beginMarker = new Node<>(null, null,null);
            endMarker = new Node<>(null, beginMarker, null);
            beginMarker.next = endMarker;
        }
        public void add(T val){
            endMarker.previous = endMarker.previous.next = new Node<>(val, endMarker.previous, endMarker);
            size++;
        }

        /**
         * 移除第index个并将链表头变为 index+1,并将尾与头相连；
         * @param index
         */
        public T removeAndReLink(int index){
            // 从头开始找，找到第index个
            int marker = 0;
            Node<T> shoudBeRemoved = beginMarker.next;
            while (marker < index){
                marker++;
                shoudBeRemoved = shoudBeRemoved.next;
                // 找到尾还没找到，从头再开始找
                if(shoudBeRemoved == endMarker){
                    shoudBeRemoved = beginMarker.next;
                }
            }
            if(shoudBeRemoved.previous == beginMarker){
                // TODO 这里容易错，重新设置表头时应该新建而不是赋值；
                beginMarker = new Node<>(null, null, shoudBeRemoved.next);
                shoudBeRemoved.next.previous = beginMarker;
            }else if(shoudBeRemoved.next == endMarker){
                shoudBeRemoved.previous.next = endMarker;
                endMarker.previous = shoudBeRemoved.previous;
            }else{
                // 表尾与表头相连，新表头为index+1
                endMarker.previous.next = beginMarker.next;
                beginMarker.next.previous = endMarker.previous;
                // TODO 这里容易错，重新设置表头时应该新建而不是赋值；容易写成，beginMarker = shoudBeRemoved.next
                beginMarker = new Node<T>(null, null, shoudBeRemoved.next);
                shoudBeRemoved.next.previous = beginMarker;
                // TODO 这里容易错，重新设置表尾时应该新建而不是赋值；
                endMarker = new Node<T>(null, shoudBeRemoved.previous, null);
                shoudBeRemoved.previous.next = endMarker;
            }
            size--;
            return shoudBeRemoved.val;
        }
    }

    /**
     * 自定义循环链表
     * @param <T>
     */
    private static class MyCircleLinkedList<T>{
        Node<T> beginMarker;
        public int size = 0;
        public MyCircleLinkedList(){
            beginMarker = new Node<>(null, null, null);
            beginMarker.previous = beginMarker;
            beginMarker.next = beginMarker;
        }
        public void add(T val){
            beginMarker.previous = beginMarker.previous.next = new Node<>(val, beginMarker.previous, beginMarker);
            size++;
        }

        /**
         * 移除第index个并将链表头变为 index+1,并将尾与头相连；
         * @param index
         */
        public T remove(int index){
            // 从头开始找，找到第index个
            int marker = 0;
            Node<T> shoudBeRemoved = beginMarker.next;
            while (marker < index){
                marker++;
                shoudBeRemoved = shoudBeRemoved.next;
                // 找到尾还没找到，从头再开始找
                if(shoudBeRemoved == beginMarker){
                    shoudBeRemoved = beginMarker.next;
                }
            }
            // 移除旧表头，这样移除后，最后一个将自己成环；
            beginMarker.previous.next = beginMarker.next;
            beginMarker.next.previous = beginMarker.previous;
            // 创建新表头
            if(shoudBeRemoved.previous == shoudBeRemoved){ // TODO，这里容易错，最后一个自成环，不能这样移除；
                beginMarker = new Node<>(null, shoudBeRemoved.previous, shoudBeRemoved.next);
                beginMarker.previous.next = beginMarker;
                beginMarker.next.previous = beginMarker;
            }else{
                beginMarker = new Node<>(null, null, null);
                beginMarker.previous = beginMarker;
                beginMarker.next = beginMarker;
            }
            size--;
            return shoudBeRemoved.val;
        }
    }
    static class Node<T>{
        T val;
        Node<T> previous;
        Node<T> next;
        Node(T val, Node<T> previous, Node<T> next){
            this.val = val;
            this.previous = previous;
            this.next = next;
        }
    }
}
