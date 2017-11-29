package ru.otus.korneev.hmw05.OUnit;

import ru.otus.korneev.hmw05.OUnit.annotations.AfterOtus;
import ru.otus.korneev.hmw05.OUnit.annotations.BeforeOtus;
import ru.otus.korneev.hmw05.OUnit.annotations.TestOtus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.korneev.hmw05.OUnit.ReflectionHelper.isAnnotationEnabled;

public class Runner {

    private static List<Method> beforeMethod = new ArrayList<>();
    private static List<Method> afterMethod = new ArrayList<>();
    private static List<Method> testMethod = new ArrayList<>();

    public static void runTestClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (isAnnotationEnabled(method, TestOtus.class)) {
                    testMethod.add(method);
                } else if (isAnnotationEnabled(method, BeforeOtus.class)) {
                    beforeMethod.add(method);
                } else if (isAnnotationEnabled(method, AfterOtus.class)) {
                    afterMethod.add(method);
                }
            }
            if (testMethod.isEmpty()) {
                throw new Exception("Test method not found");
            }
            runTestMethods(clazz);
            beforeMethod = new ArrayList<>();
            afterMethod = new ArrayList<>();
            testMethod = new ArrayList<>();

        } catch (ClassNotFoundException e) {
            try {
                throw new ClassNotFoundException("Wrong class name: " + className, e);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runTestMethods(Class<?> clazz) {
        for (Method method : testMethod) {
            Object obj = ReflectionHelper.instantiate(clazz);
            for (Method methodBefore : beforeMethod) {
                ReflectionHelper.callMethod(obj, methodBefore.getName());
            }
            ReflectionHelper.callMethod(obj, method.getName());
            for (Method methodAfter : afterMethod) {
                ReflectionHelper.callMethod(obj, methodAfter.getName());
            }
        }
    }

    public static void runTestPackage(String packageName) {
        List<Class<?>> clazzes = ClassFinder.find(packageName);
        for (Class<?> clazz : clazzes) {
            runTestClass(clazz.getName());
        }
    }
}
