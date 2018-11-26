package controller;

/** The Controller class is responsible for requesting the model
 * to update its state whenever there is an event on a button on 
 * the game board.
 * */

import java.util.ArrayList;

import model.*;
public class Controller {
	private Model m;
	
	// initializing the reference of model class
	public void setModel(Model m) {
		this.m = m;
	}
	
	// function to request the model to update the board
	public void setRequest(ArrayList<Integer> position) {
		m.PlayMove(position.get(0), position.get(1));
	}
	
	// overloaded function to request model to reset
	public void setRequest() {
		m.ResetModel();
	}
	
}