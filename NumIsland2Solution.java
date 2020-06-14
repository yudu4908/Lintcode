import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

// 434,适合用并查集去做. 并查集只能合并不能分离， 所以如果反过来让operators
//05/20 没看懂

public class NumIsland2Solution {
    private int[] father = null;

    private int find(int x) {
        if (father[x] == x) {
            return x;
        }

        return father[x] = find(father[x]);
    }

    private void union(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA != rootB) {
            father[rootA] = rootB;
            size--;
        }
    }

    int size = 0;

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList<>();
        if (operators == null || operators.length == 0) {
            return res;
        }

        father = new int[n * m];
        int[][] island = new int[n][m];  //判断这个坐标是否是岛

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                father[i * m + j] = i * m + j;
            }
        }

        int[] dirs = {-1, 0, 1, 0, -1};

        for (int i = 0; i < operators.length; ++i) {
            int x = operators[i].x, y = operators[i].y;
            if (island[x][y] == 1) {
                res.add(size);
                continue;
            }

            island[x][y] = 1;
            size++;

            for (int k = 0; k < 4; ++k) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && island[nx][ny] == 1) {
                    union(x * m + y, nx * m + ny);
                }
            }

            res.add(size);
        }

        return res;
    }
}