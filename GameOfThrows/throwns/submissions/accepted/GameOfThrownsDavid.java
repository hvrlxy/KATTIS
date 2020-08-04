import java.util.Scanner;
import java.util.Stack;

public class GameOfThrownsDavid
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		for (int i = 0; i < k; i++)
		{
			String next = scan.next();
			if (next.equalsIgnoreCase("undo"))
			{
				int numToUndo = scan.nextInt();
				for (int j = 0; j < numToUndo; j++)
				{
					stack.pop();
				}
			}
			else
			{
				int distanceToPass = Integer.parseInt(next);
				stack.push(stack.peek() + distanceToPass);
			}
		}
		
		int finalOwner = stack.peek();
		while (finalOwner < 0)
		{
			finalOwner += n;
		}
		
		finalOwner %= n;
		
		System.out.println(finalOwner);
		
		scan.close();
	}
}
