package com.linkknown.java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void testStream () {
        List<String> strList = Arrays.asList("a", "b", "c");
        // 集合创建流
        // 创建串行流
        Stream<String> stream1 = strList.stream();
        // 创建并行流
        Stream<String> stream2 = strList.parallelStream();

        String[] strArr = new String[]{"a", "b", "c"};
        // 数组创建流
        Stream<String> stream3 = Arrays.stream(strArr);

        // 工厂方法创建流
        Stream<String> stream4 = Stream.of("a", "b", "c");
        // 流转换成并行流
        Stream<String> parallelStream = stream4.parallel();
    }

    @Test
    public void testSteam2 () {
        List<String> strList = Arrays.asList("a", "b", "c", "", "d");
        // 过滤
        // Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
        strList = strList.stream().parallel().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        // 遍历
        strList.stream().forEach(str -> System.out.println(str));
        // 映射
        strList = strList.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());

        strList.stream().forEach(str -> System.out.println(str));
        // 限定数量
        strList = strList.stream().limit(3).collect(Collectors.toList());
        System.out.println(strList.size());
        // 排序
        strList = strList.stream().sorted((o1, o2) -> o2.compareTo(o1)).collect(Collectors.toList());
        System.out.println(strList);
    }

    @Test
    public void testParallel(){
        List<String> strList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        strList.stream().forEach(str -> {
            System.out.println(Thread.currentThread().getName() + " : " + str);
        });
        // 并行流会开启多线程执行
        strList.parallelStream().forEach(str -> {
            System.out.println(Thread.currentThread().getName() + " : " + str);
        });
    }
}
