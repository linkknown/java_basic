package com.linkknown.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

/**
 * 测试 Set 无序集合
 * 
 * @author Administrator
 *
 */
public class SetTest {

	/**
	 * 测试 add 方法
	 * 
	 * HashSet 底层是使用 HashMap 实现的
	 *  public HashSet() {
     *		map = new HashMap<>();
     *	}
	 */
	@Test
	public void testHashSet01() {
		String[] strArr = new String[] { "tom", "bob", "tom", "smith", "null", null, null };
		Set<String> set = new HashSet<>();
		for (String s : strArr) {
			set.add(s);
		}
		System.out.println(set);
	}

	/**
	 * 测试 addAll 方法
	 */
	@Test
	public void testHashSet02() {
		String[] strArr = new String[] { "tom", "bob", "tom", "smith", "null", null, null };
		Set<String> set = new HashSet<>();
		set.addAll(Arrays.asList(strArr));
		System.out.println(set);
	}

	/**
	 * 测试 contains\containsAll\... 等方法
	 */
	@Test
	public void testHashSet03() {
		String[] strArr = new String[] { "tom", "bob", "tom", "smith", "null", null, null };
		Set<String> set = new HashSet<>();
		set.addAll(Arrays.asList(strArr));
		System.out.println(set.contains("tom"));
		System.out.println(set.containsAll(Arrays.asList(strArr)));
		System.out.println(set.size());
		System.out.println(set.isEmpty());
	}

	/**
	 * Set 遍历 
	 * Ctrl + 数字 1 快速给行生成返回值
	 */
	@Test
	public void testHashSet04() {
		Set<String> set = new HashSet<>(Arrays.asList(new String[] { "tom", "bob", "tom", "smith", "null", null, null }));
		
//		set 不支持普通 for 循环
//		for (int i=0; i<set.size(); i++) {
//			set.get(i);
//		}
		
		// 增强 for 循环
		for (String s : set) {
			System.out.println(s);
		}
		
		System.out.println();

		// 迭代器
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String entry = iterator.next();
			System.out.println(entry);
		}
		
		set.forEach(str -> System.out.println(str));
	}
	
	/**
	 * 测试 HashSet 无序性、不可重复性、可为 null
	 */
	@Test
	public void testHashSetRepeatAndOrder () {
		Set<String> set = new HashSet<>();
		set.add("hello");
		set.add("world");
		set.add(null);
		set.add("hello");
		set.add("world");
		set.add(null);
		
		System.out.println(set);
	}
	
	/**
	 * LinkedHashSet 是有序的
	 */
	@Test
	public void testHashSetRepeatAndOrder2 () {
		Set<String> set = new LinkedHashSet<>();
		set.add("hello");
		set.add("world");
		set.add(null);
		set.add("hello");
		set.add("world");
		set.add(null);
		
		System.out.println(set);
	}
	
	/**
	 * 测试 equals 和 hashCode
	 */
	@Test
	public void testEq () {
		Set<Person> personSet = new HashSet<>();
		personSet.add(new Person("张三", 18));
		personSet.add(new Person("张三", 18));
		personSet.add(new Person("张三", 18));
		personSet.add(new Person("张三", 18));
		
		System.out.println(personSet);					// 四个不同对象
		System.out.println();
		
		// Person2 重写了 equals 和 hashCode 方法
		Set<Person2> personSet2 = new HashSet<>();
		personSet2.add(new Person2("张三", 18));
		personSet2.add(new Person2("张三", 18));
		personSet2.add(new Person2("张三", 18));
		personSet2.add(new Person2("张三", 18));
		
		System.out.println(personSet2);					// 一个相同对象
		
		// Person2 重写了 equals 和 hashCode 方法
		Set<Person3> personSet3 = new HashSet<>();
		personSet3.add(new Person3("张三", 18));
		personSet3.add(new Person3("张三", 19));
		personSet3.add(new Person3("张三", 20));
		personSet3.add(new Person3("张三", 21));
		
		System.out.println(personSet3);					// 一个相同对象,但是只比较 name 属性
	}
	

	
	/**
	 * 测试 HashSet 和 LinkedHashSet\TreeSet 顺序
	 * Java中Set真的是无序的吗
	 * 我们经常听说List是有序且可重复的，Set是无序且不重复的。这是一个误区，这里所说的顺序有两个概念，一是按照添加的顺序排列，二是按，照自然顺序a-z排列。
	 * Set并不是无序的传统所说的Set无序指的是HashSet，它不能保证元素的添加顺序，更不能保证自然顺序，而Set的其他实现类是可以实现这两种顺序的。
	 */
	@Test
	public void testOrder () {
		Set<String> hashSet = new HashSet<>();
		Set<String> linkedHashSet = new LinkedHashSet<>();
		Set<String> treeSet = new TreeSet<>();
		
		for (int i = 0; i< 100; i++) {
			hashSet.add("num_" + i);
			linkedHashSet.add("num_" + i);
			treeSet.add("num_" + i);
		}

//		[num_87, num_88, num_89, num_80, num_81, num_82, num_83, num_84, num_85, num_86, num_10, num_98, num_11, num_99, num_12, num_13, num_14, num_15, num_16, num_17, num_90, num_91, num_92, num_93, num_94, num_95, num_96, num_97, num_65, num_66, num_67, num_68, num_69, num_60, num_61, num_62, num_63, num_64, num_76, num_77, num_78, num_79, num_70, num_71, num_72, num_73, num_74, num_75, num_43, num_44, num_45, num_46, num_9, num_47, num_48, num_49, num_40, num_41, num_42, num_54, num_55, num_56, num_57, num_58, num_59, num_50, num_51, num_52, num_53, num_21, num_22, num_23, num_24, num_25, num_26, num_27, num_28, num_20, num_18, num_19, num_32, num_33, num_34, num_35, num_36, num_37, num_38, num_39, num_30, num_31, num_0, num_5, num_29, num_6, num_7, num_8, num_1, num_2, num_3, num_4]
//		[num_0, num_1, num_2, num_3, num_4, num_5, num_6, num_7, num_8, num_9, num_10, num_11, num_12, num_13, num_14, num_15, num_16, num_17, num_18, num_19, num_20, num_21, num_22, num_23, num_24, num_25, num_26, num_27, num_28, num_29, num_30, num_31, num_32, num_33, num_34, num_35, num_36, num_37, num_38, num_39, num_40, num_41, num_42, num_43, num_44, num_45, num_46, num_47, num_48, num_49, num_50, num_51, num_52, num_53, num_54, num_55, num_56, num_57, num_58, num_59, num_60, num_61, num_62, num_63, num_64, num_65, num_66, num_67, num_68, num_69, num_70, num_71, num_72, num_73, num_74, num_75, num_76, num_77, num_78, num_79, num_80, num_81, num_82, num_83, num_84, num_85, num_86, num_87, num_88, num_89, num_90, num_91, num_92, num_93, num_94, num_95, num_96, num_97, num_98, num_99]
//		[num_0, num_1, num_10, num_11, num_12, num_13, num_14, num_15, num_16, num_17, num_18, num_19, num_2, num_20, num_21, num_22, num_23, num_24, num_25, num_26, num_27, num_28, num_29, num_3, num_30, num_31, num_32, num_33, num_34, num_35, num_36, num_37, num_38, num_39, num_4, num_40, num_41, num_42, num_43, num_44, num_45, num_46, num_47, num_48, num_49, num_5, num_50, num_51, num_52, num_53, num_54, num_55, num_56, num_57, num_58, num_59, num_6, num_60, num_61, num_62, num_63, num_64, num_65, num_66, num_67, num_68, num_69, num_7, num_70, num_71, num_72, num_73, num_74, num_75, num_76, num_77, num_78, num_79, num_8, num_80, num_81, num_82, num_83, num_84, num_85, num_86, num_87, num_88, num_89, num_9, num_90, num_91, num_92, num_93, num_94, num_95, num_96, num_97, num_98, num_99]
		System.out.println(hashSet);			// HashSet 是无序的
		System.out.println(linkedHashSet);		// LinkedHashSet 是有序的，按照添加顺序
		System.out.println(treeSet);			// TreeSet 是有序的, 按照自然顺序
	}
	
	
	/**
	 * 测试 TreeSet 定制排序, 定制排序需要实现 Comparable 接口
	 */
	@Test
	public void testOrder2 () {
		Set<Person> treeSet = new TreeSet<>();
		
		for (int i = 0; i< 100; i++) {
			treeSet.add(new Person("Person_" + i, 100 - i));
		}
		
		// [Person [name=Person_9, age=91], Person [name=Person_8, age=92], Person [name=Person_7, age=93], Person [name=Person_6, age=94], Person [name=Person_5, age=95], Person [name=Person_4, age=96], Person [name=Person_3, age=97], Person [name=Person_2, age=98], Person [name=Person_1, age=99], Person [name=Person_0, age=100], Person [name=Person_99, age=1], Person [name=Person_98, age=2], Person [name=Person_97, age=3], Person [name=Person_96, age=4], Person [name=Person_95, age=5], Person [name=Person_94, age=6], Person [name=Person_93, age=7], Person [name=Person_92, age=8], Person [name=Person_91, age=9], Person [name=Person_90, age=10], Person [name=Person_89, age=11], Person [name=Person_88, age=12], Person [name=Person_87, age=13], Person [name=Person_86, age=14], Person [name=Person_85, age=15], Person [name=Person_84, age=16], Person [name=Person_83, age=17], Person [name=Person_82, age=18], Person [name=Person_81, age=19], Person [name=Person_80, age=20], Person [name=Person_79, age=21], Person [name=Person_78, age=22], Person [name=Person_77, age=23], Person [name=Person_76, age=24], Person [name=Person_75, age=25], Person [name=Person_74, age=26], Person [name=Person_73, age=27], Person [name=Person_72, age=28], Person [name=Person_71, age=29], Person [name=Person_70, age=30], Person [name=Person_69, age=31], Person [name=Person_68, age=32], Person [name=Person_67, age=33], Person [name=Person_66, age=34], Person [name=Person_65, age=35], Person [name=Person_64, age=36], Person [name=Person_63, age=37], Person [name=Person_62, age=38], Person [name=Person_61, age=39], Person [name=Person_60, age=40], Person [name=Person_59, age=41], Person [name=Person_58, age=42], Person [name=Person_57, age=43], Person [name=Person_56, age=44], Person [name=Person_55, age=45], Person [name=Person_54, age=46], Person [name=Person_53, age=47], Person [name=Person_52, age=48], Person [name=Person_51, age=49], Person [name=Person_50, age=50], Person [name=Person_49, age=51], Person [name=Person_48, age=52], Person [name=Person_47, age=53], Person [name=Person_46, age=54], Person [name=Person_45, age=55], Person [name=Person_44, age=56], Person [name=Person_43, age=57], Person [name=Person_42, age=58], Person [name=Person_41, age=59], Person [name=Person_40, age=60], Person [name=Person_39, age=61], Person [name=Person_38, age=62], Person [name=Person_37, age=63], Person [name=Person_36, age=64], Person [name=Person_35, age=65], Person [name=Person_34, age=66], Person [name=Person_33, age=67], Person [name=Person_32, age=68], Person [name=Person_31, age=69], Person [name=Person_30, age=70], Person [name=Person_29, age=71], Person [name=Person_28, age=72], Person [name=Person_27, age=73], Person [name=Person_26, age=74], Person [name=Person_25, age=75], Person [name=Person_24, age=76], Person [name=Person_23, age=77], Person [name=Person_22, age=78], Person [name=Person_21, age=79], Person [name=Person_20, age=80], Person [name=Person_19, age=81], Person [name=Person_18, age=82], Person [name=Person_17, age=83], Person [name=Person_16, age=84], Person [name=Person_15, age=85], Person [name=Person_14, age=86], Person [name=Person_13, age=87], Person [name=Person_12, age=88], Person [name=Person_11, age=89], Person [name=Person_10, age=90]]
		System.out.println(treeSet);
	}
}
