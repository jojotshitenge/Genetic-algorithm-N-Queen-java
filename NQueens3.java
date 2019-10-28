import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NQueens3 {
	
	 static Scanner fichier;
	 static int nQueens;
	 static int[] board;
	 static int nombreTrous;
 	 static int x;
 	 static int y;
     static List<Trou> listeTrous = new ArrayList<Trou>(); 
	
     static public class Trou{
		public int ligne;
		public int colonne;    
		
		public Trou(int ligne, int colonne) {
			this.ligne=ligne;
			this.colonne=colonne;
		}
	 }
	
    public static void resoudre(int[] board, int nQueens, boolean avecTrous) {
    	 //Placer la premiere piece pour commencer
    	
        int row = 0;
        board[row] = 0;
        row++;
        int column = 0;
        long solutions = 0;        

        
        while (row >= 0) {
            if (estValide(row, column, board,  avecTrous)) {
                board[row] = column;
                row++;
                column = 0;

                //Tableau Complet ?
                if (row >= board.length) {
                    solutions++;
                   // System.out.println("Solution " + solutions);
                    //afficherBoard(board);
                    row--;
                    column = board[row] + 1;
                }
            }
            else {
                //Si la colonne courante est valide
                column++;
                if (column >= board.length) {
                    board[row] = -1;
                    row--;
                    if (row >= 0)
                        column = board[row] + 1;
                }
            }
        }
        System.out.println(solutions);
    }
    
    public static boolean estValide(int row, int column, int[] board, boolean avecTrous) {
        if (column >= board.length)
            return false;        

        	
        for (int i = 0; i < row; i++) {
            //Verifier si la colonne apparait dans les autres lignes
            if (board[i] == column)
                return false;

            //Verificcation des diagonales
            if (Math.abs(row - i) == Math.abs(column - board[i])) 
                return false;
        }
        
        if(avecTrous) {
        	for(Trou t : listeTrous) {
        		if(row==t.ligne && column==t.colonne) {
        			return false;
        		}
        	}
        }
        /*if( (row==0 && column==3) || (row==5 && column==4) || (row==3 && column==7) ) {
    		return false;
        }*/

        return true;
    }

   /* public static void afficherBoard(int[] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i] == j)
                    System.out.print("|Q");
                else
                    System.out.print("| ");
            }
            System.out.println("|");
        }
    }*/
    
    
    public static void main(String[] args) throws InterruptedException {           	
    	//Scanner sc = new Scanner(System.in);
    	Scanner fichier = new Scanner(System.in);
    	/*String nomFichier=sc.nextLine();
        try {
			fichier = new Scanner(new File(nomFichier));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
        //System.out.print("Entrez le nombre de reines : ");
       /* nQueens = fichier.nextInt();
        nombreTrous=fichier.nextInt();
        
        //Creation du board
        board = new int[nQueens];        
        for (int i = 0; i < board.length; i++) {
            board[i] = -1;
        }        
        //Premiere resolution board sans trous
        resoudre(board, nQueens, false);          
        
        //Deuxieme resolution board avec trous
        nQueens = fichier.nextInt();
        nombreTrous=fichier.nextInt();
        for (int i = 0; i < board.length; i++) {
            board[i] = -1;
        }  
        for(int i=0;i<nombreTrous;i++) {
        	x=fichier.nextInt();
        	y=fichier.nextInt();
        	
        	listeTrous.add(new Trou(x,y));
        }
        resoudre(board, nQueens, true);
        */
        
        do {
        	 nQueens = fichier.nextInt();
             nombreTrous=fichier.nextInt();
             
             if(nQueens==0) {
            	 break;
             }else {
                 board= new int[nQueens];
                 for (int i = 0; i < board.length; i++) {
                     board[i] = -1;
                 }
            	 if(nombreTrous+nQueens==0) {
                     resoudre(board, nQueens, false); 
            	 }else {
            		 	listeTrous= new ArrayList<Trou>();
            	       	for(int i=0;i<nombreTrous;i++) {
            	        	x=fichier.nextInt();
            	        	y=fichier.nextInt();            	        	
            	        	listeTrous.add(new Trou(x,y));
            	        }
                        resoudre(board, nQueens, true);
            	 }
             }
             
             //Creation du board
            /* board = new int[nQueens];        
             for (int i = 0; i < board.length; i++) {
                 board[i] = -1;
             } */      
             //Premiere resolution board sans trous
         
             
             //Deuxieme resolution board avec trous
             /*nQueens = fichier.nextInt();
             nombreTrous=fichier.nextInt();
             for (int i = 0; i < board.length; i++) {
                 board[i] = -1;
             }*/
             
            
             /*for(int i=0;i<nombreTrous;i++) {
             	x=fichier.nextInt();
             	y=fichier.nextInt();
             	
             	listeTrous.add(new Trou(x,y));
             }*/
        }while(nQueens!=0);
        
    }
    
}