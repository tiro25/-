import java.io.*;
import java.util.*;

public class S2_18870_좌표압축 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] arr = Arrays.stream(input).distinct().sorted().toArray();

    Map<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < arr.length; ++i) {
      map.put(arr[i], i);
    }

    StringBuilder sb = new StringBuilder();
    for(int i: input) {
      sb.append(map.get(i)).append(' ');
    }
    System.out.println(sb);
  }

}
