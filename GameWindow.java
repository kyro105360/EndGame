/** 
 * GameWindow.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * frame class to add the game panel and display the game
 */
import javax.swing.*;
import java.awt.*;

class GameWindow extends JFrame { 
  GamePanel gamePanel = new GamePanel();
  //Window constructor
  public GameWindow() { 
   setTitle("Simple Game Loop Example");
   setSize(GamePanel.gameWidth,GamePanel.gameHeight);  // set the size of my window to 1024 by 1024 pixels
   setResizable(true);  // set my window to allow the user to resize it
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed
   getContentPane().add(gamePanel);
   pack(); //makes the frame fit the contents
   setVisible(true);
  }
/** 
 * refresh
 * refreshes the gamePanel
 */
  public void refresh(){
    gamePanel.refresh();
  }
}