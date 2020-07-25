package com.messi.mybatisFramework.config;

import com.messi.mybatisFramework.model.MappedStatement;
import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
@Data
public class Configuration {
    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public  void addMappedStatement(String statementId,MappedStatement mappedStatement){
        this.mappedStatements.put(statementId,mappedStatement);
    }
}
