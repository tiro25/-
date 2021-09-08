import java.io.*;
import java.util.*;

class G4_1774_우주신과의교감 {

    static int n, m;
    static Point[] points;
    static PriorityQueue<Node> pq;
    static int[] root;
    static int nPick = 1;

    public static void main(String[] args) throws IOException {
        input();
        kruskal();
    }

    static void kruskal() {
        double answer = 0;
        while(!pq.isEmpty()) {
            if(nPick == n) {
                break;
            }
            Node cur = pq.poll();
            if(find(cur.v1) != find(cur.v2)) {
                answer += cur.w;
                nPick++;
                union(cur.v1, cur.v2);
            }
        }
        System.out.printf("%.2f\n", answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        root = new int[n];
        points = new Point[n];
        for(int i = 0; i < n; ++i) {
            root[i] = i;
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        pq = new PriorityQueue<>((o1, o2) ->
            Double.compare(o1.w, o2.w)
        );
        for(int i = 0; i < n-1; ++i) {
            for(int j = i+1; j < n; ++j) {
                int dx = points[i].x-points[j].x, dy = points[i].y-points[j].y;
                double tmp = Math.sqrt(1L*dx*dx+1L*dy*dy);
                pq.offer(new Node(i, j, tmp));
            }
        }
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken())-1;
            int v2 = Integer.parseInt(st.nextToken())-1;
            if(find(v1) != find(v2)) {
                nPick++;
                union(v1, v2);
            }
        }
    }

    static void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if(v1 < v2) {
            root[root2] = root1;
        } else {
            root[root1] = root2;
        }
    }

    static int find(int v) {
        if(root[v] == v) {
            return v;
        }
        return root[v] = find(root[v]);
    }

    static class Node {
        int v1, v2;
        double w;

        public Node(int v1, int v2, double w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
