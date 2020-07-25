package com.messi.mybatisFramework.config;

import com.messi.mybatisFramework.model.MappedStatement;
import com.messi.mybatisFramework.model.SqlSource;
import org.dom4j.Element;

import java.util.List;

public class XMLMapperParser {

	private Configuration configuration;

	private String nameSpace;

	public XMLMapperParser(Configuration configuration) {
		this.configuration = configuration;
	}

	public void parse(Element rootElement)  {
		//将select标签解析为MappedStatement对象
		nameSpace = rootElement.attributeValue("nameSpace");
		parseStatement(rootElement.elements("select"));
		//将解析出来的MappedStatement对象放入Configuration对象中的map集合
	}

	private void parseStatement(List<Element> elements)  {
		for (Element selectEle:elements){
			String id = selectEle.attributeValue("id");
			id = nameSpace+","+id;
			String parameterType = selectEle.attributeValue("parameterType");
			Class<?> parameterTypeClss = getClassType(parameterType);
			String resultType = selectEle.attributeValue("resultType");
			Class<?> resultTypeClss = getClassType(resultType);
			String statementType = selectEle.attributeValue("statementType");

			//未解析的sql的语句，包含占位符这种
			//创建sqlsource对象，封装sql
			String oldSql = selectEle.getTextTrim();
			SqlSource sqlSource = new SqlSource(oldSql);

			// 封装MappedStatement对象
			// 可以使用构建者模式去创建MappedStatement对象
			MappedStatement mappedStatement = new MappedStatement(id, parameterTypeClss, resultTypeClss, statementType,
					sqlSource);
			configuration.addMappedStatement(id,mappedStatement);
		}
	}

	private Class<?> getClassType(String parameterType) {
		if (parameterType==null||parameterType.equals("")){
			return null;
		}
		Class<?> clazz = null;
		try {
			clazz = Class.forName(parameterType);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}

}
