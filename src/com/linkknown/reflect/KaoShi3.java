package com.linkknown.reflect;

/**
 * 模拟考试行为
 * 
 * @author Administrator
 *
 */
public class KaoShi3 implements KaoshiInterface {

	private String name;

	public KaoShi3(String name) {
		super();
		this.name = name;
	}

	public void giveTestPaper() {
		System.out.println("发试卷啦~");
	}

	public void writeTestPaper() {
		System.out.println("写试卷啦~");
	}

	public void submitTestPaper() {
		System.out.println("收试卷啦~");
	}

	public void start() {
		this.giveTestPaper();
		this.writeTestPaper();
		this.submitTestPaper();
	}
	
	public static void start(KaoshiInterface kaoshi) {
		// 使用动态代理类,而不是代理前的类
		kaoshi.giveTestPaper();
		kaoshi.writeTestPaper();
		kaoshi.submitTestPaper();
	}
	
}
