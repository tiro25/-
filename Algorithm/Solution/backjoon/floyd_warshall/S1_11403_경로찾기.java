import java.io.*;
import java.util.*;

public class S1_11403_경로찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] adj = new int[n][n];
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; ++j)
                adj[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int k = 0; k < n; ++k)
            for(int i = 0; i < n; ++i)
                for(int j = 0; j < n; ++j)
                    if(adj[i][k] == 1 && adj[k][j] == 1)
                        adj[i][j] = 1;

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j)
                System.out.print(adj[i][j] + " ");
            System.out.println();
        }
    }
}
