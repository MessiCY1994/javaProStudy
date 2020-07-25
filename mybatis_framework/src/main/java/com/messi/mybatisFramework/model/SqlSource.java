package com.messi.mybatisFramework.model;

import com.messi.mybatisFramework.utils.GenericTokenParser;
import com.messi.mybatisFramework.utils.ParameterMappingTokenHandler;
import lombok.Data;

/**
 * @ClassName SqlSource
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
@Data
public class SqlSource {
    private  String sqlText;
    public SqlSource(String sqlText){
        this.sqlText = sqlText;
    }
    public BoundSql getBoundSql(){
        // 解析sql文本
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String sql = genericTokenParser.parse(sqlText);
        // 就是将解析之后的SQL语句，和解析出来的SQL参数使用组合模式绑定到一个类中
        return new BoundSql(sql,tokenHandler.getParameterMappings());
    }
}
