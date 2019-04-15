package com.example.demo.util.spel.simba;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * spring spel 解析工具类
 * Created by yanpeng.liu on 2019/3/20.
 */
@Slf4j
public class SpelUtil {

    /**
     * spel解析器  config ：自动补全空集合、自动填充
     */
    private static final ExpressionParser PARSER = new SpelExpressionParser(new SpelParserConfiguration(Boolean.TRUE,Boolean.TRUE));


    /**
     * 执行el表达式 获取boolean结果
     * @param expression
     * @param data
     * @return
     */
    public static Boolean parserElToBoolean(String expression, Object data){
        Boolean result = PARSER.parseExpression(expression).getValue(data,Boolean.class);
        return Objects.isNull(result) ? Boolean.FALSE : result;
    }

    /**
     * 执行el表达式获取Str结果
     * @param expression
     * @param data
     * @return
     */
    public static String parserElToString(ValidateSpel expression,Object data){
        if(Objects.isNull(expression.getExpType()) || ValidateSpelTypeEnum.SPEL_TYPE_BOOLEAN.isEqual(expression.getExpType())){
            return parserElToBoolean(expression.getExpression(),data) ? expression.getResultDesc() : null;
        }else if(ValidateSpelTypeEnum.SPEL_TYPE_STRING.isEqual(expression.getExpType())){
            return PARSER.parseExpression(expression.getExpression()).getValue(data,String.class);
        }else if(ValidateSpelTypeEnum.SPEL_TYPE_ASSEMBLE.isEqual(expression.getExpType())){
            return parserElToAssemble(expression.getExpression(),data);
        }else {
            log.warn("未匹配到对应的SPEL表达式类型！expression:{}",expression);
            throw new ValidateException("未匹配到对应的SPEL表达式类型");
        }
    }

    /**
     * 校验spel 若不通过，返回提示信息
     * @param json
     * @param obj
     * @return
     */
    public static String parserSpelExpressions(String json,Object obj){
        List<ValidateSpel> expressions = JSONObject.parseArray(json,ValidateSpel.class);
        return parserSpelExpressions(expressions,obj);
    }

    /**
     * 校验spel 若不通过，返回提示信息
     * @param spelList
     * @param obj
     * @return
     */
    public static String parserSpelExpressions(List<ValidateSpel> spelList,Object obj){
        for(ValidateSpel exp : spelList){
            String result = null;
            //捕获spel表达式执行时发生的异常
            result = parserElToString(exp,obj);
            if(!StringUtils.isEmpty(result)){
                return result;
            }
        }
        return null;
    }

    /**
     * 校验spel
     * @param spel
     * @return
     */
    public static List<String> validateSpel(String spel,Object obj){
        List<ValidateSpel> expressions = JSONObject.parseArray(spel,ValidateSpel.class);
        List<String> result = new ArrayList<>();
        for(ValidateSpel exp : expressions){
            //捕获spel表达式执行时发生的异常
            try {
                PARSER.parseExpression(spel).getValue(obj);
            } catch (Exception e) {
                result.add(String.format("表达式：%s 配置错误：%s",exp.getExpression(),e.getMessage()));
            }
        }
        return result;
    }

    /**
     * spel装配类型 解析并校验
     * @param expression
     * @param data
     * @return
     */
    public static String parserElToAssemble(String expression,Object data){
        return parserSpelExpressions(JSONObject.toJSONString(getExpressionForAssemble(expression)),data);
    }

    /**
     * 装配spel表达式
     * @param assembleStr
     * @return
     */
    public static List<ValidateSpel> getExpressionForAssemble(String assembleStr){
        String[] exps = assembleStr.split(",");
        List<ValidateSpel> spels = new ArrayList<>();
        for(String exp : exps){
            String[] tempExp = exp.split("-");
            if(tempExp == null || tempExp.length < 1 || StringUtils.isEmpty(tempExp[0])){
                continue;
            }
            String expression = System.getProperty(tempExp[0]);
            if(!StringUtils.isEmpty(expression)){
                if(tempExp.length > 1){
                    for(int i = 1 ; i < tempExp.length ; i ++){
                        String[] feild = tempExp[i].split("=");
                        if(feild != null && feild.length == 2){
                            expression = JavaUtil.stringReplaceAll(expression,feild[0],feild[1]);
                        }
                    }
                }
            }
            List<ValidateSpel> tempVS = JSONObject.parseArray(expression,ValidateSpel.class);
            spels.addAll(tempVS);
        }
        return spels;
    }

}
