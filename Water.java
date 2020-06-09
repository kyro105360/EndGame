/** 
 * EndGame.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * Water class displays water on the map 
 */
import javax.swing.*;
import java.awt.*;

class Water extends Tile {
  private Image water = Toolkit.getDefaultToolkit().getImage("Water.png");
  /**
   * Constructor.
   * 
   * @param gridX, integer of the tile grid x coordinate of the tile
   * @param gridY, integer of the tile grid y coordinate coordinate of the tile
   * @param boxWidth, integer of the width of the tile
   * @param boxHeight, integer of the height of the tile
   */
  Water(int gridX, int gridY, int boxWidth, int boxHeight) {
    super(gridX, gridY, false, boxWidth, boxHeight);

  }
  /**
   * draw
   * draws image of the water tile according to the txt map file
   * @param g, draw the water tile
   * @param hero, draws the water tile if visible on the screen based on the hero coordinates
   */ 
  public void draw(Graphics g, Hero hero) {
   g.drawImage(this.water,(int)(getPixelX() - (hero.getPixelX())) + 512,
              (int)(getPixelY() - (hero.getPixelY())) + 512, World.boxWidth, World.boxHeight, null);
  }
}