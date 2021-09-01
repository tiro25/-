import java.io.*;
import java.util.*;

public class S3_2630_색종이 {

    static int n;
    static boolean[][] paper;
    static int w, b;

    public static void main(String[] args) throws IOException {

        input();
        solve();
    }

    static void solve() {
        rec(n, 0, 0);
        System.out.println(w+"\n"+b);
    }

    static void rec(int n, int x, int y) {
        boolean first = paper[x][y];
        boolean isSame = true;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if(paper[x+i][y+j] != first) {
                    isSame = false;
                    break;
                }
            }
        }

        if(isSame) {
            if(first) {
                b++;
            } else {
                w++;
            }
        } else {
            rec(n/2, x, y);
            rec(n/2, x, y+n/2);
            rec(n/2, x+n/2, y);
            rec(n/2, x+n/2, y+n/2);
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        paper = new boolean[n][n];
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; ++j) {
                paper[i][j] = "1".equals(st.nextToken());
            }
        }
    }
}
