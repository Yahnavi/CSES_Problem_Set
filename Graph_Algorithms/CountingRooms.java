
import java.util.*;
public class CountingRooms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] grid = new char[n][m];
        for(int i = 0; i < n; i++){
            grid[i] = sc.next().toCharArray();
            
        }

        boolean[][] vis = new boolean[n][m];
        int cnt = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!vis[i][j] && grid[i][j] == '.'){
                    vis[i][j] = true;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    while(!q.isEmpty()){
                        int[] curr = q.poll();
                        int x = curr[0];
                        int y = curr[1];

                        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

                        for(int ind = 0; ind < 4; ind++){
                            int curx = dir[ind][0] + x;
                            int cury = dir[ind][1] + y;

                            if(isValid(curx, cury, n, m, grid, vis)){
                                q.offer(new int[]{curx, cury});
                                vis[curx][cury] = true;
                            }
                        }
                    }
                    // System.out.println(i+" "+j);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        sc.close();
    }

    public static boolean isValid(int i, int j, int n, int m, char[][] grid, boolean[][] vis){
        return i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == '.' && !vis[i][j];
    }
}
