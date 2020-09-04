package com.linkknown.collection;

import java.util.Arrays;
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
	 * 测试 set 去重 测试 add 方法
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
	 * 测试 set 去重 测试 addAll 方法
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
	 * Set 遍历 Ctrl + 数字 1 快速给行生成返回值
	 */
	@Test
	public void testHashSet04() {
		Set<String> set = new HashSet<>(Arrays.asList(new String[] { "tom", "bob", "tom", "smith", "null", null, null }));
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
		Set<Dog> treeSet = new TreeSet<>();
		
		for (int i = 0; i< 100; i++) {
			treeSet.add(new Dog("Dog_" + i, 100 - i));
		}
		
		// [Dog [name=Dog_9, age=91], Dog [name=Dog_8, age=92], Dog [name=Dog_7, age=93], Dog [name=Dog_6, age=94], Dog [name=Dog_5, age=95], Dog [name=Dog_4, age=96], Dog [name=Dog_3, age=97], Dog [name=Dog_2, age=98], Dog [name=Dog_1, age=99], Dog [name=Dog_0, age=100], Dog [name=Dog_99, age=1], Dog [name=Dog_98, age=2], Dog [name=Dog_97, age=3], Dog [name=Dog_96, age=4], Dog [name=Dog_95, age=5], Dog [name=Dog_94, age=6], Dog [name=Dog_93, age=7], Dog [name=Dog_92, age=8], Dog [name=Dog_91, age=9], Dog [name=Dog_90, age=10], Dog [name=Dog_89, age=11], Dog [name=Dog_88, age=12], Dog [name=Dog_87, age=13], Dog [name=Dog_86, age=14], Dog [name=Dog_85, age=15], Dog [name=Dog_84, age=16], Dog [name=Dog_83, age=17], Dog [name=Dog_82, age=18], Dog [name=Dog_81, age=19], Dog [name=Dog_80, age=20], Dog [name=Dog_79, age=21], Dog [name=Dog_78, age=22], Dog [name=Dog_77, age=23], Dog [name=Dog_76, age=24], Dog [name=Dog_75, age=25], Dog [name=Dog_74, age=26], Dog [name=Dog_73, age=27], Dog [name=Dog_72, age=28], Dog [name=Dog_71, age=29], Dog [name=Dog_70, age=30], Dog [name=Dog_69, age=31], Dog [name=Dog_68, age=32], Dog [name=Dog_67, age=33], Dog [name=Dog_66, age=34], Dog [name=Dog_65, age=35], Dog [name=Dog_64, age=36], Dog [name=Dog_63, age=37], Dog [name=Dog_62, age=38], Dog [name=Dog_61, age=39], Dog [name=Dog_60, age=40], Dog [name=Dog_59, age=41], Dog [name=Dog_58, age=42], Dog [name=Dog_57, age=43], Dog [name=Dog_56, age=44], Dog [name=Dog_55, age=45], Dog [name=Dog_54, age=46], Dog [name=Dog_53, age=47], Dog [name=Dog_52, age=48], Dog [name=Dog_51, age=49], Dog [name=Dog_50, age=50], Dog [name=Dog_49, age=51], Dog [name=Dog_48, age=52], Dog [name=Dog_47, age=53], Dog [name=Dog_46, age=54], Dog [name=Dog_45, age=55], Dog [name=Dog_44, age=56], Dog [name=Dog_43, age=57], Dog [name=Dog_42, age=58], Dog [name=Dog_41, age=59], Dog [name=Dog_40, age=60], Dog [name=Dog_39, age=61], Dog [name=Dog_38, age=62], Dog [name=Dog_37, age=63], Dog [name=Dog_36, age=64], Dog [name=Dog_35, age=65], Dog [name=Dog_34, age=66], Dog [name=Dog_33, age=67], Dog [name=Dog_32, age=68], Dog [name=Dog_31, age=69], Dog [name=Dog_30, age=70], Dog [name=Dog_29, age=71], Dog [name=Dog_28, age=72], Dog [name=Dog_27, age=73], Dog [name=Dog_26, age=74], Dog [name=Dog_25, age=75], Dog [name=Dog_24, age=76], Dog [name=Dog_23, age=77], Dog [name=Dog_22, age=78], Dog [name=Dog_21, age=79], Dog [name=Dog_20, age=80], Dog [name=Dog_19, age=81], Dog [name=Dog_18, age=82], Dog [name=Dog_17, age=83], Dog [name=Dog_16, age=84], Dog [name=Dog_15, age=85], Dog [name=Dog_14, age=86], Dog [name=Dog_13, age=87], Dog [name=Dog_12, age=88], Dog [name=Dog_11, age=89], Dog [name=Dog_10, age=90]]
		System.out.println(treeSet);
	}
}
