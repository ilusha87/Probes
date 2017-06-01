package TypeErasure;

import java.util.*;

/**
 * Created by Illia Krysenko on 23.09.2016.
 */

class Erasure1 {

    private static class Foo2<T> {
        T value1, value2;

        public void print() {
            System.out.println(value1);
            System.out.println(value2);
        }
    }

    public static <T> Foo2<T> create(Object o1, Object o2) {
        Foo2<T> result = new Foo2<T>();
        result.value1 = (T) o1;
        result.value2 = (T) o2;
        return result;
    }

    public static void main(String[] args) {
        String localeCode = new Locale("es", "ES").getLanguage();
        String localeCode2 = new Locale("pl", "PL").getLanguage();
        String localeCode3 = Locale.US.getLanguage();
        String localeCode4 = Locale.GERMANY.getLanguage();

        String[] strings = {"asd", "Larry", "Moe", "Curly", "Asd"};
        List<String> listToOrder = Arrays.asList(strings);
        listToOrder.add("ASD");
        List<String> categoryOrder = Arrays.asList(strings);
        categoryOrder.add("ddd");
            Collections.sort(listToOrder, (o1, o2) -> {
                int o1Index = categoryOrder.indexOf(o1);
                int o2Index = categoryOrder.indexOf(o2);
                o1Index = o1Index == -1 ? Integer.MAX_VALUE : o1Index;
                o2Index = o2Index == -1 ? Integer.MAX_VALUE : o2Index;
                return o1Index - o2Index;
            });
        int i=0;
        int yu = ++i;

        Double pi = 3.14;
        String hello = "hello";
        Foo2<Integer> test = create(pi, hello);
        test.print();
    }
}