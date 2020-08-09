package snake;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/****************************************************
Name: An Nguyen
Course:      CSC 143
Quarter:     Fall 2018
Description: This class is to create a Snake object
*****************************************************/

public class Snake{
    
    /************************************
     * State: data fields
     *        encapsulation of data
     * **********************************/
    private ArrayList <Rectangle> snakeBody;//index 0 is tail, max is head
    private Direction direction;
    private boolean isMoving;
    private boolean elongate;
    private int score;
    
    
    /***********************************************************************
     * Constructor: Snake(Direction direction, boolean isMoving)
     *                 set values to data fields
     *                 create the snakeBody with 20 squares
     * *********************************************************************/
    public Snake(Direction direction, boolean isMoving) {
        
        this.direction = direction;
        this.isMoving = isMoving;
        snakeBody = new ArrayList <Rectangle> ();
        
        for (int i = 0; i < 20; i++) {//create snake with 20 squares
            Point pt = new Point (35 + direction.getX()*i, 35 + direction.getY()*i);
            Rectangle rec = new Rectangle (pt.getX()*6, pt.getY()*6, 6, 6);//each square's size is 6
            rec.setFill(Color.WHITE);
            rec.setStroke(Color.BLACK);
            rec.setFocusTraversable(true);
            snakeBody.add(rec);
        }
        elongate = false;
    }
    
    /***********************************************************************
     * method: checkFoodCollision (Rectangle food, boolean elongate)
     *             check if the snake collides food
     * *********************************************************************/
    public boolean checkFoodCollision (Rectangle food, boolean elongate) {
        
        double x, y;//x and y Rectangle of snake
        
        for (int i = 0; i < snakeBody.size(); i++) {
            x = snakeBody.get(i).getX();
            y = snakeBody.get(i).getY();
            
            if     (x > food.getX()-8 && x < food.getX()+8 &&
                    y > food.getY()-8 && y < food.getY()+8) {
                return true;
            }
        }
        return false;
    }
    
    /***********************************************************************
     * method: checkSelfCollision()
     *             check if snake hits itself
     *             if true, set game in Controller false (game over)
     * *********************************************************************/
    public void checkSelfCollision() {
        Rectangle head = snakeBody.get(snakeBody.size()-1);
        
      //run the whole list because needing to check for making new food
        for (int i = 0; i < snakeBody.size()-2; i++) {
            if (head.getX()==snakeBody.get(i).getX() && head.getY()==snakeBody.get(i).getY()) {
                Controller.setGame(false);;
            }
        }
    }
    
    /********************************************************
     * Method: draw()
     *             return snakeBody as ArrayList <Rectangle>
     ********************************************************/
    public ArrayList <Rectangle> draw() {
        return snakeBody;
    }
    
    /******************************************
     * Accessor: getDirection ()
     *            return direction as Direction
     *****************************************/
    public Direction getDirection() {
        return direction;
    }
    
    /*********************************
     * Accessor: getScore()
     *            return score as int
     ********************************/
    public int getScore () {
        return score;
    }
    
    /*******************************************
     * Method: getX()
     *            return x-value of snake's head
     *******************************************/
    public double getX() {//X-value of snake's head
        return snakeBody.get(snakeBody.size()-1).getX();
    }
    
    /*******************************************
     * Method: getY()
     *            return y-value of snake's head
     ******************************************/
    public double getY() {//Y-value of snake's head
        return snakeBody.get(snakeBody.size()-1).getY();
    }
    

    /*********************************************************
     * Method: grow()
     *             increase score by 1
     *             increase snake's length by adding new tail
     *             return new tail as Rectangle
     ********************************************************/
    public Rectangle grow() { //might return newTail
        
        score++;//increase score
        
        Rectangle tail = snakeBody.get(0);//tail is at index 0
        
        Rectangle newTail = new Rectangle (tail.getX()-direction.getX()*6, 
                                    tail.getY()-direction.getY()*6, 6, 6);
        newTail.setFill(Color.WHITE);
        newTail.setStroke(Color.BLACK);
        newTail.setFocusTraversable(true);
        
        snakeBody.add(0, newTail);
        
        return newTail;
    }
    
    /****************************************
     * Accessor: isMoving()
     *            return isMoving as boolean
     ***************************************/
    public boolean isMoving() {
        return isMoving;
    }
    
    /**********************************************************
     * Method: make()
     *             make the snake at the original position
     *             set direction to RIGHT and isMoving to true
     *********************************************************/
    public void make () {
        
        this.direction = Direction.RIGHT;
        this.isMoving = false;
        
        score = 0;
        
        snakeBody.clear();//delete old snake
        
        for (int i = 0; i < 20; i++) {//create new snake (length = 20)
            Point pt = new Point (35 + direction.getX()*i, 35 + direction.getY()*i);
            Rectangle rec = new Rectangle (pt.getX()*6, pt.getY()*6, 6, 6);
            rec.setFill(Color.WHITE);
            rec.setStroke(Color.BLACK);
            rec.setFocusTraversable(true);
            snakeBody.add(rec);
        }
    }
    
    /***********************************************
     * Method: move()
     *             move the snake to the direction
     ***********************************************/
    public void move() {

        Rectangle head = snakeBody.get(snakeBody.size()-1);      
        
        if (isMoving) {
            Rectangle newHead = new Rectangle (head.getX() + direction.getX()*6, head.getY() + direction.getY()*6, 6, 6);
            
            for (int i = 0; i < snakeBody.size()-1; i++) {
                snakeBody.get(i).setX(snakeBody.get(i+1).getX());
                snakeBody.get(i).setY(snakeBody.get(i+1).getY());
            }
            
            snakeBody.get(snakeBody.size()-1).setX(newHead.getX());
            snakeBody.get(snakeBody.size()-1).setY(newHead.getY());
        }
    }
    
    /*********************************************
     * Mutator: setDirection (Direction direction)
     *            set new value to direction
     ********************************************/
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    /****************************************
     * Mutator: setMoving (boolean isMoving)
     *            set new value to isMoving
     ***************************************/
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    /***********************************************************
     * Method: shrink()
     *             decrease score by 1
     *             decrease snake's length by deleting the tail
     *             set the color of tail to Black
     **********************************************************/
    public void shrink() {
        score--;//decrease score
        
        if (snakeBody.size() <= 2) {
            //minimum size is 2 squares
            //if smaller, stop the game
            isMoving = false;
            Controller.setGame(false);
            return;
        }
        
        //remove the tail
        snakeBody.get(0).setFill(Color.BLACK);
        snakeBody.remove(0); 
    }
}



