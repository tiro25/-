import java.util.*;

public class G4_6137_문자열생성 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] s = new char[n];
        char[] answer = new char[n];
        for(int i = 0; i < n; ++i) {
            s[i] = sc.next().charAt(0);
        }

        int left = 0, right = n-1;
        int idx = 0;
        while(left < right) {
            if(s[left] > s[right]) {
                answer[idx++] = s[right--];
            } else if(s[left] < s[right]) {
                answer[idx++] = s[left++];
            } else {
                int cnt = 1;
                int tmp = idx;
                while(left+cnt <= right-cnt) {
                    if(s[left+cnt] < s[right-cnt]) {
                        answer[idx++] = s[left++];
                        break;
                    } else if( s[left+cnt] > s[right-cnt]) {
                        answer[idx++] = s[right--];
                        break;
                    } else {
                        cnt++;
                    }
                }
                if(tmp == idx)
                    answer[idx++] = s[left++];
            }
            if(answer[n-2] != 0)
                break;
        }
        answer[n-1] = s[left];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            sb.append(answer[i]);
            if(i%80 == 79)
                sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}