package snake;

/***********************************************************************************
Name: An Nguyen
Course:      CSC 143
Quarter:     Fall 2018
Description: This class is to an enum Direction class to set direction of the snake
************************************************************************************/

public enum Direction {
	
	/*********************************************
	 * Enumerated constants: UP, DOWN, LEFT, RIGHT
	 ********************************************/
	UP (0, -1),
	DOWN (0, 1),
	LEFT (-1, 0),
	RIGHT (1, 0);
	
	/**********************
	 * State: data field	
	 *********************/
	private final double x;
	private final double y;
	
	/********************************************
	 * Constructor: Direction(double x, double y)	
	 * 			set data to data fields
	 ********************************************/
	Direction(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/*******************
	 * Accessor: getX()
	 * 		   return x
	 ******************/
	public double getX() {
		return x;
	}
	
	/*******************
	 * Accessor: getY()
	 * 		   return y
	 ******************/
	
	public double getY() {
		return y;
	}
}
