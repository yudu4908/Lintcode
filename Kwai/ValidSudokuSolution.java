package Kwai;

import java.util.Arrays;

/** 
数独游戏：
1. 每行1-9只能各自出现一次
2. 每列1-9只能各自出现一次
3. 每个小的九宫格（不能自己随意拼凑九宫格，要用游戏中规划好的9个子九宫格）中1-9各自只能出现一次
此题不需要判断是否可解，只要当前提供的数字不违背上面三个要求即可
*/
/**
 Determine whether a Sudoku is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character ..
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 */
/**
Input:
["53..7....","6..195...",".98....6.","8...6...3","4..8.3..1","7...2...6",".6....28.","...419..5","....8..79"]
Output: true
Explanation: 
The sudoku is look like this. It's vaild.
 */
public class ValidSudokuSolution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];
        //1. row 
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!process(visited, board[i][j])) {
                    return false;
                }
            }          
        }

        //2. col
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false); //重置之前被改乱的visited数组
            for (int j = 0; j < 9; j++) {
                if (!process(visited, board[j][i])) { //注意这里颠倒了i和j！！！
                    return false;
                }
            }
        }

        //3. sub matrix
        for (int i = 0; i < 9; i += 3) { //0, 3, 8分别是三个sub matrix的第一格的下标 ； 纵向刷， 比如中间三行；后三行
            for (int j = 0; j < 9; j += 3) { //横向刷完三个子matrix， 比如前三行中间三列和前三行后三列
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++) {// k每次循环完一个子matrix，比如前三行前三列
                    if (!process(visited, board[i + k / 3][j + k % 3])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean process(boolean[] visited, char digit) {
        if (digit == '.') {
            return true;
        }
        int num = digit - '0';
        if (num < 1 || num > 9 || visited[num - 1]) { //visited 数组长度为9， 所以下标和数字差一位
            return false;
        }
        visited[num - 1] = true;
        return true;

    }
}