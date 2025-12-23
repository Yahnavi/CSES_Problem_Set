// package Graph_Algorithms;

import java.io.*;
import java.util.*;

class DSU{
    int[] par;
    int[] rank;

    public DSU(int n){
        par = new int[n];
        rank = new int[n];

        for(int i = 0; i < n; i++)
            par[i] = i;
    }

    public int findPar(int i){
        if(par[i] == i)
            return i;
        return par[i] = findPar(par[i]);
    }

    public void union(int u, int v){
        int pu =findPar(u);
        int pv = findPar(v);

        if(pu == pv)
            return ;
        if(rank[pu] < rank[pv])
            par[pu] = pv;
        else if(rank[pv] < rank[pu])
            par[pv] = pu;
        else{
            par[pv] = pu;
            rank[pu]++;
        }

        return ;
    }
}
public class BuildingRoads {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inp[] = br.readLine().split(" ");
        int m = Integer.parseInt(inp[0]);
        int n = Integer.parseInt(inp[1]);
        DSU dsu = new DSU(m);
        for(int i = 0; i < n; i++){
            inp = br.readLine().split(" ");
            int u = Integer.parseInt(inp[0]) - 1;
            int v = Integer.parseInt(inp[1]) - 1;

            dsu.union(u, v);
        }

        List<Integer> sb = new ArrayList<>();

        for(int i = 0; i < m; i++){
            if(dsu.findPar(i) == i){
                sb.add((i+1));
            }
        }

        System.out.println(sb.size() - 1);
        
        for(int i = 0; i < sb.size() - 1; i++)
            System.out.println(sb.get(i)+" "+sb.get(i+1));

    }
}
