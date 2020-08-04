import java.util.*;

public class BRThrowns {
  public static Scanner in;
  public static int n,k;
  public static Stack<Integer> thr;

  public static void main(String[] args) {
    in = new Scanner(System.in);
    n = in.nextInt();
    k = in.nextInt();
    thr = new Stack<Integer>();
    int curr = 0;
    for (int i = 0; i < k; i++) {
      String line = in.next();
      if (line.equals("undo")) {
        int m = in.nextInt();
//System.out.println("Undoing "+m+" throws:");
        //int j = thr.size()-1;
        for (int l = 0; l < m; l++) {
          int u = thr.pop();
          curr = (curr - u+n*10000)%n;
//System.out.println("...undoing "+u+", curr = "+curr);
        }
      }
      else {
        int v = Integer.parseInt(line);
        thr.push(v);
        curr = (curr + v + n*10000)%n;
//System.out.println("Throw "+v+": curr = "+curr);
      }
    }
    System.out.println(curr);
  }
}
