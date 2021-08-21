import java.util.*;

class S1_1309_동물원 {

  static int[] dp;

  public static void main(String[] args) {
    int n = new Scanner(System.in).nextInt();
    dp = new int[n+1];
    System.out.println(dp(n));
  }

  static int dp(int n) {
    if(n == 0)
      return 1;
    if(n == 1)
      return 3;
    if(dp[n] != 0)
      return dp[n];

    int MOD = 9901;
    return dp[n] = (dp(n-1)*2+dp(n-2))%MOD;
  }


}
