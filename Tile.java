/** 
 * EndGame.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * class represents blueprint for all different tiles 
 */
import javax.swing.*;
import java.awt.*;

abstract class Tile {
  private int gridX;
  private int gridY;
  private boolean walkable;
  Rectangle boundingBox;
  /**
   * Constructor.
   * 
   * @param x, integer of the x coordinate of the character
   * @param y, integer of the y coordinate of the character
   * @param boxWidth, integer of the width of the tile
   * @param boxHeight, integer of the height of the tile
   */
  Tile(int x, int y, boolean walkable, int boxWidth, int boxHeight) {
    this.gridX = x;
    this.gridY = y;
    this.walkable = walkable;
    boundingBox = new Rectangle(this.gridX*World.boxWidth, this.gridY*World.boxHeight, boxWidth, boxHeight);
  }
  /**
   * getGridX
   * Gets the grid x coordinate
   * @return int gridX, gets the x grid coordinate of the tile
   */
  public int getGridX() {
    return this.gridX;
  }
  /**
   * getGridY
   * Gets the grid y coordinate
   * @return int gridY, gets the y grid coordinate of the tile
   */
  public int getGridY() {
    return this.gridY;
  }
  /**
   * getPixelX
   * Gets the pixel x coordinate
   * @return int, the grid x coordinate multiplied by the boxWidth gets the pixel x coordinate
   */
  public int getPixelX() {
    return this.gridX*World.boxWidth;
  }
  /**
   * getPixelY
   * Gets the pixel y coordinate
   * @return int, the grid y coordinate multiplied by the boxWidth gets the pixel y coordinate
   */
  public int getPixelY() {
    return this.gridY*World.boxHeight;
  }
  /**
   * getWalkable
   * checks if the tile is walkable
   * @return boolean, true if the tile is able to be walked on, otherwise false
   */
  public boolean getWalkable() {
    return this.walkable;
  }
  // draws tiles based on the hero's coordinates on the map
  abstract void draw(Graphics g, Hero hero);
}

