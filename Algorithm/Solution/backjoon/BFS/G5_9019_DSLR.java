import java.io.*;
import java.util.*;

public class G5_9019_DSLR {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int trgt = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            Queue<Pair> q = new LinkedList<>();
            q.offer(new Pair(src, ""));
            visited[src] = true;

            while(true) {
                Pair cur = q.poll();
                int value = cur.value;
                if(value == trgt) {
                    answer.append(cur.cmd);
                    break;
                }

                String cmd = cur.cmd;
                int next = value*2%10000;
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new Pair(next, cmd+'D'));
                }
                next = (value+9999)%10000;
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new Pair(next, cmd+'S'));
                }
                next = value*10%10000+value/1000;;
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new Pair(next, cmd+'L'));
                }
                next = value%10*1000+value/10;
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new Pair(next, cmd+'R'));                }
            }
            answer.append('\n');
        }
        System.out.print(answer.toString());
    }

    static class Pair {
        int value;
        String cmd;

        public Pair(int value, String cmd) {
            this.value = value;
            this.cmd = cmd;
        }
    }
}
