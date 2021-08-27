import java.io.*;
import java.util.*;

public class G3_11779_최소비용구하기2 {

    static int n, m;
    static List<Node>[] adj;
    static int src, dst;
    static int[] distance;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();
        printPath();
    }

    private static void printPath() {
        List<Integer> path = new ArrayList<>();
        int cur = dst;
        while(cur != src) {
            path.add(cur);
            cur = prev[cur];
        }
        path.add(src);
        System.out.println(path.size());
        for(int i = path.size()-1; i >= 0; --i) {
            System.out.print(path.get(i)+" ");
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost-o2.cost);
        pq.offer(new Node(src, 0));
        distance[src] = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(distance[cur.dst] < cur.cost)
                continue;

            for(Node node: adj[cur.dst]) {
                int next = node.dst;
                int cost = node.cost;
                if(distance[cur.dst]+cost < distance[next]) {
                    prev[next] = cur.dst;
                    pq.offer(new Node(next, distance[next] = distance[cur.dst]+cost));
                }
            }
        }
        System.out.println(distance[dst]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        adj = new List[n+1];
        distance = new int[n+1];
        prev = new int[n+1];
        for(int i = 1; i <= n; ++i) {
            adj[i] = new ArrayList<>();
            distance[i] = Integer.MAX_VALUE;
        }
        m = Integer.parseInt(br.readLine());
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[src].add(new Node(dst, cost));
        }
        st = new StringTokenizer(br.readLine(), " ");
        src = Integer.parseInt(st.nextToken());
        dst = Integer.parseInt(st.nextToken());
    }

    static class Node {
        int dst, cost;

        Node(int dst, int cost) {
            this.dst = dst;
            this.cost = cost;
        }
    }
}
