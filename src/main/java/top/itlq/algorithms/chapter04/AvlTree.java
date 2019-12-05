package top.itlq.algorithms.chapter04;

/**
 * 简单的Avl树，平衡二叉查找树
 * @param <T>
 */
public class AvlTree<T extends Comparable<? super T>> {

    private AvlNode<T> root;
    private static final int ALLOW_HEIGHT_DIFF = 1;

    public AvlTree(){
        root = null;
    }

    public void insert(T val){
        root = insert(val, root);
    }

    public void remove(T val){
        root = remove(val, root);
    }

    private AvlNode<T> insert(T val, AvlNode<T> tree){
        if(tree == null){
            return new AvlNode<>(val, null, null);
        }
        int comp = val.compareTo(tree.val);
        if(comp < 0){
            tree.left = insert(val, tree.left);
        }else if(comp > 0){
            tree.right = insert(val, tree.right);
        }else{
            ;
        }
        // 返回前，检查并平衡这个子树
        return balance(tree);
    }

    private AvlNode<T> remove(T val, AvlNode<T> tree){
        if(tree == null){
            // 没有该元素
            return null;
        }
        int comp = val.compareTo(tree.val);
        if(comp < 0){
            tree.left = remove(val, tree.left);
        }else if(comp > 0){
            tree.right = remove(val, tree.right);
        }else{
            if(tree.left != null && tree.right != null){
                // 右子树移除最小的，并将最小值赋给当前节点；
                tree.right = removeAndSaveMin(tree.right, tree);
            }else{
                // 要移除的节点最多有一个子节点；
                tree = tree.left == null ? tree.right :tree.left;
            }
        }
        return balance(tree);
    }

    /**
     * 找到最小的，保存最小值到 saveMinTree,返回移除最小值后的tree
     * @param tree
     * @param saveMinTree
     * @return
     */
    private AvlNode<T> removeAndSaveMin(AvlNode<T> tree, AvlNode<T> saveMinTree){
        if(tree.left != null){
            tree.left = removeAndSaveMin(tree.left, saveMinTree);
        }else{
            saveMinTree.val = tree.val;
            tree = tree.right;
        }
        // FIXME 此处也需要平衡，测试才想起来；
        return balance(tree);
    }

    private AvlNode<T> balance(AvlNode<T> tree){
        if(tree == null){
            return null;
        }
        if(height(tree.left) - height(tree.right) > ALLOW_HEIGHT_DIFF){
            // 左子树更高
            if(height(tree.left.left) >= height(tree.left.right)){
                // 左子树的左子树更高
                tree = rotateWithLeftChild(tree);
            }else{
                // 左子树的右子树更高
                tree = doubleRotateWithLeftChild(tree);
            }
        }else if(height(tree.right) - height(tree.left) > ALLOW_HEIGHT_DIFF){
            // 右子树更高
            if(height(tree.right.right) >= height(tree.right.left)){
                // 右子树的右子树更高
                tree = rotateWithRightChild(tree);
            }else{
                tree = doubleRotateWithRightChild(tree);
            }
        }
        tree.height = Math.max(height(tree.left), height(tree.right)) + 1;
        return tree;
    }

    /**
     * 使用该树的左子节点作为新树的根节点，左旋转
     *        k1           k2
     *     k2     --->  k2L    k1
     *   k2L  k2R           k2R
     * @param k1
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k1){
        AvlNode<T> k2 = k1.left;
        k1.left = k2.right;
        k2.right = k1;
        k1.height = Math.max(height(k1.right), height(k1.left)) + 1;
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        return k2;
    }

    /**
     * 使用该树的左子节点作为新树的根节点，左旋转
     *  k1                     k2
     *      k2     --->   k1       k2R
     *   k2L  k2R           k2L
     * @param k1
     */
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1){
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.right), height(k1.left)) + 1;
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        return k2;
    }

    /**
     * 使用该树的左子节点双旋转，将其孙节点作为根节点；
     *          k1                   k1             k3
     *       k2              --->  k3       -->  k2     k1
     *          k3               k2
     * @param k1
     * @return
     */
    private AvlNode<T> doubleRotateWithLeftChild(AvlNode<T> k1){
        k1.left = rotateWithRightChild(k1.left);
        return rotateWithLeftChild(k1);
    }

    /**
     * 使用该树的右子节点双旋转，将其孙节点作为根节点；
     *    k1                        k1                   k3
     *       k2              --->      k3       -->  k1     k2
     *    k3                              k2
     * @param k1
     * @return
     */
    private AvlNode<T> doubleRotateWithRightChild(AvlNode<T> k1){
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }


    /**
     * 获得一个树的高度；一个节点的树高度为0，空树为 -1，因为树的高度 = 高子树高度 + 1；
     * @param tree
     * @return
     */
    public int height(AvlNode<T> tree){
        return tree == null ? -1 : tree.height;
    }

    public static class AvlNode<T>{
        T val;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;
        public AvlNode(T val, AvlNode<T> left, AvlNode<T> right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String...args){
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.insert(3);
        avlTree.insert(2);
        avlTree.insert(1);
        avlTree.insert(4);
        avlTree.insert(5);
        avlTree.insert(6);
        avlTree.insert(7);
        avlTree.insert(16);
        avlTree.insert(15);
        avlTree.insert(14);
        avlTree.insert(13);
        avlTree.insert(12);
        avlTree.insert(11);
        avlTree.insert(10);
        avlTree.insert(8);
        avlTree.insert(9);
        System.out.println();
        avlTree.remove(12);
        avlTree.remove(7);
        System.out.println();
    }
}
