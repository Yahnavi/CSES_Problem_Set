import java.io.*;
import java.util.*;

public class MessageRoute {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String inp[] = br.readLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);

        // boolean[][] adj = new boolean[n][n];
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            inp = br.readLine().split(" ");
            int a = Integer.parseInt(inp[0]) - 1;
            int b = Integer.parseInt(inp[1]) - 1;

            // adj[a-1][b-1] = true;
            
            adj.get(a).add(b);
            adj.get(b).add(a);

        }

        int[] par = new int[n];

        boolean[] vis = new boolean[n];

        Queue<Integer> q = new LinkedList<>();
        
        vis[0] = true;
        q.add(0);
        par[0] = -1;

        while(!q.isEmpty()){
            int cur = q.poll();
            
            if(cur == n-1){
                break;
            }
            for(int i: adj.get(cur)){
                if(!vis[i]){
                    vis[i] = true;
                    par[i] = cur;
                    q.add(i);
                }
            }

        }

        if(!vis[n-1]){
            System.out.println("IMPOSSIBLE");
        }
        else{
            List<Integer> p = new ArrayList<>();
            int cur = n-1;
            while(cur != -1){
                p.add(cur + 1);
                cur = par[cur];
            }
            Collections.reverse(p);
            System.out.println(p.size());
            for(int i:p){
                System.out.print(i+" ");
            }
            System.out.println();
        }
        br.close();
    }
}
