package Alibaba;

import java.util.List;

/**
 代码中的螺旋顺序是啥
 Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
从左上角开始，顺时针旋转，由外向内。 简言之，从左上角开始转圈圈，圈圈越转越小
 */
public class SpiralMatrix {
    /**
     @param matrix: 
     @return: an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int m = matrix.length; //行数
        int n = matrix[0].length; //列数
        int x = 0, y = 0;

        while (m > 0 && n > 0) {
            if (m == 1) { //只有一行就直接原顺序返回
                for (int i = 0; i < n; i++) {
                    result.add(matrix[x][y++]);
                //是不是直接return matrix[0]也可以？？？？？不过用上面的写法可以全篇更一致易读
                }
                break; //跳出while循环
            } else if (n == 1) {// 多行但只有一列，那也是按顺序返回即可
                for (int i = 0; i < m; i++) {
                    result.add(matrix[x++][y]);
                }
                break;
            }
            // below, process a circle
            
            //top -- move right
            for (int i = 0; i < n - 1; i++) { //第一行先横向走到倒数第二个格子
                result.add(matrix[x][y++]);
            }

            //right -- move down //走到最后一列时往下走到倒数第二个格子
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x++][y]); //之前的for循环虽然只在result中加到matrix[x][(n-2)++]， 但是最后y坐标还是变成n - 1也就是最后一列了
            }

            //bottom -- move left
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y--]);
            }

            //left -- move up
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x--][y]); //不会回到第一行，恰好在第二行停止，这也是我们画圈圈想要的结果
            }
            //下一次扫描等于是从第二行的第二列开始了
            //这么一圈扫完我们发现第一行最后一行没了， 最后一列和第一列也没了。所以m和n各减小2
            x++; //因为x--导致它回到第一行了，我们先加回第二行
            y++; //列肯定也要往右挪一格
            m -= 2;
            n -= 2;
        }
        return result;
    }
}