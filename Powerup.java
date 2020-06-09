/** 
 * Powerup.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * abstract powerup class to represent all powerups
 */
import javax.swing.*;
import java.awt.*;

abstract class Powerup extends Entity{
  
  public Powerup(double x, double y, int width, int length) {
    super(x, y, width, length);
  }
}