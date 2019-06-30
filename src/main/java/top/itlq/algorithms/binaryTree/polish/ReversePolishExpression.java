package top.itlq.algorithms.binaryTree.polish;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Reverse Polish Expression 逆波兰式，后缀表达式；
 * 将带括号，优先级的中缀表达式转换为后缀表达式并计算
 */
public class ReversePolishExpression {
    public static void main(String...args){
        // 由中缀表达式得到后缀表达式
        Queue<String> reversePolishExpression = expressionToReversePolishExpression("1-20*(19+3)");
        // 计算后缀表达式
        Stack<Integer> results = new Stack<>();
        String poll;
        while ((poll = reversePolishExpression.poll()) != null){
            if(poll.equals("+") || poll.equals("-")
                    || poll.equals("*") || poll.equals("/")){
                results.push(calc(results.pop(), results.empty()?0:results.pop(), poll)); // second为null时，最前面有负号情况，相当于 0-
            }else{
                results.push(Integer.valueOf(poll));
            }
        }
        System.out.println(results.pop());
    }

    /**
     * 将带括号，优先级的中缀表达式转换为 没有括号按顺序方便计算机计算的后缀表达式
     * @param expression -2*(1+3) ==> 2 1 3 + * -
     * @return
     */
    public static Queue<String> expressionToReversePolishExpression(String expression){
        char[] chars = expression.toCharArray();
        Queue<String> reversePolishExpression = new LinkedList<>();
        Stack<Character> operationStack = new Stack<>();
        int sum = 0;
        for(int i=0;i<chars.length;i++){
            if(Character.isDigit(chars[i])){ // 如果是数字
                sum = sum * 10 + Integer.valueOf("" + chars[i]);
                if(i == chars.length -1 || !Character.isDigit(chars[i + 1])){
                    reversePolishExpression.offer(String.valueOf(sum));
                    sum = 0;
                }
            }else{
                if(chars[i] == '('){
                    operationStack.push('(');
                }else if(chars[i] == ')'){ // 去括号
                    while (!operationStack.empty() && operationStack.peek() != '('){
                        reversePolishExpression.offer("" + operationStack.pop());
                    }
                    operationStack.pop();
                }else if(!operationStack.empty() && priority(chars[i]) < priority(operationStack.peek())
                        && operationStack.peek() != '('){
                    while (priority(chars[i]) < priority(operationStack.peek())){
                        reversePolishExpression.offer("" + operationStack.pop());
                    }
                    operationStack.push(chars[i]);
                }else{
                    operationStack.push(chars[i]);
                }
            }
        }
        while (!operationStack.empty()){
            reversePolishExpression.offer("" + operationStack.pop());
        }
        System.out.println(reversePolishExpression);
        return reversePolishExpression;
    }

    /**
     * 中缀表达式优先级
     * @param c
     * @return
     */
    public static int priority(char c){
        if(c == '-' || c == '+'){
            return 0;
        }else if(c == '*' || c == '/'){
            return 1;
        }else if(c == '('){
            return 2;
        }else{
            throw new RuntimeException("Unsupported char:" + c);
        }
    }

    /**
     * 从栈中弹出的计算
     * @param second
     * @param first
     * @param operation
     * @return
     */
    public static int calc(Integer second, Integer first, String operation){
        switch (operation){
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return first / second;
            default:
                throw new RuntimeException("Unsupported char:" + operation);
        }
    }
}
