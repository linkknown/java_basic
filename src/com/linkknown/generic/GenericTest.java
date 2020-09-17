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
 * ���Է���
 * 
 * @author Administrator
 *
 */
public class GenericTest {
	
/**************************************** ʹ�÷���\��ʹ�÷��ͳ����Ա� *********************************************/
	
	/**
	 * 1. ���Ԫ�ش洢�İ�ȫ������
	 * 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
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
	 * generateRandom �������ع���,��ͨ��
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
	 * ���ǵ�ǿת���޷���֤���Ͱ�ȫ
	 */
	@Test
	public void testNoGeneric3 () {
		System.out.println(generateRandomInObjectArray(new String[] {"hello", "world"}));
		System.out.println(generateRandomInObjectArray(new Integer[] {1, 2}));
		System.out.println(generateRandomInObjectArray(new Date[] {new Date(), new Date()}));
		
		Object obj = generateRandomInObjectArray(new Date[] {new Date(), new Date()});
		Date date = (Date) obj;
		System.out.println(date.getTime());
		
		
		// 1. ���Ԫ�ش洢�İ�ȫ������
		// 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
		System.out.println(generateRandomInGenericArray(new String[] {"hello", "world"}));
		System.out.println(generateRandomInGenericArray(new Integer[] {1, 2}));
		System.out.println(generateRandomInGenericArray(new Date[] {new Date(), new Date()}));
		
		Date date2 = generateRandomInGenericArray(new Date[] {new Date(), new Date()});
		System.out.println(date2.getTime());
	}
	
	/**
	 * �˴��� HelloWorld Ҳ�Ƿ���,��һ���ǵ��� �� T K V E
	 * ֻ�ǽ���ʹ��  �� T K V E Լ����ͨ���׶�
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
	 * ʹ�ô�д��ĸA,B,C,D......X,Y,Z����ģ��Ͷ��Ƿ��ͣ���T����AҲһ��������Tֻ�������ϵ��������
	 */
	@Test
	public void testUseGeneric3 () {
		Date date = generateRandomWithGeneric(new Date[] {new Date(), new Date()});
		System.out.println(date.getTime());
	}
	
/**************************************** ������ **************************************************/

	/**
	 * һ�������ೡ��
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
	 * ���������ೡ��
	 */
	@Test
	public void testTwo() {
		TwoHolder<String, String> twoHolder = new TwoHolder<String, String>("hello", "hello");
		System.out.println(twoHolder.checkEq());

		TwoHolder<String, Integer> twoHolder2 = new TwoHolder<String, Integer>("100", 100);
		System.out.println(twoHolder2.checkEq());
	}

	/**
	 * ��������ೡ��
	 */
	@Test
	public void testMulti() {
		MultiHolder<String, Integer, Object, Random> multiHolder = new MultiHolder<String, Integer, Object, Random>(
				"linkknown", 10, new Object(), new Random());
		multiHolder.make();
	}

	/**
	 * ������ �� RandomList �������ȡԪ��
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
	 * ���ͽӿ� ��ϰ��Fibonacci����
	 * ΪʲôҪ���� Generator �������ӿ��أ�
	 * ��ǿ��ƣ�֧���������У�����ȱ����С��Ȳ�����...
	 */
	@Test
	public void testGenericInterface() {
//		Fibonacci fibonacci = new Fibonacci();
		Generator<Integer> fibonacci = new Fibonacci();			// �� Fibnaccio ���е�����ͨ�������Դ������ڵײ�ʵ�֣���������
		for (int i = 0; i < 100; i++) {
			System.out.print(fibonacci.next() + " ");
		}
	}

	/**
	 * ���ͷ���
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
	 * �ɱ�����뷺�ͷ���
	 */
	@Test
	public void testGenericArgs() {
		System.out.println(makeList("hello", "world", "hello", "linkknown"));
		System.out.println(makeList(1, 2, 3, 4));
		
		System.out.println(makeList(1, 2, "hello", "world"));		// ?? why ���ԣ����潲��
	}
	
	/**
	 * map key�� value ����
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
	 * ���� Map ���� K��V
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
	 * ͨ���
	 * @param lst
	 */
	public static void printCollectionForList (List<?> lst) {
		System.out.println(lst);
	}
	
	public static <T> void printCollectionForList2 (List<T> lst) {
		System.out.println(lst);
	}
	
	
	/**
	 * ͨ�������
	 * ͨ����� T ��࣬��������
	 */
	@Test
	public void testQuestion () {
		printCollectionForList(Arrays.asList(1, 2, 3, 4));
		printCollectionForList(Arrays.asList("hello", "world"));
		
		printCollectionForList2(Arrays.asList(1, 2, 3, 4));
		printCollectionForList2(Arrays.asList("hello", "world"));
	}
	
	
/************************************* ���Ͳ��� **************************************************/
	
	/**
	 * ��֤���Ͳ���
	 */
	@Test
	public void testGenericErase() {
		List<String> lst1 = new ArrayList<>();

		lst1.add("hello");

		List<Integer> lst2 = new ArrayList<>();
		lst2.add(10);

		// ���Ͳ���
		// ��Ϊ    		List<String>.class == List<Integer>.class
		// ʵ����  	List.class == List.class
		System.out.println(lst1.getClass() == lst2.getClass());	// ���� true ��ʾ�����ڱ����ڱ�������
	}

	/**
	 * ͨ�����������������Ԫ�� ��֤���Ͳ���
	 * 
	 * �����ڽ��з��Ͳ���,����ʱֻ�� add (Object obj) ����,û�� add (String str) ����
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

	
	// ����һ�����ͷ���
	public static <T> T getRandom (T... args) {
		return args[new Random().nextInt(args.length)];				// 0 ~ args.length - 1
	}

	/**
	 * ���������ƶ�
	 */
	@Test
	public void test() {
		/** ��ָ�����͵�ʱ�� */
		Integer result1 = GenericTest.getRandom(1, 2); 			// ��������������Integer������TΪInteger����
		String random2 = getRandom("hello", "world", "hello", "world");
		Boolean random3 = getRandom(true, false, false, true);
		Number random4 = GenericTest.getRandom(1, 1.2); // ����������һ����Integer��һ������� double������ȡͬһ�������С����ΪNumber
		Object random5 = GenericTest.getRandom(1, "hello"); // ����������һ����Integer��һ�������double������ȡͬһ�������С����ΪObject

		/** ָ�����͵�ʱ�� */
		int a = GenericTest.<Integer>getRandom(1, 2); // ָ����Integer������ֻ��ΪInteger���ͻ���������
//		int b = GenericEraseTest.<Integer>getRandom(1, 2.2); // �������ָ����Integer������Ϊdouble
		Number c = GenericTest.<Number>getRandom(1, 2.2); // ָ��ΪNumber�����Կ���ΪInteger��double
	}
		
	
/************************************* ���ͷ�Э���� **************************************************/

	public static void printArr (Number[] numbers) {
		System.out.println(Arrays.toString(numbers));
	}
	
	
	/**
	 * public final class Integer extends Number
	 * �����Э���ԣ�Integer ��չ�� Number����ô���� Integer �� Number������ Integer[] 
	 * Ҳ�� Number[]����Ҫ�� Number[] �ĵط���ȫ���Դ��ݻ��߸��� Integer[]
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
	 * ��Ҳ����Ϊ��һԭ��ͬ�������ڷ������� ���� List< Number> �� List< Integer> �ĳ����ͣ�
	 * ��ô��������Ҫ List< Number> �ĵط����� List< Integer>��
	 * ���ҵ��ǣ����������ˡ�Ϊɶ�أ���ô�����ƻ�Ҫ�ṩ�����Ͱ�ȫ���͡�
	 * 
	 * 
	 * �����ಢû���Լ����е� Class ����󡣱��粢������ List<String>.class ���� List<Integer>.class��
	 * ��ֻ�� List.class�����������ʱ�޷���÷��͵���ʵ������Ϣ
	 * 
	 * ����1��Ϊʲô����֧��Э���ԣ�
	 * ��ΪJava��Э�����飬����.net������ߵĵ�һ���汾ʱ��Ҳ��ȡͬ���Ĳ��ԡ���Ȼ���������Java����һ�����ϵġ�覴á�
	 * 
	 * ����2��Ϊʲô���Ͳ�֧��Э���ԣ�
	 * �����������Ϊ����������ʧ�ܣ������ڱ���ʱ��ʧ�ܡ�����̬�������Ե�ȫ���������ڴ�������ǰ�ҳ�����
	 */
	@Test
	public void testPrintList () {
		List<Integer> lst = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		
//		printList(lst);						// ??????? why ��֧��
		
		List<Number> lst2 = new ArrayList<>();
		lst2.add(1);
		lst2.add(2);
		
		printList(lst2);					// why ֧��
	}
	
/************************************* ���ͱ߽� **************************************************/	
	
	/**
	 * ���Է����Ͻ�
	 * �Ͻ�<? extends Animal>�涨��ֻ��ȡ(get)���������(add)��
	 * @param lst
	 */
	public static void printList2 (List<? extends Number> lst) {
		
//		lst.add(10.0);					// ??? why ��֧��
		
		System.out.println(lst);
	}	
	
	@Test
	public void testPrintList2 () {
		List<Integer> lst = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		
		printList2(lst);						// ??????? why ֧��
												
		List<Number> lst2 = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		printList2(lst2);
	}
	
}





