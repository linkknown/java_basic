package com.linkknown.oop_game_tank;

import java.awt.*;

public class Explode {
	int x, y;
	private boolean live = true;
	private TankClient tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image[] imgs = {
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/0.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/1.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/2.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/3.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/4.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/5.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/6.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/7.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/8.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/9.gif")),
		tk.getImage(Explode.class.getClassLoader().getResource("images_tank/10.gif"))
	};
	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	//int[] diameter = {4,7,12,18,26,32,49,30,14,6};
	int step = 0;
	
	public void draw(Graphics g) {
		if(!live) {
			tc.explodes.remove(this);
			return;
		}
		if(step == imgs.length) {
			live = false;
			step = 0;
			return;
		}
		
		g.drawImage(imgs[step], x, y, null);
		step++;
	}
}
