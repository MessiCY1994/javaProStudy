package com.messi.springFrameworkTest.test;

import com.messi.springFramework.beans.factory.BeanFactory;
import com.messi.springFramework.beans.factory.DefaultListableBeanFactory;
import com.messi.springFrameworkTest.po.Student;
import org.junit.Test;

public class TestSpringFramework {

	@Test
	public void test() throws Exception {
		
		String location = "classpath:beans.xml";
		BeanFactory beanFactory = new DefaultListableBeanFactory(location);
		//通过工厂获取学生对象
		Student student = (Student) beanFactory.getBean("student");
		System.out.println(student);
	}


}
