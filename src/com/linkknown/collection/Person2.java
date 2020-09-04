package com.linkknown.collection;

/**
 * ��д equals �� hashCode ����ԭ��
 * 
 * java��Ϊʲô��дequals����һ��Ҫ��дhashcode����
 * ��Ϊ�����������дequals������ͬʱ������hashcode����������д�Ļ���Ĭ�ϵػ��ǻ�ʹ��Object���Դ���hashcode�����������ͻ������ĳЩ����£�
 * �������������equals�����ж�����ˣ��������ǵ�hashcode��Ȼ��һ�������ǲ����Ϲ淶�ġ�����hashcode��Java�������¹涨��
 * ����������ȣ�hashcodeһ����� 
 * �������󲻵ȣ� hashcode��һ������ 
 * hashcode��ȣ���������һ�����
 * hashcode���ȣ���������һ������
 * 
 * hashcode��������ɢ�����ݵĿ��ٴ�ȡ��������ʹ��hash�����ݼ���ʱ�������ȸ��ݴ洢�Ķ����hashcodeֵȥ�ж϶����Ƿ���ͬ��
 * ����������дhashcode�����Ļ����ᵼ���ж϶�����ͬ��ʱ������equals�����ж�����ˣ�hashcodeȴ�жϲ���ȣ��ͻ�����ڲ�ͬ��λ���п��Դ��������ͬ�Ķ�����Ͳ������ˡ�
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
	 * hashCode()��Ĭ��ʵ����Ϊ��ͬ�Ķ��󷵻ز�ͬ������.��һ�����ԭ����,hashCode����ͬһ������,�����ڲ���ô�ı�,Ӧ�ö�������ͬ������ֵ.
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
