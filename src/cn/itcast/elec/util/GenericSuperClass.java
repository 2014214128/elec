package cn.itcast.elec.util;

import java.lang.reflect.ParameterizedType;

public class GenericSuperClass {
	
	@SuppressWarnings("rawtypes")
	public static Class getClass(Class tclass) {
		ParameterizedType pt = (ParameterizedType) tclass.getGenericSuperclass();
		Class entity = (Class) pt.getActualTypeArguments()[0];
		return entity;
	}
}
