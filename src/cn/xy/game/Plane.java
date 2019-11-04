package cn.xy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	
	boolean left,up,right,down;
	boolean live = true;
	
	
	//按下键时的处理
	public void addDirection(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		default:
			break;
		}
	}
	
	//抬起键时的处理
	public void minusDirection(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}
	}
	
	@Override
	public void drawSelf(Graphics g) {
		if(live) {
			g.drawImage(img,(int)x,(int)y,null);
			if(left) {
				x -= speed;
			}
			if(right) {
				x += speed;
			}
			if(up) {
				y -= speed;
			}
			if(down) {
				y += speed;
			}
		}
	}
	

	public Plane(Image img,double x,double y,int speed) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
}
