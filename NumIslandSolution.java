import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.tree.TreeNode;

// 433

//Method1:
public class NumIslandSolution {
    public int numIslands(boolean[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int num = 0;
        boolean v[][] = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] && !v[i][j]) { // v means visited status
                    num++;
                    bfs(grid, v, i, j);
                }
            }
        }
        return num;
    }

    private void bfs(boolean[][] grid, boolean[][] v, int sx, int sy) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();

        //第一步队首元素进队
        qx.offer(sx);
        qy.offer(sy);
        v[sx][sy] = true;
        //第二步， 队列不空时拿出队首元素
        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();
            //第三步， 判断四邻，满足条件的进队
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (0 <= nx && nx < grid.length && 0 < ny && ny < grid[0].length && !v[nx][ny] && grid[nx][ny]) {
                    qx.offer(nx);
                    qy.offer(ny);
                    v[nx][ny] = true;
                }
            }
        }

    }
}







// Method 2:
// class Coordinate {
//     int x, y;
//     public Coordinate(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// public class NumIslandSolution {
//     /**
//      * @param grid is a 2D matrix
//      * @return an integer
//      */
//     public int numIslands(boolean[][] grid) {
//         if (grid == null || grid.length == 0 || grid[0].length == 0) {
//             return 0;
//         }
//         int n = grid.length;
//         int m = grid[0].length;
//         int islands = 0;
        
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (grid[i][j]) { //先把矩阵扫一遍，扫出是1的岛屿
//                     markByBFS(grid, i, j);
//                     islands++;
//                 }
//             }
//         }
//         return islands;

//     }

//     private void markByBFS(boolean[][] grid, int x, int y) {
//         //magic numbers!
//         // int[] directionX = {0, 1, -1, 0};
//         // int[] directionY = {1, 0, 0, -1};
//         int[] directionX = {0, 0, 1, -1};
//         int[] directionY = {1, -1, 0, 0};
//         Queue<Coordinate> queue = new LinkedList<>();
//         queue.offer(new Coordinate(x, y));
//         grid[x][y] = false;

//         while (!queue.isEmpty()) {
//             Coordinate coor = queue.pool();
//             for (int i = 0; i < 4; i++) {
//                 Coordinate adj = new Coordinate(
//                     coor.x + directionX[i],
//                     coor.y + directionY[i] 
//                 );
//                 if (!inBound(adj, grid)) {
//                     continue;
//                 }
//                 if (grid[adj.x][adj.y]) {
//                     grid[adj.x][adj.y] = false;
//                     queue.offer(adj);
//                 }
//             }
//         }
//     }

//     private boolean inBound(Coordinate coor, boolean[][] grid) {
//         int n = grid.length;
//         int m = grid[0].length;
//         return coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m;
//     }
// }




//template for BFS
// public class Solution {
    // public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    //     ArrayList result = new ArrayList();

    //     if (root == null)
    //         return result;
    //     Queue<TreeNode> queue = new LinkedList<TreeNode>();
    //     queue.offer(root);

    //     while (!queue.isEmpty()) {
    //         ArrayList<Integer> level = new ArrayList<Integer>();
    //         int size = queue.size();
    //         for (int i = 0; i < size; i++) {
    //             TreeNode head = queue.poll();
    //             level.add(head.val);
    //             if (head.left != null)
    //                 queue.offer(head.left);
    //             if (head.right != null)
    //                 queue.offer(head.right);
    //         }
    //         result.add(level);
    //     }
    //     return result;
    // }
// }