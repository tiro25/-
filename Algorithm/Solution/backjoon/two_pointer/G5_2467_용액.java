import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_2467_용액 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int fir = 0, sec = 0;
    if(arr[0] >= 0) {
      fir = arr[0];
      sec = arr[1];
    } else if(arr[n-1] <= 0) {
      fir = arr[n-2];
      sec = arr[n-1];
    } else {
      int left = 0, right = n-1;
      int min = (int)2e9;
      while(left < right) {
        int sum = arr[left]+arr[right];
        if(Math.abs(sum) < min) {
          min = Math.abs(sum);
          fir = arr[left];
          sec = arr[right];
        }
        if(sum > 0) {
          right--;
        } else if(sum < 0) {
          left++;
        } else {
          break;
        }
      }
    }
    System.out.println(fir + " " + sec);
  }
}
