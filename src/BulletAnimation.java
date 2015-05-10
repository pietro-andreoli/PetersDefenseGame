import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BulletAnimate implements ActionListener {
			//timer that animates the bullets
			public void actionPerformed(ActionEvent e) {
				for(UserBullet b : GamePanel.bullets){
					if(b.xPos>GameFrame.FRAME_WIDTH || b.yPos>GameFrame.FRAME_HEIGHT || b.xPos<=0 || b.yPos<=0){
						//do nothing
					}else{
						if(b.mouseOrBullet=="mouse"){
							
							b.setPosition(b.xPos-1, GamePanel.user.yPos-b.trajectory*(GamePanel.user.xPos-b.xPos+1));
						}else if(b.mouseOrBullet=="bullet"){
							b.setPosition(b.xPos+1, GamePanel.user.yPos-b.trajectory*(GamePanel.user.xPos-b.xPos+1));
							
						}
					}
					
					
				}
				for(int i =0; i<GamePanel.bullets.size();i++){
					if(GamePanel.bullets.get(i).xPos>=GameFrame.FRAME_WIDTH||GamePanel.bullets.get(i).yPos>=GameFrame.FRAME_HEIGHT||GamePanel.bullets.get(i).xPos<=0||GamePanel.bullets.get(i).yPos<=0){
						GamePanel.bullets.remove(i);
						break;
					}
				}
			}
		}