package com.linkknown.array;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class ArrayTest {

	/**
	 * ��������ļ��ַ�ʽ
	 */
	public void testArray() {
		int[] intArr; // ��������
		intArr = new int[3]; // new ��ʱ�������ǿ�ƿ����ڴ�
		
		
		// ��ʽ1
		int[] intArr01 = new int[3];
		// ��ʽ2
		int intArr02[] = new int[3];
		// ��ʽ3
		int[] intArr03 = new int[] {1,2,3};
		// ��ʽ4
		int[] intArr04 = {1,2,3};
	}

	/**
	 * ��������Ĵ���
	 * �����Ĭ��ֵ
	 */
	@Test
	public void testArray1() {
		// ��������Ԫ��Ĭ������ֵ
		int[] arr = new int[3];

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		// Ӧ������Ԫ��Ĭ���� null
		Integer[] arr2 = new Integer[3];
		for (int i = 0; i < arr2.length; i++) {
			System.out.println(arr2[i]);
		}

		// �������鲢��ʼ������Ԫ��
		String[] strArr = new String[] {"hello", "world"};
		System.out.println(strArr);
		System.out.println(Arrays.toString(strArr));
	}

	/**
	 * ����Ԫ�ظ�ֵ
	 */
	@Test
	public void testArray2() {
		int[] intArr = new int[3];
		intArr[0] = 10;
		intArr[1] = 20;
		intArr[2] = 30;
		// intArr[3] = 40; // �����±�Խ��

		for (int i = 0; i < intArr.length; i++) {
			System.out.println(intArr[i]); // ��ȡ����Ԫ��
		}
	}

	/**
	 * ��ϰ������1��100֮������������ɵ����鲢�����Ҫ��ÿ10��һ�����
	 */
	@Test
	public void testArray3() {
		int[] arr = new int[50];
		for (int i = 0; i < 50; i++) {
			arr[i] = i * 2 + 1;
		}

		for (int i = 0; i < arr.length; i++) {
			if (i != 0 && i % 10 == 0) {
				System.out.println();
			}
			System.out.print(arr[i] + "\t"); // \t��ʾ tab ��
		}
	}

	/**
	 * �ַ���ת����
	 */
	@Test
	public void testStringToArr() {
		String str = "helloworld"; 
		char[] chars = str.toCharArray();
		byte[] bytes = str.getBytes();
		System.out.println(chars.length);
		System.out.println(bytes.length);

		str = "��� Java"; // GBK��һ�����������ֽ�  UTF-8��һ�����������ֽ�
		chars = str.toCharArray();
		bytes = str.getBytes();
		System.out.println(chars.length);
		System.out.println(bytes.length);
		
		System.out.println(Charset.defaultCharset().toString());
	}

	/**
	 * ð������ �ܽ�ǰ���ͼ�⣬���鳤����Ϊn����㹲ѭ����n-1�Σ����ѭ������һ�Σ���Ӧ�ڲ�ѭ���� ����һ�Ρ� ���ѭ��Ϊ��for (int i = 0;
	 * i < arr.length-1; i++) �ڲ�ѭ��Ϊ��for (int j = 0; j < arr.length - 1 - i; j++)
	 */
	@Test
	public void testSort() {
		int[] arr = { 8, 7, 6, 4, 5 };

		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		for (int num : arr) {
			System.out.println(num);
		}
	}

	@Test
	public void testSort2() {
		int[] arr = { 8, 7, 6, 4, 5 }; // ������ʹ�����ַ�ʽ��д

		Arrays.sort(arr);

		for (int num : arr) {
			System.out.println(num);
		}
	}

	@Test
	public void testSort3() {
		Integer[] arr = { 8, 7, 6, 4, 5 };

		Arrays.sort(arr, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// return o1 - o2;
				return o2 - o1;
			}
		});

		for (int num : arr) {
			System.out.println(num);
		}
	}

	/**
	 * �������鿽����ʽ
	 * forѭ����clone()��System.arraycopy()��Arrays.copyof()
	 * ������ֵ���͡�������������
	 */
	@Test	
	public void testCopy () {
		int[] array = {1,2,3,4,5,6};
		int[] copyArray = new int[array.length];
		for (int i=0; i<array.length; i++) {
			copyArray[i] = array[i];
		}
		
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(copyArray));
	
		array[0] = 9;
		
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(copyArray));
		
		
		String[] strArray = {"hello", "world"};
		String[] copyStrArray = new String[strArray.length];
		for (int i=0; i<strArray.length; i++) {
			copyStrArray[i] = strArray[i];
		}
		
		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(copyStrArray));
	
		strArray[0] = "java";
		
		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(copyStrArray));
	}

	@Test
	public void testCopy2() {
		int[] array = { 1, 2, 3, 4, 5, 6 };
		int[] cloneArray = array.clone();

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(cloneArray));

		array[0] = 9;

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(cloneArray));

		String[] strArray = { "hello", "world" };
		String[] cloneStrArray = strArray.clone();

		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(cloneStrArray));

		strArray[0] = "java";

		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(cloneStrArray));
	}

	@Test
	public void testCopy3() {
		int[] array = { 1, 2, 3, 4, 5, 6 };
		int[] copyArray = new int[array.length];
		System.arraycopy(array, 0, copyArray, 0, array.length);

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(copyArray));

		array[0] = 9;

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(copyArray));

		String[] strArray = { "hello", "world" };
		String[] copyStrArray = new String[strArray.length];
		System.arraycopy(strArray, 0, copyStrArray, 0, strArray.length);

		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(copyStrArray));

		strArray[0] = "java";

		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(copyStrArray));
	}

	@Test
	public void testCopy4() {
		int[] array = { 1, 2, 3, 4, 5, 6 };
		int[] copyArray = Arrays.copyOf(array, array.length);

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(copyArray));

		array[0] = 9;

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(copyArray));

		String[] strArray = { "hello", "world" };
		String[] copyStrArray = Arrays.copyOf(strArray, strArray.length);

		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(copyStrArray));

		strArray[0] = "java";

		System.out.println(Arrays.toString(strArray));
		System.out.println(Arrays.toString(copyStrArray));
	}

	/**
	 * ���Է�Χ����
	 */
	@Test
	public void testCopyRange() {
		int[] array = { 1, 2, 3, 4, 5, 6 };
		System.out.println(Arrays.toString(Arrays.copyOfRange(array, 0, 3)));
	}

	/**
	 * ��ά���鴴���ͱ���
	 */
	@Test
	public void test2Array() {
		int[][] array = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7 }, { 8, 9, 10 } }; // �����ά�����е�ÿ�е�Ԫ�ظ��������
		System.out.println(Arrays.toString(array));

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.println(array[i][j]);
			}
		}
	}

	/**
	 * ��˾�����۶���� ĳ��˾���ռ��Ⱥ��·�ͳ�Ƶ��������£���λ(��Ԫ) ��һ���ȣ�22,66,44 �ڶ����ȣ�77,33,88 �������ȣ�25,45,65
	 * ���ļ��ȣ�11,66,99
	 */
	@Test
	public void test2Array2() {
		int[][] arr = new int[][] { { 22, 66, 44 }, { 77, 33, 88 }, { 25, 45, 65 }, { 11, 66, 99 } };
		// ��λ���������Ԫ��������Ҫ����ȣ��ڶ�����ֻ��һ����ӯ���ĳ�����
//		int[][] arr = new int[][] { { 22, 66, 44 }, { 77 }, { 25, 45, 65 }, { 11, 66, 99 } };

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sum += arr[i][j];
			}
		}
		System.out.println(sum);
	}

}
