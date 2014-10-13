/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ReflectionUtils.java 763 2009-12-27 18:36:21Z calvinxiu $
 */
package com.sitech.basd.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 
 * <p>
 * Title: ReflectionUtils
 * </p>
 * <p>
 * Description: 反射工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-14 下午3:19:44
 * 
 */
public class ReflectionUtils {

	private static Logger logger = LoggerFactory
			.getLogger(ReflectionUtils.class);

	static {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		ConvertUtils.register(dc, Date.class);
	}

	/**
	 * 
	 * @Title: invokeGetterMethod
	 * @Description: 调用Getter方法
	 * @param
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:19:59
	 */
	public static Object invokeGetterMethod(Object target, String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		return invokeMethod(target, getterMethodName, new Class[] {},
				new Object[] {});
	}

	/**
	 * 
	 * @Title: invokeSetterMethod
	 * @Description: 调用Setter方法.使用value的Class来查找Setter方法
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:20:17
	 */
	public static void invokeSetterMethod(Object target, String propertyName,
			Object value) {
		invokeSetterMethod(target, propertyName, value, null);
	}

	/**
	 * 
	 * @Title: invokeSetterMethod
	 * @Description: 调用Setter方法.
	 * @param 用于查找Setter方法
	 *            ,为空时使用value的Class替代.
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:20:27
	 */
	public static void invokeSetterMethod(Object target, String propertyName,
			Object value, Class<?> propertyType) {
		Class<?> type = propertyType != null ? propertyType : value.getClass();
		String setterMethodName = "set" + StringUtils.capitalize(propertyName);
		invokeMethod(target, setterMethodName, new Class[] { type },
				new Object[] { value });
	}

	/**
	 * 
	 * @Title: getFieldValue
	 * @Description: 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 * @param
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:20:46
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * @Title: setFieldValue
	 * @Description: 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:20:55
	 */
	public static void setFieldValue(final Object object,
			final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: invokeMethod
	 * @Description: 直接调用对象方法, 无视private/protected修饰符.
	 * @param
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:21:03
	 */
	public static Object invokeMethod(final Object object,
			final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method ["
					+ methodName + "] on target [" + object + "]");
		}

		method.setAccessible(true);

		try {
			return method.invoke(object, parameters);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 
	 * @Title: getDeclaredField
	 * @Description: 循环向上转型, 获取对象的DeclaredField. 向上转型到Object仍无法找到, 返回null.
	 * @param
	 * @return Field
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:21:11
	 */
	protected static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object, "object不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: makeAccessible
	 * @Description: 强行设置Field可访问.
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:21:25
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 
	 * @Title: getDeclaredMethod
	 * @Description: 循环向上转型,获取对象的DeclaredMethod. 如向上转型到Object仍无法找到, 返回null.
	 * @param
	 * @return Method
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:21:31
	 */
	protected static Method getDeclaredMethod(Object object, String methodName,
			Class<?>[] parameterTypes) {
		Assert.notNull(object, "object不能为空");

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: getSuperClassGenricType
	 * @Description: 通过反射,获得Class定义中声明的父类的泛型参数的类型
	 * @param
	 * @return Class<T>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:21:47
	 */
	/**
	 * 通过反射,获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao
	 * extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * 
	 * @Title: convertElementPropertyToList
	 * @Description: 提取集合中的对象的属性(通过getter函数), 组合成List.
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:23:53
	 */
	@SuppressWarnings("unchecked")
	public static List convertElementPropertyToList(
			final Collection collection, final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 
	 * @Title: convertElementPropertyToString
	 * @Description: 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:23:38
	 */
	@SuppressWarnings("unchecked")
	public static String convertElementPropertyToString(
			final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 
	 * @Title: convertStringToObject
	 * @Description: 转换字符串到相应类型.
	 * @param* @param value 待转换的字符串
	 * @param toType
	 *            转换目标类型
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:23:16
	 */
	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 
	 * @Title: convertReflectionExceptionToUnchecked
	 * @Description:将反射时的checked exception转换为unchecked exception.
	 * @param
	 * @return RuntimeException
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-14 下午3:23:06
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(
			Exception e) {
		if (e instanceof IllegalAccessException
				|| e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.",
					((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
}
