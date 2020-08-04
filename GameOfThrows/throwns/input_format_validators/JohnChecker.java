import java.util.*;

// Game of Throwns checker

public class JohnChecker
{
    public static final int MINN = 1;
	public static final int MAXN = 30;
	public static final int MINK = 1;
	public static final int MAXK = 100;
	public static final int MINP = -10000;
	public static final int MAXP = 10000;

	public static void printError(int line, String msg)
	{
		System.out.println("ERROR Line " + line + ": " + msg);
		System.exit(-1);
	}

    public static void checkIntBounds(int x, int min, int max, String name, int nLines)
    {
        if (x < min || x > max)
            printError(nLines, "invalid " + name + " value: " + x);
    }

	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		int n, k, p, m, nLines = 0;
		String line;

        line = in.nextLine();
        nLines++;
        StringTokenizer st = new StringTokenizer(line);
        if (st.countTokens() != 2)
            printError(nLines, "number of values on line incorrect");
		n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
		checkIntBounds(n, MINN, MAXN, "n", nLines);
		checkIntBounds(k, MINK, MAXK, "k", nLines);
		while (in.hasNext()) {
			line = in.nextLine();
			nLines++;
			st = new StringTokenizer(line);
            for(int i=1; i<=k; i++) {
                String s = st.nextToken();
                try {
                    if (s.equals("undo")) {
                        m = Integer.parseInt(st.nextToken());
                        checkIntBounds(m, 1, i, "m", nLines);
                    }
                    else {
                        p = Integer.parseInt(s);
                        checkIntBounds(p, MINP, MAXP, "p", nLines);
                    }
                } catch(NumberFormatException e) {
                    printError(nLines, "expected integer, got " + s);
                }
            }
            if (st.hasMoreTokens())
                printError(nLines, "extraneous tokens");
		}
		if (nLines != 2)
			printError(nLines, "incorrect number of lines");
System.exit(42);
	}
}

