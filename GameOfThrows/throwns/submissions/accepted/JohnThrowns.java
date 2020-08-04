import java.util.*;

public class JohnThrowns
{
    public static final int MAXC = 30;
    public static final int MAXI = 100;

    public static Scanner in = new Scanner(System.in);

    public static int [] cstack = new int[MAXI];
    public static int top;

    public static  void main(String [] args)
    {
        int n, p, m;
        n = in.nextInt();
        p = in.nextInt();
        cstack[0] = 0;
        top = 0;
        for(int i=0; i<p; i++) {
            String s = in.next();
            if (s.equals("undo")) {
                m = in.nextInt();
                top -= m;
            }
            else {
                m = Integer.parseInt(s);
                cstack[top+1] = (cstack[top]+m) % n;
                top++;
                if (cstack[top] < 0)
                    cstack[top] += n;
            }
        }
        System.out.println(cstack[top]);
    }
}
