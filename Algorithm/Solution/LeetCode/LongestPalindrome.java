/* 문제: 가장 긴 팰린드롬 찾기
 * 분류: 완전탐색
 * 
 *
 * 내 풀이: 문자열의 모든 원소를 가운데로 두고 홀수, 짝수인 경우를 나누어 반환된 문자열 중 가장 긴 값을 저장
 * 
 * 개선된 풀이: 문자열이 아닌 시작, 끝 점의 인덱스만 저장하여 마지막에 substring반환
 * 
 * 개선된 점:
 * 1. 매번 substring을 안 구해도 됨.
 * 2. 홀수, 짝수를 각각 다른 메서드로 만들지 않아도 됨.
 */

import java.util.Scanner;

public class LongestPalindrome {
    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        System.out.println(solve(s));   // 내 풀이
        System.out.println(solve2(s));  // 개선된 풀이
    }

    static String solve(String s){
        String odd = oddPalin(s);       // 길이가 홀수인 팰린드롬 중 가장 길이가 긴 스트링
        String even = evenPalin(s);     // 길이가 짝수인 팰린드롬 중 가장 길이가 긴 스트링

        if(even.length() > odd.length())
            return even;
        else
            return odd;                 // 더 길이가 긴 스트링 반환
    }

    static String oddPalin(String s){
        int len = s.length();

        String longestStr = s.charAt(0)+"";
        int maxLen = 1;
        for(int i = 0; i < len; ++i){
            int j = 0;
            while(i-j >= 0 && i+j < len && s.charAt(i-j) == s.charAt(i+j))
                j++;
            if(--j*2 > maxLen){         // 길이가 홀수인 가장 긴 팰린드롬 갱신
                maxLen = j*2+1;
                longestStr = s.substring(i-j, i+j+1);
            }
        }
        return longestStr;
    }

    static String evenPalin(String s){
        int len = s.length();
        if(len == 1)                    // 문자열의 길이가 1일 때 빈 문자열 반환
            return "";

        String longestStr = "";
        int maxLen = 0;
        for(int i = 0; i < len; ++i){
            int j = 0;
            while(i-j >= 0 && i+1+j < len && s.charAt(i-j) == s.charAt(i+1+j))
                j++;
            if((--j+1)*2 > maxLen){     // 길이가 짝수인 가장 긴 팰린드롬 갱신
                maxLen = (j+1)*2;
                longestStr = s.substring(i-j, i+j+2);
            }
        }
        return longestStr;
    }

    //------------------------------------------------------------------------------

    static String solve2(String s){
        int srt = 0, end = 0;
        for(int i = 0; i < s.length(); ++i){
            int oddLen = getPalinLen(s, i, i);          // 길이가 홀수인 가장 긴 팰린드롬의 길이
            int evenLen = getPalinLen(s, i, i+1);   // 길이가 짝수인 가장 긴 팰린드롬의 길이
            int len = Math.max(oddLen, evenLen);        // 둘 중 더 긴 길이
            if(len > end-srt+1){                        // 가장 긴 팰린드롬 길이 갱신
                srt = i - (len-1)/2;
                end = i + len/2;
            }
        }
        return s.substring(srt, end+1);
    }

    static int getPalinLen(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;                    // (--right)-(++left)+1
    }
}
