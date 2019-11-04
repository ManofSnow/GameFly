package cn.xy.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

public class MyGameFrame extends Frame{
	Image planeImage = GameUtil.GetImage("Images/plane.png");
	Image bgImg = GameUtil.GetImage("Images/bg.jpg"); 
	
	Plane plane = new Plane(planeImage,250,250,5);
	ArrayList<Shell>  shellList = new ArrayList<Shell>(); 
	
	Explode bao;
	Date startTime = new Date();
	Date endTime;
	int period; //持续时间
	
	@Override
	public void paint(Graphics g) {  
        g.drawImage(bgImg, 0, 0, null);
        plane.drawSelf(g);    //画出飞机本身
        //画出容器中所有的子弹
        for(int i=0;i<shellList.size();i++){
            Shell b =  shellList.get(i);
            b.draw(g);
             
            //飞机和所有炮弹对象进行矩形检测
            boolean peng = b.getRect().intersects(plane.getRect());
            if(peng){
                plane.live = false;   //飞机死掉,画面不显示
                endTime = new Date();
                if(bao==null){
                    bao = new Explode(plane.x,plane.y);
                }
                bao.draw(g);
            }
        }
         
        if(!plane.live){
            if(endTime==null){
                endTime = new Date();
            }
            int period = (int)((endTime.getTime()-startTime.getTime())/1000);
            printInfo(g, "时间："+period+"秒", 50, 120, 260, Color.white);
        }
    }
	
	 public void printInfo(Graphics g,String str,int size,int x,int y,Color color){
	        Color c = g.getColor();
	        g.setColor(color);
	        Font f = new Font("宋体",Font.BOLD,size);
	        g.setFont(f);
	        g.drawString(str,x,y);
	        g.setColor(c);
	    } 
	
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
				
				try {
					Thread.sleep(40);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			plane.minusDirection(e);
		}
		
	}
	
	public void launchFrame() {
		this.setTitle("xy");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
		this.addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		});
		
		for(int i=0;i<50;i++){
	        Shell b = new Shell();
	        shellList.add(b);
	    }
		
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
	}
	
	public static void main(String[] args) {
		MyGameFrame frame = new MyGameFrame();
		frame.launchFrame();
		
	}
	
	//双缓冲
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	} 
}
