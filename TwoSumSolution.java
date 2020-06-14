import java.util.HashMap;
import java.util.Map;

public class TwoSumSolution {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < numbers.length; ++i) {
            if(map.containsKey(target - numbers[i])) {
                //int[] res = {map.get(target - numbers[i]), i};//这里为什么能保证i比前面的数大是因为for循环从0 开始，map一开始是空的逐渐从前面的数开始往里put， 当你找到一个数被target减去后等于前面的数时前面的数下标当然小于i
                //return res;
                return new int[] {map.get(target - numbers[i]), i};
            } else {
                map.put(numbers[i], i);
            }
        }
        //int[] res = {};
        //return res;
        return new int[0]; //长度为0 的空数组， 程序肯定是跑不到这里的，因为题目一定会让你有返回值，但是不这么写会导致function报错
    }
}