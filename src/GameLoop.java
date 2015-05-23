import javax.swing.JPanel;


public class GameLoop {
	JPanel currGame;
	boolean gameRunning;
	//The last time the loop recorded a time
	long lastLoopTime = System.nanoTime();
	//The target FPS
	final int TARGET_FPS = 60;
	final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	//The current fps
	int fps=0;
	//the last fps time
	int lastFpsTime;
	
	//Constructor that takes in a panel object (this is so we can use the same game loop class for all games
	public GameLoop(JPanel currGame){
		this.currGame=currGame;
	}
	
	public void startGameLoop(){
		if(currGame!=null){
			while(gameRunning){
				//calculate the time since the last update 
				long now=System.nanoTime();
				long updateLength=now-lastLoopTime;
				lastLoopTime=now;
				double changeInTime=updateLength/((double)OPTIMAL_TIME);
				
				//update the frame count
				lastFpsTime += updateLength;
			    fps++;
			    //update if the last time is greater than 1 second
			    if(lastFpsTime>=1000000000){
			    	System.out.println("(FPS: "+fps+")");
			        lastFpsTime = 0;
			        fps = 0;
			    }
			    
			    
			}
		}
	}
	/**Sets the gameRunning variable to T or F. This starts or stops the game loop
	 * @param x
	 */
	public void setRunning(boolean x){
		gameRunning=x;
	}
}
