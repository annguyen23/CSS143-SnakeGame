package snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/*************************************************************************************
Name: An Nguyen
Course:      CSC 143
Quarter:     Fall 2018
Description: This class is to create a Controller to control snake, food and bad food
*************************************************************************************/

public class Controller extends Pane{
    
    /************************************
     * State: data fields
     *        encapsulation of data
     * **********************************/
    private Timeline animation;
    
    private Food badFood;
    private Line boundary;
    private Food food;
    private Text gameBoard;
    private Button gameButton;
    private Text gameOver;
    private boolean paused;
    private Text rule;
    private Snake snake;
    private int speed;
    private Stage stage;
    
    /************************************
     * Constant:  game = true
     *               RULE: rule to play game
     * **********************************/
    private static boolean game = true;
    private static final String RULE =  "About Snake: " + "\n" +
                                        "To play game, use the arrow keys to control the" + "\n" +
                                        "snake and eat the green food. If snake eats red " + "\n" + 
                                        "food, it shrinks. Game ends if the snake collides  " + "\n" +
                                        "with the walls or itself or its size's smaller than" + "\n" +
                                        "2 squares.";
    
    
    /*******************************************************
     * Constructor: Controller ()
     *                 create snake, food, and bad food
     *                 create "New Game" button
     *                 create Text gameBoard, gameOver, rule
     *                 create Timeline animation
     *                 draw boundary
     * *****************************************************/
    public Controller (Stage stage) {
        
        snake = new Snake (Direction.RIGHT, false);
        food = new Food (snake);
        badFood = new Food (snake);
        this.stage = stage;
        
        draw();
        
        createGame();
        
        setStyle("-fx-background-color: black;");//set background color
        
        speed = 100;
        speedUp (speed);
    }
    
    /*****************************************************************
     * Method: animate ()
     *            check if snake can move and game can continue
     *            move snake
     *            if snake eats food, move food and increase snake's length
     *            if snake eats bad food, move bad food and decrease snake's length
     *            update score
     ****************************************************************/
    public void animate () {
        
        if (!snake.isMoving() || !game) {//check if snake can move and game can continue
            return;
        }
        
        snake.move();//move snake
        
        snake.checkSelfCollision();//check if hit itself
        
        //check if eat the food
        if (snake.checkFoodCollision(food.getFood(), true)) {
            getChildren().add(snake.grow());//increase snake's length
            speed -= 5;//set new speed
            speedUp(speed);//increase speed
        }
        
        food.checkSnakeCollision(snake);//move the food if eat
        
        //check if eat the bad food
        if (snake.checkFoodCollision(badFood.getFood(), true)) {
            snake.shrink();//decrease snake's length
        }
        
        badFood.checkSnakeCollision(snake);//move the bad food if eat
        
        setScoreBoard();//update score
    }
    
    /********************************************************************
     * Method: checkCollisions ()
     *            check if snake hits the wall
     *            if true, change Snake's isMoving to false, game to false
     ********************************************************************/
    public void checkCollisions() {
        
        //x-value of wall is 0-600. 600-6*2=588 (6 if size of each square snake)
        //x-value of wall is 0-450. 450-6*2=338 (6 if size of each square snake)
        if (snake.getX()<0 || snake.getX()>588 || snake.getY()<0 || snake.getY()>438) {
            
            snake.setMoving(false);
            game = false;
           
            setScoreBoard();//update score
        }
    }
    
    /**************************************************************
     * Method: control(KeyEvent event)
     *            change direction based on KeyEvent
     *            Key arrow can be opposite with direction of snake
     *            Space for pause and continue game
     **************************************************************/
    public void control(KeyEvent event) {
        if (!game) {
            return;
        }
        snake.setMoving(true);
        switch(event.getCode()){
            case DOWN:
                if (snake.getDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                }
                break;
            case UP:
                if (snake.getDirection() != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                }
                break;
            case RIGHT:
                if (snake.getDirection() != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                }
                break;
            case LEFT:
                if (snake.getDirection() != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                }
                break;
            case SPACE:
                paused = !paused;
                
                System.out.println(paused);
                if (paused) {
                    animation.pause();
                } else {
                    animation.play();
                }
                
                break;
            default:
        }
    }
    
    /*************************************
     * Method: createGame ()
     *            create the boundary
     *            write score
     *            write Game Over
     *               write rule
     *            create "New Game" button
     ************************************/
    public void createGame () {
        
        //create the boundary
        boundary = new Line (0,450,600, 451);
        boundary.setStroke(Color.WHITE);
        getChildren().add(boundary);
        
        //write score
        gameBoard = new Text ();
        gameBoard.setFont(new Font (25));
        gameBoard.setX(12);
        gameBoard.setY(530);
        gameBoard.setFill(Color.WHITE);
        getChildren().add(gameBoard);
        
        //write Game Over
        gameOver = new Text ();
        gameOver.setFont(new Font (50));
        gameOver.setX(200);
        gameOver.setY(230);
        gameOver.setFill(Color.RED);
        game = true;
        setScoreBoard() ;
        getChildren().add(gameOver);
        
        //write rule
        rule = new Text ();
        rule.setFont(new Font (17));
        rule.setX(130);
        rule.setY(472);
        rule.setFill(Color.WHITE);
        rule.setText(RULE);
        getChildren().add(rule);
        
        //button "New Game"
        gameButton = new Button("NEW GAME");
        gameButton.setLayoutX(503);
        gameButton.setLayoutY(510);
        gameButton.setOnAction(e->createNewGame(e));
        getChildren().add(gameButton);
        
        //set paused
        paused = false;
        
    }
    
    /***************************************************************************
     * Method: createNewGame(ActionEvent event)
     *            draw all rule, boundary, gameBoard, gameOver, gameButton again
     *            create the Stage again
     **************************************************************************/
    public void createNewGame(ActionEvent event) {
        
        getChildren().clear();
        
        draw();
        
        getChildren().addAll(rule, boundary, gameBoard, gameOver, gameButton);
        
       //close the stage and add the Controller again
        stage.close();
              
        Controller pane = new Controller(stage);
        Scene scene = new Scene(pane, GameWindow.WIDTH, GameWindow.HEIGHT, Color.BLACK);
        scene.setOnKeyPressed(e->pane.control(e));
        scene.setOnKeyReleased(e->pane.control(e));     
        
        stage.setTitle("Snake");            
        stage.setScene(scene);                  
        stage.show();
        
    }
    
    /****************************************
     * Method: draw()
     *            draw snake, food, bad food
     ***************************************/
    public void draw() {
        snake.make();
        getChildren().addAll(snake.draw());
        getChildren().add(food.draw(Color.GREEN));
        getChildren().add(badFood.draw(Color.RED));
        
    }
    
    /*****************************************************
     * Method: setGame (boolean move)
     *            to change boolean game from outside class
     *****************************************************/
    public static void setGame (boolean move) {
        game = move;
    }
    
    /*********************************************
     * Method: setScoreBoard()
     *            set score to gameBoard
     *            set game over if game == false
     ********************************************/
    public void setScoreBoard() {
        int score = snake.getScore();
        gameBoard.setText("Score: " + score);
        if (!game) {
            gameOver.setText("Game Over \n   Score: " + score);
        }
    }
    
    /*****************************************************
     * Method: speedUp(int second)
     *         update time in frame and call animation again 
     *****************************************************/
    public void speedUp(int second) {
        
        //speed increases
        KeyFrame frame =  new KeyFrame(Duration.millis(second), e-> update());
        animation = new Timeline(frame);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    /************************************************************
     * Method: update ()
     *            call 2 methods: checkCollisions() and animate()
     ************************************************************/
    public void update () {
        
        checkCollisions();//check if snake hits the wall
        
        animate();//move the snake
    }

}



