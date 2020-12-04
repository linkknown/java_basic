package com.linkknown.java8;


import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

public class NewTX {

	@Test
	public void testLamda () {
		List<String> names = Arrays.asList("tom", "bob", "smith", "john");

		Collections.sort(names, new Comparator<String>() {
		    @Override
		    public int compare(String a, String b) {
		        return b.compareTo(a);
		    }
		});
	}
	
	@Test
	public void testLamda2 () {
		List<String> names = Arrays.asList("tom", "bob", "smith", "john");

		Collections.sort(names, (String a, String b) -> {
	        return b.compareTo(a);
	    });
	}
	
	@Test
	public void testLamda3 () {
		List<String> names = Arrays.asList("tom", "bob", "smith", "john");

		Collections.sort(names, (String a, String b) -> b.compareTo(a));
	}
	
	@Test
	public void testLamda4 () {
		List<String> names = Arrays.asList("tom", "bob", "smith", "john");

		Collections.sort(names, (a, b) -> b.compareTo(a));
	}
	
	interface Converter<F, T> {
	    T convert(F from);
	}
	
	@Test
	public void testFunctionalInterface() {
		Converter<String, Integer> converter = new Converter<String, Integer>() {

			@Override
			public Integer convert(String from) {
				return Integer.valueOf(from);
			}
		};
	}
	
	@FunctionalInterface
	interface Converter2<F, T> {
	    T convert(F from);
	}
	
	@Test
	public void testFunctionalInterface2() {
		Converter2<String, Integer> converter = (from) -> Integer.valueOf(from);
	}
	


	
}
