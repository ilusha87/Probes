//import java.io.File;
//import java.io.FilenameFilter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

import reflect.MyAnno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Test1 {
    static String str = "nnnHello World";
    public final static String MULTIVALUE_DELIM = "|";
    private static final String MUTLIVALUE_DELIM_REGEX = "\\" + MULTIVALUE_DELIM;

    public static void changeIt(String s) {
        s = "Good bye world";
    }

    protected static Test1[][] test1s = new Test1[10][10];

    private void doHelp() {
        System.out.println("\n Program parameters help. " +
                "\n");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        test1s[0][0] = new Test1();
        String value = "|";
        List<String> newValues = new ArrayList<String>();
        Collections.addAll(newValues, value.split(MUTLIVALUE_DELIM_REGEX));

        // Java 8 lambdas
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach(System.out::print);

        //Runnable
        new Thread(() -> {
            System.out.print("asd");
        }).start();

        // Java 8 Streams
        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            integers.add((int) (Math.random() * 10));
        }

        integers
                .parallelStream()
                .filter(i -> i % 2 == 0)
                .sorted()
                .forEachOrdered(System.out::println);


        Object s = TestEnum.ACTIVE;
        if (s.getClass().isEnum()) {
            System.out.println(getValueFieldFromEnum((Enum) s));
        }
        int a = 1000;
        int bb = 1000;
        System.out.println(a == bb);
        Object o = new int[10];
        ((int[]) o)[0] = 9;
        System.out.println("\n New Main class. " + ((int[]) o)[0]);
        //doHelp();
        changeIt(str);
        System.out.println(str);
        A b = new B();
        b.test();
//		b.test2();
    }

    /**
     * Returns a 'value' field's value from source object, if source is an Enum instance,
     * which is generated using internalmodel JAXB
     *
     * @param source - instance of Enum which potentially contains 'value' field
     * @return 'value' field's value from source object, if source is an Enum instance
     */
    public static String getValueFieldFromEnum(Enum source) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("value")) {
                try {
                    field.setAccessible(true);
                    Object fieldValue = field.get(source);
                    if (fieldValue instanceof String)
                        return (String) fieldValue;
                } catch (IllegalAccessException e) {
                    //e.printStackTrace(); // just return null if no such field or some other Exception
                }
            }
        }
        return null;
    }

}

abstract class Test1child extends Test1 {
    public void doHelp() {
        System.out.println("Child method");
    }

}

class A {
    A() {
        System.out.println("Constructor of class A");
        //super();
    }

    void test() {
        System.out.println("test method in class A");
    }
}

class B extends A {
    B() {
        System.out.println("Constructor of class B");
//		//super();
    }

    /*final void test() {
        System.out.println("test method in class B");
    }*/
    void test() {
        System.out.println("test method in class B");
    }

    public void test2() {
        System.out.println("test2 method in class B");
    }

}

// Static and default methods in Java 8
@FunctionalInterface
interface MyData {

    void method1(String str);

    default void print(String str) {
        if (!isNull(str))
            System.out.println("����� MyData. �������� ������: " + str);
    }

    static boolean isNull(String str) {
        System.out.println("����������� ����� �������� �� null " + str);

        return false;//str == null ? true : "".equals(str) ? true : false;
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class MyDataImpl implements MyData {

    public boolean isNull(String str) {
        System.out.println("isNull null");
        return str == null ? true : false;
    }

    @MyAnno( str = "dfgdfgdf" , val = 100 )
    public static void myMeth ( ) {
    }


    public static void main(String args[]) {

        int i = 9, newNum;
        byte number = -7;
        for (i = 1; i <= 40; i++) {
            System.out.printf("%d%n", newNum = number >>> 1);
        }
        int iand1 = i & 1;
        Calendar rightNow = java.util.Calendar.getInstance();
        int day = rightNow.get(java.util.Calendar.DAY_OF_MONTH);
        int month = rightNow.get(java.util.Calendar.MONTH);
        int year = rightNow.get(java.util.Calendar.YEAR);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int hour2 = rightNow.get(Calendar.HOUR);
        int min = rightNow.get(Calendar.MINUTE);
        int sec = rightNow.get(Calendar.SECOND);
        int msec = rightNow.get(Calendar.MILLISECOND);

        MyDataImpl obj = new MyDataImpl();
        obj.print("");
        MyData.isNull("abc");

//        List names = Arrays.asList("asd", "gsd", "bsd");
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        // 3 semantics of lambda below are identical
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        Collections.sort(names, (a, b) -> b.compareTo(a));

        System.out.println(String.join(", ", "A", "B", "C")); // A, B, C
        names.replaceAll(String::toUpperCase);
        names.forEach((from) -> System.out.print(from));
        names.forEach(System.out::print);

        new Random().ints(10, 20, 100).forEach(System.out::print);

        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

        converter = Integer::valueOf;
        converted = converter.convert("123");
        System.out.println(converted);    // 123

        /*Comparator<User> comparator = Comparator
                .comparingDouble(User::getAge)
                .thenComparing(User::getName);
        List<User> hList = ...;
        hList.sort(comparator);*/

        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123");     // "123"

        Supplier<Person> personSupplier = Person::new;
        Person nullP = personSupplier.get();   // new Person2

        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));
        greeter.accept(nullP);
    }


    @Override
    public void method1(String str) {

    }
}
