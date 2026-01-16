import java.io.*;
import java.util.*;
class DSU{
    int par[];
    int size[];

    public DSU(int n){
        par = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++){
            par[i] = i;
            size[i] = 1;
        }
    }

    public int findPar(int u){
        if(par[u] == u)
            return u;

        return par[u] = findPar(par[u]);
    }

    public void union(int u, int v){
        int pu = findPar(u);
        int pv = findPar(v);

        if(pu == pv)
            return ;
        if(size[pu] <= size[pv]){
            size[pv] += size[pu];
            par[pu] = pv;
        }
        else{
            size[pu] += size[pv];
            par[pv] = pu;
        }

        return ;
    }
}

public class BuildingTeams {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inp[] = br.readLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);

        DSU dsu = new DSU(n);

        for(int i = 0; i < m; i++){
            inp = br.readLine().split(" ");
            int a = Integer.parseInt(inp[0]) - 1;
            int b = Integer.parseInt(inp[1]) - 1;

            dsu.union(a, b);
        }

        int[] teams = new int[n];
        int par0 = dsu.findPar(0);
        Set<Integer> s = new HashSet<>();

        for(int i = 0; i < n; i++){
            int ans = dsu.findPar(i);
            System.out.print(ans+" ");
            if(par0 == ans){
                teams[i] = 1;
            }
            else{
                teams[i] = 2;
            }
            s.add(ans);
        }
        System.out.println();
        
        if(s.size() < 2){
            System.out.println("IMPOSSIBLE");
        }
        else{
            for(int i: teams){
                System.out.print(i+"");
            }
        }
        br.close();
    }
}
