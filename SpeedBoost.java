/** 
 * SpeedBoost.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * speedboost class to give user a power up of speed
 */
import javax.swing.*;
import java.awt.*;
class SpeedBoost extends Powerup{
  private Image speedBoost = Toolkit.getDefaultToolkit().getImage("speedBoost.png");
  
  SpeedBoost(double x, double y, int width, int length){
    super(x, y, width, length);
  }
  /**
   * updateSpeedBoosts
   * draws the speedboost at its respective location
   * @param g to draw on specific graphic
   */ 
  public void draw(Graphics g, Hero hero){
    g.drawImage(this.speedBoost,(int)(getPixelX() - (hero.getPixelX())) + 512,
                (int)(getPixelY() - (hero.getPixelY())) + 512, World.boxWidth, World.boxHeight, null);
  }
  
}


