package model;

/** The model class is where the current state of the game
 * as well as the winning logic resides. The model class calls 
 * the view to update the gui according to the current state of 
 * the game.
 * */

import view.*;

import java.sql.*;
import javax.swing.*;
import database.sqlConnection;

public class Model{
	private View v;
	private int playerId;
	private int movesCount;
	private char[][] board;
	private String message;
	
	//-------------------------------------------------------------
	private static String A = "Player 1";
	private static String B = "Player 2";
	

	
	

	
	public void setNames(String A1, String B1) {
		Model.A = A1;
		Model.B = B1;
	}
	
	Connection connection = null;
	
	//--------------------------------------------------------------

	// default constructor
	public Model() {
		this.board = new char[3][3];
		this.movesCount = 9;
		this.playerId = 1;
	}
	
	// initializing the reference of view class
	public void registerView(View v) {
		this.v = v;
	}
    
	//setters and getters
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getMovesCount() {
		return movesCount;
	}

	public void setMovesCount(int movesCount) {
		this.movesCount = movesCount;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// function to update the board model
	public void PlayMove(int x, int y) {	
		
		
		if(getMovesCount() > 0){
			// mark the board with x or o depending on playerId
			if(playerId%2 != 0) 
				board[x][y] = 'X';
			else 
				board[x][y] = 'O';
			
			// reduce the count of moves left
			setMovesCount(--movesCount);
			
			// check if playerId has won or if game is tied,
			// and send message accordingly to view, also update the view
			if(isWinner(x, y)) {
				setMessage("Player " + playerId + " is Winner!");
				v.isWinner(x, y, board[x][y], getMessage());
				
				
				//----------------------------------------------------------
				
				//PlayerNames playername = new PlayerNames();
				//playername.setVisible(true);
				
				String A1 = Model.A;
				String B1 = Model.B;
				try {
					connection = sqlConnection.dbConnector();
					String query = "insert into playerscores (playeronename, playertwoname, winner) values(?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,A1);
					pst.setString(2, B1);
					
					if (playerId %2 != 0 )
						pst.setString(3, A1);
					else
						pst.setString(3, B1);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Data Saved Successfully");
					
					pst.close();
					JOptionPane.showMessageDialog(null,"Database Connection Closed");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			else if(getMovesCount()==0) {
				setMessage("No Winner! Game ended in a Tie");
				v.isWinner(x, y, board[x][y], getMessage());
			}
			else {
				if(playerId%2 != 0) {
					// toggle the playerId
					setPlayerId(2);
					setMessage("'O':  Player " +getPlayerId());
				}
				else {
					setPlayerId(1);
					setMessage("'X':  Player " +getPlayerId());

				}
				// update the board with message for next player
				v.update(x, y, board[x][y], getMessage());
			}
			
		}
		
	}
	
	// function to check if there is a winner
	public boolean isWinner(int x, int y) {
		int countRow = 0;
		int countCol = 0;
		int countLDiag = 0;
		int countRDiag = 0;
		char symbol;
		if(getPlayerId() %2 !=0)
			symbol = 'X';
		else
			symbol = 'O';
		
		for(int i=0; i<board.length;i++) {
			if(board[x][i] == symbol)
				countRow++;
			if(board[i][y] == symbol)
				countCol++;
			if(board[i][i] == symbol)
				countRDiag++;
			if(board[board.length-1-i][i] == symbol)
				countLDiag++;	
		}
		
		if(countCol==board.length || countRow==board.length 
		   || countLDiag == board.length || countRDiag == board.length)
			return true;
		
		return false;
	}
	
	// function to clear the model and reset it to initial state
	public void ResetModel() {
		movesCount = 9;
		setPlayerId(1);
		setMessage("");
		for(int i=0; i<board.length;i++) {
			for(int j=0; j<board.length;j++) {
				board[i][j] = '\0';
			}
		}
		v.resetGame();
		
	}
		
}
