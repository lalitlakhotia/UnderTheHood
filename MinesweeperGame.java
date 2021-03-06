import java.util.*;
import java.io.*;

/*
@author Shashank
*/
class Minesweeper { 
   int row;
   int column;
   double probability;
   boolean [][]board;
   int start=0;
   // sol[i][j] = # bombs adjacent to cell (i, j)
   int[][] sol;
   int bomb=0;
   Minesweeper(int row,int column,double probability)
   {
		this.row=row;
		this.column=column;
		this.probability=probability;
		board=new boolean[row][column];
		sol= new int[row][column];
		bomb=0;
   }
    // game grid is [0..M)[0..N) excluding M,N
	//Step-1 Iniatlize grid based on probaiblity
   void iniatlize(int row,int column)
   {    
        for (int i = 0; i <row; i++)
        {
			for (int j = 0; j < column; j++)
			{
				board[i][j] = (Math.random() < probability);
			}
		}
		
		System.out.println();
		ConfigureGame(row,column);
   }
   
   //Step-2 Configure Grid
   void ConfigureGame(int row,int column)
   {
      for (int i = 0; i <row; i++) {
         for (int j = 0; j <column; j++)
            if (board[i][j])  //// only 10 positions should be filled with bomb ???
			{   
				//if(bomb>=10)
				//System.out.print(". ");
			//	else
				{
					System.out.print("* "); 
					//bomb++;
					start++;
				}
			} 
            else             System.out.print(". ");
        System.out.println("start"+ start);
      }
		
		/* Jusr for testing purpose
		for (int i = 0; i <M; i++)
         for (int j = 0; j<N; j++)
           System.out.print( bombs[i][j] + " ");
           System.out.println();
		*/
   }
   
   // Play the Game
   void startGame(int M,int N)
   {
      for (int i = 0; i <M; i++)
      {   
		  for (int j = 0; j <N; j++)
          {
          	initializeNeighbourhood(i,j,M,N);   	
          }  
      }
   }
   
   //Initialize neighbourhood of any given point [i,j] 0<=i<M and 0<=j<N
   void initializeNeighbourhood(int i,int j,int M,int N)
   {
			if(i>0 && j>0)
          	{
          		if (board[i-1][j-1]) sol[i][j]++;
          	}
          	if(i>0)
          	{
          		if(board[i-1][j]) sol[i][j]++;
          	}
          	if(i>0 && j<N-1)//9
          	{
          		if(board[i-1][j+1]) sol[i][j]++;
          	}
          	if(i<M && j>0)
          	{
          		if(board[i][j-1])sol[i][j]++;
          	}
          	if(i<M && j<N-1)
          	{
				if(board[i][j+1]) sol[i][j]++;          	
          	}
          	if(i<M-1 && j>0)
          	{
          		if(board[i+1][j-1]) sol[i][j]++;
          	}
          	if(i<M-1)
          	{
          		if(board[i+1][j]) sol[i][j]++;
          	}
          	if(i<M-1 && j<N-1)
          	{
          		if(board[i+1][j+1]) sol[i][j]++;
          	}
   }
   
   //Generate Random number in given range [min,max) 
   int myRandom(int min, int max)
   { 
		int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
   }
   
   //Get randomly generated game at any time e.g. fill only some square with numbers.
   void generateRandomGame(int M,int N)
   {	boolean flag=true;
		while(flag)
		{
			int random_row=myRandom(0,M); //[0...M) excluding M and N
			int random_column=myRandom(0,N); //[0...N)
		
			//this If condition isn not necessary as we generating random number in range  
			if(random_row>=0 && random_row<M && random_column>=0 && random_column<N)
			{
				if(!board[random_row][random_column])
				{
					//Check not necessary neighbouHood ????
					initializeNeighbourhood(random_row,random_column,row,column);
					flag=false;
				}
				
			}
		
		}
			
		
		
   }
   //Print configuration of board at any time
   void printConfiguration(int M,int N)
   {
      for (int i = 0; i <M; i++) {
         for (int j = 0; j <N; j++)
            if (board[i][j]) {System.out.print("* ");start--;}
            else             System.out.print(sol[i][j] + " ");
         System.out.println("start"+ start);
      }
   }
   //Check if you lost or win print solution
   void isWon()
   {
		if(start==0) System.out.println("Won");
		else 		 System.out.println("Lost");
   }
   
   
}
class MinesweeperGame
{
	
	
	public static void main(String[] args) { 
      int M =8;// Integer.parseInt(args[0]);
      int N = 8;//Integer.parseInt(args[1]);
      double p =0.3;// Double.parseDouble(args[2]);
	  
	  if(M>8 || N>8 ) return;// Quetsion saya board should be 8*8 only 
	  
	  Minesweeper obj=new Minesweeper(M,N,p);
	  obj.iniatlize(M,N);
	
	obj.startGame(M,N);
		  obj.printConfiguration(M,N);
		  //validate if won or not
		  obj.isWon();
	  System.out.println("Please Press 1 to play new or 2 ro generate random game()");
	  /*int start=new Scanner(System.in).nextInt();
	  switch(start)
	  {
		  case 1:
		  //Randomly Generated Game 
		  obj.generateRandomGame(M,N);
		  obj.startGame(M,N);
		  //validate if won or not
		  obj.printConfiguration(M,N);
		  obj.isWon();
		  break;
		  
		  case 2:
		  //Start/Play New Game
		  obj.startGame(M,N);
		  obj.printConfiguration(M,N);
		  //validate if won or not
		  obj.isWon();
		  break;
		 
		  default:
	  }  */
   }
}

Run Here: http://ideone.com/rCHrbb ( Shoot me mail to run using this link )
Feel free to suggest me improvement , its not perfect :)
