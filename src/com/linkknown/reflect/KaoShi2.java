package com.linkknown.reflect;

/**
 * Ä£Äâ¿¼ÊÔĞĞÎª
 * 
 * @author Administrator
 *
 */
public class KaoShi2 implements KaoshiInterface {

	private String name;

	public KaoShi2(String name) {
		super();
		this.name = name;
	}

	public void giveTestPaper() {
		System.out.println("·¢ÊÔ¾íÀ²~");
	}

	public void writeTestPaper() {
		System.out.println("Ğ´ÊÔ¾íÀ²~");
	}

	public void submitTestPaper() {
		System.out.println("ÊÕÊÔ¾íÀ²~");
	}

	public void start() {
		this.giveTestPaper();
		this.writeTestPaper();
		this.submitTestPaper();
	}
}
