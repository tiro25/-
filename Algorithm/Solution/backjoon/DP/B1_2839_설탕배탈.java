import java.util.*;

class B1_2839_¼³ÅÁ¹èÅ» {

  static int[] dp;

  public static void main(String[] args) {
    int n = new Scanner(System.in).nextInt();
    dp = new int[n+1];
    int answer=dp(n);
    System.out.println(answer>=1<<29?-1:answer);
  }

  static int dp(int n) {
    if(n < 0)
      return 1<<29;
    if(dp[n] != 0)
      return dp[n];
    if(n == 3 || n == 5)
      return dp[n] = 1;

    return dp[n] = Math.min(dp(n-3), dp(n-5))+1;
  }
}
