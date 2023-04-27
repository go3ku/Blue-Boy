package main;

import java.awt.Color;
import object.SuperObject;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;
import entity.Entity;

public class GamePanel extends JPanel implements Runnable{
	
	//game resolution
	final int originalTileSize = 16;
	final int scale=3;
	public final int tileSize =originalTileSize * scale;
	public final int maxScreenCol=16;
	public final int maxScreenRow=12;
	public final int screenWidth=tileSize * maxScreenCol;
	public final int screenHeight=tileSize * maxScreenRow;
	
	
	//World
	public final int maxWorldCol=50;
	public final int maxWorldRow=51;
	public final int worldWidth =tileSize*maxWorldCol;
	public final int worldHright=tileSize *maxWorldRow; 

	//FPS
	int FPS=60;
	
	
	TileManager tileM=new TileManager(this);
	KeyHandler keyH= new KeyHandler();
	Sound music=new Sound();
	Sound se=new Sound(); 
	Thread gameThread; 
	public CollisionChecker cChecker =new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this); 
	public UI ui=new UI(this);
	
	public Player player=new Player(this,keyH);
	public SuperObject obj[]=new SuperObject[10];
    public Entity npc[]=new Entity[10];

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		
	}
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval =1000000000/FPS;
		double delta =0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer=0;
		int drawCount=0;
		
		while(gameThread !=null) {
			currentTime =System.nanoTime();
			
			delta +=(currentTime - lastTime)/drawInterval;
			timer +=(currentTime-lastTime);
			lastTime=currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer>=1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount=0;
				timer=0;
			}
			
			
			}
		
	}
	public void update() {
		
		player.update();
		for(int i=0;i<npc.length;i++) {
			if(npc[i] != null) {
				npc[i].update();
			}
			
		}
		
	}
		
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2= (Graphics2D)g;
		
		
		tileM.draw(g2);
		
		for(int i=0; i<obj.length; i++) {
			if(obj[i] !=null) {
				obj[i].draw(g2, this);
			}
		}
		
		for(int i=0;i<npc.length;i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		
		player.draw(g2);
		
		ui.draw(g2);
		
		g2.dispose();
			
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
		
		
	}

}
