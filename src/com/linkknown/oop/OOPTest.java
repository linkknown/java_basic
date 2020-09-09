package com.linkknown.oop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	 * ����һ�¶���ʵ��������
	 */
	@Test
	public void testNew () {
//		String str = "helloworld....";
		
		String str = new String("helloworld...");
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
	public void testObject() {
		Address address = new Address("����ʡ", "�Ϸ���", "������");
		// getClass ����û������ô��ʹ�� ??
		System.out.println(address.getClass());
	}

	/**
	 * ��֤�����඼�� Object ������ʵ��
	 * Object ����������Ĺ�������
	 */
	@Test
	public void testObject2() {
		// ��֤ Object ��
		String str = "helloworld...";
//		String str = new String("helloworld");
		
		// String��Date �����е��඼����ʹ�� Object ���еķ���
		System.out.println(str.getClass());
		System.out.println(str.hashCode());
		
		Date date = new Date();
		System.out.println(date.getClass());
		System.out.println(date.hashCode());
		
		
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

	/**
	 * �Ƚ����
	 * == �������ͱȽ�ֵ���,�������ͱȽ���ͬһ������
	 */
	@Test
	public void testEquals() {
		int i = 10;
		int j = 10;
		char k = 10;
		System.out.println(i == j);
		System.out.println(i == k);		// ��ͬ���ͻ�������ֵ���,���� true
		
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
		
		String str1 = "helloworld...";
		String str2 = "helloworld...";
		String str3 = new String("helloworld...");
		
//		˵����������û�����´����µĶ���,ֻ�ᴴ��һ��
//		String str1 = "helloworld...";
//		String str2 = "helloworld...";
		System.out.println(str1 == str2);		// ��ͬ
//		str3 �����´���,���Բ���ͬһ������
		System.out.println(str1 == str3);		// �����
		
		
		System.out.println(str1.equals(str2));
	}

	/**
	 * ʹ�� jconsole �����ڴ�
	 * ģ���ڴ����
	 */
	@Test
	public void testGC () {
		List<String> lst = new ArrayList<>();
		for (;;) {
			lst.add("helloworld helloworld helloworld");
			
			System.out.println(lst.size());
		}
	}
	
	/**
	 * ��װ����
	 * ��װ�����ã�
	 * 1����֤���ݵİ�ȫ
	 * 2������ϸ��
	 */
	@Test
	public void testFengZhuang() {
		UnSecurityPerson person = new UnSecurityPerson();
		// ��ʹ�� setter getter �������û�ȡ����ֵ
		person.name = "zhang^san";
		person.age = 200;
		person.score = 1000;
		System.out.println(person);

		SecurityPerson person2 = new SecurityPerson();
		person2.setName("zhang^san");
		person2.setAge(200);
		person2.setScore(1000);
		System.out.println(person2);
		
		// ����ϸ��
		System.out.println(person2.getPerson("��&*����%��", 25, "A"));
		
		// ����ϸ��
		SecurityPerson[] persons = SecurityPerson.generateRandomPersons(10);
		for (SecurityPerson person3 : persons) {
			System.out.println(person3);
		}
	}
	
	/**
	 * ���� extends �ؼ���ʹ��
	 * ���� 2 ������
	 */
	@Test
	public void testExtends () {
		// ���˽� Random �� SecureRandom ��ļ̳й�ϵ
		// ���˽� Date �� Time	��ļ̳й�ϵ
		// �˽ⵥ�̳�����
		
		// �پ���˵��
		Dog animal = new Dog();
		animal.talk();
		animal.sleep();
		
		Cat animal2 = new Cat();
		animal2.talk();
		animal2.sleep();
		
		
		// ����˽� Object ��
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
	 * ���ڵ�ǰ����������ʱ������������
	 */
	public static void animalTalk (Animal animal) {
		animal.talk();
	}
	
	/**
	 * ���Զ�̬:��ͬ�������в�ͬ����̬
	 * ����1����������ָ���������
	 * ����2����������ָ���������  => "��������"
	 * 
	 * Java�Ķ�̬������
	 * 1 Java�Ķ�������Ƕ�̬��,�����ܱ��治ֹһ�����Ͷ���
	 * 2 �����Ա����������͵ı���,���������͵�����Ķ���
	 * 3 ������Ķ��󸳵�����ı�����ʱ��ͷ�������������
	 * 
	 * ��̬��ǰ�������� 
	 * 1���м̳еĴ���
	 * 2��������д���෽��
	 * 3����������ָ���������
	 */
	@Test
	public void testDuoTai () {
		Animal cat = new Cat();
		Animal dog = new Dog();
		
		OOPTest.animalTalk(cat);
		OOPTest.animalTalk(dog);
		
		if (cat instanceof Cat) {
			((Cat)cat).eatMouse();
		}
	}
	
	/**
	 * ���� this �� super �ؼ���, �� Dog Ϊ��
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
	
	
	/**
	 * ������д������
	 */
	@Test
	public void testOverloadAndOverride () {
		// ���غ���д��ϰ
		Dog dog = new Dog();
		dog.talk();
		
		System.out.println();
		
		dog.talk(3);
	}
	
	public static Address currentAddress;
	
	/**
	 * static ����
	 */
	@Test
	public void testStatic () {
		// ���ԷǾ�̬����,ÿһ��ʵ��ӵ���Լ������ԣ�����Ӱ��
		Address address01 = new Address("����ʡ", "�Ϸ���", "�ʶ���");
		System.out.println(address01.getProvince());
		
		// ���Ծ�̬����
		OOPTest test01 = new OOPTest();
		OOPTest test02 = new OOPTest();
		// �������������е�ͬһ������
		test01.currentAddress = address01;
		test02.currentAddress = address01;
		
		// ����ʹ������ȥ������������ʹ��ʵ������ȥ������̬����
		OOPTest.currentAddress = address01;
		
		
		
		// ���Ծ�̬����
		Address address02 = new Address("����ʡ", "�Ϸ���", "�ʶ���");
		address01.getAddress(address01.getProvince(), address01.getCity(), address01.getCountry());
		address02.getAddress(address02.getProvince(), address02.getCity(), address02.getCountry());
	
		// ͬ��������ʹ��ʵ������ȥ������̬����
		Address.getAddress(address01.getProvince(), address01.getCity(), address01.getCountry());
		
		// ˼������̬�����ܲ���ʵ�����Ժ�ʵ����������ʹ�� this ô��
	
		
		
		// ���Ծ�̬�����, class �ļ�ֻ����һ�Σ�static �����Ҳִֻ��һ��
		// ��̬�������Ҫ���ڼ��ؾ�̬��Դ
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
	}
	
	/**
	 * ���� static
	 * ��ͨ�����ͺ���������
	 */
	@Test
	public void testMethodAndFunction() {
		Address address1 = new Address("����ʡ", "�Ϸ���", "�ʶ���");
		System.out.println(address1.toString()); // ͨ������.���� �ķ���������

		// static �ؼ��ʵ����þ��������÷�����ȫ�ֹ��еģ��������ʵ������
		Address address2 = Address.getAddress("����ʡ", "�Ϸ���", "������"); // ͨ�� ��.��̬���� �����ú���
	}
	
	@Test
	public void testFinal () {
		// ���� final ����
		final double PI = 3.1415;
//		PI = 3.14159;
		
		// ���� final ����
		Animal animal = new Animal();
		animal.finalMethod();
	}
	
	@Test
	public void testAbstract () {
		// �����಻�ܱ�����
//		Person person = new Person();	
		
		// ֻ�ܴ��������������ʵ��
		Person person = new Student();
		person.work();
		person.eat();
	}
	
	/**
	 * ���Խӿ�
	 * �ӿڵ�����Ҫϸ
	 * ����ӿڸ���ԭ��
	 */
	@Test
	public void testInterface () {
		Student student = new Student();
		student.play();
		student.sing();
		
		Dog dog = new Dog();
		dog.setName("����");
		dog.setPlaymate(dog);
		dog.play();
		dog.sing();
	}
	

	
	private void printAddress (Address... addresses) {
		for (int i=0; i<addresses.length; i++) {
			System.out.println(addresses[i]);
		}
	}
	
	/**
	 * ���Կɱ����
	 */
	@Test
	public void testArgs () {
		OOPTest test = new OOPTest();
		test.printAddress(Address.generateRandomAddress(10));
	}
	
	/**
	 * ������������
	 */
	@Test
	public void testFinalize () {
		// ���ϴ�����������
		Address address;
		for (int i=0;; i++) {
			address = Address.getAddress("����ʡ","�Ϸ���","�ʶ���");
			
			System.out.println("new Address :" + i);
		}
	}
	
	/**
	 * ������������
	 * �������� System.gc()
	 * @throws InterruptedException 
	 */
	@Test
	public void testFinalize2 () throws InterruptedException {
		// ���ϴ�����������
		Address address;
		for (int i=0;; i++) {
			address = Address.getAddress("����ʡ","�Ϸ���","�ʶ���");
			
			System.out.println("new Address :" + i);
			System.gc();		// ����֪ͨ gc ����
			
			TimeUnit.SECONDS.sleep(1);
		}
	}
	
	/**
	 * ���԰�װ��
	 */
	@Test
	public void testWarpClass () {
		// int <==> Integer
		// int <==> String
		// Integer <==> String
		
		// int => Integer
		Integer number = new Integer(10);
		// Integer => int
		int number2 = number.intValue();
		// int => String
		String number3 = String.valueOf(10);
		// String => int
		int number4 = Integer.parseInt("10");
		// Integer => String
		String number5 = number.toString();
		// String => Integer
		Integer number6 = new Integer("10");
	}
	
	
	/**
	 * ���Ծ�̬�ڲ���
	 */
	@Test
	public void testOuter01() {
		Outer01 outer01 = new Outer01();
		outer01.printName();
	}
	
	/**
	 * ���Գ�Ա�ڲ���
	 */
	@Test
	public void testOuter02() {
		Outer02 outer02 = new Outer02();
		outer02.printName();
	}
	
	/**
	 * ���Ծֲ������ڲ���
	 */
	@Test
	public void testOuter03() {
		Outer03 outer03 = new Outer03();
		outer03.printName();
	}
	
	/**
	 * ���������ڲ���
	 */
	@Test
	public void testOuter04() {
		Outer04 outer04 = new Outer04();
		outer04.printName();
	}

}
