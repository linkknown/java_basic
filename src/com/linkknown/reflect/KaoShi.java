package com.linkknown.reflect;

/**
 * Ä£Äâ¿¼ÊÔĞĞÎª
 * 
 * @author Administrator
 *
 */
public class KaoShi {

	private String name;

	public KaoShi(String name) {
		super();
		this.name = name;
	}

	public void giveTestPaper() {
		System.out.println(name + " before giveTestPaper");

		System.out.println("·¢ÊÔ¾íÀ²~");

		System.out.println(name + " after giveTestPaper");
	}

	public void writeTestPaper() {
		System.out.println(name + " before writeTestPaper");

		System.out.println("Ğ´ÊÔ¾íÀ²~");

		System.out.println(name + " after writeTestPaper");
	}

	public void submitTestPaper() {
		System.out.println(name + " before submitTestPaper");

		System.out.println("ÊÕÊÔ¾íÀ²~");

		System.out.println(name + " after submitTestPaper");
	}

	public void start() {
		this.giveTestPaper();
		this.writeTestPaper();
		this.submitTestPaper();
	}
}
