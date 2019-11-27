package com.example.demo.util.restTemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.result.RespDTO;

@RestController
public class TargetController {
    @RequestMapping(value = "weight", method = RequestMethod.GET)
    public RespDTO getWeight(@RequestParam(value = "height", required = false) Integer height,   @RequestParam(value = "sex", required = false) Integer sex, @RequestParam(value = "age", required = false) Integer age) {
        if (height == null || age == null || sex == null || (!sex.equals(0) && !sex.equals(1))) {
            return RespDTO.badRequest("缺少请求参数或者参数错误");
        }

        double condition = getStandardWeight(sex, age, height);
        return RespDTO.success(condition);

    }

    /**
     * 获取标准体重
     *
     * @param sex    性别 1：男  2：女
     * @param age    年龄
     * @param height
     * @return 体重(单位：kg)
     */
    public double getStandardWeight(int sex, int age, int height) {

        double weight = 0.0;

        switch (sex) {
            //男性
            case 1:
                if (age < 12 && age > 2) {
                    weight = age * 2 + 12;
                } else if (age > 12) {
                    weight = (height - 150) * 0.6 + 50;
                }
                break;
            case 0:

                if (age < 12 && age > 2) {
                    weight = age * 2 + 12;
                } else if (age > 12) {
                    weight = (height - 100) * 0.6 + 50;
                }
                break;
            default:
                weight = 0;
                break;
        }

        return weight;
    }
}