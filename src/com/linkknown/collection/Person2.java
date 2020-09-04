package com.linkknown.collection;

/**
 * 重写 equals 和 hashCode 方法原则：
 * 
 * java中为什么重写equals方法一定要重写hashcode方法
 * 因为如果我们在重写equals方法的同时，不对hashcode方法进行重写的话，默认地还是会使用Object类自带的hashcode方法，这样就会出现在某些情况下，
 * 明明两个对象的equals方法判断相等了，但是它们的hashcode居然不一样，这是不符合规范的。对于hashcode，Java中有如下规定：
 * 两个对象相等，hashcode一定相等 
 * 两个对象不等， hashcode不一定不等 
 * hashcode相等，两个对象不一定相等
 * hashcode不等，两个对象一定不等
 * 
 * hashcode经常用于散列数据的快速存取，例如在使用hash类数据集合时，都是先根据存储的对象的hashcode值去判断对象是否相同，
 * 因此如果不重写hashcode方法的话，会导致判断对象相同的时候，明明equals方法判断相等了，hashcode却判断不相等，就会造成在不同的位置中可以存放两个相同的对象，这就不合理了。
 * 
 * @author Administrator
 *
 */
public class Person2 {

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

	public Person2(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person2() {
		super();
	}

	@Override
	public String toString() {
		return "Person2 [name=" + name + ", age=" + age + "]";
	}

	/**
	 * hashCode()的默认实现是为不同的对象返回不同的整数.有一个设计原则是,hashCode对于同一个对象,不管内部怎么改变,应该都返回相同的整数值.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person2 other = (Person2) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
