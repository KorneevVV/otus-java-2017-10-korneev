package ru.otus.korneev.hmw08;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyGson {

	private final Set<String> SET_NAME_WRAPPER = new HashSet<>();

	{
		SET_NAME_WRAPPER.add("String");
		SET_NAME_WRAPPER.add("Integer");
		SET_NAME_WRAPPER.add("Boolean");
		SET_NAME_WRAPPER.add("Double");
		SET_NAME_WRAPPER.add("Long");
		SET_NAME_WRAPPER.add("Byte");
		SET_NAME_WRAPPER.add("Float");
		SET_NAME_WRAPPER.add("Short");
	}

	public String toJson(final Object src) {
		Object temp;
		Class<?> clazz = src.getClass();
		if (clazz.isArray()) {
			temp = getArrayValue(src);
		} else if (Collection.class.isAssignableFrom(clazz)) {
			temp = getCollectionValue((Collection) src);
		} else if (Map.class.isAssignableFrom(clazz)) {
			temp = getMapValue((Map) src);
		} else {
			temp = getValueMemberClass(src);
		}
		return ((JSONAware) temp).toJSONString();
	}

	@SuppressWarnings("unchecked")
	private JSONObject getMapValue(Map value) {
		JSONObject jsonObject = new JSONObject();
		value.forEach((key, val) -> jsonObject.put(key, getValueMemberClass(val)));
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	private JSONArray getCollectionValue(Collection value) {
		JSONArray jsonArray = new JSONArray();
		for (Object o : value) {
			jsonArray.add(getValueMemberClass(o));
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	private JSONArray getArrayValue(Object array) {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < Array.getLength(array); i++) {
			jsonArray.add(getValueMemberClass(Array.get(array, i)));
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	private Object getValueMemberClass(final Object src) {
		JSONObject jsonObject = new JSONObject();
		Class<?> clazz = src.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (clazz.isPrimitive() || isWrapper(clazz.getSimpleName())) {
			return src;
		} else {
			for (Field field : fields) {
				Class<?> type = field.getType();
				boolean primitive = type.isPrimitive();
				boolean isWrapper = isWrapper(type.getSimpleName());
				field.setAccessible(true);
				try {
					if (primitive || isWrapper) {
						Object value = field.get(src);
						jsonObject.put(field.getName(), value);
					} else {
						jsonObject.put(field.getName(), getValueMemberClass(field.get(src)));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObject;
	}

	private boolean isWrapper(final String simpleName) {
		return SET_NAME_WRAPPER.contains(simpleName);
	}
}
