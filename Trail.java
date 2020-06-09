/** 
 * EndGame.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * Trail class displays trail on the map 
 */
import javax.swing.*;
import java.awt.*;
 
class Trail extends Tile {
  private Image trail = Toolkit.getDefaultToolkit().getImage("trail.png");
  /**
   * Constructor.
   * 
   * @param gridX, integer of the tile grid x coordinate of the tile
   * @param gridY, integer of the tile grid y coordinate coordinate of the tile
   * @param boxWidth, integer of the width of the tile
   * @param boxHeight, integer of the height of the tile
   */
  Trail(int gridX, int gridY, int boxWidth, int boxHeight) {
    super(gridX, gridY, true, boxWidth, boxHeight);
  }
  /**
   * draw
   * draws image of the trail tile according to the txt map file
   * @param g, draw the trail tile
   * @param hero, draws the trail tile if visibile on the screen based on the hero coordinates
   */

  public void draw(Graphics g, Hero hero) {
   g.drawImage(this.trail,(int)(getPixelX() - (hero.getPixelX())) + 512,
              (int)(getPixelY() - (hero.getPixelY())) + 512, World.boxWidth, World.boxHeight, null);

  }
}