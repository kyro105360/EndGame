/** 
 * Grass.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * Grass class displays grass tile on the map 
 */
import javax.swing.*;
import java.awt.*;

class Grass extends Tile {
  private Image grass = Toolkit.getDefaultToolkit().getImage("Grass.png");
  static Color backgroundColor = new Color(34, 139, 34);
  /**
   * Constructor.
   * 
   * @param gridX, integer of the tile grid x coordinate of the tile
   * @param gridY, integer of the tile grid y coordinate coordinate of the tile
   * @param boxWidth, integer of the width of the tile
   * @param boxHeight, integer of the height of the tile
   */
  Grass(int gridX, int gridY, int boxWidth, int boxHeight) {
    super(gridX, gridY, true, boxWidth, boxHeight);
  }
  /**
   * draw
   * draws image of the grass tile according to the txt map file
   * @param g, draw the grass tile
   * @param hero, draws the grass tile if visibile on the screen based on the hero coordinates
   */
  public void draw(Graphics g, Hero hero) {
    g.drawImage(this.grass, (int)(getPixelX() - (hero.getPixelX())) + 512,
              (int)(getPixelY() - (hero.getPixelY())) + 512, World.boxWidth, World.boxHeight, null);
  }
}