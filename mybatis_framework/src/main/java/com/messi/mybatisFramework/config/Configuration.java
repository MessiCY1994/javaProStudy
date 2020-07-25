package com.messi.mybatisFramework.config;

import javax.sql.DataSource;

/**
 * @ClassName Configuration
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
public class Configuration {
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
