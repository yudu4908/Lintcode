package meituan2020;

public class ChessPieceRotationSolution {
    /**
     * 在4x4的棋盘上摆满了黑白棋子，黑白两色的位置和数目随机其中左上角坐标为(1,1),右下角坐标为(4,4),现在依次有一些翻转操作，要对一些给定支点坐标为中心的上下左右四个棋子的颜色进行翻转，请计算出翻转后的棋盘颜色。
       给定两个数组A和f,分别为初始棋盘和翻转位置。其中翻转位置共有3个。
       输入: 
        A:[[0,0,1,1],[1,0,1,0],[0,1,1,0],[0,0,1,0]]
        F：[[2,2],[3,3],[4,4]]
       输出: [[0,1,1,1],[0,0,1,0],[0,1,1,0],[0,0,1,0]]
     */
    /**
     * 题意很简单，对于一个点的四个方向进行翻转
       注意数组不要越界去翻转
        * @param A: Initial chessboard
        * @param F: Flipped coordinates
        * @return:  return to the flipped board.
     */
    public int[][] ChessPieceRotation(int[][] A, int[][] F) {
      int[] dx = new int[] {0, -1, 0, 1}; 
      int[] dy = new int[] {-1, 0, 1, 0}; //上下左右对应的坐标偏移
      for (int[] item : F) {
        int x = item[0] - 1, y = item[1] - 1;//之前的坐标是[1, 1]表示第一行第一列，但是实际坐标是[0, 0]
        for (int i = 0; i < 4; i++) {
          if (x + dx[i] > -1 && x + dx[i] < 4 && y + dy[i] > -1 && y + dy[i] < 4) {//保证坐标不要越界
            A[x + dx[i]][y + dy[i]] = 1 - A[x + dx[i]][y + dy[i]]; //flip
          }
        }
      }
      return A;
    }
}