package com.linkknown.collection;

public class Dog implements Comparable<Dog>{
	
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

	public Dog(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Dog() {
		super();
	}

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
		Dog other = (Dog) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + "]";
	}

	// 先按名字长度排序,相等再按年龄排序
	@Override
	public int compareTo(Dog o) {
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
