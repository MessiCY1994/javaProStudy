package com.messi.mybatisFramework.model;

import lombok.Data;

/**
 * @ClassName ParameterMapping
 * @Description: TODO
 * @Author messi
 * @Date 2020/7/25
 * @Version V1.0
 **/
@Data
public class ParameterMapping {
    private String name;

    public ParameterMapping(String content) {
        this.name = content;
    }
}
