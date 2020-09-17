package com.linkknown.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * 测试泛型
 * 
 * @author Administrator
 *
 */
public class GenericTest {
	
/**************************************** 使用泛型\不使用泛型场景对比 *********************************************/
	
	/**
	 * 1. 解决元素存储的安全性问题
	 * 2. 解决获取数据元素时，需要类型强转的问题
	 */
	@Test
	public void testNoGeneric () {
//		List<String>
//		List<Integer>
//		List<Date>
		
		
		List lst = new ArrayList();
		
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		
//		lst.add(new String("helloworld"));
		
		for (int i=0; i<lst.size(); i++) {
			Object object = lst.get(i);
			Date date = (Date) object;
			System.out.println(date);
		}
	}
	
	@Test
	public void testGeneric () {
		List<Date> lst = new ArrayList<>();
	
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		
		for (int i=0; i<lst.size(); i++) {
			Date date = lst.get(i);
			System.out.println(date.getTime());
		}
	}

	private static String generateRandomInArray (String[] strArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(strArr.length);					// 0 ~ strArr.length - 1
		return strArr[randomIndex];
	}
	
	private static Integer generateRandomInArray (Integer[] intArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(intArr.length);					// 0 ~ intArr.length - 1
		return intArr[randomIndex];
	}
	
	private static Date generateRandomInArray (Date[] dateArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(dateArr.length);					// 0 ~ dateArr.length - 1
		return dateArr[randomIndex];
	}
	
	/**
	 * generateRandom 方法重载过多,不通用
	 */
	@Test
	public void testNoGeneric2 () {
		System.out.println(generateRandomInArray(new String[] {"hello", "world"}));
		System.out.println(generateRandomInArray(new Integer[] {1, 2}));
		System.out.println(generateRandomInArray(new Date[] {new Date(), new Date()}));
	}
	
	private Object generateRandomInObjectArray (Object[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	private <T> T generateRandomInGenericArray (T[] objectArr) {
		return objectArr[new Random().nextInt(objectArr.length)];			// 0 ~ objectArr.length - 1
	}
	
	/**
	 * 还是得强转，无法保证类型安全
	 */
	@Test
	public void testNoGeneric3 () {
		System.out.println(generateRandomInObjectArray(new String[] {"hello", "world"}));
		System.out.println(generateRandomInObjectArray(new Integer[] {1, 2}));
		System.out.println(generateRandomInObjectArray(new Date[] {new Date(), new Date()}));
		
		Object obj = generateRandomInObjectArray(new Date[] {new Date(), new Date()});
		Date date = (Date) obj;
		System.out.println(date.getTime());
		
		
		// 1. 解决元素存储的安全性问题
		// 2. 解决获取数据元素时，需要类型强转的问题
		System.out.println(generateRandomInGenericArray(new String[] {"hello", "world"}));
		System.out.println(generateRandomInGenericArray(new Integer[] {1, 2}));
		System.out.println(generateRandomInGenericArray(new Date[] {new Date(), new Date()}));
		
		Date date2 = generateRandomInGenericArray(new Date[] {new Date(), new Date()});
		System.out.println(date2.getTime());
	}
	
	/**
	 * 此处的 HelloWorld 也是泛型,不一定非得是 ？ T K V E
	 * 只是建议使用  ？ T K V E 约定，通俗易懂
	 * @param <HelloWorld>
	 * @param objectArr
	 * @return
	 */
	private <HelloWorld> HelloWorld generateRandomWithGeneric (HelloWorld[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * 使用大写字母A,B,C,D......X,Y,Z定义的，就都是泛型，把T换成A也一样，这里T只是名字上的意义而已
	 */
	@Test
	public void testUseGeneric3 () {
		Date date = generateRandomWithGeneric(new Date[] {new Date(), new Date()});
		System.out.println(date.getTime());
	}
	
/**************************************** 泛型类 **************************************************/

	/**
	 * 一个泛型类场景
	 */
	@Test
	public void testOne() {
		Holder<String> holder = new Holder<>();
		holder.setObject("helloworld");
		holder.printObject();
		holder.writeToFile();
		
		Holder<Date> holder2 = new Holder<>();
		holder2.setObject(new Date());
		holder2.printObject();
		holder2.writeToFile();
		
		// xxxx
	}

	/**
	 * 两个泛型类场景
	 */
	@Test
	public void testTwo() {
		TwoHolder<String, String> twoHolder = new TwoHolder<String, String>("hello", "hello");
		System.out.println(twoHolder.checkEq());

		TwoHolder<String, Integer> twoHolder2 = new TwoHolder<String, Integer>("100", 100);
		System.out.println(twoHolder2.checkEq());
	}

	/**
	 * 多个泛型类场景
	 */
	@Test
	public void testMulti() {
		MultiHolder<String, Integer, Object, Random> multiHolder = new MultiHolder<String, Integer, Object, Random>(
				"linkknown", 10, new Object(), new Random());
		multiHolder.make();
	}

	/**
	 * 泛型类 从 RandomList 中随机获取元素
	 */
	@Test
	public void testGenericClass() {
		RandomList<String> randomList = new RandomList<>();
		for (String s : "hello world hello linkknown".split(" ")) {
			randomList.add(s);
		}
		for (int i = 0; i < 100; i++) {
			System.out.println(randomList.select());
		}
	}

	/**
	 * 泛型接口 练习：Fibonacci数列
	 * 为什么要定义 Generator 生成器接口呢？
	 * 加强设计：支持其它数列，比如等比数列、等差数列...
	 */
	@Test
	public void testGenericInterface() {
//		Fibonacci fibonacci = new Fibonacci();
		Generator<Integer> fibonacci = new Fibonacci();			// 把 Fibnaccio 数列当成普通数列来对待，至于底层实现，毫不关心
		for (int i = 0; i < 100; i++) {
			System.out.print(fibonacci.next() + " ");
		}
	}

	/**
	 * 泛型方法
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testGenericMethod() throws InstantiationException, IllegalAccessException {
		ClassUtil util = new ClassUtil();
		util.getClasName(new Object());
		util.getClasName(new String());
		util.getClasName(new Date());
		
		ClassUtil.getClasName2(new Object());
		ClassUtil.getClasName2(new String());
		ClassUtil.getClasName2(new Date());
		
		System.out.println(util.getInstance(new Date()));
	}

	public static <T> List<T> makeList(T... args) {
		List<T> lst = new ArrayList<>();
		for (T item : args) {
			lst.add(item);
		}
		return lst;
	}

	/**
	 * 可变参数与泛型方法
	 */
	@Test
	public void testGenericArgs() {
		System.out.println(makeList("hello", "world", "hello", "linkknown"));
		System.out.println(makeList(1, 2, 3, 4));
		
		System.out.println(makeList(1, 2, "hello", "world"));		// ?? why 可以，后面讲解
	}
	
	/**
	 * map key， value 互换
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public <K,V> Map<V,K> changeMap (Map<K, V> map) {
		Map<V, K> _map = new HashMap<V, K>();
		
		Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<K, V> entry = iterator.next();
			_map.put(entry.getValue(), entry.getKey());
		}
		
		return _map;
		
	}

	/**
	 * 测试 Map 泛型 K，V
	 */
	@Test
	public void testGenericKV() {
		Map<String, Integer> map = new HashMap<>();
		map.put("tom", 20);
		map.put("bob", 22);
		Map<Integer, String> resultMap = changeMap(map);
		System.out.println(resultMap);
		
		Map<String, Date> map2 = new HashMap<>();
		map2.put("tom", new Date());
		map2.put("bob", new Date());
		
		System.out.println(map2);
		
		Map<Date, String> resultMap2 = changeMap(map2);
		System.out.println(resultMap2);
	}
	
	
	/**
	 * 通配符
	 * @param lst
	 */
	public static void printCollectionForList (List<?> lst) {
		System.out.println(lst);
	}
	
	public static <T> void printCollectionForList2 (List<T> lst) {
		System.out.println(lst);
	}
	
	
	/**
	 * 通配符测试
	 * 通配符和 T 差不多，稍有区别
	 */
	@Test
	public void testQuestion () {
		printCollectionForList(Arrays.asList(1, 2, 3, 4));
		printCollectionForList(Arrays.asList("hello", "world"));
		
		printCollectionForList2(Arrays.asList(1, 2, 3, 4));
		printCollectionForList2(Arrays.asList("hello", "world"));
	}
	
	
/************************************* 泛型擦除 **************************************************/
	
	/**
	 * 验证泛型擦除
	 */
	@Test
	public void testGenericErase() {
		List<String> lst1 = new ArrayList<>();

		lst1.add("hello");

		List<Integer> lst2 = new ArrayList<>();
		lst2.add(10);

		// 泛型擦除
		// 以为    		List<String>.class == List<Integer>.class
		// 实际上  	List.class == List.class
		System.out.println(lst1.getClass() == lst2.getClass());	// 返回 true 表示类型在编译期被擦除了
	}

	/**
	 * 通过反射添加其它类型元素 验证泛型擦除
	 * 
	 * 编译期进行泛型擦除,运行时只有 add (Object obj) 方法,没有 add (String str) 方法
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGenericErase2 () throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> lst = new ArrayList<>();
		
		lst.add("hello");
		
//		Method addMethod01 = lst.getClass().getMethod("add", String.class);
//		addMethod01.invoke(lst, "world");
		
		Method addMethod02 = lst.getClass().getMethod("add", Object.class);
		addMethod02.invoke(lst, 10);
		
		System.out.println(lst);
	}

	
	// 这是一个泛型方法
	public static <T> T getRandom (T... args) {
		return args[new Random().nextInt(args.length)];				// 0 ~ args.length - 1
	}

	/**
	 * 泛型类型推断
	 */
	@Test
	public void test() {
		/** 不指定泛型的时候 */
		Integer result1 = GenericTest.getRandom(1, 2); 			// 这两个参数都是Integer，所以T为Integer类型
		String random2 = getRandom("hello", "world", "hello", "world");
		Boolean random3 = getRandom(true, false, false, true);
		Number random4 = GenericTest.getRandom(1, 1.2); // 这两个参数一个是Integer，一个风格是 double，所以取同一父类的最小级，为Number
		Object random5 = GenericTest.getRandom(1, "hello"); // 这两个参数一个是Integer，一个风格是double，所以取同一父类的最小级，为Object

		/** 指定泛型的时候 */
		int a = GenericTest.<Integer>getRandom(1, 2); // 指定了Integer，所以只能为Integer类型或者其子类
//		int b = GenericEraseTest.<Integer>getRandom(1, 2.2); // 编译错误，指定了Integer，不能为double
		Number c = GenericTest.<Number>getRandom(1, 2.2); // 指定为Number，所以可以为Integer和double
	}
		
	
/************************************* 泛型非协变性 **************************************************/

	public static void printArr (Number[] numbers) {
		System.out.println(Arrays.toString(numbers));
	}
	
	
	/**
	 * public final class Integer extends Number
	 * 数组的协变性：Integer 扩展了 Number，那么不仅 Integer 是 Number，而且 Integer[] 
	 * 也是 Number[]，在要求 Number[] 的地方完全可以传递或者赋予 Integer[]
	 */
	@Test
	public void testPrintArr () {
		printArr(new Integer[] {1,2,3,4,5});
	}
	
	
	public static void printList (List<Number> lst) {
		
		lst.add(10.0);
		
		System.out.println(lst);
	}
	
	
	/**
	 * 您也许认为这一原理同样适用于泛型类型 —— List< Number> 是 List< Integer> 的超类型，
	 * 那么可以在需要 List< Number> 的地方传递 List< Integer>。
	 * 不幸的是，情况并非如此。为啥呢？这么做将破坏要提供的类型安全泛型。
	 * 
	 * 
	 * 泛型类并没有自己独有的 Class 类对象。比如并不存在 List<String>.class 或是 List<Integer>.class，
	 * 而只有 List.class，因此在运行时无法获得泛型的真实类型信息
	 * 
	 * 问题1：为什么数组支持协变性？
	 * 因为Java有协变数组，所以.net的设计者的第一个版本时，也采取同样的策略。虽然这个功能在Java中是一个公认的“瑕疵”
	 * 
	 * 问题2：为什么泛型不支持协变性？
	 * 泛型设计者认为与其在运行失败，不如在编译时就失败——静态类型语言的全部意义在于代码运行前找出错误。
	 */
	@Test
	public void testPrintList () {
		List<Integer> lst = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		
//		printList(lst);						// ??????? why 不支持
		
		List<Number> lst2 = new ArrayList<>();
		lst2.add(1);
		lst2.add(2);
		
		printList(lst2);					// why 支持
	}
	
/************************************* 泛型边界 **************************************************/	
	
	/**
	 * 测试泛型上界
	 * 上界<? extends Animal>规定：只能取(get)，不能添加(add)。
	 * @param lst
	 */
	public static void printList2 (List<? extends Number> lst) {
		
//		lst.add(10.0);					// ??? why 不支持
		
		System.out.println(lst);
	}	
	
	@Test
	public void testPrintList2 () {
		List<Integer> lst = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		
		printList2(lst);						// ??????? why 支持
												
		List<Number> lst2 = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		printList2(lst2);
	}
	
}





