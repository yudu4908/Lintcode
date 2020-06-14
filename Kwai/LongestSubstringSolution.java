package Kwai;

public class LongestSubstringSolution {
    //Given a string, find the length of the longest substring without repeating characters.
    /**
Input: "abcabcbb"
Output: 3
Explanation: The longest substring is "abc".
Input: "bbbbb"
Output: 1
Explanation: The longest substring is "b".
     */
    /**
枚举, 记录每一个字母上一次出现的位置.
再设定一个左边界, 到当前枚举到的位置之间的字符串为不含重复字符的子串.
若新碰到的字符的上一次的位置在左边界右边, 则需要向右移动左边界, 枚举的过程中取最大值即可.
     */
    /**
     * @param s: a string
     * @return: an integer 
     */
     //方法一：
     public int lengthOfLongestSubstring(String s) {
         int[] map = new int[256]; // // map from character's ASCII to its last occured index. 0-255是所有ascii码的范围
         int j = 0;
         int i = 0;
         int ans = 0;
         for (i = 0; i < s.length(); i++) { //i是左指针
            while (j < s.length() && map[s.charAt(j)] == 0) { //j是右指针
                map[s.charAt(j)] = 1; //定义某个字符出现过一次
                ans = Math.max(ans, j - i + 1);
                j++;
            }
            map[s.charAt(i)] = 0; //左指针右移一格，那么这个数字自然要算做没出现过，即使右指针此时指的是这个字符我们也先置为0，因为再次走到右指针时就不算重复了
         }
         return ans;
     }
}

//上面是一个o（n2）的解法，还有简单的o（n）的解法可以练习