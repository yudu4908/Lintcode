package BiliBili;

import java.util.Arrays;
import java.util.Comparator;

//给定一个整数数组，请将其重新排序，以构造最小值。
/**
 * 
输入：[3, 32, 321]
输出：[321, 32, 3]
解释：通过将数组重新排序，可构造 6 个可能性数字：
	3+32+321=332321
	3+321+32=332132
	32+3+321=323321
	32+321+3=323213
	321+3+32=321332
	321+32+3=321323
其中，最小值为 321323，所以，将数组重新排序后，该数组变为 [321, 32, 3]。
Challenge
在原数组上完成，不使用额外空间。
**/
public class ReorderArrayToConstructTheMinNum {
       /**
     * @param nums n non-negative integer array
     * @return a string
     */
    public String minNumber(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return "";
        }
        String[] strs = new String[n];//使用额外空间了
        for (int i = 0; i < n; i++) {//整数型数组转化为字符串型数组
            //strs[i] = String.valueOf(nums[i]);// correct 1
            //strs[i] = nums[i].toString();//wrong, Primitive types do not have methods, as they are not objects in Java. You should use the matching class:
            strs[i] = Integer.toString(nums[i]); //correct 2
        }
        Arrays.sort(strs, new Cmp());
        String ans = "";
        for (int i = n - 1; i >= 0; i--) {
            ans = ans.concat(strs[i]);
        }
        int i = 0;
        while (i < n && ans.charAt(i) == '0') {//排除全0 的字符串， 从非0开始输出。 注意不能用双引号，否则报错char不能和string比
            i++;
        }
        if (i == n) {
            return "0";
        }
        return ans.substring(i);//从i开始的子串
        /**
Input
Show Difference
[0,1]

Output
"01"

Expected
"1"
**/
    }
}
class Cmp implements Comparator<String> {
    /**
    一般来说，@Override写与不写没什么区别，JVM可以自识别 
  写的情况下：即说明子类要覆盖基类的方法，基类必须存在方法 （控制类型public,protected，返回值，参数列表类型）
  与子类方法完全一致的方法，
  否则会报错（找不到被Override的方法）。  
  在不写@Override注解的情况下，当基类存在与子类各种条件都符合的方法时实现覆盖；如果条件不符合时，则是当成新定义的方法使用。 
  所以如果想覆盖基类方法时，最好还是写上@Override注解，这样有利于编译器帮助检查错误

     */
    @Override
    public int compare(String a, String b) {
        String ab = a.concat(b);
        String ba = b.concat(a);
        return ba.compareTo(ab); //return a-b就是从小到大默认排序；return b - a就是从大到小排序
        ////从第一个字母开始从大到小排序所有字串比如["4", "345", "24"]
    }
}

// System.out.println("345".compareTo("24"));//1 ，从第一个char比起，只要第一个大就返回1
// System.out.println("345".compareTo("4")); //-1
