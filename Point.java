package snake;

/***************************************************
Name: An Nguyen
Course:      CSC 143
Quarter:     Fall 2018
Description: This class is to create a Point object
**************************************************/

public class Point {

    /*******************
     * State: data field	
     *******************/
    private double x;
    private double y;
    
    
    /****************************************
     * Constructor: Point(double x, double y)	
     *          set data to data fields
     ***************************************/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /*******************
     * Accessor: getX()
     *          return x
     ******************/
    public double getX() {
        return x;
    }
    
    /*****************************
     * Mutator: setX()
     * 		   set new value to x
     ****************************/
    public void setX(double x) {
        this.x = x;
    }
    
    /*******************
     * Accessor: getY()
     *          return y
     *******************/
    public double getY() {
        return y;
    }
    
    /***************************
     * Mutator: setY()
     *         set new value to y
     ***************************/
    public void setY(double y) {
        this.y = y;
    }
}
