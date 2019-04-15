package com.example.demo.util.spel.simba;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * spel表达式 校验
 * Created by yanpeng.liu on 2019/3/21.
 */
@Slf4j
public class SpelValidateHandler extends AbstractValidateHandler {

    /**
     * 待校验数据
     */
    private Map<String,Object> paramMap;

    /**
     * spel表达式json  由 XDiamond 配置
     */
    private String spelJson;

    /**
     * 构造
     * @param paramMap
     * @param spelJson
     */
    public SpelValidateHandler(Map<String, Object> paramMap, String spelJson) {
        this.paramMap = paramMap;
        this.spelJson = spelJson;
    }

    /**
     * 静态构造
     * @param paramMap
     * @param spelJson
     * @return
     */
    public static SpelValidateHandler of(Map<String, Object> paramMap, String spelJson){
        return new SpelValidateHandler(paramMap,spelJson);
    }

    /**
     * 数据校验
     */
    @Override
    public void validate() {
        String result = SpelUtil.parserSpelExpressions(this.spelJson,this.paramMap);
        if(!StringUtils.isEmpty(result)){
            log.warn("SPEL校验信息：{} ，paramMap：{}",result, JSONObject.toJSONString(this.paramMap));
            throw new ValidateException(result);
        }
    }
}
