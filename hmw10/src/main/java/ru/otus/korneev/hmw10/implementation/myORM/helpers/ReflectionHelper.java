package ru.otus.korneev.hmw10.implementation.myORM.helpers;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.*;

public class ReflectionHelper {

    private final static Set<String> SET_NAME_WRAPPER = new HashSet<>();
    private final static HashMap<Class, Method> map = new HashMap<>();

    static {
        SET_NAME_WRAPPER.add("String");
        SET_NAME_WRAPPER.add("Integer");
        SET_NAME_WRAPPER.add("Boolean");
        SET_NAME_WRAPPER.add("Double");
        SET_NAME_WRAPPER.add("Long");
        SET_NAME_WRAPPER.add("Byte");
        SET_NAME_WRAPPER.add("Float");
        SET_NAME_WRAPPER.add("Short");
        SET_NAME_WRAPPER.add("Character");
        SET_NAME_WRAPPER.add("BigDecimal");

        try {
            map.put(String.class, ResultSet.class.getDeclaredMethod("getString", String.class));
            map.put(Integer.class, ResultSet.class.getDeclaredMethod("getInt", String.class));
            map.put(int.class, ResultSet.class.getDeclaredMethod("getInt", String.class));
            map.put(Array.class, ResultSet.class.getDeclaredMethod("getArray", String.class));
            map.put(Long.class, ResultSet.class.getDeclaredMethod("getLong", String.class));
            map.put(long.class, ResultSet.class.getDeclaredMethod("getLong", String.class));
            map.put(Double.class, ResultSet.class.getDeclaredMethod("getDouble", String.class));
            map.put(double.class, ResultSet.class.getDeclaredMethod("getDouble", String.class));
            map.put(Float.class, ResultSet.class.getDeclaredMethod("getFloat", String.class));
            map.put(float.class, ResultSet.class.getDeclaredMethod("getFloat", String.class));
            map.put(Short.class, ResultSet.class.getDeclaredMethod("getShort", String.class));
            map.put(short.class, ResultSet.class.getDeclaredMethod("getShort", String.class));
            map.put(Byte.class, ResultSet.class.getDeclaredMethod("getByte", String.class));
            map.put(byte.class, ResultSet.class.getDeclaredMethod("getByte", String.class));
            map.put(Boolean.class, ResultSet.class.getDeclaredMethod("getBoolean", String.class));
            map.put(boolean.class, ResultSet.class.getDeclaredMethod("getBoolean", String.class));
            map.put(BigDecimal.class, ResultSet.class.getDeclaredMethod("getBigDecimal", String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static String objectToString(final Object src) throws IllegalAccessException {
        return getValueObject(src).toString();
    }

    private static Object getValueObject(Object src) throws IllegalAccessException {
        Object temp;
        Class<?> clazz = src.getClass();
        if (clazz.isArray()) {
            throw new UnsupportedOperationException("Массивы как члены класса не поддерживаются");
        } else if (Collection.class.isAssignableFrom(clazz)) {
            throw new UnsupportedOperationException("Коллекции как члены класса не поддерживаются");
        } else if (Map.class.isAssignableFrom(clazz)) {
            throw new UnsupportedOperationException("Map как члены класса не поддерживаются");
        } else {
            temp = getValueMemberClass(src);
        }
        return temp;
    }

    @SuppressWarnings("unchecked")
    private static Object getValueMemberClass(final Object src) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        Class<?> clazz = src.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean isFirst = true;
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            Object value = field.get(src);
            if (type.isPrimitive() || isWrapper(type.getSimpleName())) {
                if (!isFirst) {
                    result.append(", ");
                }
                if (type.isAssignableFrom(String.class) ||
                        type.isAssignableFrom(Character.class)) {
                    result.append("'");
                }
                result.append(value.toString());
                if (type.isAssignableFrom(String.class) ||
                        type.isAssignableFrom(Character.class)) {
                    result.append("'");
                }
            } else {
                throw new UnsupportedOperationException("Объекты как члены класса не поддерживаются");
            }
            isFirst = false;
        }
        return result;
    }

    public static <T> T fillingObject(Object obj, final Class<T> clazz) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        T result=  clazz.getConstructor().newInstance();
        Field[] arrayFields = result.getClass().getDeclaredFields();
        for (Field field : arrayFields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Class<?> type = field.getType();
            Method met = map.get(type);
            Object value = met.invoke(obj, fieldName);
            field.set(result, value);
            field.setAccessible(false);
        }
        return result;
    }

    private static boolean isWrapper(final String simpleName) {
        return SET_NAME_WRAPPER.contains(simpleName);
    }
}
