package com.linkknown.generic;

import java.lang.reflect.InvocationTargetException;
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
	
/**************************************** ���Բ�ʹ�÷��ͻ��ƣ�JDK1.4�� *********************************************/
	
	/**
	 * 1. ���Ԫ�ش洢�İ�ȫ������
	 * 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
	 */
	@Test
	public void testNoGeneric () {
		List lst = new ArrayList();
		
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		
//		lst.add(new String("helloworld"));
		
//		for (int i=0; i<lst.size(); i++) {
//			Object object = lst.get(i);
//			Date date = (Date) object;
//			System.out.println(date);
//		}
		
		foreachList(lst);
	}


	private void foreachList(List lst) {
		for (int i=0; i<lst.size(); i++) {
			Object object = lst.get(i);
			Date date = (Date) object;
			System.out.println(date.getTime());
		}
	}
	
	
	private String generateRandom (String[] strArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(strArr.length);
		return strArr[randomIndex];
	}
	
	private Integer generateRandom (Integer[] intArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(intArr.length);
		return intArr[randomIndex];
	}
	
	private Date generateRandom (Date[] dateArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(dateArr.length);
		return dateArr[randomIndex];
	}
	
	/**
	 * generateRandom �������ع���,��ͨ��
	 */
	@Test
	public void testNoGeneric2 () {
		System.out.println(generateRandom(new String[] {"hello", "world"}));
		System.out.println(generateRandom(new Integer[] {1, 2}));
		System.out.println(generateRandom(new Date[] {new Date(), new Date()}));
	}
	
	private Object generateRandomAdjust (Object[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * ���ǵ�ǿת���޷���֤���Ͱ�ȫ
	 */
	@Test
	public void testNoGeneric3 () {
		Object obj = generateRandomAdjust(new Date[] {new Date(), new Date()});
		Date date = (Date) obj;
		System.out.println(date.getTime());
	}
	
	
/************************************* ʹ�÷��ͳ��� ************************************************/	
	
	/**
	 * 1. ���Ԫ�ش洢�İ�ȫ������
	 * 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
	 */
	@Test
	public void testUseGeneric () {
		List<Date> lst = new ArrayList<>();
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
		lst.add(new Date());
//		lst.add(new String("helloworld"));
	
		for (int i=0; i<lst.size(); i++) {
			Date date = lst.get(i);
			System.out.println(date.getTime());
		}
	}
	
	
	private <T> T generateRandomWithGeneric (T[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * 1. ���Ԫ�ش洢�İ�ȫ������
	 * 2. �����ȡ����Ԫ��ʱ����Ҫ����ǿת������
	 */
	@Test
	public void testUseGeneric2 () {
		Date date = generateRandomWithGeneric(new Date[] {new Date(), new Date()});
		System.out.println(date.getTime());
		
		String str = generateRandomWithGeneric(new String[] {"hello", "world"});
		System.out.println(str);
	}
	
	/**
	 * �˴��� HelloWorld Ҳ�Ƿ���,��һ���ǵ��� �� T K V E
	 * ֻ�ǽ���ʹ��  �� T K V E Լ����ͨ���׶�
	 * @param <HelloWorld>
	 * @param objectArr
	 * @return
	 */
	private <HelloWorld> HelloWorld generateRandom2 (HelloWorld[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * ʹ�ô�д��ĸA,B,C,D......X,Y,Z����ģ��Ͷ��Ƿ��ͣ���T����AҲһ��������Tֻ�������ϵ��������
	 */
	@Test
	public void testUseGeneric3 () {
		Date date = generateRandom2(new Date[] {new Date(), new Date()});
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
	 */
	@Test
	public void testGenericInterface() {
		Fibonacci fibonacci = new Fibonacci();
		for (int i = 0; i < 100; i++) {
			System.out.print(fibonacci.next() + " ");
		}
	}

	/**
	 * ���ͷ���
	 */
	@Test
	public void testGenericMethod() {
		ClassDesc classDesc = new ClassDesc();
		classDesc.getClassName(new String("helloworld"));
		ClassDesc.getClassName2(new Object());
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
			Map.Entry<K, V> entry = (Map.Entry<K, V>) iterator.next();
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
	}
	
	
	/**
	 * ͨ���
	 * @param lst
	 */
	public void print (List<?> lst) {
		System.out.println(lst);
	}
	
	/**
	 * ͨ�������
	 */
	@Test
	public void testQuestion () {
		print(Arrays.asList("hello", 1));
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

		// ���� true ��ʾ�����ڱ����ڱ�������
		System.out.println(lst1.getClass() == lst2.getClass());
	}

	/**
	 * ͨ�����������������Ԫ�� ��֤���Ͳ���
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testErase2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		ArrayList<String> lst = new ArrayList<>();
		lst.add("hello");

		lst.getClass().getMethod("add", Object.class).invoke(lst, 10);

		for (Object item : lst) {
			System.out.println(item);
		}
	}

	// ����һ���򵥵ķ��ͷ���
	public static <T> T random(T x, T y) {
		return y;
	}

	/**
	 * ���������ƶ�
	 */
	@Test
	public void test() {
		/** ��ָ�����͵�ʱ�� */
		Integer result1 = GenericTest.random(1, 2); // ��������������Integer������TΪInteger����
		Number result2 = GenericTest.random(1, 1.2); // ����������һ����Integer��һ�������Float������ȡͬһ�������С����ΪNumber
		Object random = GenericTest.random(1, "hello"); // ����������һ����Integer���Է����Float������ȡͬһ�������С����ΪObject

		/** ָ�����͵�ʱ�� */
		int a = GenericTest.<Integer>random(1, 2); // ָ����Integer������ֻ��ΪInteger���ͻ���������
//		int b = GenericEraseTest.<Integer>random(1, 2.2); // �������ָ����Integer������ΪFloat
		Number c = GenericTest.<Number>random(1, 2.2); // ָ��ΪNumber�����Կ���ΪInteger��Float
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





