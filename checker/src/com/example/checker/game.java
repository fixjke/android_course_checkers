package com.example.checker;

import com.example.checker.cell;

public class game {
	cell[][] board;
	final int size=8;
	boolean currentPlayer;
	
	game() {
		board = new cell[size][size];
		for(int i=0; i<size; i++)
		{
			for (int j=0; j<size; j++)
				board[j][i]=cell.empty;
		}
		for (int i=0; i<4; i+=2)
		{
			for (int j=1; j<size; j+=2)
				board[j][i]=cell.black;
			if (i+1>2) break;
			for (int j=0; j<size; j+=2)
				board[j][i+1]=cell.black;
		}
		for (int i=size-1; i>size-4; i-=2)
		{
			for (int j=0; j<size; j+=2)
				board[j][i]=cell.white;
			if (i<6) break;
			for (int j=1; j<size; j+=2)
				board[j][i-1]=cell.white;
		}
		currentPlayer=true;
		//ltd=0;

	}
	
	void makeMove(int startx,int starty, int endx, int endy) {
		if (currentPlayer && board[startx][starty]==cell.black && board[endx][endy]==cell.empty
				&& Math.abs(startx-endx)==1 && endy-starty==1)   {
			board[startx][starty]=cell.empty;
			board[endx][endy]=cell.black;
		}
		if (!currentPlayer && board[startx][starty]==cell.white && board[endx][endy]==cell.empty
				 && Math.abs(startx-endx)==1 && endy-starty==-1) {
			board[startx][starty]=cell.empty;
			board[endx][endy]=cell.white;
		}
		
		if (currentPlayer && board[startx][starty]==cell.black && board[endx][endy]==cell.empty
				&& endx-startx==2 && endy-starty==2 && board[startx+1][starty+1]==cell.white) {
			board[startx][starty]=cell.empty;
			board[endx][endy]=cell.black;
			board[startx+1][starty+1]=cell.empty;
		}
		
		if (currentPlayer && board[startx][starty]==cell.black && board[endx][endy]==cell.empty
				&& endx-startx==-2 && endy-starty==2 && board[startx-1][starty+1]==cell.white) {
			board[startx][starty]=cell.empty;
			board[endx][endy]=cell.black;
			board[startx-1][starty+1]=cell.empty;
		}
		
		if (!currentPlayer && board[startx][starty]==cell.white && board[endx][endy]==cell.empty
				&& endx-startx==2 && endy-starty==-2 && board[startx+1][starty-1]==cell.black) {
			board[startx][starty]=cell.empty;
			board[endx][endy]=cell.white;
			board[startx+1][starty-1]=cell.empty;
		}
		if (!currentPlayer && board[startx][starty]==cell.white && board[endx][endy]==cell.empty
				&& endx-startx==-2 && endy-starty==-2 && board[startx-1][starty-1]==cell.black) {
			board[startx][starty]=cell.empty;
			board[endx][endy]=cell.white;
			board[startx-1][starty-1]=cell.empty;
		}
		
		
		
	}
}

