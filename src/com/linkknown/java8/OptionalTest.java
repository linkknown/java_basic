package com.linkknown.java8;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OptionalTest {

    @Test
    public void testOf () {
        System.out.println(Optional.empty());           // 空 Optional

        String nameStr = "zhangsan";
        // 调用 of 工厂方法创建 Optional 实例
        Optional<String> name = Optional.of(nameStr);

        nameStr = null;
        // of 方法参数传入 null，会报空指针异常
//        Optional<String> name2 = Optional.of(nameStr);

        // 和上面的方法相比，不会抛出空指针异常，会返回一个空的 Optional 对象
        Optional<String> name3 = Optional.ofNullable(nameStr);
    }

    @Test
    public void testIsPresent() {
        String nameStr = null;
        Optional<String> name = Optional.ofNullable(nameStr);
        System.out.println(name.isPresent());       // 是否存在
    }

    @Test
    public void testGet() {
        String nameStr = "zhangsan";
        Optional<String> name = Optional.ofNullable(nameStr);
        if (name.isPresent()) {
            // 获取值，如果 Optional有值则将其返回，否则抛出 NoSuchElementException
            System.out.println(name.get());
        }
    }

    @Test
    public void testIfPresent () {
        String nameStr = "zhangsan";
        Optional<String> name = Optional.ofNullable(nameStr);
        name.ifPresent(new Consumer<String>() {     // 如果存在则消费数据
            @Override
            public void accept(String s) {
                System.out.println("Optional 是有值的，消费数据【" + s + "】");
            }
        });
    }

    @Test
    public void testOrElse () {
        String nameStr = "zhangsan";
        Optional<String> name = Optional.ofNullable(nameStr);
        System.out.println(name.orElse("没有值的话就取我的值"));

        nameStr = null;
        Optional<String> name2 = Optional.ofNullable(nameStr);
        System.out.println(name2.orElse("没有值的话就取我的值"));

        // orElseGet与orElse方法类似，区别在于得到的默认值。
        // orElse方法将传入的字符串作为默认值，orElseGet方法可以接受Supplier接口的实现用来生成默认值
        System.out.println(name2.orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "没有值得话就取我的值，我是通过 Supplier 提供者提供的默认值";
            }
        }));

        try {
            // 如果有值则将其返回，否则抛出supplier接口创建的异常。
            // 在orElseGet方法中，我们传入一个Supplier接口。
            // 然而，在orElseThrow中我们可以传入一个lambda表达式或方法，如果值不存在来抛出异常。
            System.out.println(name2.orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return new Exception("没有值得话那就抛个异常咯.");
                }
            }));
        } catch (Throwable throwable) {
            System.out.println("捕获到了异常信息：" + throwable.getMessage());
        }
    }

    @Test
    public void testMap () {
        String nameStr = "zhangsan";
        Optional<String> name = Optional.ofNullable(nameStr);
        // 如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，
        // 则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。
        String upperName = name.map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        }).get();

        System.out.println(upperName);

        // 如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional。
        // flatMap方法与map方法类似，区别在于mapping函数的返回值不同。
        // map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
        upperName = name.flatMap(new Function<String, Optional<String>>() {
            @Override
            public Optional<String> apply(String s) {
                return Optional.ofNullable(s.toUpperCase());
            }
        }).get();
        System.out.println(upperName);
    }

    @Test
    public void testFilter () {
        String nameStr = "zhangsan123";
        Optional<String> name = Optional.ofNullable(nameStr);
        // filter 如果值存在并且满足提供的谓词，
        // 就返回包含该Optional对象；否则返回一个空的Optional对象
        nameStr = name.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("zhangsan");
            }
        }).orElse("不是zhangsan");
        System.out.println(nameStr);
    }
}
