import java.io.*;
import java.util.*;

class G3_16947_서울지하철2호선 {

    static int n;
    static List<Integer>[] adj;
    static boolean[] visited;
    static boolean[] isCycle;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        input();
        checkCycle();
        getDistance();
    }

    static void getDistance() {
        distance = new int[n+1];
        visited = new boolean[n+1];

        for(int i = 1; i <= n; ++i)
            if(isCycle[i] && adj[i].size() > 2)
                searchRoute(i);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; ++i) {
            sb.append(distance[i] + " ");
        }
        System.out.println(sb.toString());
    }

    static void searchRoute(int i) {
        for(int next: adj[i]) {
            if(isCycle[next] || distance[next] > 0) {
               continue;
            }
            distance[next] = distance[i]+1;
            searchRoute(next);
        }
    }

    static void checkCycle() {
        isCycle = new boolean[n+1];

        for(int i = 1; i <= n; ++i)
            if(!isCycle[i]) {
                visited = new boolean[n+1];
                dfs(i, i, 0);
            }
    }

    private static boolean dfs(int strt, int i, int cnt) {
        for(int next: adj[i]) {
            if(next == strt) {
                if (cnt > 1)
                    return isCycle[i] = true;
                else
                    continue;
            }
            if(isCycle[i] || isCycle[next] || visited[next])
                continue;
            visited[next] = true;
            isCycle[i] = dfs(strt, next, cnt+1);
        }
        return isCycle[i];
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        adj = new List[n+1];
        for(int i = 1; i <= n; ++i)
            adj[i] = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adj[v1].add(v2);
            adj[v2].add(v1);
        }
    }
}
