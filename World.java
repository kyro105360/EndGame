import javax.swing.*;
import java.awt.*;
/** 
 * EndGame.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * Draws the tileMap from map.txt
 * each box is a coordinate on a grid, and their size is based on the visible height
 */
class World{
  Tile[][] world;
  static int visibleHeight = 16;
  // the number of boxes to be displayed on the screen (will always be a square)
  static int boxWidth = 1024/visibleHeight; // to calculate how big each box will be on the map
  static int boxHeight = 1024/visibleHeight; // to calculate how big each box will be on the map
  
  /**
  * Constructor.
  * 
  * @param w[][], 2d int array that stores the ints of map.txt
  * creates new tiles according to the map.txt
  */
  World(int w[][]) {
    world = new Tile[w.length][w[0].length];  
    for (int i = 0; i < w.length; i++) {
      for (int j = 0; j < w[0].length; j++) {
        if (w[i][j] == 0){
          world[i][j] = new Grass(j, i, boxWidth, boxHeight);
        }else if (w[i][j] == 1){
          world[i][j] = new Tree(j, i, boxWidth, boxHeight);
        }else if(w[i][j] == 2){
          world[i][j] = new Trail(j, i, boxWidth, boxHeight);
        }else if(w[i][j] == 3){
          world[i][j] = new Water(j, i, boxWidth, boxHeight);
        }else if(w[i][j] == 4){
          world[i][j] = new Flower(j, i, boxWidth, boxHeight);
        }
      }
    }
  }
  /**
   * getWorld
   * This method returns the map to tileMap 
   * @return int yShift, the vertical speed of the character
   */
  public Tile[][] getWorld() {
    return this.world;
  } 
  /**
   * ifEmpty
   * checks the coordinate of world map and sees if it is empty
   * @param gridX, the tile x coordinate
   * @param gridY, the tile y coordinate
   */
  public boolean ifEmpty(int gridX, int gridY){
    if (world[gridY][gridX] != null && !world[gridY][gridX].getWalkable()){
    return true;
  }else{
    return false;
  }
}
  // return the length of the tile map
  /**
   * getLength
   * returns the length of the world
   * @return int this.world.length, the length of the world
   */
  public int getLength(){
    return this.world.length;
  }
  /**
   * drawWorld
   * draws the world with the tiles
   * @param g, draw the enemy sprites
   * @param hero, draws the visible map based on hero's coordinates
   */
  public void drawWorld(Graphics g, Hero hero) {
    if(hero.getHealth() > 0){
      for (int i = 0; i < visibleHeight + 1; i++) {
        for (int j = 0; j < visibleHeight + 1; j++) {
          g.setColor(Color.black);
          g.fillRect(j*boxWidth - (int)(hero.getPixelX() - (hero.getGridX()*boxWidth)),
                     i*boxHeight - (int)(hero.getPixelY() - (hero.getGridY()*boxWidth)), boxWidth, boxHeight);
          //using visible height is not required with method of drawing but gives better fps
          if ((hero.getGridY()-visibleHeight/2+i) < world.length && (hero.getGridX()-visibleHeight/2+j) >= 0      // check if the desired location is within bounds of the array
                && (hero.getGridY()-visibleHeight/2+i) >= 0 && (hero.getGridX()-visibleHeight/2+j) < world.length){
            if (world[hero.getGridY()-visibleHeight/2+i][hero.getGridX()-visibleHeight/2+j] != null){          
              world[hero.getGridY()-visibleHeight/2+i][hero.getGridX()-visibleHeight/2+j].draw(g,hero);  // draw the area around the player
            }
          }
        }
      }
    }
  }
}
