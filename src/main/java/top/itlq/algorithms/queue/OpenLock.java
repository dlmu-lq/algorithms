package top.itlq.algorithms.queue;

import java.util.*;

public class OpenLock {
    public int openLock(String[] deadends, String target) {
        Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));
        if(deadendsSet.contains(target)){
            return -1;
        }
        if(target.equals("0000")){
            return 0;
        }
        Set<String> visitedFromStart = new HashSet<>(), visited = visitedFromStart,  visitedFromTarget = new HashSet<>();
        Queue<String> queueFromStart = new LinkedList(), queue = queueFromStart, queueFromTarget = new LinkedList();
        int re = 1, roundNums = 1;
        queueFromStart.add("0000");
        queueFromTarget.add(target);
        while(!queue.isEmpty()){
            String current = queue.poll();
            Set<String> neighbors = getNeighbors(current);
            for(String neighbor:neighbors){
                if(visited.contains(neighbor)){
                    continue;
                }
                if(!deadendsSet.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
            roundNums--;
            if(roundNums == 0){
                if(hasCommon(queueFromStart, queueFromTarget)){
                    return re;
                }
                re++;
                if(queueFromStart.size() > queueFromTarget.size()){
                    queue = queueFromTarget;
                    visited = visitedFromTarget;
                }else{
                    queue = queueFromStart;
                    visited = visitedFromStart;
                }
                roundNums = queue.size();
            }
        }
        return -1;
    }

    private Set<String> getNeighbors(String value){
        Set<String> re = new HashSet<>(8);
        char[] chars = value.toCharArray();
        for(int i=0;i<4;i++){
            char temp = chars[i];
            if(temp == '9'){
                chars[i] = '0';
            }else{
                chars[i]++;
            }
            re.add(new String(chars));
            chars[i] = temp;
            if(temp == '0'){
                chars[i] = '9';
            }else{
                chars[i]--;
            }
            re.add(new String(chars));
            chars[i] = temp;
        }
        return re;
    }

    private boolean hasCommon(Queue<String> queue1, Queue<String> queue2){
        for(String s1:queue1){
            for(String s2:queue2){
                if(s1.equals(s2)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new OpenLock().openLock(new String[]{"0101"}, "0202"));
//        char t = '9';
//        System.out.println(new OpenLock().getNeighbors("0090"));
    }
}