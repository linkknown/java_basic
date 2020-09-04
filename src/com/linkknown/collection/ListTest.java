package com.linkknown.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * ���� List ���򼯺�
 * 
 * @author Administrator
 *
 */
public class ListTest {

	/**
	 * ���� add ����
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
	}

	/**
	 * List ����
	 */
	@Test
	public void testArrayList04() {
		List<Integer> lst = new ArrayList<>();

		lst.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

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
}
