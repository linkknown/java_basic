package com.linkknown.reflect;

/**
 * ģ�⿼����Ϊ
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
		System.out.println("���Ծ���~");
	}

	public void writeTestPaper() {
		System.out.println("д�Ծ���~");
	}

	public void submitTestPaper() {
		System.out.println("���Ծ���~");
	}

	public void start() {
		this.giveTestPaper();
		this.writeTestPaper();
		this.submitTestPaper();
	}
	
	public static void start(KaoshiInterface kaoshi) {
		// ʹ�ö�̬������,�����Ǵ���ǰ����
		kaoshi.giveTestPaper();
		kaoshi.writeTestPaper();
		kaoshi.submitTestPaper();
	}
	
}
