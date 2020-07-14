package BiliBili;
//用最少的次数让word1变为word2
/**
Input: 
"horse"
"ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

eg2
Input: 
"intention"
"execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

 */
public class EditDistance {
    /**
     * 
     算法：dp
经典的二维dp。
定义f[i][j]为word1前i个字符到word2的前j个字符的转化的最小步。
接着，我们来考虑状态转移方程。

假设对于f[i][j]以前的值都已知，考虑f[i][j]的情形。
若word1[i] = word2[j]，那么说明只要word1的前i-1个能转换到word2的前j-1个即可，所以 f[i][j] = f[i-1][j-1]
反之，若不等，我们就要考虑以下情形了。
给word1插入一个和word2最后的字母相同的字母，这时word1和word2的最后一个字母就一样了，此时编辑距离等于1（插入操作） + 插入前的word1到word2去掉最后一个字母后的编辑距离 f[i][j - 1] + 1
删除word1的最后一个字母，此时编辑距离等于1（删除操作） + word1去掉最后一个字母到word2的编辑距离 f[i - 1][j] + 1
把word1的最后一个字母替换成word2的最后一个字母，此时编辑距离等于 1（替换操作） + word1和word2去掉最后一个字母的编辑距离。为f[i - 1][j - 1] + 1
三者取最小值即可。
复杂度分析
时间复杂度O(n^2)
二维dp
空间复杂度O(n^2)
需要保存二维的状态数
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;//word1的前i个都得remove掉才等于word2的前0个
        }
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[n][m];
    }
}