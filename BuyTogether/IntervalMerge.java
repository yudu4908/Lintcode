package BuyTogether;
/**
 现在给你两个字符串区间(按字典顺序), 请你判断两个区间是否可以合并。
字符串区间[a, b)，包括所有以a开头的字符串。
例如，区间[a, b)和区间[ab,c)是可以合并的，区间[a,b)和区间[b, c]也是可以合并的。
若是可以合并请返回true, 不可以请返回false。
若两个区间A和B，满足A ⋃ B是一个连续区间，则A和B可合并。
 */
/**
输入："[a,b]" "[b,c]"
输出： true
输入："(b,c)" "[a,b]"
输出： true
输入："[a,b)" "(b,c]"
输出： false
 */
public class IntervalMerge {
    //有点绕。需要再练练。。。。。。。。。。。。。。。。。。。。。。。
     /**
     * @param interval_A: a string represent a interval.
     * @param interval_B: a string represent a interval.
     * @return: if two intervals can merge return true, otherwise false.
     */
    public boolean MergeJudge(String interval_A, String interval_B) {
        String[] aParts = interval_A.split("\\[|\\]|\\(|\\)|\\,");
        String startA = aParts[1]; //小标为啥不是0？？因为分割后开头是个“”，因为你把空字符串和Test中间的/作为分隔符了
        /**
         * 
         for instance
            String[] test = "/Test/Stuff".split("/");
            results in an array with "", "Test", "Stuff".
         */
        String endA = aParts[2]; 
        if (interval_A.charAt(0) == '(') { //左边是小括号说明不包含这个字母，所以需要改变一下原字母以免后面误判
            startA += "a";
            //System.out.println(startA);   //"[a, b]"  "[b, c]" 
            /**
             * "[a,b）"
                "(b,c]"
             * 应该返回false，但如果不做处理就会错误返回true
             */
        }
        if (interval_A.charAt(interval_A.length() - 1) == ']') {//如果interval_A的左边是（导致我们给它加了个a，那么如果interval_B的右边是]的话endB和startA相等还是可能合并的
            endA += "a"; //"[a, b]"  "[b, c]" --> endA = "ba"
            /**
                "[a,b]"
                "(b,c]"
                如果不给endA做上述处理则这个结果会被误判为false
             */
        }
        String[] bParts = interval_B.split("\\[|\\]|\\(|\\)|\\,");
        String startB = bParts[1];
        String endB = bParts[2];
        if (interval_B.charAt(0) == '(') {
            startB += "a";
            //System.out.println(startA);   //"[a, b]"  "[b, c]" 
        }
        if (interval_B.charAt(interval_B.length() - 1) == ']') {
            endB += "a"; //"[a, b]"  "[b, c]" --> endB = "ca"
        }
        if (startA.equals(startB)) { //两个字符串第一个字母相同那必然可以合并
            return true;
        }
        if (startA.compareTo(startB) < 0) {//A的起始字母比B的起始字母小,那么B的起始字母只有小于等于A的结束字母才有机会合并
            return startB.compareTo(endA) <= 0;
        }
        return startA.compareTo(endB) <= 0; //反之A的起始字母要小于等于B的起始字母
    }
}