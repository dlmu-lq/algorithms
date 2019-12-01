package top.itlq.algorithms.binaryTree.polish;

import org.junit.jupiter.api.Test;
import top.itlq.algorithms.chapter04.ExpressionTree;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {

    @Test
    void reversePolishExpressionTreeTest() {
        String[] reversePolishExpression = {"2","3","4","*","+"};
        ExpressionTree.TreeNode treeNode = ExpressionTree.reversePolishExpressionToTree(reversePolishExpression);
        LinkedList<String> reversePolishExpressionList =
                ExpressionTree.searchTreeNodesBackWard(treeNode, new LinkedList<String>());
        int i = 0;
        for (String aReversePolishExpression : reversePolishExpressionList) {
            assertEquals(aReversePolishExpression, reversePolishExpression[i++]);
        }
    }

    @Test
    void polishExpressionTreeTest() {
        String[] polishExpression = {"+","+","2","3","*","4","5"};
        ExpressionTree.TreeNode treeNode = ExpressionTree.polishExpressionToTree(polishExpression);
        LinkedList<String> polishExpressionList =
                ExpressionTree.searchTreeNodesForward(treeNode, new LinkedList<>());
        int i = 0;
        for (String aPolishExpression : polishExpressionList) {
            assertEquals(aPolishExpression, polishExpression[i++]);
        }
    }
}