import java.io.*;
import java.util.*;

class G4_16236_아기상어 {

    static int n;
    static int[][] board;
    static boolean[][] visited;
    static Fish shark;
    static Fish next;
    static Queue<Fish> q;

    static int answer;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void solve() {

        while(true) {
            int distance = init();

            while(!q.isEmpty() && next == null) {
                int size = q.size();
                distance++;
                while (size-- > 0) {
                    Fish cur = q.poll();
                    promise(cur.x, cur.y);

                    for (int dir = 0; dir < 4; ++dir) {
                        findRoute(cur.x+dx[dir], cur.y+dy[dir]);
                    }
                }
            }

            if(next != null) {
                move(distance);
            } else {
                System.out.println(answer);
                break;
            }
        }
    }

    static void move(int distance) {
        answer += distance;
        board[next.x][next.y] = 0;
        shark = next;
        shark.grow();
    }

    static void findRoute(int x, int y) {
        if (!isIn(x, y) || visited[x][y] || board[x][y] > shark.size){
            return;
        }

        visited[x][y] = true;
        q.offer(new Fish(x, y, board[x][y], -1));
    }

    static void promise(int x, int y) {
        if (board[x][y] > 0 && board[x][y] < shark.size) {
            Fish candidate = new Fish(x, y, shark.size, shark.feed+1);
            if(next == null ||
                    candidate.x < next.x ||
                    (candidate.x == next.x && candidate.y < next.y)) {
                next = candidate;
            }
        }
    }

    static int init() {
        visited = new boolean[n][n];
        q = new LinkedList<>();
        q.offer(shark);
        visited[shark.x][shark.y] = true;
        next = null;
        return -1;
    }

    static boolean isIn(int nx, int ny) {
        return nx>=0&&nx<n&&ny>=0&&ny<n;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9) {
                    shark = new Fish(i, j, 2, 0);
                    board[i][j] = 0;
                }
            }
        }
    }

    static class Fish {
        int x, y;
        int size;
        int feed;

        public Fish(int x, int y, int size, int feed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.feed = feed;
        }

        void grow() {
            if(feed == size) {
                size++;
                feed = 0;
            }
        }
    }
}
