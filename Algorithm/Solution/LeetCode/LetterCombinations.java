/* 문제: 문자 조합
 * 분류: 완전탐색
 * 
 * 풀이: 각 숫자에 대한 문자를 배열로 저장하여, 완전탐색
 * 
 * 실수:
 * 1. 길이가 0인 경우
 *    빈 문자열이 정답 list에 추가되는 문제가 발생했다.
 *      => 길이가 0인 경우 따로 처리
 */

import java.util.*;

public class LetterCombinations {

    static char[][] board = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'},
            {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};   // 각 숫자에 매핑되는 문자 배열

    static List<String> answer = new ArrayList<>();     // 답을 저장하는 list
    static int len;                                     // 입력 문자열 길이

    public static void main(String[] args) {
        String digits = new Scanner(System.in).nextLine();
        System.out.println(letterCombinations(digits).toString());
    }

    static public List<String> letterCombinations(String digits) {
        len = digits.length();
        if(len == 0)        // 길이가 0인 경우 바로 반환
            return answer;

        rec(digits, len, new String());
        return answer;
    }

    static void rec(String digits, int toPick, String picked){
        if(toPick == 0){    // 답 추가
            answer.add(picked);
            return;
        }

        int digit = digits.charAt(len-toPick)-'0';  // 현재 위치에서의 숫자
        for(int i = 0; i < board[digit].length; ++i){   // 숫자에 매핑된 문자 추가
            rec(digits, toPick-1, picked+board[digit][i]);
        }
    }
}
