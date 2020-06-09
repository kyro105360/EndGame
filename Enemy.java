/** 
 * Enemy.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * enemy inherits character, attacks and follows hero
 */
import javax.swing.*;
import java.awt.*;

class Enemy extends Character {
  private int damage;
  private int goldAmount;
  private Image enemy = Toolkit.getDefaultToolkit().getImage("Enemy.png");
  /**
   * Constructor.
   * 
   * @param x, integer of x coordinate of the enemy
   * @param y, integer of y coordinate of the enemy
   * @param width, integer of the width of the enemy
   * @param length, integer of the length of the enemy
   * @param health, integer of the health of the enemy
   */
  Enemy(double x, double y, int width, int length, int health) {
    super(x, y, width, length, health);
  }
  /**
   * draw
   * draws the enemy and health bar above the enemy 
   * @param hero, draws the enemy coordinates based on the hero coordinates to follow the human
   * @param g, draw the enemy sprites
   */
  public void draw(Hero hero, Graphics g) {
    if(hero.getHealth() > 0) {
     g.drawImage(this.enemy,(int)(getPixelX() - (hero.getPixelX())) + 512,
              (int)(getPixelY() - (hero.getPixelY())) + 512, World.boxWidth, World.boxHeight, null);
     g.setColor(Color.red);
     g.fillRect((int)(getPixelX() - (hero.getPixelX())) + 520, (int)(getPixelY() - (hero.getPixelY())) + 495, (int)(Math.round(this.getHealth()/4)),5);
    }
  }
  /**
   * ifTouchingEnemy
   * draws the enemy and health bar above the enemy 
   * @param hero, draws the enemy coordinates based on the hero coordinates
   * @param g, draw the enemy sprites
   */
  public boolean ifTouchingEnemy(Enemy[] enemies) {
    boolean result = false;
    for (int i = 0; i < enemies.length; i++) {
      if (enemies[i] != null && enemies[i] != this && getBoundingBox().intersects(enemies[i].getBoundingBox())) {
        result = true;
      }
    }
    return result;
  }
  /**
   * setDirection
   * sets direction based on location of hero to move towards hero
   * @param hero to get hero's location
   */ 
  public void setDirection(Hero hero){ 
    if(getPixelX() - hero.getPixelX() >= 0){
      setXShift(-175);
    }else{
      setXShift(175);
    }    
    if(getPixelY() - hero.getPixelY() > 0){
      setYShift(-175);
    }else{
      setYShift(175);
    }
  }
  /**
   * update
   * updates the position of the enemy (pixelX and pixelY)
   * makes sure enemy is in bounds
   * @param clock, gets the elapsedTime to change the position based on elapsed time
   * @param Tile[][] world, a 2D dimensional Tile array containing the tile map
   * @param enemies, a 1D dimensional Enemy array 
   * @param g, draw the enemy sprites
   */
  public void update(Clock clock, Tile[][] world, Enemy[] enemies) {
    //boxWidth*world.length is equal to the total size of the map converted to pixels
    if ((getPixelX() + getXShift()*clock.getElapsedTime()*(World.boxWidth)*.01 > 0) 
          && (getPixelX() + getXShift()*clock.getElapsedTime()*(World.boxWidth)*.01) + getWidth() < World.boxWidth*world.length ) {
      // multiply by (World.boxWidth*world.length)/1024) to make it so you move the same number of blocks regardless of the visible height(will only work if the window is 1024x1024
      setBoundingBoxX((getPixelX() + getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01));
      setPixelX((getPixelX() + getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01));
      if (!ifWalkable(world) ||  ifTouchingEnemy(enemies)){
        setBoundingBoxX((getPixelX() - getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01));
        setPixelX((getPixelX() - getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01));
      }
    }if ((getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01 > 0) 
           && (getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01 + getLength() < World.boxWidth*world.length)) {
      // multiply by (World.boxWidth*world.length)/1024) to make it so you move the same number of blocks regardless of the visible height
      setBoundingBoxY((getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01));
      setPixelY((getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01));
      if (!ifWalkable(world) || ifTouchingEnemy(enemies)){
        setBoundingBoxY((getPixelY() - getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01));
        setPixelY((getPixelY() - getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01));
      } 
    }
  }
}
  //enemies have different skills