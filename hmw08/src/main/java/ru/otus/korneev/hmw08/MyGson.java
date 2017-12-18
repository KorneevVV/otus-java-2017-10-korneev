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

	private final static Set<String> SET_NAME_WRAPPER = new HashSet<>();

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
	}

	public String toJson(final Object src) {
		Object temp;
		Class<?> clazz = src.getClass();
		if (clazz.isArray()) {
			temp = getValueElementArray(src);
		} else if (Collection.class.isAssignableFrom(clazz)) {
			temp = getValueElementCollection((Collection) src);
		} else if (Map.class.isAssignableFrom(clazz)) {
			temp = getValueEntrySetMap((Map) src);
		} else {
			temp = getValueMemberClass(src);
		}
		return ((JSONAware) temp).toJSONString();
	}

	@SuppressWarnings("unchecked")
	private JSONObject getValueEntrySetMap(Map value) {
		JSONObject jsonObject = new JSONObject();
		value.forEach((key, val) -> jsonObject.put(key, getValueMemberClass(val)));
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	private JSONArray getValueElementCollection(Collection value) {
		JSONArray jsonArray = new JSONArray();
		for (Object o : value) {
			jsonArray.add(getValueMemberClass(o));
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	private JSONArray getValueElementArray(Object array) {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < Array.getLength(array); i++) {
			jsonArray.add(getValueMemberClass(Array.get(array, i)));
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	private Object getValueMemberClass(final Object src) {
		Class<?> clazz = src.getClass();
		if (clazz.isPrimitive() || isWrapper(clazz.getSimpleName())) {
			return src;
		} else {
			Field[] fields = clazz.getDeclaredFields();
			JSONObject jsonObject = new JSONObject();
			for (Field field : fields) {
				field.setAccessible(true);
				Class<?> type = field.getType();
				try {
					Object value = field.get(src);
					if (type.isPrimitive() || isWrapper(type.getSimpleName())) {
						jsonObject.put(field.getName(), value);
					} else {
						jsonObject.put(field.getName(), getValueMemberClass(value));
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return jsonObject;
		}
	}

	private boolean isWrapper(final String simpleName) {
		return SET_NAME_WRAPPER.contains(simpleName);
	}
}
