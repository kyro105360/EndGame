/** 
 * Flower.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * flower class displays flower tile on the map 
 */
import javax.swing.*;
import java.awt.*;
class Flower extends Tile {
  private Image flower = Toolkit.getDefaultToolkit().getImage("Flower.png");
  /**
   * Constructor.
   * 
   * @param gridX, integer of the tile grid x coordinate of the tile
   * @param gridY, integer of the tile grid y coordinate coordinate of the tile
   * @param boxWidth, integer of the width of the tile
   * @param boxHeight, integer of the height of the tile
   */

  Flower(int gridX, int gridY, int boxWidth, int boxHeight) {
    super(gridX,gridY, true, boxWidth, boxHeight);
  }
  /**
   * draw
   * draws image of the flower tile according to the txt map file
   * @param g, draw the flower tile
   * @param hero, draws the flower tile if visibile on the screen based on the hero coordinates
   */
  public void draw(Graphics g, Hero hero) {
    g.drawImage(this.flower,(int)(getPixelX() - (hero.getPixelX())) + 512,
              (int)(getPixelY() - (hero.getPixelY())) + 512, World.boxWidth, World.boxHeight, null);
  }
}