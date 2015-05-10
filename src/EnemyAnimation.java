import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EnemyAnimation implements ActionListener {
			//timer that animates the enemies
			public void actionPerformed(ActionEvent e) {
				//checks for collision
				MovingComponent.checkForEnemyCollision();
				
				for(EnemyCharacter enemy : GamePanel.enemyList){
					if(enemy.type==0){
						if(enemy.side==0){
							//if the enemy's x position is greater thanthe width of the frame, treat him as if he were on that side of the frame
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=2;
								enemy.setLocation(enemy.body.getX()-2, enemy.body.getY());
							}else{
								enemy.setLocation(enemy.body.getX()+2, enemy.body.getY());
							}
						}// end of enemy.side=0
						else if(enemy.side==1){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=3;
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()-2);
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()+GamePanel.enemySpeed+2);
							}
						}//end of enemy.side ==1
						else if(enemy.side==2){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=0;
								enemy.setLocation(enemy.body.getX()+2, enemy.body.getY());
							}else{
								enemy.setLocation(enemy.body.getX()-GamePanel.enemySpeed+2, enemy.body.getY());
							}
						}
						else if(enemy.side==3){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=1;
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()+2);
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()-GamePanel.enemySpeed+2);
							}
						}
						
					}// end of if enemy == 0 
					else if(enemy.type==1){
						if(enemy.side==0){
							//if the enemy's x position is greater thanthe width of the frame, treat him as if he were on that side of the frame
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=2;
								enemy.setLocation(enemy.body.getX()-2, enemy.body.getY());
							}else{
								enemy.setLocation(enemy.body.getX()+GamePanel.enemySpeed, enemy.body.getY());
							}
						}// end of enemy.side=0
						else if(enemy.side==1){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=3;
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()-2);
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()+GamePanel.enemySpeed);
							}
						}//end of enemy.side ==1
						else if(enemy.side==2){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=0;
								enemy.setLocation(enemy.body.getX()+2, enemy.body.getY());
							}else{
								enemy.setLocation(enemy.body.getX()-GamePanel.enemySpeed, enemy.body.getY());
							}
						}
						else if(enemy.side==3){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=1;
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()+2);
							}else{
								enemy.setLocation(enemy.body.getX(), enemy.body.getY()-GamePanel.enemySpeed);
							}
						}
					}else if(enemy.type==2){
						if(enemy.side==0){
							//if the enemy's x position is greater thanthe width of the frame, treat him as if he were on that side of the frame
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=3;
								enemy.setLocation(enemy.body.getX()-2, enemy.body.getY()-2);
							}else{
								//int diff=GameFrame.FRAME_WIDTH+2-enemy.body.getX()
								enemy.setLocation(enemy.body.getX()+GamePanel.enemySpeed, enemy.body.getX()+ Math.sqrt(enemy.body.getX()+15+GamePanel.enemySpeed));
							}
						}// end of enemy.side=0
						else if(enemy.side==1){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=3;
								enemy.setLocation(enemy.body.getX()-2, enemy.body.getY()-2);
							}else{
								enemy.setLocation(enemy.body.getY()+Math.sqrt(enemy.body.getY()+5+GamePanel.enemySpeed), enemy.body.getY()+GamePanel.enemySpeed );
								//enemy.setLocation(enemy.body.getX()+Math.cos((double)(enemy.body.getY()+10)), enemy.body.getY()+2);
							}
						}//end of enemy.side ==1
						else if(enemy.side==2){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=0;
								enemy.setLocation(enemy.body.getX()+2, enemy.body.getY()+2);
							}else{
								enemy.setLocation(enemy.body.getX()-GamePanel.enemySpeed, enemy.body.getX()+Math.sqrt(enemy.body.getX()-5-GamePanel.enemySpeed));
							}
						}
						else if(enemy.side==3){
							if(enemy.xPos>GameFrame.FRAME_WIDTH+2||enemy.yPos>GameFrame.FRAME_HEIGHT+2||enemy.xPos<0||enemy.yPos<0){
								enemy.side=1;
								enemy.setLocation(enemy.body.getX()+2, enemy.body.getY()+2);
							}else{
								enemy.setLocation(enemy.body.getY()+Math.sqrt(enemy.body.getY()-5-GamePanel.enemySpeed), enemy.body.getY()-GamePanel.enemySpeed );
							}
						}
					}
					
				}//end of enemy character animation
				//checks if an enemy is hit by a bullet
				int enemySize=GamePanel.enemyList.size();
				int bulletSize=GamePanel.bullets.size();
				for(int i =0; i<enemySize;i++){
					for(int j=0; j<bulletSize;j++){
						try{
						if(GamePanel.enemyList.get(i).isHit(GamePanel.bullets.get(j))){
							if(GamePanel.enemyList.get(i).type==0)
								GamePanel.score+=3;
							else if(GamePanel.enemyList.get(i).type==1)
								GamePanel.score+=4;
							else if(GamePanel.enemyList.get(i).type==2)
								GamePanel.score+=5;
							GamePanel.enemyList.remove(i);
							enemySize=GamePanel.enemyList.size();
							break;
						}
						}catch (Exception l){
							GamePanel.enemyList.remove(i);
						}
					}
				}
				
			}

		}