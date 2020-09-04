package com.linkknown.oop_game_saolei.tools;

import java.util.TreeSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.linkknown.oop_game_saolei.bean.HeroBean;

public class StaticTool {

	public static int allcount = 10;
	public static int allcol = 9;
	public static int allrow = 9;
	public static int timecount = 0;
	public static int bombCount = allcount;

	public static boolean isStart = false;
	public static boolean isHole = false;

	public static Icon[] num = new Icon[9];
	public static Icon[] time = new Icon[11];

	public static ImageIcon imageIcon = getImageIcon("images_saolei/icon.gif");
	public static Icon iconBlank = getImageIcon("images_saolei/blank.gif");
	public static Icon bloodIcon = getImageIcon("images_saolei/blood.gif");
	public static Icon icon0 = getImageIcon("images_saolei/0.gif");
	public static Icon clickIcon = getImageIcon("images_saolei/face2.gif");
	public static Icon smileIcon = getImageIcon("images_saolei/face0.gif");
	public static Icon faultFaceIcon = getImageIcon("images_saolei/face3.gif");
	public static Icon winFaceIcon = getImageIcon("images_saolei/face4.gif");
	public static Icon flagIcon = getImageIcon("images_saolei/flag.gif");
	public static Icon askIcon = getImageIcon("images_saolei/ask.gif");
	public static Icon askPressIcon = getImageIcon("images_saolei/ask1.gif");
	public static Icon downSmileIcon = getImageIcon("images_saolei/face1.gif");
	public static Icon errorBombIcon = getImageIcon("images_saolei/error.gif");
	public static Icon blackBombIcon = getImageIcon("images_saolei/mine.gif");
	public static Icon holeIcon = getImageIcon("images_saolei/hole.gif");

	static {

		for (int i = 0; i < num.length; i++) {
			num[i] = getImageIcon("images_saolei/" + i + ".gif");
		}
		for (int j = 0; j <= num.length; j++) {
			time[j] = getImageIcon("images_saolei/d" + j + ".gif");
		}

	}
	
	public static ImageIcon getImageIcon (String path) {
		return new ImageIcon(StaticTool.class.getClassLoader().getResource(path));
	}

	public static TreeSet<HeroBean> treeSetC = new TreeSet<HeroBean>();
	public static TreeSet<HeroBean> treeSetZ = new TreeSet<HeroBean>();
	public static TreeSet<HeroBean> treeSetG = new TreeSet<HeroBean>();
	static {

		treeSetC.add(new HeroBean(999, "ÄäÃû"));
		treeSetZ.add(new HeroBean(999, "ÄäÃû"));
		treeSetG.add(new HeroBean(999, "ÄäÃû"));

	}

	public static int getLevel() {
		if (allrow == 9 && allcol == 9 && allcount == 10) {
			return 1;
		} else if (allrow == 16 && allcol == 16 && allcount == 40) {
			return 2;
		} else if (allrow == 16 && allcol == 30 && allcount == 99) {
			return 3;
		} else {
			return 0;
		}
	}

}
