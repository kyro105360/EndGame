/** 
 * Clock.java
 * Version Final
 * @author Kyrollous Nassif
 * @6/13/2019
 * Clock checks the elapsed time during the program
 * gets the last time checked and calculates the current time
 */
class Clock {
  long elapsedTime;
  long lastTimeCheck;

  public Clock() { 
    lastTimeCheck=System.nanoTime();
    elapsedTime=0;
  }
  
  public void update() {
  long currentTime = System.nanoTime();  //if the computer is fast you need more precision
  elapsedTime=currentTime - lastTimeCheck;
  lastTimeCheck=currentTime;
  }
  
  //return elapsed time in milliseconds
  public double getElapsedTime() {
 
   return elapsedTime/1.0E9;

 }
}