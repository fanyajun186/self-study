package com.example.demo.util.spel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * spel表达式实体
 * Created by yanpeng.liu on 2019/3/20.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateSpel implements Serializable {
    /**
     * spel 表达式
     */
    private String expression;
    /**
     * 表达式类型 默认1  1：表达式结果为boolean，当结果为true时，提示信息从resultDesc获取  2：表达式为String 直接返回提示信息
     */
    private Integer expType;
    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 提示信息
     */
    private String resultDesc;
}
