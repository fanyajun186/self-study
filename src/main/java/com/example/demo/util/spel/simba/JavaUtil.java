package com.example.demo.util.spel.simba;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * 工具类
 *
 * @author caixiang.sun
 * @date 2018/6/30
 */
public class JavaUtil {

    private static Logger logger = LoggerFactory.getLogger(JavaUtil.class);

    /**
     * set属性值:在获取大bean中小bean属性值时，需要将首次获取的小bean数据放入
     *
     * @param sourceObj
     * @param fields
     * @param value
     * @param form
     * @return
     */
   /* public static Object setField(Object sourceObj, String[] fields, Object value, int form) {
        if (form == fields.length - 1) {
            return setField(sourceObj, fields[form], value);
        }
        return setField(getValue(sourceObj, fields[form]), fields, value, form + 1);
    }

    *//**
     * 通过反射set属性值
     *
     * @param object
     * @param field
     * @param value
     *//*
    public static Object setField(Object object, String field, Object value) {
        try {
            Field fieldType = ReflectionUtils.findField(object.getClass(), field);
            value = getValue(fieldType.getType().getName(), value);
            PropertyUtils.setProperty(object, field, value);
        } catch (Exception e) {
            logger.info("根据object set属性值异常！获取字段->{}", field, e);
        }

        return object;
    }

    public static Object getValue(String propertyTypeName, Object value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        String dateType = "java.util.Date";
        if (dateType.equals(propertyTypeName)) {
            value = new Date(Long.parseLong(String.valueOf(value)));
        }
        String bigDecimalType = "java.math.BigDecimal";
        if (bigDecimalType.equals(propertyTypeName)) {
            value = new BigDecimal(String.valueOf(value));
        }

        String integerType = "java.lang.Integer";
        if (integerType.equals(propertyTypeName)) {
            value = Integer.parseInt(String.valueOf(value));
        }
        String longType = "java.lang.Long";
        if (longType.equals(propertyTypeName)) {
            value = Long.parseLong(String.valueOf(value));
        }
        return value;
    }

    *//**
     * 获取数据值:在获取大bean中小bean属性值时，需要将首次获取的小bean数据放入
     *
     * @param fields
     * @param form
     * @param sourceObj
     * @return
     *//*
    public static Object getValue(String[] fields, int form, Object sourceObj) {
        if (form == fields.length - 1) {
            return getValue(sourceObj, fields[form]);
        }
        return getValue(fields, form + 1, getValue(sourceObj, fields[form]));
    }

    *//**
     * field，从object中反射获取值
     *
     * @param object
     * @param field
     * @return
     *//*
    public static Object getValue(Object object, String field) {
        try {
            return PropertyUtils.getProperty(object, field);
        } catch (Exception e) {
            logger.info("根据object获取属性值异常！获取字段->{}", field, e);
        }
        return null;

    }

    *//**
     * 封装request成map对象 参数名作为key，参数值作为value
     *
     * @param request
     * @return
     *//*
    public static Map<String, Object> getMap(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();

        Map<String, String[]> tmp = request.getParameterMap();
        if (tmp != null) {
            for (String key : tmp.keySet()) {
                key = key.trim();
                String[] values = tmp.get(key);
                Object obj = values.length == 1 ? values[0].trim() : values;
                map.put(key, obj == null ? "" : obj);
            }
        }
        return map;
    }

    *//**
     * 根据url加密获取base64数据
     *
     * @param appCode
     * @param imgUrl
     * @return
     *//*
    public static String getStrBase64(String appCode, String imgUrl) {
        try {
            return FileCommonUtil.getStrBase64(imgUrl);
        } catch (Exception e) {
            throw new InvokeException(ResultCodeEnum.FILE_EXCEPTION.getKey(), ResultCodeEnum.EXCEPTION.getKey(),
                appCode, WebUtil.getUid(), imgUrl, null, "文件URL解析异常，请联系技术支持", e,"本地JavaUtil类根据url加密获取base64数据失败");
        }
    }

    *//**
     * 格式化数字
     * @param object
     * @return
     *//*
    public static String getFormatNum(Object object){
        String value = String.valueOf(Optional.ofNullable(object).orElse("0"));
        DecimalFormat myFormat = new DecimalFormat("###,##0.0");
        return myFormat.format(new BigDecimal(value));
    }
    *//**
     * 格式化数字
     * @param object
     * @return
     *//*
    public static String getFormatNum2(Object object){
        String value = String.valueOf(Optional.ofNullable(object).orElse("0"));
        DecimalFormat myFormat = new DecimalFormat("###,###.#");
        return myFormat.format(new BigDecimal(value));
    }

    *//**
     * 通过带参构造函数 创建实例
     * @param fillName
     * @param initMethod
     * @param params
     * @param <T>
     * @return
     *//*
    public static <T> T instanceClass(String fillName,String initMethod, Object ... params){
        try {
            Class tClass = Class.forName(fillName);
            if(Objects.isNull(tClass)){
                return null;
            }
            Class[] paramClasses = null;
            if(params != null && params.length > 0){
                paramClasses = new Class[params.length];
                Arrays.asList(params).stream().map(p -> p.getClass()).collect(Collectors.toList()).toArray(paramClasses);
            }
            //获取特定构造
            Constructor<T> constructor = tClass.getDeclaredConstructor(paramClasses);
            //调用构造创建实例
            T s = constructor.newInstance(params);
            //执行初始化方法
            tClass.getMethod(initMethod).invoke(s);
            return s;
        }catch (Exception e) {
            logger.error("动态创建实例异常,class:{}", fillName);
            throw new ValidateException("动态创建实例异常");
        }
    }

    *//**
     * 通过无参构造 创建实例
     * @param fillName
     * @param initMethod
     * @param fieldNames
     * @param fieldValues
     * @param <T>
     * @return
     *//*
    public static <T> T instanceClass(String fillName,String initMethod,String[] fieldNames,Object ... fieldValues){
        try {
            Class tClass = Class.forName(fillName);
            if(Objects.isNull(tClass)){
                return null;
            }
            //获取无参构造
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            //调用构造创建实例
            T s = constructor.newInstance();
            if(fieldNames != null && fieldNames.length > 0){
                for(int i = 0 ; i < fieldNames.length ; i ++){
                    if(StringUtils.isEmpty(fieldNames[i])){
                        continue;
                    }
                    Field f = tClass.getField(fieldNames[i]);
                    f.set(s,fieldValues[i]);
                }
            }
            //执行初始化方法
            tClass.getMethod(initMethod).invoke(s);
            return s;
        }catch (Exception e) {
            logger.error("动态创建实例异常,class:{}", fillName);
            throw new ValidateException("动态创建实例异常");
        }
    }

    *//**
     * 用反射获取对象属性值
     * @param obj
     * @param fieldName
     * @return
     *//*
    public static Object getFieldValue(Object obj ,String fieldName){
        Class tClass = obj.getClass();
        try {
            Field f = tClass.getField(fieldName);
            return f.get(obj);
        } catch (Exception e) {
            logger.error("获取实例属性异常,class:{}", fieldName);
            throw new ValidateException("获取实例属性异常");
        }
    }*/

    /**
     * 替换String字符
     * @param target
     * @param p1
     * @param p2
     * @return
     */
    public static String stringReplaceAll(String target,String p1,String p2){
        int indexBegin = 0;
        int indexEnd = target.indexOf(p1) + p1.length();
        StringBuffer stringBuffer = new StringBuffer("");
        while (indexEnd > -1){
            String temp = target.substring(indexBegin,indexEnd);
            String tempStr = temp.replace(p1,p2);
            stringBuffer.append(tempStr);
            indexBegin = indexEnd;
            int tempIndex = target.indexOf(p1,indexEnd);
            indexEnd = tempIndex > -1 ? tempIndex + p1.length() : tempIndex;
        }
        if(indexBegin < target.length()){
            stringBuffer.append(target.substring(indexBegin));
        }
        return stringBuffer.toString();
    }
}
