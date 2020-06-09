/** 
 * Entity.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * abstract class of all objects in the game
 */
import javax.swing.*;
import java.awt.*;
abstract class Entity{
  
  private double x;
  private double y;
  private int length;
  private int width;
  private Rectangle boundingBox;
  /**
   * Constructor.
   * 
   * @param x, integer of the x coordinate of the character
   * @param y, integer of the y coordinate of the character
   * @param width, integer of the length of the character 
   * @param length, integer of the length of the character
   */
  Entity(double x, double y, int width, int length) {
    this.x = x;
    this.y = y;
    this.length = length;
    this.width = width;
    boundingBox = new Rectangle((int)x, (int)y, width, length);
  }
  /**
   * getGridX
   * Gets the y coordinate number position which is the x position multiplied by boxWidth
   * @return int of the x grid position
   */
  public int getGridX() {
    return (int)this.x/World.boxWidth;
  }
  /**
   * getGridY
   * Gets the y coordinate number position which is y position multiplied by boxHeight
   * @return int of the y grid position
   */
  public int getGridY() {
    return (int)this.y/World.boxHeight;
  }
  /**
   * getPixelX
   * Gets the x pixel coordinate of the entity
   * @return int x, the x position of the entity
   */
  public double getPixelX() {
    return this.x;
  }
  /**
   * getPixelY
   * Gets the y pixel coordinate of the entity
   * @return int y, the y position of the entity
   */
  public double getPixelY() {
    return this.y;
  }
  /**
   * setPixelX
   * Sets the x pixel coordinate of the entity
   * @param x, the x coordinate position of the entity
   */
  public void setPixelX(double x) {
    this.x = x;
  }
  /**
   * setPixelY
   * Sets the y pixel coordinate of the entity
   * @param y, the y coordinate position of the entity
   */
  public void setPixelY(double y) {
    this.y = y;
  }
  /**
   * getWidth
   * Gets the width of the entity tile
   * @return int width, the width of the entity tile
   */
  public int getWidth() {
    return this.width;
  }
  /**
   * getLength
   * Gets the length of the entity tile
   * @return int length, the length of the entity tile
   */
  public int getLength() {
    return this.length;
  }
  /**
   * setBoundingBoxX
   * Sets the x bounding box of the entity
   * @param x, the bounding box x of the entity
   */
  public void setBoundingBoxX(double x) {
    boundingBox.x = (int)x;
  }
  /**
   * setBoundingBoxY
   * Sets the y bounding box of the entity
   * @param y, the y coordinate position of the entity
   */
  public void setBoundingBoxY(double y) {
    boundingBox.y = (int)y;
  }
  /**
   * getBoundingBox
   * Gets the boundingBox for collisions
   * @return boundingBox, rectangle of the coordinates for collisions
   */
  public Rectangle getBoundingBox() {
    return this.boundingBox;
  }
}
