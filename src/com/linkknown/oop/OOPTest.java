package com.linkknown.oop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	 * 分析一下对象实例化过程
	 */
	@Test
	public void testNew () {
//		String str = "helloworld....";
		
		String str = new String("helloworld...");
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
	public void testObject() {
		Address address = new Address("安徽省", "合肥市", "肥西县");
		// getClass 方法没定义怎么能使用 ??
		System.out.println(address.getClass());
	}

	/**
	 * 验证所有类都是 Object 的子类实现
	 * Object 类是所有类的公共父类
	 */
	@Test
	public void testObject2() {
		// 验证 Object 类
		String str = "helloworld...";
//		String str = new String("helloworld");
		
		// String、Date 等所有的类都可以使用 Object 的中的方法
		System.out.println(str.getClass());
		System.out.println(str.hashCode());
		
		Date date = new Date();
		System.out.println(date.getClass());
		System.out.println(date.hashCode());
		
		
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

	/**
	 * 比较相等
	 * == 基本类型比较值相等,引用类型比较是同一个对象
	 */
	@Test
	public void testEquals() {
		int i = 10;
		int j = 10;
		char k = 10;
		System.out.println(i == j);
		System.out.println(i == k);		// 不同类型基本类型值相等,返回 true
		
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
		
		String str1 = "helloworld...";
		String str2 = "helloworld...";
		String str3 = new String("helloworld...");
		
//		说明下面两句没有重新创建新的对象,只会创建一次
//		String str1 = "helloworld...";
//		String str2 = "helloworld...";
		System.out.println(str1 == str2);		// 相同
//		str3 被重新创建,所以不是同一个对象
		System.out.println(str1 == str3);		// 不相等
		
		
		System.out.println(str1.equals(str2));
	}

	/**
	 * 使用 jconsole 监听内存
	 * 模拟内存溢出
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
	 * 封装测试
	 * 封装的作用：
	 * 1、保证数据的安全
	 * 2、隐藏细节
	 */
	@Test
	public void testFengZhuang() {
		UnSecurityPerson person = new UnSecurityPerson();
		// 不使用 setter getter 方法设置获取属性值
		person.name = "zhang^san";
		person.age = 200;
		person.score = 1000;
		System.out.println(person);

		SecurityPerson person2 = new SecurityPerson();
		person2.setName("zhang^san");
		person2.setAge(200);
		person2.setScore(1000);
		System.out.println(person2);
		
		// 隐藏细节
		System.out.println(person2.getPerson("张&*……%三", 25, "A"));
		
		// 隐藏细节
		SecurityPerson[] persons = SecurityPerson.generateRandomPersons(10);
		for (SecurityPerson person3 : persons) {
			System.out.println(person3);
		}
	}
	
	/**
	 * 测试 extends 关键词使用
	 * 测试 2 个子类
	 */
	@Test
	public void testExtends () {
		// 先了解 Random 和 SecureRandom 类的继承关系
		// 先了解 Date 和 Time	类的继承关系
		// 了解单继承特性
		
		// 再举例说明
		Dog animal = new Dog();
		animal.talk();
		animal.sleep();
		
		Cat animal2 = new Cat();
		animal2.talk();
		animal2.sleep();
		
		
		// 最后了解 Object 类
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
	 * 对于当前方法，运行时决定具体类型
	 */
	public static void animalTalk (Animal animal) {
		animal.talk();
	}
	
	/**
	 * 测试多态:不同场景下有不同的形态
	 * 场景1、子类引用指向子类对象
	 * 场景2、父类引用指向子类对象  => "向上造型"
	 * 
	 * Java的多态变量：
	 * 1 Java的对象变量是多态的,他们能保存不止一种类型对象
	 * 2 它可以保存申明类型的变量,或申明类型的子类的对象
	 * 3 把子类的对象赋到父类的变量的时候就发生了向上造型
	 * 
	 * 多态的前提条件： 
	 * 1，有继承的存在
	 * 2，子类重写父类方法
	 * 3，父类引用指向子类对象
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
	 * 测试 this 和 super 关键字, 以 Dog 为例
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
	
	
	/**
	 * 测试重写和重载
	 */
	@Test
	public void testOverloadAndOverride () {
		// 重载和重写练习
		Dog dog = new Dog();
		dog.talk();
		
		System.out.println();
		
		dog.talk(3);
	}
	
	public static Address currentAddress;
	
	/**
	 * static 测试
	 */
	@Test
	public void testStatic () {
		// 测试非静态属性,每一个实例拥有自己的属性，互不影响
		Address address01 = new Address("安徽省", "合肥市", "肥东县");
		System.out.println(address01.getProvince());
		
		// 测试静态属性
		OOPTest test01 = new OOPTest();
		OOPTest test02 = new OOPTest();
		// 操作的是内容中的同一个区域
		test01.currentAddress = address01;
		test02.currentAddress = address01;
		
		// 建议使用类名去操作，不建议使用实例对象去操作静态属性
		OOPTest.currentAddress = address01;
		
		
		
		// 测试静态方法
		Address address02 = new Address("安徽省", "合肥市", "肥东县");
		address01.getAddress(address01.getProvince(), address01.getCity(), address01.getCountry());
		address02.getAddress(address02.getProvince(), address02.getCity(), address02.getCountry());
	
		// 同样不建议使用实例对象去操作静态方法
		Address.getAddress(address01.getProvince(), address01.getCity(), address01.getCountry());
		
		// 思考：静态方法能操作实例属性和实例方法吗？能使用 this 么？
	
		
		
		// 测试静态代码块, class 文件只加载一次，static 代码块也只执行一次
		// 静态代码块主要用于加载静态资源
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
		System.out.println(new Animal());
	}
	
	/**
	 * 测试 static
	 * 普通方法和函数的区别
	 */
	@Test
	public void testMethodAndFunction() {
		Address address1 = new Address("安徽省", "合肥市", "肥东县");
		System.out.println(address1.toString()); // 通过对象.方法 的方法来调用

		// static 关键词的作用就是申明该方法是全局共有的，不是类的实例方法
		Address address2 = Address.getAddress("安徽省", "合肥市", "肥西县"); // 通过 类.静态方法 来调用函数
	}
	
	@Test
	public void testFinal () {
		// 测试 final 变量
		final double PI = 3.1415;
//		PI = 3.14159;
		
		// 测试 final 方法
		Animal animal = new Animal();
		animal.finalMethod();
	}
	
	@Test
	public void testAbstract () {
		// 抽象类不能被创建
//		Person person = new Person();	
		
		// 只能创建抽象类的子类实现
		Person person = new Student();
		person.work();
		person.eat();
	}
	
	/**
	 * 测试接口
	 * 接口的粒度要细
	 * 满足接口隔离原则
	 */
	@Test
	public void testInterface () {
		Student student = new Student();
		student.play();
		student.sing();
		
		Dog dog = new Dog();
		dog.setName("阿旺");
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
	 * 测试可变参数
	 */
	@Test
	public void testArgs () {
		OOPTest test = new OOPTest();
		test.printAddress(Address.generateRandomAddress(10));
	}
	
	/**
	 * 测试析构方法
	 */
	@Test
	public void testFinalize () {
		// 不断创建不断销毁
		Address address;
		for (int i=0;; i++) {
			address = Address.getAddress("安徽省","合肥市","肥东县");
			
			System.out.println("new Address :" + i);
		}
	}
	
	/**
	 * 测试析构方法
	 * 主动回收 System.gc()
	 * @throws InterruptedException 
	 */
	@Test
	public void testFinalize2 () throws InterruptedException {
		// 不断创建不断销毁
		Address address;
		for (int i=0;; i++) {
			address = Address.getAddress("安徽省","合肥市","肥东县");
			
			System.out.println("new Address :" + i);
			System.gc();		// 主动通知 gc 回收
			
			TimeUnit.SECONDS.sleep(1);
		}
	}
	
	/**
	 * 测试包装类
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
	 * 测试静态内部类
	 */
	@Test
	public void testOuter01() {
		Outer01 outer01 = new Outer01();
		outer01.printName();
	}
	
	/**
	 * 测试成员内部类
	 */
	@Test
	public void testOuter02() {
		Outer02 outer02 = new Outer02();
		outer02.printName();
	}
	
	/**
	 * 测试局部变量内部类
	 */
	@Test
	public void testOuter03() {
		Outer03 outer03 = new Outer03();
		outer03.printName();
	}
	
	/**
	 * 测试匿名内部类
	 */
	@Test
	public void testOuter04() {
		Outer04 outer04 = new Outer04();
		outer04.printName();
	}

}
