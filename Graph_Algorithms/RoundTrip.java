import java.io.*;
import java.util.*;

public class RoundTrip {

    static int n, m;
    static ArrayList<Integer>[] adj;
    static boolean[] vis;
    static int[] parent;
    static int start = -1, end = -1;
    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }

        vis = new boolean[n];
        parent = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            if (!vis[i] && dfs(i, -1)) {
                buildOutput();
                System.out.print(out.toString());
                return;
            }
        }

        out.append("IMPOSSIBLE\n");
        System.out.print(out.toString());
    }

    static boolean dfs(int v, int p) {
        vis[v] = true;

        for (int u : adj[v]) {
            if (u == p) continue;

            if (!vis[u]) {
                parent[u] = v;
                if (dfs(u, v)) return true;
            } else {
                start = u;
                end = v;
                return true;
            }
        }
        return false;
    }

    static void buildOutput() {
        ArrayList<Integer> cycle = new ArrayList<>();
        cycle.add(start);

        for (int v = end; v != start; v = parent[v]) {
            cycle.add(v);
        }
        cycle.add(start);

        out.append(cycle.size()).append('\n');
        for (int x : cycle) {
            out.append(x + 1).append(' ');
        }
        out.append('\n');
    }
}
