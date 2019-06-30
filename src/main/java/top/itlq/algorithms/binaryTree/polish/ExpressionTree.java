package top.itlq.algorithms.binaryTree.polish;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 将前缀、后缀表达式转换为树，树不同方式遍历分别得到前缀、后缀表达式
 */

public class ExpressionTree {
    public static void main(String[]args){
        //2+3*4
        // 由后缀表达式得到树
        TreeNode tree1 = reversePolishExpressionToTree("2,3,4,*,+".split(","));
        // 由树得到前缀表达式；
        LinkedList<String> polishExpression = searchTreeNodesForward(tree1,new LinkedList<>());
        System.out.println(polishExpression);
        // 由前缀表达式得到树；
        TreeNode tree2 = polishExpressionToTree(polishExpression.toArray(new String[]{}));
        // 由树得到后缀表达式；
        LinkedList<String> reversePolishExpression = searchTreeNodesBackWard(tree2,new LinkedList<>());
        System.out.println(reversePolishExpression);
    }

    /**
     * 后缀表达式转换成二叉树，利用栈
     * @param reversePolishExpression 后缀表达式 数组形式
     */
    public static TreeNode reversePolishExpressionToTree(String [] reversePolishExpression){
        Stack<TreeNode> treeStack = new Stack<>();
        for(String poll:reversePolishExpression){
            if(poll.equals("+") || poll.equals("-") || poll.equals("*") || poll.equals("/")){
                TreeNode right = treeStack.pop(); // 注意压入栈后，先出栈的为右树；
                TreeNode left = treeStack.empty()
                        ?new TreeNode(0,null,null):treeStack.pop();
                treeStack.push(new TreeNode(poll, left, right));
            }else{
                treeStack.push(new TreeNode(poll,null,null));
            }
        }
        return treeStack.pop();
    }

    /**
     * 使用栈
     * 前缀表达式转换为二叉树
     * @param  polishExpression 前缀表达式 数组形式
     */
    public static TreeNode polishExpressionToTree(String[] polishExpression){
        Stack<TreeNode> treeNodeStack = new Stack<>();
        for(int i=polishExpression.length - 1;i>=0;i--){
            if(polishExpression[i].equals("+") || polishExpression[i].equals("-")
                    || polishExpression[i].equals("*") || polishExpression[i].equals("/")){
                treeNodeStack.push(new TreeNode(polishExpression[i],
                        treeNodeStack.pop(),treeNodeStack.pop())); // 从右向左遍历，后入栈先出栈的为左树
            }else{ // 树叶
                treeNodeStack.push(new TreeNode(polishExpression[i], null, null));
            }
        }
        return treeNodeStack.pop();
    }

    /**
     * 递归先序遍历树产生前缀表达式
     * @param treeNode
     * @param list
     * @return
     */
    public static LinkedList<String> searchTreeNodesForward(TreeNode treeNode, LinkedList<String> list){
        if(treeNode != null){
            list.add(treeNode.element.toString());
            searchTreeNodesForward(treeNode.left, list);
            searchTreeNodesForward(treeNode.right, list);
        }
        return list;
    }

    /**
     * 递归后序遍历（深度遍历）树产生后缀表达式
     * @param treeNode
     * @param list
     * @return
     */
    public static LinkedList<String> searchTreeNodesBackWard(TreeNode treeNode, LinkedList<String> list){
        if(treeNode.left != null){
            searchTreeNodesBackWard(treeNode.left,list);
        }
        if(treeNode.right != null ){
            searchTreeNodesBackWard(treeNode.right,list);
        }
        list.add(treeNode.element.toString());
        return list;
    }
}

/**
 * 树节点
 */
class TreeNode {
    public TreeNode(Object element,TreeNode left,TreeNode right){
        this.element = element;
        this.left = left;
        this.right = right;
    }
    public Object element;
    public TreeNode left;
    public TreeNode right;
}
