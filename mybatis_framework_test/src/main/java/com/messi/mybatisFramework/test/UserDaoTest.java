package com.messi.mybatisFramework.test;

import java.io.InputStream;

import com.messi.mybatisFramework.dao.UserDao;
import com.messi.mybatisFramework.dao.UserDaoImpl;
import com.messi.mybatisFramework.po.User;
import com.messi.mybatisFramework.sqlsession.SqlSessionFactory;
import com.messi.mybatisFramework.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;



public class UserDaoTest {
	// SqlSessionFactory加载依赖于全局配置文件的加载方式
	// 所以说我们需要定制到底要哪一种加载方式去创建SqlSessionFactory
	// 使用构建者模式去定制SqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;

	// 去创建SqlSessionFactory
	@Before
	public void init() throws Exception {
		//指定类路径下的全局配置文件路径，通过类加载器去加载
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
		 sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testQueryUserById() {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User user = userDao.queryUserById(1);
		System.out.println(user);
	}

}
