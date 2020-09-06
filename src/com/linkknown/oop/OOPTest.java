package com.linkknown.oop;

import org.junit.jupiter.api.Test;

public class OOPTest {
	
	/**
	 * �������ʵ��������
	 */
	@Test
	public void testCreateClass() {
		// Address ��Ϊ��, address1��address2��address3 ��Ϊ���ʵ��
		Address address1 = new Address("����ʡ", "�Ϸ���", "�ʶ���");
		Address address2 = new Address("����ʡ", "�Ϸ���", "������");
		Address address3 = new Address("����ʡ", "������", "������");

		System.out.println(address1);
		System.out.println(address2);
		System.out.println(address3);
	}

	/**
	 * ���������ɲ��֣����ԡ�������������
	 */
	@Test
	public void testClassComponent() {
		// ʲô�����ԣ�
		// 1���������� 2�����Ը���ֵ
		// ʲô�Ƿ�����
		// �����ķ���������
		// ʲô�ǹ�������
		// ��ô�������캯��
	}

	/**
	 * ��ͨ�����ͺ���������
	 */
	@Test
	public void testMethodAndFunction() {
		Address address1 = new Address("����ʡ", "�Ϸ���", "�ʶ���");
		System.out.println(address1.toString()); // ͨ������.���� �ķ���������

		// static �ؼ��ʵ����þ��������÷�����ȫ�ֹ��еģ��������ʵ������
		Address address2 = Address.getAddress("����ʡ", "�Ϸ���", "������"); // ͨ�� ��.��̬���� �����ú���
	}

	/**
	 * �вι��������޲ι�����
	 */
	@Test
	public void testConstructor() {
		// �����޲ι�����
		Address address = new Address();
		address.setProvince("����ʡ");
		address.setCity("�Ϸ���");
		address.setCountry("�ʶ���");
		System.out.println(address);

		// �����вι�����
		Address address2 = new Address("����ʡ", "�Ϸ���", "������");
		System.out.println(address2);

		// ���ò��ֲ���������
		Address address3 = new Address("����ʡ");
		address3.setCity("�Ϸ���");
		address3.setCountry("�ʶ���");
		System.out.println(address3);
	}

	/**
	 * ���� toString
	 */
	@Test
	public void testToString() {
		Address address = new Address("����ʡ", "�Ϸ���", "������");
		System.out.println(address); // Ĭ�Ͼ��ǵ��� toString ����
		System.out.println(address.toString());
	}

	/**
	 * ���� Object ��
	 */
	@Test
	public void testSuper() {
		Address address = new Address("����ʡ", "�Ϸ���", "������");
		// getClass ����û������ô��ʹ�� ??
		System.out.println(address.getClass());
	}

	@Test
	public void testObject() {
		Object object = new Address("����ʡ", "�Ϸ���", "������");
		System.out.println(object);
	}

	@Test
	public void testConvert() {
		Object object = new Address("����ʡ", "�Ϸ���", "������");

		if (object instanceof Address) {
			Address address = (Address) object;
			System.out.println(address);
		}
	}

	@Test
	public void testEquals() {
		// δ��д equals ����
		Address address1 = new Address("����ʡ", "�Ϸ���", "������");
		Address address2 = new Address("����ʡ", "�Ϸ���", "������");

		System.out.println(address1 == address2); // false
		// δ��д equals �� hashCode ������Ϊ��false
		// ��д equals �� hashCode ������Ϊ��true
		System.out.println(address1.equals(address2));

		// address3 �� address1 ��ͬһ������(�ڴ�ģ����ֻ new ��һ��),���ݽ��ѣ����ý�ջ
		Address address3 = address1;
		System.out.println(address1 == address3); // true
		System.out.println(address1.equals(address3)); // true
	}

	/**
	 * ��װ����
	 */
	@Test
	public void testFengZhuang() {
		UnSecurityPerson person = new UnSecurityPerson();
		person.name = "zhang^san";
		person.age = 200;
		person.score = 1000;
		System.out.println(person);

		SecurityPerson person2 = new SecurityPerson();
		person2.setName("zhang^san");
		person2.setAge(200);
		person2.setScore(1000);
		System.out.println(person2);
		
		System.out.println(person2.getPerson("��&*����%��", 25, "A"));
	}
	
	/**
	 * ���� extends �ؼ���ʹ��
	 * ���� 2 ������
	 */
	@Test
	public void testExtends () {
		Dog animal = new Dog();
		animal.talk();
		animal.sleep();
		
		Cat animal2 = new Cat();
		animal2.talk();
		animal2.sleep();
	}
	
	
	/**
	 * ���� > 2 ��̳�
	 */
	@Test
	public void testHigerExtends () {
		WhiteCat cat = new WhiteCat();
		cat.talk();
		
		BlackCat cat2 = new BlackCat();
		cat2.talk();
	}
	
	/**
	 * ���� this �ؼ���, �� Dog Ϊ��
	 */
	@Test
	public void testThis () {
		Dog dog = new Dog();
		dog.setName("С��1");
		
		Dog dog2 = new Dog();
		dog2.setName("С��2");
		
		dog.setPlaymate(dog2);
		dog.play();
	}
	
	
	@Test
	public void testOverloadAndOverride () {
		// ���غ���д��ϰ
		Dog dog = new Dog();
		dog.talk();
		
		System.out.println();
		
		dog.talk(3);
	}
	

	/**
	 * ���� static
	 */
	@Test
	public void testStatic () {
		// ���ξ�̬����
		// ���ξ�̬����
		// ���ξ�̬�����
		ConfigUtil configUtil = ConfigUtil.getInstance();
		System.out.println(configUtil.getUserName());
		System.out.println(configUtil.getPassword());
	}
	
	/**
	 * ���� final��abstract��interface �� �ɱ����
	 */
	@Test
	public void testOOP () {
		Calculator ordinaryCalculator = new OrdinaryCalculator();
		int result = ordinaryCalculator.getOperator("+").eval(1, 1);
		System.out.println(result);
		ordinaryCalculator.showDesc(true);
		ordinaryCalculator.showDesc();
		
		ScienceCalculator newScienceCalculator = new NewScienceCalculator();
		int result2 = newScienceCalculator.getOperator("^").eval(2,8);
		System.out.println(result2);
		newScienceCalculator.showDesc(true);
		newScienceCalculator.showDesc();
		
		System.out.println("5 ����ʽ��ѧ�������۸��ǣ�" + newScienceCalculator.getPrice() * 10);
		
		Calculator oldScienceCalculator = new OldScienceCalculator();
		int result3 = oldScienceCalculator.getOperator("^").eval(2,16);
		System.out.println(result3);
		oldScienceCalculator.showDesc(true);
		oldScienceCalculator.showDesc();
	}
}

class UnSecurityPerson {
	public String name;
	public int age;
	public int score;

	@Override
	public String toString() {
		return "UnSecurityPerson [name=" + name + ", age=" + age + ", score=" + score + "]";
	}

}

class SecurityPerson {
	private String name;
	private int age;
	private int score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String regEx = "[\\s~��`!��@#��$%^����&*��()��\\-����\\-_=+��\\[\\]����{}��\\|��\\\\��;��:��'����\"��,��<��.��>��/��?]";
		this.name = name.replaceAll(regEx, "");
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age > 0 && age < 150 ? age : this.age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score >= 0 && score <= 150 ? score : this.score;
	}

	@Override
	public String toString() {
		return "SecurityPerson [name=" + name + ", age=" + age + ", score=" + score + "]";
	}
	
	/**
	 * ���ݳɼ��ȼ��������һ�� person ʵ��
	 * @param name
	 * @return
	 */
	public SecurityPerson getPerson (String name, int age, String grade) {
		SecurityPerson person = new SecurityPerson();
		person.setName(name);
		person.setAge(age);
		switch (grade) {
		case "A":
			person.setScore(150);
			break;
		case "B":
			person.setScore(120);
		case "C":
			person.setScore(90);
		case "D":
			person.setScore(60);
			break;
		default:
			person.setScore(0);
			break;
		}
		return person;
	}

}