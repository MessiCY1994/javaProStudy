package com.messi.mybatisFramework.sqlsession;

import com.messi.mybatisFramework.config.Configuration;

/**
 * @ClassName DefaulSqlSessionFactory
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
public class DefaulSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaulSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSeesion() {
        return new DefaulSqlSession(configuration);
    }
}
