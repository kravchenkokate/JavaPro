package Lesson01.Task2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Saver {

    public static void save() {

        Class<?> cls = TextContainer.class;
        SaveTo saveTo = cls.getAnnotation(SaveTo.class);
        String path = saveTo.path();

        String text = "";

        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            if(field.getName().equals("text")) {
                field.setAccessible(true);
                try {
                    text = field.get(text).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        Method[] methods = cls.getDeclaredMethods();
        for(Method method : methods) {
            if(method.isAnnotationPresent(Save.class)) {
                try {
                    method.invoke(null, path, text);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
