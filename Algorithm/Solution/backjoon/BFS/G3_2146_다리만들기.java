import java.io.*;
import java.util.*;

class G3_2146_다리만들기 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int n;
    static boolean[][] board;
    static int[][] island;
    static boolean[][] visited;

    static Queue<Island> islandQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        input();
        setIslands();
        System.out.println(getAnswer());
    }

    static int getAnswer() {
        int answer = 200;
        int depth = -1;
        visited = new boolean[n][n];
        Island cur = islandQ.peek();
        visited[cur.x][cur.y] = true;

        while(true) {
            depth++;
            int size = islandQ.size();

            while(size-- > 0) {
                cur = islandQ.poll();
                for(int dir = 0; dir < 4; ++dir) {
                    Island next = cur.next(dir);
                    int x = next.x, y = next.y;
                    int num = next.num;
                    if(!next.isIn() || island[x][y] == num)
                        continue;
                    if(!next.visited()) {
                        visited[x][y] = true;
                        island[x][y] = num;
                        islandQ.offer(next);
                        continue;
                    }

                    if(island[x][y] < num)
                        answer = Math.min(answer, depth*2+1);
                    else
                        answer = Math.min(answer, depth*2);
                }
            }
            if(answer != 200)
                return answer;
        }
    }

    private static void setIslands() {
        island = new int[n][n];
        visited = new boolean[n][n];
        int num = 0;
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < n; ++j)
                if(!visited[i][j] && board[i][j])
                    setIsland(i, j, ++num);
    }

    static void setIsland(int i, int j, int num) {
        Queue<Island> q = new LinkedList<>();
        Island cur = new Island(i, j);
        cur.num = num;
        q.offer(cur);
        islandQ.offer(cur);
        visited[i][j] = true;
        island[i][j] = num;
        while(!q.isEmpty()) {
            cur = q.poll();
            for(int dir = 0; dir < 4; ++dir) {
                Island next = cur.next(dir);
                if(!next.isIn() || next.visited() || !next.land())
                    continue;
                next.setNum();
                q.offer(next);
                islandQ.offer(next);
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new boolean[n][n];
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; ++j) {
                board[i][j] = "1".equals(st.nextToken());
            }
        }
    }

    static class Island {
        int x, y;
        int num;

        Island(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Island next(int dir) {
            Island next = new Island(x+dx[dir], y+dy[dir]);
            next.num = this.num;
            return next;
        }

        boolean isIn() {
            return 0<=x&&x<n&&0<=y&&y<n;
        }

        boolean visited() {
            return visited[x][y];
        }

        public boolean land() {
            return board[x][y];
        }

        void setNum() {
            island[x][y] = num;
            visited[x][y] = true;
        }
    }
}
