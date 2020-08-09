package snake;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/***********************************************
Name: An Nguyen
Course:      CSC 143
Quarter:     Fall 2018
Description: This class is to create Food object
************************************************/

public class Food{
    
    /************************************
     * State: data fields
     *        encapsulation of data
     * **********************************/
    private Rectangle food;
    
    /***************************************
     * Constructor: Food (Snake snake)
     *                 create Rectangle food
     * *************************************/
    public Food (Snake snake) {
        
        Random random = new Random();
        double x = random.nextInt (75); // x-max = 600, 600/8=75
        double y = random.nextInt (56); // y-max = 402, 402/8=50
        
        food = new Rectangle (x*8, y*8, 8, 8);
        
        boolean snakeContainsFood = snake.checkFoodCollision(food, false);//check if snake contains food
        
        while (snakeContainsFood) {//if true, change food's position
            x = random.nextInt (75);
            y = random.nextInt (56);
            
            createFoodLocation(x*8, y*8);
            
            snakeContainsFood = snake.checkFoodCollision(food, false);//check if snake contains food

        }
    }
    
    /*********************************************
     * Method: checkSnakeCollision(Snake snake)
     *             check if snake collides food
     *             if true, change food's position
     *********************************************/
    public void checkSnakeCollision(Snake snake) {//x, y is head of the snake
        
        boolean snakeContainsFood = snake.checkFoodCollision(food, false);
        
        Random random = new Random();
        
        double x, y;//x and y for Point of food
        
        while (snakeContainsFood) {
            x = random.nextInt(75);
            y = random.nextInt(56);
            
            createFoodLocation(x*8, y*8);
            
            snakeContainsFood = snake.checkFoodCollision(food, false);
        }
    }
    
    /*********************************************
     * Method: createFoodLocation (double x, double y)
     *             change food's location
     *********************************************/
    public void createFoodLocation (double x, double y) {
        food.setX(x);
        food.setY(y);
    }
    

    /***************************************************
     * Method: draw(Color color)
     *             WHITE for food, RED for bad food
     *                change food's Point into Rectangle
     *             return the Rectangle
     ****************************************************/
    public Rectangle draw(Color color) {//might change to return food
        
        food.setFill(color);
        food.setFocusTraversable(true);
        return food;
    }
    
    /***************************************
     * Accessor: getFood ()
     *             return food as Rectangle
     **************************************/
    public Rectangle getFood () {
        return food;
    }
    
}



