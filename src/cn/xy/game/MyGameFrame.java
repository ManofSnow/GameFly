package cn.xy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyGameFrame extends JFrame{
	Image ball = GameUtil.GetImage("Images/ball.png");
	
	@Override
	public void paint(Graphics g) {
		g.drawLine(100, 100, 300, 300);
		
		g.drawImage(ball,250,250,null);
	}
	
	public void launchFrame() {
		this.setTitle("xy");
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocation(300, 300);
		
		this.addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		});
		
	}
	
	public static void main(String[] args) {
		MyGameFrame frame = new MyGameFrame();
		frame.launchFrame();
	}
}
