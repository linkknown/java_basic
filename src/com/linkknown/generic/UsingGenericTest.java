package com.linkknown.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * 
 * 使用泛型场景
 * @author Administrator
 *
 */
public class UsingGenericTest {

	/**
	 * 1. 解决元素存储的安全性问题
	 * 2. 解决获取数据元素时，需要类型强转的问题
	 */
	@Test
	public void testUsingGeneric () {
		List<Bird> lst = new ArrayList<>();
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
		lst.add(new Bird());
//		lst.add(new Animal());
	
		for (int i=0; i<lst.size(); i++) {
			Bird bird = lst.get(i);
			bird.fly();
		}
	}
	
	
	private <T> T generateRandom (T[] objectArr) {
		Random random = new Random();
		int randomIndex = random.nextInt(objectArr.length);
		return objectArr[randomIndex];
	}
	
	/**
	 * 1. 解决元素存储的安全性问题
	 * 2. 解决获取数据元素时，需要类型强转的问题
	 */
	@Test
	public void testNoGeneric3 () {
		Bird bird = generateRandom(new Bird[] {new Bird(), new Bird()});
		bird.fly();
		
		String str = generateRandom(new String[] {"hello", "world"});
		System.out.println(str);
	}
	
	/**
	 * 此处的 HelloWorld 也是泛型,不一定非得是 ？ T K V E
	 * 只是建议使用  ？ T K V E 约定，通俗易懂
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
	 * 使用大写字母A,B,C,D......X,Y,Z定义的，就都是泛型，把T换成A也一样，这里T只是名字上的意义而已
	 */
	@Test
	public void testNoGeneric4 () {
		Bird bird = generateRandom2(new Bird[] {new Bird(), new Bird()});
		bird.fly();
	}
}
