import java.io.*;
import java.util.*;

class S1_7569_토마토 {

  static int[] dx = {1, 0, 0, 0, 0, -1};
  static int[] dy = {0, 0, 1, 0, -1, 0};
  static int[] dz = {0, 1, 0, -1, 0, 0};

  static int m, n, h;
  static int[][][] box;
  static Queue<Coor> q = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    input();
    int answer = bfs();
    if(!check())
      answer = -1;
    System.out.println(answer);
  }

  static int bfs() {
    int day = -1;
    while(!q.isEmpty()) {
      int size = q.size();
      day++;
      while(size-- > 0) {
        Coor cur = q.poll();
        for(int dir = 0; dir < 6; ++dir) {
          Coor next = cur.move(dir);
          if(!next.isIn())
            continue;
          if(!next.isRipe()) {
            next.ripe();
            q.offer(next);
          }
        }
      }
    }
    return day;
  }

  static boolean check() {
    for(int i = 0; i < h; ++i)
      for(int j = 0; j < n; ++j)
        for(int k = 0; k < m; ++k)
          if(box[i][j][k] == 0)
            return false;
    return true;
  }

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());
    h = Integer.parseInt(st.nextToken());
    box = new int[h][n][m];
    for(int i = 0; i < h; ++i) {
      for(int j = 0; j < n; ++j) {
        st = new StringTokenizer(br.readLine(), " ");
        for(int k = 0; k < m; ++k) {
          box[i][j][k] = Integer.parseInt(st.nextToken());
          if(box[i][j][k] == 1) {
            q.offer(new Coor(k, j, i));
          }
        }
      }
    }
  }

  static class Coor {
    int x, y, z;

    public Coor(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    Coor move(int dir) {
      return new Coor(x+dx[dir], y+dy[dir], z+dz[dir]);
    }

    boolean isIn() {
      return 0<=x&&x<m && 0<=y&&y<n && 0<=z&&z<h;
    }

    boolean isRipe() {
      if(box[z][y][x] == 0)
        return false;
      return true;
    }

    void ripe() {
      box[z][y][x] = 1;
    }
  }
}
