/** 
 * Hero.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * hero class that extends character. this is the playable character
 */
import javax.swing.*;
import java.awt.*;

class Hero extends Character {
  private String direction;
  private int currentSprite;
  private boolean directionSet = false;
  private Image[] character = new Image[9];
  private int speedMultiplier;
  long lastTimeCheck; //store the time of the last time the time was recorded
  long deltaTime; //to keep the elapsed time between current time and last time
  long currentTime; // the current time used to get delta time
  /**
   * Constructor.
   * 
   * @param x, integer of x coordinate of the hero
   * @param y, integer of y coordinate of the hero
   * @param width, integer of the width of the hero
   * @param length, integer of the length of the hero
   * @param health, integer of the health of the hero
   */
  Hero(double x, double y, int width, int length, int health, int speedMultiplier) {
    super(x, y, width, length, health);
    character[0] = (Toolkit.getDefaultToolkit().getImage("CharacterRightWalk1.png"));
    character[1] = (Toolkit.getDefaultToolkit().getImage("CharacterRightWalk2.png"));
    character[2] = (Toolkit.getDefaultToolkit().getImage("CharacterLeftWalk1.png"));
    character[3] = (Toolkit.getDefaultToolkit().getImage("CharacterLeftWalk2.png"));
    character[4] = (Toolkit.getDefaultToolkit().getImage("CharacterBackWalk2.png"));
    character[5] = (Toolkit.getDefaultToolkit().getImage("CharacterBackWalk3.png"));
    character[6] = (Toolkit.getDefaultToolkit().getImage("CharacterRightStep.png"));
    character[7] = (Toolkit.getDefaultToolkit().getImage("CharacterLeftStep.png"));
    character[8] = (Toolkit.getDefaultToolkit().getImage("CharacterStanding.png"));
    this.speedMultiplier = 2;
  }
  /**
   * draw
   * draws the hero sprites depending on the direction
   * draws hero's health bar 
   */
  public void draw(Graphics g) { 
    if(this.getHealth() <=0) {
      return;
    }
    setImage();
    g.drawImage(character[currentSprite], 512, 512, getWidth(), getLength(), null);
    // use getGridX()*World.boxWidth and getGridY()*World.boxWidth to get its current coordinate on the grid
    // and then subtract it from the actual pixel coordinate to get smoother movement
    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    g.setColor(Color.cyan);
    g.fillRect(520, 500, (int)(Math.round(getHealth()/4)), 5);
  }
  /**
   * update
   * updates the position of the hero (pixelX and pixelY)
   * makes sure the hero is in bounds
   * @param clock, gets the elapsedTime to change the position based on elapsed time
   * @param Tile[][] world, a 2D dimensional Tile array containing the tile map
   */
  public void update(Clock clock, Tile[][] world) {
    //boxWidth*world.length is equal to the total size of the map converted to pixels
    if ((getPixelX() + getXShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier > 0) 
          && (getPixelX() + getXShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier) + getWidth() < World.boxWidth*world.length ) {
      // multiply by (World.boxWidth*world.length)/1024) to make it so you move the same number of blocks regardless of the visible height(will only work if the window is 1024x1024
      setBoundingBoxX((getPixelX() + getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01*speedMultiplier));
      setPixelX((getPixelX() + getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01*speedMultiplier));
      if (ifWalkable(world) == false) {
        setBoundingBoxX((getPixelX() - getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01*speedMultiplier));
        setPixelX((getPixelX() - getXShift()*clock.getElapsedTime()*((World.boxWidth))*.01*speedMultiplier));
      }
    }if ((getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier > 0) 
           && (getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier + getLength() < World.boxWidth*world.length)) {
      // multiply by (World.boxWidth*world.length)/1024) to make it so you move the same number of blocks regardless of the visible height
      setBoundingBoxY((getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier));
      setPixelY((getPixelY() + getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier));
      if (ifWalkable(world) == false) {
        setBoundingBoxY((getPixelY() - getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier));
        setPixelY((getPixelY() - getYShift()*clock.getElapsedTime()*(World.boxWidth)*.01*speedMultiplier));
      } 
    }
  }
  /**
   * getWidth
   * This method gets the width of hero 
   * @return int getWidth, the width of hero
   */
  public int getWidth(int width) {
    return getWidth();
  }
  /**
   * getLength
   * This method gets the length of hero 
   * @return int getWidth, the length of hero
   */
  public int getLength(int length) {
    return getLength();
  }
  /**
   * setDirection
   * This method determines what sprite to display depending on the direction
   * @param String direction, the direction of the hero
   */
  public void setDirection(String direction) {
    if (direction!= this.direction){  
      this.direction = direction;
      if (direction != null) {
        if (this.direction.equals("right")) {
          currentSprite = 0;
        }else if(this.direction.equals("left")) {
          currentSprite = 2;
        }else if(this.direction.equals("up")) {
          currentSprite = 6;
        }else if(this.direction.equals("down")) {
          currentSprite = 8;
        }
      }
    }
  }
  /**
   * getDirection
   * This method gets the direction of the hero
   * @return int diretion, the direction of the hero
   */
  public String getDirection() {
    return this.direction;
  }
  public void ifAttacked(Enemy enemy){
    if (getBoundingBox().intersects(enemy.getBoundingBox())) {
      this.loseHealth(1);
    }
  }
  public boolean ifCollided(Entity entity) {
    boolean result = false;
    if (getBoundingBox().intersects(entity.getBoundingBox())) {
      result = true;
    }
    return result;
  }
  
/**
* setImage
* sets the current sprite based on direction
*/  
  public void setImage(){
    if (this.direction == null) {
      currentSprite = 8;
    }else if (this.direction.equals("right")) {
      currentSprite++;
      if (currentSprite >= 2){
        currentSprite = 0;
      }
    }else if(this.direction.equals("left")) {
      currentSprite++; 
      if (currentSprite >= 4){
        currentSprite = 2;
      }
    }else if(this.direction.equals("up")) {
      currentSprite++;
      if (currentSprite >= 6){
        currentSprite = 4;
      }
    }else if(this.direction.equals("down")) {
      currentSprite++;
      if (currentSprite >= 8) {
        currentSprite = 6;
      }
    }
  }
/**
* setSpeedMultiplier
* sets the speed multiplier
* @param speedMultiplier sets to given number
*/  
  public void setSpeedMultiplier(int speedMultiplier) {
    this.speedMultiplier = speedMultiplier; // set the speed multiplier
    this.deltaTime = 0; // reset delta time incase they already have a speed boost
    this.lastTimeCheck = System.currentTimeMillis(); // set the initial time checked
  }
/**
* checkIfTimeUp
* checks if the time is up for the speed boost
* @param speedDuration for maximum amount of time speedboost can be used for
*/ 
  public void checkIfTimeUp(int speedDuration){
    if (this.speedMultiplier > 1){ // only run if they currently have a speed boost
      this.currentTime = System.currentTimeMillis();
      this.deltaTime += currentTime - lastTimeCheck; //add to the elapsed time
      this.lastTimeCheck = currentTime; //update the last time var
      if (this.deltaTime >= speedDuration){ // if the time has run out
        deltaTime = 0; // reset delta time
        setSpeedMultiplier(2); // reset speed multiplier
      }
    }
  }
}








