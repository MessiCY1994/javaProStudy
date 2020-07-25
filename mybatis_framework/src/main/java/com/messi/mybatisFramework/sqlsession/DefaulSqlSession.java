package com.messi.mybatisFramework.sqlsession;

import com.messi.mybatisFramework.config.Configuration;
import com.messi.mybatisFramework.model.MappedStatement;

import java.util.List;

/**
 * @ClassName DefaulSqlSession
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
public class DefaulSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaulSqlSession(Configuration configuration){
        this.configuration = configuration;
    }
    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> lists = selectList(statementId,param);
        if(lists!=null&&lists.size()==1){
            return (T) lists.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        //真正和数据库交互的地方
        Executor executor = new SimpleExcutor();
        MappedStatement mappedStatement = configuration.getMappedStatements().get(statementId);
        return executor.query(configuration,mappedStatement,param);
    }
}
