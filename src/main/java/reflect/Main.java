package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) {

        // создаем тестовый объект
        TestObject source = new TestObject();

        try {
            // с помощью рефлексии создаем копию объекта
            Object testObj = source.getClass().newInstance();

            // доступ к полю класса
            Field privateStringField = testObj.getClass().getDeclaredField("name");
            privateStringField.setAccessible(true);
            String name = (String) privateStringField.get(testObj);
            System.out.println("Значение поля name = " + name);

            System.out.println("Меняем значение поля name");
            privateStringField.set(testObj, "Новое значение");
            name = (String) privateStringField.get(testObj);
            System.out.println("Значение поля name = " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Вызвать указанный метод на объекте
     *
     * @param object      Объект для вызова
     * @param methodName  Имя метода
     * @param paramsTypes Типы аргументов - необязательно
     * @param arg         Аргумент вызываемого метода - необязательно
     * @return Результат выполнения метода
     */
    private static Object invokeMethod(Object object, final String methodName,
                                       final Class<?>[] paramsTypes, Object arg) {

        Object result = null;

        try {

            // тип объекта, на кот. вызывается метод
            Class<?> targetObjectClass = object.getClass();

            // получаем метод класса
            Method method = findMethodAtClass(targetObjectClass, paramsTypes,
                    methodName);

            // если есть аргумент
            if (arg != null)
                result = method.invoke(object, arg);
            else
                result = method.invoke(object);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Получить метод класса или его суперкласса по имени и по типу аргуметов
     *
     * @param type        Класс
     * @param paramsTypes Классы аргуметов (Необязательно, если null - ищется метод без
     *                    аргументов)
     * @param methodName  Имя метода
     * @return Метод класса
     * @throws NoSuchMethodException
     */
    private static Method findMethodAtClass(final Class<?> type,
                                            final Class<?>[] paramsTypes, final String methodName)
            throws NoSuchMethodException {

        Class<?> currentType = type;
        Method[] methods = type.getMethods();

        // поиск метода с именем methodName в заданном классе и всех его
        // суперклассах
        while (currentType != null) {
            for (Method buff : methods) {

                if (buff.getName().equals(methodName)) {

                    if (paramsTypes != null) {
                        Class<?>[] params = buff.getParameterTypes();
                        if (Arrays.deepEquals(paramsTypes, params))
                            return buff;
                    } else if (buff.getParameterTypes().length == 0)
                        return buff;
                }
            }

            currentType = currentType.getSuperclass();
        }

        throw new NoSuchMethodException();
    }

}
