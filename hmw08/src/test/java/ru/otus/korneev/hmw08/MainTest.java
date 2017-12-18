package ru.otus.korneev.hmw08;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

	private Gson gson;
	private MyGson myGson;
	private ExampleClass exampleClass;
	private ExampleSimpleClass exampleSimpleClass;

	@Before
	public void getGson() {
		gson = new Gson();
		myGson = new MyGson();
		exampleClass = new ExampleClass();
		exampleSimpleClass = new ExampleSimpleClass();
	}

	@Test
	public void exampleClass() {
		ExampleClass obg = gson.fromJson(myGson.toJson(exampleClass), ExampleClass.class);
		assertEquals(exampleClass, obg);
	}

	@Test
	public void exampleSimpleClass() {
		ExampleSimpleClass obg = gson.fromJson(myGson.toJson(exampleSimpleClass), ExampleSimpleClass.class);
		assertEquals(exampleSimpleClass, obg);
	}

	@Test
	public void arrayExampleClass() {
		ExampleClass[] arrayExampleClass = new ExampleClass[]{new ExampleClass(), new ExampleClass()};
		ExampleClass[] arrayFromMyGson = gson.fromJson(myGson.toJson(arrayExampleClass), ExampleClass[].class);
		assertArrayEquals(arrayExampleClass, arrayFromMyGson);
	}

	@Test
	public void arrayInt() {
		int[] arrayInt = new int[]{0, 1};
		int[] arrayFromMyGson = gson.fromJson(myGson.toJson(arrayInt), int[].class);
		assertArrayEquals(arrayInt, arrayFromMyGson);
	}

	@Test
	public void arrayDouble() {
		double[] arrayDouble = new double[]{0.1, 0.2};
		double[] arrayFromMyGson = gson.fromJson(myGson.toJson(arrayDouble), double[].class);
		assertArrayEquals(arrayDouble, arrayFromMyGson, 0.00000001);
	}

	@Test
	public void arrayBoolean() {
		Boolean[] arrayBoolean = new Boolean[]{true, false};
		Boolean[] arrayFromMyGson = gson.fromJson(myGson.toJson(arrayBoolean), Boolean[].class);
		assertArrayEquals(arrayBoolean, arrayFromMyGson);
	}

	@Test
	public void listBoolean() {
		List<Boolean> listBoolean = new ArrayList<>();
		listBoolean.add(true);
		listBoolean.add(false);
		Type type = new TypeToken<List<Boolean>>(){}.getType();
		List<Boolean> listFromMyGson = gson.fromJson(myGson.toJson(listBoolean), type);
		assertEquals(listBoolean, listFromMyGson);
	}

	@Test
	public void listExampleClass() {
		List<ExampleClass> listExampleClass = new ArrayList<>();
		listExampleClass.add(exampleClass);
		listExampleClass.add(exampleClass);
		Type type = new TypeToken<List<ExampleClass>>(){}.getType();
		System.out.println(myGson.toJson(listExampleClass));
		List<ExampleClass> arrayFromMyGson = gson.fromJson(myGson.toJson(listExampleClass),type);
		assertEquals(listExampleClass, arrayFromMyGson);
	}

	@Test
	public void mapExampleClass() {
		Map<String, ExampleClass> mapExampleClass = new HashMap<>();
		mapExampleClass.put("one",exampleClass);
		mapExampleClass.put("two",exampleClass);
		Type type = new TypeToken<Map<String, ExampleClass>>(){}.getType();
		Map<String,ExampleClass> mapFromMyGson = gson.fromJson(myGson.toJson(mapExampleClass), type);
		assertEquals(mapExampleClass, mapFromMyGson);
	}
}
