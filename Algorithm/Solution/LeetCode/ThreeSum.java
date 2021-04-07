/* 문제: 세 수의 합
 * 분류: 완전탐색, 이분탐색
 *
 * 풀이: 배열에서 2개의 수 조합에 대해 더했을 때 0이 되는 값을 key 값으로하는 upperbound를 찾아준다.
 *
 * 실수:
 * 1. 처음에는 Lower Bound를 사용했었다.
 *    Lower Bound 사용 시 {0, 0, 0}과 같이 같은값이 3개 이상 나오는 케이스에 대해 답을 찾지 못한다.
 *      => Upper Bound - 1 로 해서 같은 값에 대해서 가장 뒤에 있는 값을 찾았다.
 */

import java.io.*;
import java.util.*;

public class ThreeSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; ++i)
            arr[i] = Integer.parseInt(st.nextToken());

        System.out.println(threeSum(arr).toString());
    }

    static public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> answer = new HashSet<>();        // 뽑힌 세 수 중복을 막기 위해 set 사용
        int len = nums.length;
        Arrays.sort(nums);

        for(int i = 0; i < len-2; ++i){
            for(int j = i+1; j < len-1; ++j){
                int sum = nums[i]+nums[j];          // 두 수의 합
                int k = upperBound(nums, -sum)-1;   // 나머지 수가 되어야 되는 값 찾기
                if(k > j && k < len && nums[k] == -sum)
                    answer.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));  // 값이 있으면 set에 추가
            }
        }
        return new ArrayList<>(answer);
    }

    static int upperBound(int[] arr, int key){      // Upper Bound
        int lo = 0, hi = arr.length;
        while(lo < hi){
            int mid = (lo+hi)/2;

            if(key < arr[mid])
                hi = mid;
            else
                lo = mid+1;
        }
        return hi;
    }
}
