/** 
 * EndGame.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * Tree class displays tree on the map 
 */
import javax.swing.*;
import java.awt.*;

class Tree extends Tile {
  private Image tree = Toolkit.getDefaultToolkit().getImage("tree.png"); 
  /**
   * Constructor.
   * 
   * @param gridX, integer of the tile grid x coordinate of the tile
   * @param gridY, integer of the tile grid y coordinate coordinate of the tile
   * @param boxWidth, integer of the width of the tile
   * @param boxHeight, integer of the height of the tile
   */
  Tree(int gridX, int gridY, int boxWidth, int boxHeight) {
    super(gridX,gridY, false, boxWidth, boxHeight);
  }
  /**
   * draw
   * draws image of the tree tile according to the txt map file
   * @param g, draw the tree tile
   * @param hero, draws the tree tile if visibile on the screen according to the hero coordinates
   */      
  public void draw(Graphics g, Hero hero) {
    g.drawImage(this.tree,(int)(getPixelX() - (hero.getPixelX())) + 512,
              (int)(getPixelY() - (hero.getPixelY())) + 512, World.boxWidth, World.boxHeight, null);
  }
}