/* 문제: 끝에서부터 n번째 노드 삭제
 * 분류: 연결리스트 구현
 *
 * 풀이: 지우고자하는 노드의 앞 노드 인덱스를 구한 이후, 앞 노드와 뒤 노드 연결
 */

package leetcode;

import java.io.*;
import java.util.StringTokenizer;

public class RemoveNthFromEnd {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static ListNode head = new ListNode();  // head를 list는
    public static void main(String[] args) throws IOException {
        input();
        ListNode answer = removeNthFromEnd(head.next, n);
        output(head.next);
    }

    static void output(ListNode node) throws IOException {      // 출력
        while(node != null && node.val != 0){
            bw.write(node.val+" ");
            System.out.println(node.val);
            node = node.next;
        }
        bw.flush();
    }

    static ListNode removeNthFromEnd(ListNode head, int n) {
        int len = -n;
        ListNode prev = new ListNode();
        prev.next = head;

        ListNode cur = head;
        while(cur != null && cur.val != 0){     // 원래는 뒤에 조건식은 없어도 되지만, 내 구현 부분에서는 필요했다. (정답 제출시 불필요)
            len++;
            cur = cur.next;
        }
        // 지우고자 하는 노드의 앞 노드 인덱스 찾기

        cur = prev;
        while(len-- > 0){
            cur = cur.next;
        }
        cur.next = cur.next.next;

        return prev.next;
    }

    static void input() throws IOException {        // 입력
        st = new StringTokenizer(br.readLine(), " ,");
        n = Integer.parseInt(br.readLine());

        ListNode cur = new ListNode();
        head.next = cur;
        while(st.hasMoreTokens()){
            cur.val = Integer.parseInt(st.nextToken());
            cur.next = new ListNode();
            cur = cur.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
