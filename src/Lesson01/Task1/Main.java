package Lesson01.Task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main {

    public static void main(String[] args) {
        test();
    }

    private static void test() {

        Class<?> cls = Test.class;
        Method[] methods = cls.getDeclaredMethods();
        for(Method method: methods) {
            if(method.isAnnotationPresent(Sum.class)) {
                Sum annotation = (Sum)method.getAnnotation(Sum.class);
                try {
                    method.invoke(null, annotation.a(), annotation.b());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}