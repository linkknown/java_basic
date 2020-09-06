package com.linkknown.oop;

import org.junit.jupiter.api.Test;

public class OOPTest {
	
	/**
	 * 测试类和实例的区别
	 */
	@Test
	public void testCreateClass() {
		// Address 称为类, address1、address2、address3 称为类的实例
		Address address1 = new Address("安徽省", "合肥市", "肥东县");
		Address address2 = new Address("安徽省", "合肥市", "肥西县");
		Address address3 = new Address("安徽省", "滁州市", "凤阳县");

		System.out.println(address1);
		System.out.println(address2);
		System.out.println(address3);
	}

	/**
	 * 测试类的组成部分：属性、方法、构造器
	 */
	@Test
	public void testClassComponent() {
		// 什么是属性？
		// 1、定义属性 2、属性赋初值
		// 什么是方法？
		// 函数的方法的区别
		// 什么是构造器？
		// 怎么声明构造函数
	}

	/**
	 * 普通方法和函数的区别
	 */
	@Test
	public void testMethodAndFunction() {
		Address address1 = new Address("安徽省", "合肥市", "肥东县");
		System.out.println(address1.toString()); // 通过对象.方法 的方法来调用

		// static 关键词的作用就是申明该方法是全局共有的，不是类的实例方法
		Address address2 = Address.getAddress("安徽省", "合肥市", "肥西县"); // 通过 类.静态方法 来调用函数
	}

	/**
	 * 有参构造器和无参构造器
	 */
	@Test
	public void testConstructor() {
		// 调用无参构造器
		Address address = new Address();
		address.setProvince("安徽省");
		address.setCity("合肥市");
		address.setCountry("肥东县");
		System.out.println(address);

		// 调用有参构造器
		Address address2 = new Address("安徽省", "合肥市", "肥西县");
		System.out.println(address2);

		// 调用部分参数构造器
		Address address3 = new Address("安徽省");
		address3.setCity("合肥市");
		address3.setCountry("肥东县");
		System.out.println(address3);
	}

	/**
	 * 测试 toString
	 */
	@Test
	public void testToString() {
		Address address = new Address("安徽省", "合肥市", "肥西县");
		System.out.println(address); // 默认就是调用 toString 方法
		System.out.println(address.toString());
	}

	/**
	 * 测试 Object 类
	 */
	@Test
	public void testSuper() {
		Address address = new Address("安徽省", "合肥市", "肥西县");
		// getClass 方法没定义怎么能使用 ??
		System.out.println(address.getClass());
	}

	@Test
	public void testObject() {
		Object object = new Address("安徽省", "合肥市", "肥西县");
		System.out.println(object);
	}

	@Test
	public void testConvert() {
		Object object = new Address("安徽省", "合肥市", "肥西县");

		if (object instanceof Address) {
			Address address = (Address) object;
			System.out.println(address);
		}
	}

	@Test
	public void testEquals() {
		// 未重写 equals 方法
		Address address1 = new Address("安徽省", "合肥市", "肥西县");
		Address address2 = new Address("安徽省", "合肥市", "肥西县");

		System.out.println(address1 == address2); // false
		// 未重写 equals 和 hashCode 方法是为：false
		// 重写 equals 和 hashCode 方法是为：true
		System.out.println(address1.equals(address2));

		// address3 和 address1 是同一个对象(内存模型中只 new 了一次),数据进堆，引用进栈
		Address address3 = address1;
		System.out.println(address1 == address3); // true
		System.out.println(address1.equals(address3)); // true
	}

	/**
	 * 封装测试
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
		
		System.out.println(person2.getPerson("张&*……%三", 25, "A"));
	}
	
	/**
	 * 测试 extends 关键词使用
	 * 测试 2 个子类
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
	 * 测试 > 2 层继承
	 */
	@Test
	public void testHigerExtends () {
		WhiteCat cat = new WhiteCat();
		cat.talk();
		
		BlackCat cat2 = new BlackCat();
		cat2.talk();
	}
	
	/**
	 * 测试 this 关键字, 以 Dog 为例
	 */
	@Test
	public void testThis () {
		Dog dog = new Dog();
		dog.setName("小狗1");
		
		Dog dog2 = new Dog();
		dog2.setName("小狗2");
		
		dog.setPlaymate(dog2);
		dog.play();
	}
	
	
	@Test
	public void testOverloadAndOverride () {
		// 重载和重写练习
		Dog dog = new Dog();
		dog.talk();
		
		System.out.println();
		
		dog.talk(3);
	}
	

	/**
	 * 测试 static
	 */
	@Test
	public void testStatic () {
		// 修饰静态属性
		// 修饰静态方法
		// 修饰静态代码块
		ConfigUtil configUtil = ConfigUtil.getInstance();
		System.out.println(configUtil.getUserName());
		System.out.println(configUtil.getPassword());
	}
	
	/**
	 * 测试 final、abstract、interface 和 可变参数
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
		
		System.out.println("5 个老式科学计算器价格是：" + newScienceCalculator.getPrice() * 10);
		
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
		String regEx = "[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
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
	 * 根据成绩等级随机产生一个 person 实例
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