package entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		solidArea = new Rectangle(8,16,32,32);
		solidArea.x=8;
		solidArea.y=16;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		
		direction="down";
		speed=1;
		getPlayerImage();
	
	}
	public void getPlayerImage() {
		try {
			up1=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	public void setAction() {
		
		actionLockCounter ++;
		
		if(actionLockCounter==120) {
			Random random =new Random();
			int i =random.nextInt(101);
			
			if(i<=25) {
				direction="up";
			}
			if(i>25 && i<=50) {
				direction="down";
			}
			if(i>50 && i<=75) {
				direction="left";
			}
			if(i>75) {
				direction="right";
			
		    }
			actionLockCounter=0;
		
		}
	}

}
