package com.linkknown.oop_game_tank;

import java.awt.*;
import java.util.List;

public class Missile {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	int x, y;
	Tank.Direction dir;
	private boolean good;
	
	private boolean Live = true;
	private TankClient tc;
	
	public boolean isLive() {
		return Live;
	}

	public Missile(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public Missile(int x, int y, boolean good,Tank.Direction dir, TankClient tc) {
		this(x, y, dir);
		this.good = good;
		this.tc = tc;
	}
	
	public void draw (Graphics g) {
		if(!Live) {
			tc.missiles.remove(this);
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x+20, y+20, 10, 10);
		g.setColor(c);
		
		move();
	}

	private void move() {
		switch(dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
			Live = false;
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean hitTank(Tank t) {
		if(this.Live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
			if(t.isGood()) {
				t.setLife(t.getLife()-20);
				if(t.getLife() <= 0) t.setLive(false);
			} else {
				t.setLive(false);
			}
			this.Live = false;
			Explode e = new Explode(x,y, tc);
			tc.explodes.add(e);
			return true;
		}
			return false;
	}
	public boolean hitTanks(List<Tank> tanks) {
		for(int i=0; i<tanks.size(); i++) {
			if(hitTank(tanks.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hitWall(Wall w) {
		if(this.Live && this.getRect().intersects(w.getRect())) {
			this.Live = false;
			return true;
		}
		return false;
	}
}
