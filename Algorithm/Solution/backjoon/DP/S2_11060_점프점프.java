import java.io.*;
import java.util.*;

class S2_11060_점프점프 {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < n; ++i) {
            int cur = Integer.parseInt(st.nextToken());

            if(dp[i] == 0)
                continue;

            for(int j = 1; j <= cur; ++j) {
                if(i+j == n)
                    break;
                if(dp[i+j] > dp[i]+1 || dp[i+j] == 0)
                    dp[i+j] = dp[i]+1;
            }
        }

        if(dp[n-1] == 0)
            dp[n-1] = 0;
        System.out.println(dp[n-1]-1);
    }
}
