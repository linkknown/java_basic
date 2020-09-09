package com.linkknown.oop;

import java.util.Random;

public class SecurityPerson {

	private String name;
	private int age;
	private int score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String regEx = "[\\s~��`!��@#��$%^����&*��()��\\-����\\-_=+��\\[\\]����{}��\\|��\\\\��;��:��'����\"��,��<��.��>��/��?]";
		this.name = name.replaceAll(regEx, "");
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age > 0 && age < 150 ? age : this.age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score >= 0 && score <= 150 ? score : this.score;
	}

	@Override
	public String toString() {
		return "SecurityPerson [name=" + name + ", age=" + age + ", score=" + score + "]";
	}
	
	/**
	 * �������ָ�������� person
	 * @param count
	 * @return
	 */
	public static SecurityPerson[] generateRandomPersons (int count) {
		SecurityPerson[] persons = new SecurityPerson[count];
		for (int i=0; i<count; i++) {
			SecurityPerson person = new SecurityPerson();
			person.setName("randomName_" + i);
			person.setAge(new Random().nextInt(150));
			person.setScore(new Random().nextInt(150));
			persons[i] = person;
		}
		return persons;
	}
	
	/**
	 * ���ݳɼ��ȼ��������һ�� person ʵ��
	 * @param name
	 * @return
	 */
	public SecurityPerson getPerson (String name, int age, String grade) {
		SecurityPerson person = new SecurityPerson();
		person.setName(name);
		person.setAge(age);
		switch (grade) {
		case "A":
			person.setScore(150);
			break;
		case "B":
			person.setScore(120);
		case "C":
			person.setScore(90);
		case "D":
			person.setScore(60);
			break;
		default:
			person.setScore(0);
			break;
		}
		return person;
	}


}
