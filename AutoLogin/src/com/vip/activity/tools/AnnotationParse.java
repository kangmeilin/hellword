package com.vip.activity.tools;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

public class AnnotationParse {
	
	public String getTestClassAnnotationInfo(final String className) throws ClassNotFoundException {
		String annotationInfo = "";
		if(className != null && !"".equals(className)) {
			Class<?> cls = Class.forName(className);
			TestClass testClass = cls.getAnnotation(TestClass.class);
			annotationInfo = testClass.description();
		} else {
			annotationInfo = className;
		}
		return annotationInfo;
	}
	
	public String getTestMethodAnnotationInfo(final String className) throws ClassNotFoundException {
		String annotationInfo = "";
		if(className != null && !"".equals(className)) {
			Class<?> cls = Class.forName(className);
			for(Method method : cls.getDeclaredMethods()) {
				if(method.getAnnotation(Test.class) != null && method.getAnnotation(TestMethod.class) != null) {
					annotationInfo = method.getAnnotation(TestMethod.class).description();
				}
			}
		} else {
			annotationInfo = className;
		}
		return annotationInfo;
	}
	
	
	public String getTestAuthorAnnotationInfo(final String className) throws ClassNotFoundException {
		String annotationInfo = "";
		if(className != null && !"".equals(className)) {
			Class<?> cls = Class.forName(className);
			TestAuthor testAuthor = cls.getAnnotation(TestAuthor.class);
			annotationInfo = testAuthor.description();
		} else {
			annotationInfo = className;
		}
		return annotationInfo;
	}
}
