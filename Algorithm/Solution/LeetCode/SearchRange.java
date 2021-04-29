/* 문제: 처음과 시작 인덱스 찾기
 * 분류: 이분탐색
 * 풀이: lowerbound와 upperbound를 각각 찾아 두 값이 일치하는 경우(답이 없는 경우)는 {-1, -1}을 반환하고 그렇지 않은 경우 {lowerbound, upperbound-1}을 반환한다.
 */

public class SearchRange{
	public int[] searchRange(int[] nums, int target) {
		int[] answer = new int[2];
		int lowerBound = getLowerBound(nums, target);	// lowerbound
		int upperBound = getUpperBound(nums, target);	// upperbound
		if(lowerBound == upperBound){		// 없을 때
			answer[0] = answer[1] = -1;
		} else {				// 있을 때
			answer[0] = lowerBound;
			answer[1] = upperBound-1;
		}
		return answer;
	}

	public int getLowerBound(int[] arr, int target){
		int lo = 0, hi = arr.length;
		while(lo < hi){
			int mid = (lo + hi)/2;
			if(target > arr[mid])
				lo = mid+1;
			else
				hi = mid;
		}
		return hi;
	}

	public int getUpperBound(int[] arr, int target){
		int lo = 0, hi = arr.length;
		while(lo < hi){
			int mid = (lo + hi)/2;
			if(target >= arr[mid])
				lo = mid+1;
			else
				hi = mid;
		}
		return hi;
	}

}
