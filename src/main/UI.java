package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Key;
import java.text.DecimalFormat;
public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public  boolean messageOn=false;
	public String message="";
	int messageCounter = 0;
	public boolean gameFinished=false;
	double playTime;
	DecimalFormat dFormat=new DecimalFormat("#0.00");
	
	
	
	public UI(GamePanel gp) {
		this.gp=gp;
		arial_40 =new Font("Arial",Font.PLAIN,40);
		arial_80B =new Font("Arial",Font.BOLD,40);
		OBJ_Key key=new OBJ_Key();
		keyImage=key.image;
	}
	
	public void showMessages(String text) {
		message=text;
		messageOn=true;
	}
	
	public void draw(Graphics2D g2) {
		
		
		
		if(gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			String text;
			int textLenght;
			int x;
			int y;
			
			text="You found the treasure";
			textLenght=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			
			x=gp.screenWidth/2 - textLenght/2;
			y=gp.screenHeight/2 - (gp.tileSize *3);
			g2.drawString(text, x, y);
			
			text="Your Time is :"+dFormat.format(playTime);
			textLenght=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			
			x=gp.screenWidth/2 - textLenght/2;
			y=gp.screenHeight/2 + (gp.tileSize *4);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text="Congratulations! Try Again !";
			textLenght=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x=gp.screenWidth/2 - textLenght/2;
			y=gp.screenHeight/2 + (gp.tileSize *2);
			g2.drawString(text, x, y);
			
			gp.gameThread=null;
			
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2,gp.tileSize/2 ,gp.tileSize,gp.tileSize,null);
			g2.drawString("x "+gp.player.hasKey, 70, 60);
			
			playTime +=(double)1/60;
			g2.drawString("Time:"+ dFormat.format(playTime),  gp.tileSize *11,65 );
			if(messageOn==true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				messageCounter++;
				if(messageCounter>120) {
					messageCounter=0;
					messageOn=false;
				}
			}
			
		}

	}

}
