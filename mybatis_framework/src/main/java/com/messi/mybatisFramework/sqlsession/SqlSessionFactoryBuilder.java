package com.messi.mybatisFramework.sqlsession;


import com.messi.mybatisFramework.config.Configuration;
import com.messi.mybatisFramework.config.XMLConfigParser;
import com.messi.mybatisFramework.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
public class SqlSessionFactoryBuilder {
    //封装全局配置文件
    private Configuration configuration;

    public SqlSessionFactory build(InputStream inputStream){
        // 解析全局配置文件，封装为Configuration对象
        // 通过InputStream流对象，去创建Document对象（dom4j）---此时没有针对xml文件中的语义进行解析
        // DocumentReader---去加载InputStream流，创建Document对象的
        Document document =  DocumentReader.createDocument(inputStream);
        // 进行mybatis语义解析（全局配置文件语义解析、映射文件语义解析）
        // XMLConfigParser---解析全局配置文件
        // XMLMapperParser---解析全局配置文件
        XMLConfigParser xmlConfigParser = new XMLConfigParser(configuration);
        configuration = xmlConfigParser.parseConfiguration(document.getRootElement());
        return build();
    }

    public SqlSessionFactory build(Reader reader){
        return build();
    }
    //构建SqlSessionFactory需要全局配置文件的信息，也就是configuration
    private   SqlSessionFactory build(){
        return  new DefaulSqlSessionFactory();
    }
}
