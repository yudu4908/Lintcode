package Mi;

public class SpiralMatrix2 { //螺旋矩阵
    /**
Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
(The spiral rotates clockwise from the outside to the inside, referring to examples)
input: 3
output:
[
  [ 1, 2, 3 ],
  [ 8, 9, 4 ],
  [ 7, 6, 5 ]
]    
*/


    public int[][] generalMatrix(int n) {
        if (n < 0) {
            return null;
        }
        int[][] result = new int[n][n];

        int xStart = 0, yStart = 0, num = 1;
        while (n > 0) {
            if (n == 1) {
                result[xStart][yStart] = num++;
                // result[yStart][xStart] = num++;
                   //答案的xStart和yStart和我的理解是反的，不用看那个。用我们自己这个思路即可。x表示横坐标
                break;
            }
            for (int i = 0; i < n - 1; i++) { //向右
                result[xStart][yStart + i] = num++;
            }

            for (int i = 0; i < n - 1; i++) { //向下
                result[xStart + i][yStart + n - 1] = num++;
            }

            for (int i = 0; i < n - 1; i++) { //向左
                result[xStart + n - 1][yStart + n - 1 - i] = num++;
            }
            for (int i = 0; i < n - 1; i++) { //向上
                result[xStart + n - 1 - i][yStart] = num++; //到这里已经把1-8的数字填到二维数组的正确位置了
            }

            xStart++; //最后在result[1][1]处填入9即可
            yStart++;
            n = n - 2;//这个我从画图4^2和3^2总结规律看到是对的，但是怎么推理我也不会
        }
        return result;
    }

    
}