package com.linkknown.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * ���� List ���򼯺�
 * 
 * @author Administrator
 *
 */
public class ListTest {

	/**
	 * ���� add ����,�ײ� ensureCapacityInternal(size + 1) ��������
	 * 
	 *   public boolean add(E e) {
	 *       ensureCapacityInternal(size + 1);  // Increments modCount!!
	 *       elementData[size++] = e;
	 *       return true;
	 *   }
	 *   
	 */
	@Test
	public void testArrayList01() {
		List<Integer> lst = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			lst.add(i);
		}

		System.out.println(lst);
	}

	/**
	 * ���� addAll ����
	 */
	@Test
	public void testArrayList02() {
		List<Integer> lst = new ArrayList<>();

		lst.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		System.out.println(lst);
	}

	/**
	 * ���� list �����ԡ����ظ��ԡ����Է��� null
	 */
	@Test
	public void testListRepeat () {
		List<String> lst = new ArrayList<>();
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		System.out.println(lst);
		
		lst = new LinkedList<>();
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		lst.add("hello");
		lst.add("world");
		lst.add(null);
		System.out.println(lst);
	}
	
	/**
	 * ���� contains\containsAll\... �ȷ���
	 */
	@Test
	public void testArrayList03() {
		List<Integer> lst = new ArrayList<>();

		lst.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		System.out.println(lst.contains(4));
		System.out.println(lst.containsAll(Arrays.asList(11, 12)));
		System.out.println(lst.size());
		System.out.println(lst.isEmpty());
		System.out.println(lst.subList(0, 5));
		System.out.println(lst.subList(0, lst.size()));
		System.out.println(lst.subList(0, lst.size() - 1));
	}

	/**
	 * List ����
	 */
	@Test
	public void testArrayList04() {
		List<Integer> lst = new ArrayList<>();

		lst.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		// ��ͨ for ѭ��
		for (int i=0; i<lst.size(); i++) {
			System.out.println(lst.get(0));
		}
		
		// ��ǿ for ѭ��
		for (int num : lst) {
			System.out.println(num);
		}

		System.out.println();

		// ������
		Iterator<Integer> iterator = lst.iterator();
		while (iterator.hasNext()) {
			int entry = iterator.next();
			System.out.println(entry);
		}

		System.out.println();

		// forEach ����
		lst.forEach(num -> System.out.println(num));
	}

	/**
	 * �������顢arraylist��linkedlist ������ʺͱ������ܱȽ�
	 */
	@Test
	public void testArrayListAndLinkedList() {
		final int MAX = 100000;				// �ֱ���� 10��100��1000��10000��100000 ������
		long start, end;
		int arr[] = new int[MAX];
		ArrayList<Integer> arraylist = new ArrayList<>();
		LinkedList<Integer> linklist = new LinkedList<>();
		/* ��ʼ�������������� */
		for (int i = 0; i < MAX; i++) {
			arr[i] = i;
			arraylist.add(i);
			linklist.add(i);
		}
		/* ������ʲ��� */
		System.out.println("������ʲ���(" + MAX + "):");
		start = System.nanoTime();
		int tmp;
		for (int i = 0; i < MAX; i++)
			tmp = arr[i];
		end = System.nanoTime();
		System.out.println("����:    		" + (end - start));
		start = System.nanoTime();
		for (int i = 0; i < MAX; i++)
			tmp = arraylist.get(i);
		end = System.nanoTime();
		System.out.println("arraylist:	" + (end - start));
		start = System.nanoTime();
		for (int i = 0; i < MAX; i++)
			tmp = linklist.get(i);
		end = System.nanoTime();
		System.out.println("linkedlist:	" + (end - start));
		/* �������� */
		System.out.println("��������(" + MAX + "):");
		start = System.nanoTime();
		for (int e : arr)
			tmp = e;
		end = System.nanoTime();
		System.out.println("����:    		" + (end - start));
		start = System.nanoTime();
		for (int e : arraylist)
			tmp = e;
		end = System.nanoTime();
		System.out.println("arraylist:	" + (end - start));
		start = System.nanoTime();
		for (int e : linklist)
			tmp = e;
		end = System.nanoTime();
		System.out.println("linkedlist:	" + (end - start));
	}
	
	
	/**
	 * arraylist��linkedlist ��ʹ�ó���
	 */
	@Test
	public void testArrayListAndLinkedList2 () {
		// ʹ�� List �洢��� 30 �������
		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();
		
		long start = System.nanoTime();
		
		for (int i=0; i< 365; i++) {
			arrayList.add(new Random().nextInt(40));
			if (arrayList.size() > 30) {
				arrayList.remove(0);
			}
		}
		
		long end = System.nanoTime();
		System.out.println(end - start);
	
		start = System.nanoTime();
		
		for (int i=0; i< 365; i++) {
			linkedList.add(new Random().nextInt(40));
			if (linkedList.size() > 30) {
				linkedList.remove(0);
			}
		}
		
		end = System.nanoTime();
		System.out.println(end - start);
	}
	
	
	/**
	 * �Ż���֪�����ݳ��ȵ�ʱ�������Ż�
	 * ��ʼ����ʱ��ָ�����ȿ϶���Ҫ�Ȳ�ָ�����ȵ����ܺúܶ�, ���������ظ�������ռ�, ��������, �����ϵķ���ռ�
	 */
	@Test
	public void testArrayList05 () {
		long start = System.nanoTime();
		
		List<String> lst = new ArrayList<>();
		for (int i=0; i< 1000; i++) {
			lst.add("hello");
		}
		
		long end = System.nanoTime();
		System.out.println(end - start);
		
		
		start = System.nanoTime();
		
		// ���� List ��ʱ��ָ������
		lst = new ArrayList<>(1000);
		for (int i=0; i< 1000; i++) {
			lst.add("hello");
		}
		end = System.nanoTime();
		
		System.out.println(end - start);
	}
	
	/**
	 * ���ó�ʼ���ȵ�ʱ��Ҫ����,С�˻��Զ����ݣ����˻��˷�
	 */
	@Test
	public void testArrayList06 () {
		List<String> lst = new ArrayList<>(100);
		for (int i=0; i< 1000; i++) {
			lst.add("hello");
		}
		System.out.println(lst.size());
	}

	/**
	 * ����ʱɾ��Ԫ��
	 * ʹ�õ�������ɾ���ɿ�Щ
	 */
	@Test
	public void testRemove () {
		// ������� 100 ���ɼ���0 ~ 100 ��
		List<String> lst = new ArrayList<>();
		for (int i=0; i< 100; i++) {
			lst.add(new Random().nextInt(100) + "");
		}
		
		System.out.println(lst); 
		
		// ��ǿ for ѭ��ɾ���ᱨ��java.util.ConcurrentModificationException
//		for(String s: lst){  
//		    if(Integer.parseInt(s) < 60){  
//		    	// ����ÿһ���ɼ���Ȼ���޳�������ĳɼ�
//		        lst.remove(s);  
//		    }  
//		} 
//		
		// ��ͨ for ѭ��Ҳɾ�����ɾ����д�λ����
		for(int i=0; i<lst.size(); i++){  
			String s = lst.get(i);
		    if(Integer.parseInt(s) < 60){  
		    	// ����ÿһ���ɼ���Ȼ���޳�������ĳɼ�
		        lst.remove(s);  
		    }  
		} 
		System.out.println(lst); 
	}
	
	/**
	 * ����ʱɾ��Ԫ��
	 * ʹ�õ�������ɾ���ɿ�Щ
	 * 
	 * 
	 * ������ArrayList����size������ά�����ѵ�״̬����Iterator����cursor����ά�����ѵ�״̬��
	 * ��size���ֱ仯ʱ��cursor����һ���ܹ��õ�ͬ�����������ֱ仯��Iterator�������µġ�
	 * ������Ĵ�����Կ�����Iterator.remove��������ArrayList�б����仯ʱ���������cursor��ͬ����һ�仯��
	 * ��������ʽ���µ�ArrayList�仯��Iterator���޷���֪�ġ�ArrayList��ȻҲ��������֪ͨIterator�ǣ��ǽ���һ�����صĹ�����
	 * Iterator���׻�������Ŭ����Ϊ�˷�ֹ״̬��һ�¿����������޷�����ĺ����Iterator�ᾭ����checkForComodification��飬
	 * �Է��б䡣����б䣬�����쳣�׳������Ծͳ�����������쳣��
	 * 
	 */
	@Test
	public void testRemove2 () {
		List<String> lst = new ArrayList<>();
		for (int i=0; i< 100; i++) {
			lst.add(new Random().nextInt(100) + "");
		}
		
		System.out.println(lst);
		Iterator<String> iterator = lst.iterator();
		while (iterator.hasNext()) {
			String number = iterator.next();
			if (Integer.parseInt(number) < 60) {
				iterator.remove();
			}
		}
		System.out.println(lst);
	}
}
