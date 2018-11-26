/** This is the Driver class for the TicTacToe game.
 * It creates objects of Model, View and Controller 
 * classes and aggregates them.
 * */

import model.*;
import view.*;
import controller.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame{   
	
	//-------------------------------------------------------------
	private JPanel contentPane;
	private static JTextField player1name;
	private static JTextField player2name;
	static String A;
	static String B;
	static JButton done = new JButton("Done");
	//--------------------------------------------------------------
                                                                                 
    public static void main(String[] args) {                                   
        // Create the components     
        
    	    	
    	TicTacToe frame = new TicTacToe();
		frame.setVisible(true);
    		Controller c = new Controller();  
    		View v = new View();   
    		Model m = new Model(); 
    		
    		
    		done.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				 A = player1name.getText();
    				 B = player2name.getText();
    				 initialize(m);
    				 frame.setVisible(false);
    				
    			}
    		});
    		
    		
    		
    		
    		//----------------------------------------------------------------
    		
    		
    		
    		//----------------------------------------------------------------
    		
        
        // Use aggregation to put the components together
        m.registerView(v);
        c.setModel(m);
        v.setActionListener(c);
        
        
    }
    
    
    private static void initialize(Model m) {
		// TODO Auto-generated method stub
    	m.setNames(A, B);
		
	}
    
    
    
    
    
    public TicTacToe() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlayerName = new JLabel("Player1 Name:");
		lblPlayerName.setBounds(10, 11, 106, 14);
		contentPane.add(lblPlayerName);
		
		player1name = new JTextField();
		player1name.setBounds(126, 8, 283, 20);
		contentPane.add(player1name);
		player1name.setColumns(10);
		
		JLabel lblPlayerName_1 = new JLabel("Player2 Name:");
		lblPlayerName_1.setBounds(10, 44, 106, 14);
		contentPane.add(lblPlayerName_1);
		
		player2name = new JTextField();
		player2name.setBounds(126, 39, 283, 20);
		contentPane.add(player2name);
		player2name.setColumns(10);
		
		//JButton btnDone = new JButton("Done");
//		btnDone.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				 A = player1name.getText();
//				 B = player2name.getText();
//				 
//				
//			}
//		});
		done.setBounds(175, 85, 89, 23);
		contentPane.add(done);
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} 