package com.dev2.intern.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMapUtil {
	public static Map<Object, Object> createHashMap() {
		return new HashMap<Object, Object>();
	}
	
	public static Map<Object, Object> createHashMap(Object key, Object value) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(key, value);
		
		return map;
	}
	
	public static void addMap(Map<Object, Object> map, Object key, Object value) {
		map.put(key, value);
	}
	
	public static Object mapConvertToVO(Map<?, ?> srcMap, Class<?> targetClass) {
		
		Object targetObject = null;
		
		Field targetField = null;
		boolean targetAccessible = false;
		
		try {
			Constructor<?> cons = targetClass.getConstructor();
			targetObject = cons.newInstance();
			
			Set<?> key = srcMap.keySet();
			Iterator<?> iterator = key.iterator();
			
			Field[] fields = targetClass.getDeclaredFields();
			List<String> fieldNames = getNameListByFields(fields);

			while (iterator.hasNext()) {
				Object keyName = iterator.next();
				Object value = srcMap.get(keyName);
				
				boolean checkIncludeField = fieldNames.contains(keyName.toString());
				
				if (checkIncludeField == true) {
					try {
						targetField = targetClass.getDeclaredField(keyName.toString());
						targetAccessible = targetField.isAccessible();
						
						targetField.setAccessible(true);
						
						if (targetField.getType() == int.class || targetField.getType() == Integer.class) {
							value = Integer.parseInt((String)value);
						} else if (targetField.getType() == Date.class) {
							value = new Date(Long.parseLong((String)value));
						}
						
						targetField.set(targetObject, value);
					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException e) {
						e.printStackTrace();
					} finally {
						if (targetField != null) {
							targetField.setAccessible(targetAccessible);
							targetField = null;
						}
					}
				}
				
			}
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return targetObject;
	}
	
	private static List<String> getNameListByFields(Field[] fields) {
		List<String> names = new ArrayList<String>();
		
		for (int i = 0 ; i < fields.length ; i++) {
			names.add(fields[i].getName());
		}
		
		return names;
	}
}
