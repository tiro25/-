import java.util.*;

public class B1_2839_설탕배탈 {

  public static void main(String[] args) {
    int n = new Scanner(System.in).nextInt();
    int[] memo = new int[n+1];
    memo[3] = 1;
    if(n>4)
      memo[5] = 1;
    for(int i = 6; i <= n; ++i) {
      if(memo[i-3] == 0 && memo[i-5] == 0)
        memo[i] = 0;
      else if(memo[i-3] == 0)
        memo[i] = memo[i-5]+1;
      else if(memo[i-5] == 0)
        memo[i] = memo[i-3]+1;
      else
        memo[i] = Math.min(memo[i-3], memo[i-5])+1;
    }
    System.out.println(memo[n]==0?-1:memo[n]);
  }

}
