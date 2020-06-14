package Alibaba;

import java.util.Arrays;

/**
定义上升字符串为任意 0 < i < s.length ; s_i >= s_i-1 
​​ 
例如“aab” “aaa”是上升字符串，“acb”不是上升字符串
给定n个上升字符串，选择任意个拼接起来，求拼接出来的最长上升字符串的长度
 */
/**
输入：["aaa","bbb","ccc"]
输出：9
解释：aaabbbccc
 */
public class MaxLengthofIncreasingString {
    /**
     算法一
        暴力，n！枚举n个字符串的全排列，然后判断一下是否合法，如果合法就统计一下答案，然后取最大值
    算法二
因为若字符串b能拼接在a后面，则肯定a的字典序比b小才可以
那我们先对字符串按字典序排序
之后用LIS类比，令dp[i]位为考虑1到i的字符串的最长上升字符串长度
dp[i]=max(dp[j]+strlen(str[i])) j<i,j的末尾字符不大于i的开头字符
时间复杂度O(N^2)

算法三
我们在算法二的基础上，进行优化
令dp[i]为结尾为字符i的最长上升字符串的长度

for k in 1:n
	mx=max(dp[j]+strlen(str[k])) j<=k的开头字符
    dp[k.endchar]=max(dp[k.endchar],mx)  k.endchar 表示k的结尾字符
时间复杂度O(N*26)O(N∗26)
     */
        /**
     * @param strs: the given increasing strings
     * @return: return the Maxium length of compose incresing string
     */
    public int getMaximumLength(String[] strs) {
        //对字符串按照字典序排序
        Arrays.sort(strs);
        //initiate the dp array
        int[] dp = new int[50];//int[26]就够了，只有26个字母
        for (int i = 0; i < 50; i++) {
            dp[i] = 0;
        }
        //枚举字符串
        for (int i = 0; i < strs.length; i++) {
            //得到i字符串的开头字符，长度，结尾字符
            //int beginindex = (int)(strs[i].charAt(0)) - (int)('a'); //char转int不一定要强制转换，但是string转int需要Integer.parsexxx
            int beginindex = strs[i].charAt(0) - 'a'; //写这样是一样的，但是显式转换更好些
            int strlen = strs[i].length();
            int endindex = (int)(strs[i].charAt(strlen - 1)) - (int)('a');
            //由dp转移方程，从'a'到开头字符 取max
            int mx = 0;
            for (int j = 0; j <= beginindex; j++) {
                mx = Math.max(mx, dp[j] + strlen); //dp[0]表示以‘a’结尾的最长上升字符串长度，显然是0
            }
            //update the answer
            dp[endindex] = Math.max(mx, dp[endindex]); //比如strs = ["bbb", "bbb"],i = 0时dp【1】是3， 但是i = 1时dp【2】需要更新为6
        }
        //枚举最后结尾的字符串，求得最后的答案
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}