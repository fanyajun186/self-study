package com.example.demo.util.spel;


/**
 * Created by yanpeng.liu on 2019/3/20.
 */
public enum  ValidateSpelTypeEnum {

    SPEL_TYPE_BOOLEAN(1,"校验表达式返回布尔值"),
    SPEL_TYPE_STRING(2,"校验表达式返回提示信息"),
    SPEL_TYPE_ASSEMBLE(3,"装配模式"),
    SPEL_TYPE_ADD_DATA(4,"填充数据");

    private Integer type;
    private String desc;

    ValidateSpelTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 校验是否为当前类型
     * @param type
     * @return
     */
    public Boolean isEqual(Integer type){
        return this.type.equals(type);
    }
}
