import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G4_2473_세용액 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

    int fst = 0, snd = 0, trd = 0;
    if(arr[0] >= 0) {
      fst = arr[0];
      snd = arr[1];
      trd = arr[2];
    } else if(arr[n-1] <= 0) {
      fst = arr[n-3];
      snd = arr[n-2];
      trd = arr[n-1];
    } else {
      long min = (long)3e9;
      for(int i = 0; i < n-2; ++i) {
        for(int j = i+1; j < n-1; ++j) {
          int two = arr[i]+arr[j];
          int lo = j+1, hi = n;
          while(lo < hi) {
            int mid = (lo+hi)/2;
            long sum = 0L+two+arr[mid];

            if(Math.abs(sum) < min) {
              min = Math.abs(sum);
              fst = arr[i];
              snd = arr[j];
              trd = arr[mid];
            }

            if(sum > 0) {
              hi = mid;
            } else if(sum < 0) {
              lo = mid+1;
            } else {
              i = n;
              j = n;
              break;
            }
          }
        }
      }
    }
    System.out.println(fst + " " + snd + " " + trd);
  }
}
