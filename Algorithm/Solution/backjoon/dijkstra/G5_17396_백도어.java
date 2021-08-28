import java.io.*;
import java.util.*;

class G5_17396_백도어 {

    static int n;
    static int m;
    static boolean[] visable;
    static long[] distance;
    static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost-o2.cost<0?-1:1);
        pq.offer(new Node(0, 0));
        distance[0] = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(distance[cur.dst] < cur.cost)
                continue;

            for(Node node: adj[cur.dst]) {
                int next = node.dst;
                long cost = node.cost;
                if(distance[cur.dst]+cost < distance[next])
                    pq.offer(new Node(next, distance[next] = distance[cur.dst]+cost));
            }
        }
        if(distance[n-1] == Long.MAX_VALUE)
            distance[n-1] = -1;
        System.out.println(distance[n-1]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        visable = new boolean[n];
        adj = new List[n];
        distance = new long[n];
        for(int i = 0; i < n; ++i) {
            visable[i] = "1".equals(st.nextToken());
            adj[i] = new ArrayList<>();
            distance[i] = Long.MAX_VALUE;
        }
        visable[n-1] = false;
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(!visable[n1] && !visable[n2]) {
                adj[n1].add(new Node(n2, cost));
                adj[n2].add(new Node(n1, cost));
            }
        }
    }

    static class Node {
        int dst;
        long cost;

        public Node(int dst, long cost) {
            this.dst = dst;
            this.cost = cost;
        }
    }
}
