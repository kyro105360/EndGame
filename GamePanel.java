/** 
 * GamePanel.java
 * Version Final
 * @author Kyrollous Nassif + Tony Lu
 * @6/13/2019
 * framerate class to see how many times the screen is being refreshed
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.*;
import java.awt.event.MouseEvent;
import java.util.Scanner;

class GamePanel extends JPanel implements KeyListener, MouseListener{//, ActionListener{
  static int gameHeight; // frame and panel height
  static int gameWidth;  // frame and panel width
  static int heroHealth; // variable hero health
  static int enemyHealth; // variable enemy health
  static int enemyKillCount; // kill score
  int speedBoostDuration; // duration of speed boost
  long lastTimeCheck; //store the time of the last time the time was recorded
  long deltaTime; //to keep the elapsed time between current time and last time
  long currentTime; // the current time used to get delta time
  //boolean values to check which screen to draw
  boolean menuScreen; 
  boolean gameScreen;
  boolean gameOverScreen;
  boolean buttonPressed;
  int randomX;
  int randomY;
  private FrameRate frameRate;
  private Clock clock;
  private char tempMapNum;
  private int buttonState;
  private Image menuBackground = Toolkit.getDefaultToolkit().getImage("MenuBackground.png");
  ArrayList<Bullet> bullets; // list for bullets
  ArrayList<SpeedBoost> speedBoosts; // list for speedboosts
  World world; //world
  Hero hero; // hero
  Enemy[] enemies; // array of enemies
  int textMap[][];  // text map to be read from text file
  File file;
  Scanner mapReader;
  /**
   * Constructor.
   * 
   * creates the screen
   * adds key and mouse listener
   * 
   */
  GamePanel() {     
    enemyKillCount = 0;
    gameHeight = 1024;
    gameWidth = 1024;
    menuScreen = true;
    try{
      file = new File("map.txt");
      mapReader = new Scanner(file);  
    }catch (FileNotFoundException e){
      System.out.println("No file found! data corrupted");
    }
    lastTimeCheck = System.currentTimeMillis();
    setPreferredSize(new Dimension(gameWidth,gameHeight));
    frameRate = new FrameRate();
    clock = new Clock();
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(this);
    addMouseListener(this);
    textMap = new int[64][64];
    for(int i = 0; i<textMap[0].length;i=i+1){ 
      for(int j = 0; j<textMap.length;j=j+1){
        tempMapNum = mapReader.next().charAt(0);
        if (tempMapNum == '1'){
          textMap[i][j] = 1;
        }else if(tempMapNum == '2'){
          textMap[i][j] = 2;
        }else if(tempMapNum == '3'){
          textMap[i][j] = 3;
        }else if(tempMapNum == '4'){
          textMap[i][j] = 4;
        }else if(tempMapNum == '5'){
          textMap[i][j] = 5;
        }else{
          textMap[i][j] = 0;
        }
      }
    }
    buttonState = 0x0;
    enemies = new Enemy[20];
    mapReader.close();
    world = new World(textMap);
    //player is drawn in the centre of the screen. the rest  map is drawn according to the player's coordinates.
    
  }
  /**
   * mouseClicked
   * checks if user clicked a button
   * @param e to get mouse event
   */   
  public void mouseClicked(MouseEvent e) {
    int mouseX = e.getX();
    int mouseY = e.getY();
    buttonPressed = false;
    if(menuScreen){
      // if they click novice
      if((mouseX >= 300 && mouseX <= 724) && (mouseY >= 480 && mouseY <= 560)){
        heroHealth = 200;
        enemyHealth = 50;
        speedBoostDuration = 5000;
        buttonPressed = true;
        // if they click skilled
      }else if((mouseX >= 300 && mouseX <= 724) && (mouseY >= 590 && mouseY <= 670)){
        heroHealth = 100;
        enemyHealth = 100;
        speedBoostDuration = 4000;
        buttonPressed = true;
        // if they click advanced
      }else if ((mouseX >= 300 && mouseX <= 724) && (mouseY >= 700 && mouseY <= 780)){
        heroHealth = 50;
        enemyHealth = 150;
        speedBoostDuration = 3000;
        buttonPressed = true;
      }else if((mouseX >= 300 && mouseX <= 724) && (mouseY >= 810 && mouseY <= 890)){
        System.exit(0);
        // to check if they clicked a button, and switch the screen
      }if (buttonPressed){
        this.menuScreen = false;
        this.gameScreen = true;
        bullets = new ArrayList<Bullet>();
        speedBoosts = new ArrayList<SpeedBoost>();
        buttonState = 0x0;
        for (int i = 0; i < enemies.length; i++){
          // checks if coordinate is null
          do{
            randomX = (int)(Math.random()*world.getLength())*World.boxWidth;
            randomY = (int)(Math.random()*world.getLength())*World.boxHeight;   
          }while(world.ifEmpty(randomX/World.boxWidth, randomY/World.boxHeight));
          enemies[i] = new Enemy(randomX, randomY, World.boxWidth, World.boxHeight, enemyHealth);
        }
        hero = new Hero(32*World.boxWidth, 32*World.boxWidth, World.boxWidth, World.boxHeight, heroHealth, 1);
      }
    }else if(gameOverScreen){
      gameOverScreen = false;
      menuScreen = true;
      buttonPressed = true;
    }
  }
  public void mousePressed(MouseEvent e) { 
  }
  public void mouseReleased(MouseEvent e) {
  }
  public void mouseEntered(MouseEvent e) {
  }
  public void mouseExited(MouseEvent e) {
  }
  
  public void keyTyped(KeyEvent e) {      
  }
  
  /**
   * keyPressed
   * checks if user clicked a key and switches the button state
   * @param e to get key event
   */   
  public void keyPressed(KeyEvent e) {
    if (gameScreen){
      switch (e.getKeyChar()) {
        case 'a':
          buttonState = buttonState | 0x1; // OR with binary value of 0001
          break;
        case 's':
          buttonState = buttonState | 0x2; // OR with binary value of 0010
          break;
        case 'd':
          buttonState = buttonState | 0x4; // OR with binary value of 0100
          break;
        case 'w':
          buttonState = buttonState | 0x8; // OR with binary value of 1000
          break;
        default:
          // Do nothing
      }
      if (e.getKeyChar() == 'k') {
        Bullet tempBullet = new Bullet(hero.getPixelX() + hero.getWidth()/2, hero.getPixelY() + hero.getLength()/2, hero.getDirection());
        bullets.add(tempBullet);
      }
      updateHeroDirectionAndShift();
    }
  }
  /**
   * keyReleased
   * checks if user released a key and switches the button state
   * @param e to get key event
   */   
  public void keyReleased(KeyEvent e) {
    if (gameScreen){
      switch (e.getKeyChar()) {
        case 'a':
          buttonState = buttonState & 0xE; // AND with binary value of 1110
          break;
        case 's':
          buttonState = buttonState & 0xD; // AND with binary value of 1101
          break;
        case 'd':
          buttonState = buttonState & 0xB; // AND with binary value of 1011
          break;
        case 'w':
          buttonState = buttonState & 0x7; // AND with binary value of 0111
          break;
        default:
          // Do nothing
      }
      updateHeroDirectionAndShift();
    }
  }
  /**
   * updateHeroDirectionAndShift
   * switches the direction of the character based on the button state
   */ 
  private void updateHeroDirectionAndShift() {
    switch (buttonState) {
      case 0x1:
        hero.setXShift(-300);
        hero.setYShift(0);
        hero.setDirection("left");
        break;
      case 0x2:
        hero.setXShift(0);
        hero.setYShift(300);
        hero.setDirection("down");
        break;
      case 0x4:
        hero.setXShift(300);
        hero.setYShift(0);
        hero.setDirection("right");
        break;
      case 0x8:
        hero.setXShift(0);
        hero.setYShift(-300);
        hero.setDirection("up");
        break;
      case 0x3:
        hero.setXShift(-200);
        hero.setYShift(200);
        hero.setDirection("left");
        break;
      case 0x6:
        hero.setXShift(200);
        hero.setYShift(200);
        hero.setDirection("right");
        break;
      case 0xC:
        hero.setXShift(200);
        hero.setYShift(-200);
        hero.setDirection("right");
        break;
      case 0x9:
        hero.setXShift(-200);
        hero.setYShift(-200);
        hero.setDirection("left");
        break;
      default: 
        hero.setXShift(0);
        hero.setYShift(0);
        hero.setDirection(null);
    }
  }
  /**
   * drawGameOver
   * draws end game screen
   * @param g to draw on specific graphic
   */ 
  public void drawGameOver(Graphics g){
    g.setColor(Color.black);
    g.fillRect(0,0,1024,1024);
    g.setColor(Color.white);
    g.setFont(new Font("Dialog", Font.BOLD, 80)); 
    g.drawString("GAME OVER", 280, 512);
    g.setFont(new Font("Dialog", Font.BOLD, 30));
    g.drawString("Your score was " + enemyKillCount,360,612);
    g.drawString("Click anywhere to continue",335,812);
  }
  /**
   * drawGameMenu
   * draws menu screen
   * @param g to draw on specific graphic
   */ 
  public void drawGameMenu(Graphics g){  
    enemyKillCount = 0;
    g.setColor(Color.black);
    g.drawImage(menuBackground,0,0,1024,1024,null);
    g.setColor(Color.red);
    g.setFont(new Font("Dialog", Font.BOLD, 120)); 
    g.drawString("END", 360, 312);
    g.setColor(Color.blue);
    g.drawString("GAME", 360, 412);
    g.setColor(Color.white);
    g.fillRect(300, 480, 424, 80);
    g.fillRect(300, 590, 424, 80);
    g.fillRect(300, 700, 424, 80);
    g.fillRect(300, 810, 424, 80);
    g.setColor(Color.black);
    g.setFont(new Font("Dialog", Font.PLAIN, 30));
    g.drawString("Novice", 470, 530);
    g.drawString("Skilled", 460, 640);
    g.drawString("Advanced", 450, 750);
    g.drawString("Exit", 490, 860);
  }
  /**
   * updateEnemies
   * updates enemies coordinates or makes new enemies if the slot is empty
   * @param g to draw on specific graphic
   */   
  public void updateEnemies(Graphics g){
    for (int i = 0; i < enemies.length; i++) {
      if (enemies[i] == null){
        // check if coordinate is null
        do{
          randomX = (int)(Math.random()*world.getLength())*World.boxWidth;
          randomY = (int)(Math.random()*world.getLength())*World.boxHeight;   
        }while(world.ifEmpty(randomX/World.boxWidth, randomY/World.boxHeight));
        enemies[i] = new Enemy(randomX, randomY, World.boxWidth, World.boxHeight, enemyHealth);
      }else{ 
        enemies[i].setDirection(hero);
        enemies[i].update(clock, world.getWorld(), enemies);         
        enemies[i].draw(hero, g);         
      }
    }
  }
  /**
   * updateBullets
   * moves bullets and removes if out of bounds or if it hits an enemy
   * @param g to draw on specific graphic
   */   
  public void updateBullets(Graphics g){
    for (int i = 0; i < bullets.size(); i++) {
      bullets.get(i).update(clock);
      bullets.get(i).draw(g, hero);
      if (bullets.get(i).isOutOfBounds(World.boxWidth*world.getWorld().length)){
        bullets.remove(i);
        i--;
      }else if (bullets.get(i).hitsEnemy(enemies)) {
        bullets.remove(i);
        i--;
      } 
    }  
  }
  /**
   * updateSpeedBoosts
   * checks if hero collides with speed boost and gives speed boost if it does
   * @param g to draw on specific graphic
   */ 
  public void updateSpeedBoosts(Graphics g){
    for (int i = 0; i <speedBoosts.size(); i++){
      speedBoosts.get(i).draw(g, hero);
      if (hero.ifCollided(speedBoosts.get(i))){
        speedBoosts.remove(i);
        hero.setSpeedMultiplier(3);
        i--;
      }
    } 
  }
  /**
   * addSpeedBoost
   * adds a speed boost if 5 seconds have passed
   */ 
  public void addSpeedBoost(){
    currentTime = System.currentTimeMillis();
    deltaTime += currentTime - lastTimeCheck; //add to the elapsed time
    lastTimeCheck = currentTime; //update the last time var
    if (deltaTime >= 5000){
      deltaTime = 0;
      // check if the coordinate is null
      do{
        randomX = (int)(Math.random()*world.getLength())*World.boxWidth;
        randomY = (int)(Math.random()*world.getLength())*World.boxHeight;   
      }while(world.ifEmpty(randomX/World.boxWidth, randomY/World.boxHeight));
      SpeedBoost tempSpeedBoost = new SpeedBoost(randomX, randomY, World.boxWidth, World.boxHeight);
      speedBoosts.add(tempSpeedBoost);
    }
  }
  /**
   * checkIfDead
   * checks if hero's health is diminished
   */ 
  public void checkIfDead(){
    if((hero.getHealth() <=0)){
      gameScreen = false;
      gameOverScreen = true;
    }
  }
  /**
   * addScore
   * adds score if enemy is killed
   */ 
  public void addScore(){
    for(int i = 0; i < enemies.length; i++) {
      hero.ifAttacked(enemies[i]); 
      if(enemies[i].getHealth() <= 0){
        enemies[i] = null;
        enemyKillCount++;
      }
    }   
  }
  /**
   * refresh
   * repaints the screen
   */ 
  public void refresh(){
    repaint();
  }
  /**
   * paintComponent
   * paints the entire screen
   * @param g to draw on specific graphic
   */ 
  public void paintComponent(Graphics g) {
    super.paintComponent(g); //required to ensure the panel is correctly redrawn
    if (menuScreen){
      drawGameMenu(g);
    }else if (gameScreen){
      world.drawWorld(g, hero);
      addSpeedBoost();
      clock.update();
      frameRate.update();
      updateSpeedBoosts(g);
      hero.update(clock, world.getWorld()); 
      hero.checkIfTimeUp(speedBoostDuration);
      checkIfDead();
      updateEnemies(g);
      updateBullets(g);
      frameRate.draw(g,10,10); 
      hero.draw(g);
      addScore();
    }else if (gameOverScreen){
      drawGameOver(g);
    }
  }
}
