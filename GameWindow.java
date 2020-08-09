package snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**********************************************************************
Name: An Nguyen
Course:      CSC 143
Quarter:     Fall 2018
Description: This class is a general window for displaying graphical
             components.This format can be used for all window
             displays.
************************************************************************/

public class GameWindow extends Application{
    
    /************************************
     * Constant:  WIDTH  = 600
     *            HEIGHT = 600
     * **********************************/
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    
    
    /*****************************************************************
     * override: start
     *           overrides the start method in the Application class
     * ***************************************************************/
    @Override
    public void start(Stage stage) {
        
        //create a Controller to hold graphics
        Controller controller = new Controller(stage);
            
        /**********************************************************
         * required:  Scene
         *               create a scene object to hold the controller object
         *               and place it in the stage for display
         * ********************************************************/
        Scene scene = new Scene(controller, WIDTH, HEIGHT, Color.BLACK);
        scene.setOnKeyPressed(e->controller.control(e));
        scene.setOnKeyReleased(e->controller.control(e));        
            
        stage.setTitle("Snake");            
        stage.setScene(scene);                    
        stage.show();
        
    }
    
    /********************************************************************
     * main method: launch application
     *              needed to launch JavaFX program when running in IDE
     * ******************************************************************/
    public static void main(String[] args) {
        launch(args);
    }
    
}






