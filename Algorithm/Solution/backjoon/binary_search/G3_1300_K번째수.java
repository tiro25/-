import java.util.*;

public class G3_1300_K번째수 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();

        int left = 1, right = k;
        while(left < right) {
            int mid = (left+right)/2;

            int cnt = 0;
            for(int i = 1; i <= n; ++i) {
                cnt += Math.min(n, mid/i);
            }

            if(cnt >= k) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        System.out.println(right);
    }
}
