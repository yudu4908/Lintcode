package NetEase;
/**
给定一个非负二维矩阵Matrix，元素均大于等于0
问是否存在c行d列 元素都为0 的子矩阵
 */
public class ZeroMatrix {
   /**
   2≤n≤2000
   2≤m≤2000
   2≤c≤500
   2≤d≤500
   0 ≤ Matrix[i][j] ≤10
   0≤i≤n−1
   0≤j≤m−1
   n为矩阵行数，m为矩阵列数 
   */ 

   /**
    * 
输入:[[0,0,0],[1,0,0]]
2
2
输出：true
你可以选择(0,1),(0,2),(1,1),(1,2) 这四个点
就是在大的矩阵中查找是否存在一个n x m的值为0的小矩阵
    */
/**
 /**
     * @param Matrix: 
     * @param c: the zero submatrix rows
     * @param d: the zero submatrix columns
     * @return: return if there exists zero submatrix
    
 */
    public boolean isZeroMatrix(int[][] Matrix, int c, int d) {
        int row = Matrix.length, col = Matrix[0].length;
        //前缀和数组
        int [][] prefix_sum = new int[row + 1][col + 1];
        //计算前缀和
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefix_sum[i][j] = Matrix[i - 1][j - 1];
                prefix_sum[i][j] += prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1]; //画图可知每个点都是整个左上方矩阵的“面积”和
            }
        }
        //枚举矩阵左上角
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                //计算得到子矩阵右下角
                int nx = i + c - 1; //i自己也算一行所以要- 1
                int ny = j + d - 1;
                //越界非法
                if (nx > row || ny > col) {
                    continue;
                }
            //得到子矩阵元素和,注意画图理解。 这里每个矩阵右下角的点代表整个矩阵面积， 所以子矩阵面积就等于总面积减去子矩阵左上角的点左侧所有面积，再减去上方所有面积最后加上重复减去的面积[i - 1][j - 1]
                int sum = prefix_sum[nx][ny] - prefix_sum[i - 1][ny] - prefix_sum[nx][j - 1] + prefix_sum[i - 1][j - 1];

                if (sum == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}