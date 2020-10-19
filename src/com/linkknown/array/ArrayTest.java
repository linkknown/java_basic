package com.linkknown.array;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class ArrayTest {

	/**
	 * 创建数组的几种方式
	 */
	public void testArray() {
		int[] intArr; // 声明数组
		intArr = new int[3]; // new 的时候给数组强制开辟内存
		
		
		// 方式1
		int[] intArr01 = new int[3];
		// 方式2
		int intArr02[] = new int[3];
		// 方式3
		int[] intArr03 = new int[] {1,2,3};
		// 方式4
		int[] intArr04 = {1,2,3};
	}

	/**
	 * 测试数组的创建
	 * 数组的默认值
	 */
	@Test
	public void testArray1() {
		// 基本类型元素默认是零值
		int[] arr = new int[3];

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		// 应用类型元素默认是 null
		Integer[] arr2 = new Integer[3];
		for (int i = 0; i < arr2.length; i++) {
			System.out.println(arr2[i]);
		}

		// 创建数组并初始化数组元素
		String[] strArr = new String[] {"hello", "world"};
		System.out.println(strArr);
		System.out.println(Arrays.toString(strArr));
	}

	/**
	 * 数组元素赋值
	 */
	@Test
	public void testArray2() {
		int[] intArr = new int[3];
		intArr[0] = 10;
		intArr[1] = 20;
		intArr[2] = 30;
		// intArr[3] = 40; // 数组下标越界

		for (int i = 0; i < intArr.length; i++) {
			System.out.println(intArr[i]); // 读取数组元素
		}
	}

	/**
	 * 练习：产生1到100之间所有奇数组成的数组并输出。要求每10个一行输出
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
			System.out.print(arr[i] + "\t"); // \t表示 tab 键
		}
	}

	/**
	 * 字符串转数组
	 */
	@Test
	public void testStringToArr() {
		String str = "helloworld"; 
		char[] chars = str.toCharArray();
		byte[] bytes = str.getBytes();
		System.out.println(chars.length);
		System.out.println(bytes.length);

		str = "你好 Java"; // GBK：一个中文两个字节  UTF-8：一个中文三个字节
		chars = str.toCharArray();
		bytes = str.getBytes();
		System.out.println(chars.length);
		System.out.println(bytes.length);
		
		System.out.println(Charset.defaultCharset().toString());
	}

	/**
	 * 冒泡排序 总结前面的图解，数组长度设为n。外层共循环了n-1次，外层循环增加一次，对应内层循环就 减少一次。 外层循环为：for (int i = 0;
	 * i < arr.length-1; i++) 内层循环为：for (int j = 0; j < arr.length - 1 - i; j++)
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
		int[] arr = { 8, 7, 6, 4, 5 }; // 不建议使用这种方式简写

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
	 * 四种数组拷贝方式
	 * for循环、clone()、System.arraycopy()、Arrays.copyof()
	 * 拷贝数值类型、拷贝引用类型
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
	 * 测试范围拷贝
	 */
	@Test
	public void testCopyRange() {
		int[] array = { 1, 2, 3, 4, 5, 6 };
		System.out.println(Arrays.toString(Arrays.copyOfRange(array, 0, 3)));
	}

	/**
	 * 二维数组创建和遍历
	 */
	@Test
	public void test2Array() {
		int[][] array = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7 }, { 8, 9, 10 } }; // 允许二维数组中的每行的元素个数不相等
		System.out.println(Arrays.toString(array));

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.println(array[i][j]);
			}
		}
	}

	/**
	 * 公司年销售额求和 某公司按照季度和月份统计的数据如下：单位(万元) 第一季度：22,66,44 第二季度：77,33,88 第三季度：25,45,65
	 * 第四季度：11,66,99
	 */
	@Test
	public void test2Array2() {
		int[][] arr = new int[][] { { 22, 66, 44 }, { 77, 33, 88 }, { 25, 45, 65 }, { 11, 66, 99 } };
		// 二位数组里面的元素数量不要求相等（第二季度只有一个月盈利的场景）
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
