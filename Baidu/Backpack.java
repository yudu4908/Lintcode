package Baidu;
/**
given n items with size Ai, an integer m denotes the size of a backpack. how full you can fill this backpack?
O(n x m) time and O(m) memory.
O(n x m) memory is also acceptable if you do not know how to optimize memory. 
*/
public class Backpack {

    /**
    Example 1:
	Input:  [3,4,8,5], backpack size=10
	Output:  9

    Example 2:
	Input:  [2,3,5,7], backpack size=12
	Output:  12
     */
    //f[i][j]表示前i个物品选一些物品放入容量为j的背包中能否放满。
        /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        boolean[][] f = new boolean[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = false;
            }
        }
        f[0][0] = true;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];//前i - 1件物品中的一部分能装满或者恰好i件物品装满都是可以的
                if (j >= A[i - 1] && f[i - 1][j - A[i - 1]]) {
                    f[i][j] = true;
                }
            }
        }

        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        return 0;
    }
    
}



// O（m）空间复杂度的解法
    // public int backPack(int m, int[] A) {
    //     int f[] = new int[m + 1];
    //     for (int i = 0; i < A.length; i++) {
    //         for (int j = m; j >= A[i]; j--) {
    //             f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
    //         }
    //     }
    //     retrun f[m];
    // }


