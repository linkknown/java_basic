package com.linkknown.java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class DefaultMethodTest {

    @Test
    public void testDefaultMethod () {
        AddCalculator calculator = new AddCalculator();
        System.out.println(calculator.eval(1, 2));
    }


    @Test
    public void testMethodReference () {
        String[] strArr = new String[]{"c", "b", "d", "e", "a"};
        Arrays.sort(strArr, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strArr));
    }
}

// 计算器接口
interface Calculator<T> {
    T eval(T t1, T t2);
    default void log(T t1, T t2, T t3) {
        System.out.println(String.format("input %d, %d, output %d", t1, t2, t3));
    }
}

class AddCalculator implements Calculator<Integer> {

    @Override
    public Integer eval(Integer t1, Integer t2) {
        Integer result = t1 + t2;
        this.log(t1, t2, result);
        return result;
    }
}

class SubCalculator implements Calculator<Integer> {

    @Override
    public Integer eval(Integer t1, Integer t2) {
        Integer result = t1 - t2;
        this.log(t1, t2, result);
        return result;
    }
}