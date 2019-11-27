package com.example.demo.util.restTemplate;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController{

    @Autowired
    private RestUtil restUtil;

    /**
     * 请求方法为GEt
     * @return
     */
    @GetMapping("/restTest")
    public void  requestGet(){

        String url="http://localhost:8080/weight?age={age}&sex={sex}&height={height}";
        //组装请求参数
        Map<String,Object> parmMap =new HashMap<String,Object>();
        parmMap.put("age",35);
        parmMap.put("sex",1);
        parmMap.put("height",178);
        String result = restUtil.get(url, parmMap);

        System.out.println(result);

    }
}
