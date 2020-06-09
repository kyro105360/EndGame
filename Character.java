/** 
 * Character.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * Character class to act as a blueprint for hero and enemy
 */

import javax.swing.*;
import java.awt.*;

abstract class Character extends Entity{
  private int xShift;
  private int yShift;
  private int health;
  /**
  * Constructor.
  * 
  * @param x, integer of the x coordinate of the character
  * @param y, integer of the y coordinate of the character
  * @param width, integer of the length of the character 
  * @param length, integer of the length of the character
  * @param health, integer of the health of the character
  */
  Character(double x, double y, int width, int length, int health) {
    super(x, y, length, width);
    this.xShift = 0;
    this.yShift = 0;
    this.health = health;
  /**
   * setXShift
   * This method sets the speed of the character when it travels horizontally
   * @param shift, the horizontal speed of the character
   */  
  }
  public void setXShift(int shift) {
    this.xShift = shift;
  }
  /**
   * setYShift
   * This method sets the speed of the character when it travels vertically
   * @param shift, the vertical speed of the character
   */
  public void setYShift(int shift) {
    this.yShift = shift;
  }
  /**
   * getXShift
   * This method gets the horizontal speed of the character 
   * @return int xShift, the horizontal speed of the character
   */
  public int getXShift() {
    return this.xShift;
  }
  /**
   * getYShift
   * This method gets the vertical speed of the character 
   * @return int yShift, the vertical speed of the character
   */
  public int getYShift() { 
    return this.yShift;    
  }
  /**
   * getHealth
   * Gets the health of the character
   * @return int health, the health of the character
   */
  public int getHealth() {
    return this.health;
  }
  /**
   * loseHealth
   * Takes away health from the character as a result of it being attacked
   * @return int health, the health is subtracted by the amount of damage taken
   */
  public void loseHealth(int amount) {
    this.health -= amount;
  }
  /**
   * setHealth
   * Sets the health of the character for different difficulty settings
   * @param health, the health of the character
   */
  public void setHealth(int health) {
    this.health = health;
  }
  /**
   * ifWalkable
   * sees if the block is walkable or not, water blocks and tree blocks are not walkable
   * @param Tile[][] world, a 2D Tile array containing the blocks of the tile map 
   * @return boolean, true if the block is walkable (trail or grass), false if not
   */
  public boolean ifWalkable(Tile[][] world) {
    boolean result = true;
    for (int i = getGridY(); i < getGridY() + 2; i++) {
      for (int j = getGridX(); j < getGridX() + 2; j++) {
        if (i < world.length && i >= 0 && j < world.length && j >= 0) {
          if (world[i][j] != null && getBoundingBox().intersects(world[i][j].boundingBox) && !world[i][j].getWalkable()) {
            result = false;          
          }
        }
      }
    }
    return result;
  }
}