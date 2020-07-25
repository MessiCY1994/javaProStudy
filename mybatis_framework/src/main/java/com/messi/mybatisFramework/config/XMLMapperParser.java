package com.messi.mybatisFramework.config;

import org.dom4j.Element;

public class XMLMapperParser {

	private Configuration configuration;

	public XMLMapperParser(Configuration configuration) {
		this.configuration = configuration;
	}

	public void parse(Element rootElement) {
		//将select标签解析为MappedStatement对象
		
		//将解析出来的MappedStatement对象放入Configuration对象中的map集合
	}

}
