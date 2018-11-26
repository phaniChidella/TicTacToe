package view;


/** The View class is responsible for setting up the gui and 
 * displaying the state of the game on the gui as informed by 
 * the model.
 */

import controller.*;
import adapter.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.*;
import database.*;

import net.proteanit.sql.DbUtils;

import javax.swing.*;

public class View {
	private Adapter adapter;
	private JFrame gui;
    private JButton[][] blocks;
    private JButton reset;
    private JButton scores;
    private JTextArea playerturn;
    
    //-----------------------------------------------------
    //private JTextField player1name, player2name;
    //private JButton go;
    //------------------------------------------------------
    
    Connection connection = null;
    
    
    // default constructor to initialize the gui as JFrame
    public View() {
    		
    		
    		this.gui = new JFrame("Tic Tac Toe");
    		
    		//----------------------------------------
    		//this.player1name = new JTextField();
    		//this.player2name = new JTextField();
    		//this.go = new JButton();
    		
    		//this.player1name = new JTextField();
    		//player1name.setBounds(10, 11, 96, 20);
    		//add(player1name);
    		//player1name.setColumns(10);
    		//----------------------------------------
    		
    		this.blocks = new JButton[3][3];
    		this.reset = new JButton("Reset");
    		this.scores = new JButton("Scores");
    		scores.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				
    				PlayerScoresInfo playerscore = new PlayerScoresInfo();
    				playerscore.setVisible(true);
    			}
    		});
    		this.playerturn = new JTextArea();
    		// call the initialize method to set up the layout and initialize buttons
    		initialize();
    }
    
    // function to add action listeners to buttons
    public void setActionListener(Controller c) {
    		// adapter needs reference of controller and view class
		this.adapter = new Adapter(c,this);
		for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	        		blocks[row][column].addActionListener(adapter);
	        }
		}
	    reset.addActionListener(adapter);
    }
    
    // function to initialize layout and buttons
    public void initialize () {
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gui.setSize(new Dimension(500, 350));
	    gui.setResizable(true);
	    
	    JPanel gamePanel = new JPanel(new FlowLayout());
	    JPanel game = new JPanel(new GridLayout(3,3));
	    gamePanel.add(game, BorderLayout.CENTER);
	    JPanel options = new JPanel(new FlowLayout());
	    options.add(reset);
	    options.add(scores);
	    
	    //-----------------------------------------------------
	    //options.add(player1name);
	    //options.add(player2name);
	    //-----------------------------------------------------
	    JPanel messages = new JPanel(new FlowLayout());
	    messages.setBackground(Color.white);
	    gui.add(gamePanel, BorderLayout.NORTH);
	    gui.add(options, BorderLayout.CENTER);
	    gui.add(messages, BorderLayout.SOUTH);
	    messages.add(playerturn);
	    playerturn.setText("Player 1 to play 'X'");
	    
	    for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	            blocks[row][column] = new JButton();
	            blocks[row][column].setPreferredSize(new Dimension(75,75));
	            blocks[row][column].setText("");
	            game.add(blocks[row][column]);
	            
		    }
		}
	    
	    // make the gui visible as the final step
	    gui.setVisible(true);

    }
    
    // function to check if the action event was generated because of reset key
    public boolean isReset(ActionEvent e) {
    		if(e.getSource() == reset)
    			return true;
    		return false;
    }
    
    // function to check if the action event was generated from the scores button
    public boolean isScore(ActionEvent e) {
		if(e.getSource() == scores)
			return true;
		return false;
}
    
    // function to find the x,y-coordinates of the pressed button
    public ArrayList<Integer> getPosition(ActionEvent e) {
    	ArrayList<Integer> position = new ArrayList<Integer>();
    	for(int row = 0; row<3 ;row++) {
	        for(int column = 0; column<3 ;column++) {
	        		if(e.getSource() == blocks[row][column]) {
	        			position.add(row);
	        			position.add(column);
	        		}
	        }
    		}
    		return position;
    }
    
    // function to update the view with the correct mark and message
    public void update(int row, int column, char symbol, String message) {
    		blocks[row][column].setText(Character.toString(symbol));
    		blocks[row][column].setEnabled(false);
    		playerturn.setText(message);
    	
    }
    
    // function to freeze the view if there is a winner or game is tied
    public void isWinner(int row, int column, char symbol, String message) {
		blocks[row][column].setText(Character.toString(symbol));
		blocks[row][column].setEnabled(false);
		for(int i = 0; i<3 ;i++) {
	        for(int j = 0; j<3 ;j++) {
	        	blocks[i][j].setEnabled(false);
	        }
		}
		playerturn.setText(message);

    }
    
    // function to clear the view and reset it for a new game
    public void resetGame() {
    	for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                blocks[row][column].setText("");
                blocks[row][column].setEnabled(true);
            }
        }
        playerturn.setText("Player 1 to play 'X'");
    }
    
    // mock getter function for checking the value of a button on the grid
    public String getButtonText(int i, int j) {
    		return blocks[i][j].getText();
    }

}
