package top.itlq.algorithms.chapter04;

import java.util.Comparator;

/**
 * 二叉树定义，每个节点都不能有多于两个子节点；
 * 二叉查找树
 * 嵌套类，节点类型；
 * 数据域：root根节点；
 * 方法：contains findMax findMin insert remove printTree
 * 注意利用树的定义，树的左右子树还是一个树，可以方便的使用递归简化代码逻辑；
 * 插入，移除的递归总是返回传入的树，即使用递归消除了while的复杂逻辑；
 * 移除最小值的方法；结合移除和最小值的查找；
 */
public class BinarySearchTree<T> {

    private BinaryNode<T> root;
    private Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator){
        root = null;
        this.comparator = comparator;
    }

    public boolean contains(T val){
        return contains(val, root);
    }

    public BinaryNode<T> findMax(){
        return findMax(root);
    }

    public BinaryNode<T> findMin(){
        return findMin(root);
    }

    public void insert(T val){
        root = insert(val, root);
    }

    public void remove(T val){
        root = remove(val, root);
    }

    /**
     * 一个树中是否含有 val
     * @param val
     * @param tree
     * @return
     */
    private boolean contains(T val, BinaryNode<T> tree){
        if(tree == null){
            return false;
        }
        int comp = comparator.compare(val, tree.val);
        if(comp < 0){
            return contains(val, tree.left);
        }else if(comp > 0){
            return contains(val, tree.right);
        }else{
            return true;
        }
    }

    /**
     * 递归找最大
     * @param tree
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> tree){
        if(tree == null){
            return null;
        }
        if(tree.right == null){
            return tree;
        }else{
            return findMax(tree.right);
        }
    }

    /**
     * 消除尾递归，使用while取最小
     * @param tree
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> tree){
        if(tree == null){
            return null;
        }
        while (tree.left != null){
            tree = tree.left;
        }
        return tree;
    }

    /**
     * 插入，其实是查找，找到最后没找到的位置即是它自己应该在的位置，
     * 所以每次比较查找时返回递归插入后的一个子树，最后找到null返回一个新节点即连上了
     * @param val
     * @param tree
     */
    private BinaryNode<T> insert(T val, BinaryNode<T> tree){
        if(tree == null){
            return new BinaryNode<>(val, null, null);
        }
        int comp = comparator.compare(val, tree.val);
        if(comp < 0){
            tree.left = insert(val, tree.left);
        }else if(comp > 0){
            tree.right = insert(val, tree.right);
        }else{// 已经有这个元素了，什么也不做；
            ;
        }
        return tree;
    }

    private BinaryNode<T> remove(T val, BinaryNode<T> tree){
        if(tree == null){
            return null;
        }
        int comp = comparator.compare(val, tree.val);
        if(comp < 0){
            tree.left = remove(val, tree.left);
        }else if(comp > 0){
            tree.right = remove(val, tree.left);
        }else{
            if(tree.right != null && tree.left != null){
                // 移除有两个子树的节点；找到右子树的最小节点，移除它，并把他的值赋给实际要移除的节点；
                tree.right = removeAndSaveMin(tree.right, tree);
            }else{
                // 移除树叶或只有一个子树的节点
                return tree.right != null ? tree.right : tree.left;
            }
        }
        return tree;
    }

    /**
     * 移除 tree 中的最小值，并将最小值赋给 minValTree；结合了找最小值和简单移除和保存最小值
     * @param tree
     * @param minValTree
     * @return
     */
    private BinaryNode<T> removeAndSaveMin(BinaryNode<T> tree, BinaryNode<T> minValTree){
        if(tree.left != null){
            // 未找到其最小值；在其左子树中继续找，并返回自己；
            tree.left = removeAndSaveMin(tree.left, minValTree);
            return tree;
        }else{
            // 找到最小值，将最小值存入 minValTree,并移除该最小值（直接返回其右子树）
            minValTree.val = tree.val;
            // FIXME 重要，找到最小值后，移除它，既返回它的右子树作为其原来的左子树，注意这样利用递归消除while的复杂逻辑；
            return tree.right;
        }
    }

    public static class BinaryNode<T>{
        T val;
        BinaryNode<T> left;
        BinaryNode<T> right;
        public BinaryNode(T val, BinaryNode<T> left, BinaryNode<T> right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String...args){
        BinarySearchTree<Integer> testBinarySearchTree = new BinarySearchTree<>(Integer::compareTo);
        testBinarySearchTree.insert(6);
        testBinarySearchTree.insert(2);
        testBinarySearchTree.insert(8);
        testBinarySearchTree.insert(1);
        testBinarySearchTree.insert(5);
        testBinarySearchTree.insert(3);
        testBinarySearchTree.insert(4);
        testBinarySearchTree.remove(2);
        System.out.println();
    }
}
