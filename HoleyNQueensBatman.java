import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class HoleyNQueensBatman {

	static int[] counts = new int[13];
	static ArrayList<HashSet<int[]>> total = new ArrayList<>();

	public static boolean inBounds(int i , int j , int length) {
		return i >= 0 && i < length && j >= 0 && j < length; 
	}

	public static boolean[][] queenX(boolean[][] board, int row , int column) {

		for (int i = -board.length; i < board.length; i++)
		{
			if (inBounds(row + i , column + i , board.length))
				board[row + i][column + i] = true;

			if (inBounds(row + i , column - i , board.length))
				board[row + i][column - i] = true;

			if (inBounds(row + i , column , board.length))
				board[row + i][column] = true;

			if (inBounds(row , column + i , board.length))
				board[row][column + i] = true;
		}

		return board;
	}

	public static boolean[][] clone(boolean[][] in) {
		boolean[][] newOne = new boolean[in.length][in[0].length];

		for (int i = 0; i < newOne.length; i++)
			newOne[i] = Arrays.copyOf(in[i] , in[i].length);

		return newOne;
	}

	public static void print(boolean[][] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.println(Arrays.toString(arr[i]).replace("false" , "0").replace("true" , "1").replaceAll("[\\[, \\]]" , ""));

		System.out.println();
	}

	public static void Nqueens(boolean[][] board , int queens , int row , int[] nums) throws InterruptedException {

		if (queens == board.length)
		{
			counts[board.length]++;
			total.get(board.length).add(nums);
			return;
		}

		for (int column = 0; column < board.length; column++)
		{
			if (!board[row][column])
			{
				if (queens == 0)
					nums = new int[board.length];
				else
					nums = Arrays.copyOf(nums , board.length);


				boolean[][] newBoard = queenX(clone(board) , row , column);
				nums[row] = column;
				Nqueens(newBoard , queens + 1 , row + 1 , nums);
			}
		}

	}

	public static int countBreaks(HashSet<int[]> arrs , int[] holes) {
		int count = 0;

		for (int[] queens : arrs)
			if (breaks(queens , holes))
				count++;

		return count;
	}

	public static boolean breaks(int[] queens , int[] holes) {
		for (int i = 0; i < holes.length; i += 2)
			if (queens[holes[i]] == holes[i + 1])
				return true;

		return false;
	}
	

	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc=null;
		
		//Pour executer sur Kattis. Commenter l'instruction ci-dessous
		//sc = new Scanner(System.in);
		
		//Pour executer sur Kattis localement a partir d'un fichier. Commenter l'instruction ci-dessus
		try {
			sc = new Scanner(new File("src/fichier.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'existe pas\n");
		}

		for (int i = 0; i < 13; i++) {
			total.add(new HashSet<>());
		}

		for (int N = 1; N <= 12; N++)
		{
			boolean[][] board = new boolean[N][N];
			int[] trash = new int[0];

			Nqueens(board , 0 , 0 , trash);
		}
		
		/*for(int i=0;i<counts.length;i++) {
			System.out.println(i+" : "+counts[i]);
		}
		
		for(int j=0;j<total.size();j++) {
			System.out.println("\n->"+j+"\n");
			for(int [] tab : total.get(j)) {
				for(int i : tab)
					System.out.print(i);
				System.out.print("\n");
			}
		}*/
		
		
		while (true)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();

			if (N + M == 0) {
				break;
			}

			int[] trous = new int[2*M];

			for (int i = 0; i < trous.length; i++) {
				trous[i] = sc.nextInt();
			}				
			System.out.println(counts[N] - countBreaks(total.get(N) , trous));
		}

		//System.out.println(counts);
		sc.close();
	}
}