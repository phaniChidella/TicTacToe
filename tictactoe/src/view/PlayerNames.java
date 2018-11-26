package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.*;

public class PlayerNames extends JFrame {

	private JPanel contentPane;
	private JTextField player1name;
	private JTextField player2name;
	private Model m;//----------------------------
	
	//private JFrame gui;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					PlayerNames frame = new PlayerNames();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setModel(Model m) {
		m = new Model();
		this.m = m;
	}
	
	Connection connection = null;
	/**
	 * Create the frame.
	 */
	public PlayerNames() {
		//this.gui = new JFrame("Player names");
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
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					int playerId = m.getPlayerId();
					String a = player1name.getText();
					String b = player2name.getText();
					String c;
					if (playerId %2 != 0)
						c = a;
					else
						c = b;
					
					String query = "insert into playerscores (player1name, player2name, winner) values(" +a+ " , " +b+ " , " +c+ " )";
					PreparedStatement pst1 = connection.prepareStatement(query);
					pst1.execute();
					JOptionPane.showMessageDialog(null,"Game result Saved Successfully");
					
					pst1.close();
					
					JOptionPane.showMessageDialog(null,"Database Connection Closed");
					
//					PreparedStatement pst = connection.prepareStatement(query);
//					pst.setString(1, player1name.getText());
//					pst.setString(2, player2name.getText());
//					
//					if (m.getPlayerId() == 1)
//						pst.setString(3, player1name.getText());
//					else
//						pst.setString(3, player2name.getText());
//					
//					pst.execute();
					
					//JOptionPane.showMessageDialog(null,"Game result Saved Successfully");
					
					//pst.close();
					//JOptionPane.showMessageDialog(null,"Database Connection Closed");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDone.setBounds(175, 85, 89, 23);
		contentPane.add(btnDone);
	}
}
