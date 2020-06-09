/** 
 * EndGame
 * Version Final
 * @author Kyrollous Nasif and Tony Lu
 * @6/13/2019
 * Generates a large map and player controls hero that kills enemies by shooting with 'k' key. 
 * The enemies follow the Hero, and the hero tries to survive and kill as many enemies as possible
 * You are trying to get the highest score by killing the most amount of enemies
 * Contains novice level, skilled, and advanced where hero and enemy healths are different
 * Speed powerups boost the player's speed for a period of duration
 * Trees are obstacles in the map
 */
class EndGame { 
  public static void main(String[] args) { 
    GameWindow game = new GameWindow();
    while (true){
      game.refresh();
      try{ Thread.sleep(16); }catch(Exception e) {};

  }
}
}