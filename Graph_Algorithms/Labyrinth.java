import java.io.*;
import java.util.*;

public class Labyrinth {

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inp = br.readLine().split(" ");

        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);

        char[][] grid = new char[n][m];
        int sx = 0, sy = 0, ex = 0, ey = 0;

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'A') {
                    sx = i;
                    sy = j;
                }
                if (grid[i][j] == 'B') {
                    ex = i;
                    ey = j;
                }
            }
        }

        boolean[][] vis = new boolean[n][m];
        char[][] parent = new char[n][m];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        char[] dir = {'U', 'R', 'D', 'L'};

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx, sy));
        vis[sx][sy] = true;

        boolean found = false;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == ex && cur.y == ey) {
                found = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                        !vis[nx][ny] && grid[nx][ny] != '#') {

                    vis[nx][ny] = true;
                    parent[nx][ny] = dir[i];
                    q.add(new Node(nx, ny));
                }
            }
        }

        if (!found) {
            System.out.println("NO");
            return;
        }

        // reconstruct path
        StringBuilder path = new StringBuilder();
        int x = ex, y = ey;

        while (x != sx || y != sy) {
            char d = parent[x][y];
            path.append(d);

            if (d == 'U') x++;
            else if (d == 'D') x--;
            else if (d == 'L') y++;
            else if (d == 'R') y--;
        }

        path.reverse();

        System.out.println("YES");
        System.out.println(path.length());
        System.out.println(path);
    }
}
