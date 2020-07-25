package com.messi.mybatisFramework.model;

import lombok.Data;

/**
 * @ClassName MappedStatement
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
@Data
public class MappedStatement {
    private String id;

    private Class<?> parameterTypeClass;

    private Class<?> resultTypeClass;

    private String statementType;

    private SqlSource sqlSource;

    public MappedStatement(String id, Class<?> parameterTypeClass, Class<?> resultTypeClass, String statementType,
                           SqlSource sqlSource) {
        this.id = id;
        this.parameterTypeClass = parameterTypeClass;
        this.resultTypeClass = resultTypeClass;
        this.statementType = statementType;
        this.sqlSource = sqlSource;
    }

}
