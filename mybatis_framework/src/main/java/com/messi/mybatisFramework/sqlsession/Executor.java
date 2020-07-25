package com.messi.mybatisFramework.sqlsession;

import com.messi.mybatisFramework.config.Configuration;
import com.messi.mybatisFramework.model.MappedStatement;

import java.util.List;

/**
 * @ClassName Executor
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/26
 * @Version V1.0
 **/
public interface Executor {

    <T> List<T> query(Configuration configuration,MappedStatement mappedStatement, Object param);
}
