package com.linkknown.collection;

public class Person implements Comparable<Person>{

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	/**
	 * 1、先按照名称排序,名称字符长度越长越靠后
	 * 2、再按照年龄排序，年龄越大越靠后
	 * 
	 * 负数代表小于，0 代表相等，1代表大于
	 * 
	 *  a negative integer, zero, or a positive integer as this object
     *  is less than, equal to, or greater than the specified object.
     *  
	 */
	@Override
	public int compareTo(Person o) {
		if (this.name.length() > o.name.length()) {
			return 1;
		}
		if (this.name.length() < o.name.length()) {
			return -1;
		}
		if (this.age > o.age) {
			return 1;
		}
		if (this.age < o.age) {
			return -1;
		}
		return 0;
	}

}
