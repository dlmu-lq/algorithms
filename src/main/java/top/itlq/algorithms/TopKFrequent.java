package top.itlq.algorithms;

import java.util.*;

public class TopKFrequent {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num:nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Map<Integer, List<Integer>> timesValuesMap = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry:map.entrySet()){
            List<Integer> list = timesValuesMap.get(entry.getValue());
            if(list == null){
                list = new ArrayList<>();
                timesValuesMap.put(entry.getValue(), list);
            }
            list.add(entry.getKey());
        }
        Set<Integer> times = timesValuesMap.keySet();
        TreeSet<Integer> sortedTimes = new TreeSet<>(Comparator.reverseOrder());
        sortedTimes.addAll(times);
        List<Integer> re = new ArrayList<>();
        for(Integer i:sortedTimes){
            if(k-- > 0){
                re.addAll(timesValuesMap.get(i));
            }
        }
        return re;
    }

    public static void main(String[] args) {
        System.out.println(new TopKFrequent().topKFrequent(new int[]{
                1,1,1,2,2,3
        }, 2));
    }
}