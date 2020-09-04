package com.linkknown.oop_game_tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class Tank {
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	private int x, y;
	private int oldX, oldY;
	
	private static Random r = new Random();
	
	private boolean live = true;
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	private boolean good;
	
	public boolean isGood() {
		return good;
	}

	private int life = 100;
	private BloodBar bb = new BloodBar();
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	TankClient tc;
	
	private boolean bL = false, bU = false, bR = false, bD = false;
	enum Direction {L, LU, U, RU, R, RD, D, LD, STOP};
	
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	static {
		tankImages = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankL.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankLU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankRU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankR.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankRD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images_tank/tankLD.gif"))
		};
		
		imgs.put("L", tankImages[0]);
		imgs.put("LU", tankImages[1]);
		imgs.put("U", tankImages[2]);
		imgs.put("RU", tankImages[3]);
		imgs.put("R", tankImages[4]);
		imgs.put("RD", tankImages[5]);
		imgs.put("D", tankImages[6]);
		imgs.put("LD", tankImages[7]);
		
	}
	
	private int step = r.nextInt(12) + 3;
	
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}
	
	public Tank(int x, int y, boolean good, Direction dir,TankClient tc) {
		this(x, y, good);
		this.tc = tc;
		this.dir = dir;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			if(!good) {
				tc.tanks.remove(this);
			}
			return;
		}
		if(good) bb.draw(g);
		
		switch (ptDir) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"), x, y, null);
			break;
		}
		move();
	}
	
	void move() {
		this.oldX = x;
		this.oldY = y;
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
		
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		
		if (x < 0) x = 0;
		if (y < 30) y = 30;
		if (y + Tank.HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
		if (x + Tank.WIDTH > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.WIDTH;
		
		if (!good) {
			Direction[] dirs = Direction.values();
			if (step == 0) {
				step = r.nextInt(12) + 3;
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
			}
		}
		step--;
		if (r.nextInt(40) > 38) {
			this.fire();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			bR = true;
		}
		else if(key == KeyEvent.VK_LEFT) {
			bL = true;
		}
		else if(key == KeyEvent.VK_UP) {
			bU = true;
		}
		else if(key == KeyEvent.VK_DOWN) {
			bD = true;
		}
		else if(key == KeyEvent.VK_CONTROL) {
			tc.missiles.add(fire());
		}
		else if(key == KeyEvent.VK_A) {
			superFire();
		}
		else if(key == KeyEvent.VK_F2) {
			if(!this.live) {
				this.live = true;
				this.life = 100;
			}
		}
		locateDirection();
	}
	
	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.L;
		else if(bL && bU && !bR && !bD) dir = Direction.LU;
		else if(!bL && bU && !bR && !bD) dir = Direction.U;
		else if(!bL && bU && bR && !bD) dir = Direction.RU;
		else if(!bL && !bU && bR && !bD) dir = Direction.R;
		else if(!bL && !bU && bR && bD) dir = Direction.RD;
		else if(!bL && !bU && !bR && bD) dir = Direction.D;
		else if(bL && !bU && !bR && bD) dir = Direction.LD;
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}
	
	public void stay() {
		x = oldX;
		y = oldY;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			bR = false;
		}
		else if(key == KeyEvent.VK_LEFT) {
			bL = false;
		}
		else if(key == KeyEvent.VK_UP) {
			bU = false;
		}
		else if(key == KeyEvent.VK_DOWN) {
			bD = false;
		}else if(key == KeyEvent.VK_CONTROL) {
			fire();
		}
		locateDirection();
	}
	
	public Missile fire() {
		if(!live) return null;
		int x = this.x + WIDTH/2 - HEIGHT/2;
		int y = this.y + WIDTH/2 - HEIGHT/2;
		Missile m = new Missile(x, y, good, ptDir, this.tc);
		tc.missiles.add(m);
		return m;
	}
	
	public Missile fire(Direction dir) {
		if(!live) return null;
		int x = this.x + WIDTH/2 - HEIGHT/2;
		int y = this.y + WIDTH/2 - HEIGHT/2;
		Missile m = new Missile(x, y, good, dir, this.tc);
		tc.missiles.add(m);
		return m;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	public boolean collidesWithWall(Wall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}
	
	public boolean collidesWithTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.stay();
					t.stay();
					return true;
				}
			}
		}
		return false;
	}
	
	private void superFire() {
		Direction[] dirs = Direction.values();
		for (int i = 0; i < 8; i++) {
			fire(dirs[i]);
		}
	} 
	
	private class BloodBar{
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y - 10, WIDTH, 10);
			int w = WIDTH * life /100;
			g.fillRect(x, y-10, w, 10);
			g.setColor(c);
		}
	}
	
	public boolean eat(Blood b) {
		if(this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			this.life = 100;
			return true;
		}
		return false;
	}
}
