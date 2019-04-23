package com.example.demo.util.designMode.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.example.demo.dto.common.Student;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ReflectUtil {

	
	public static void main(String[] args) throws Exception {
		Student dto=new Student();
		dto.setAlive(true);
		dto.setName("小明");		
		dto.setGrade("六年级");
		dto.setSchool("光明小学");
		dto.setAppTime(new Date());
		dto.setAlive(true);
		dto.setaLoanAmount("10000");
		dto.setrCarLoanAmount("20000");
		
		/*System.out.println("================>>>>>>>>>");
		Map<String,Object> map1=getAllFieldValueByObject(dto);
		System.out.println(map1);
		System.out.println(map1.size());
		
		//getFieldValueByTargetName(dto,"belongSale");	
		System.out.println("================");
		setFieldValueByObject(dto,"rLoanAmount","666");
		Map<String,Object> map2=getAllFieldByObject(dto);
		System.out.println(map2);
		System.out.println(map2.size());
		System.out.println(getFieldValueByObject(dto,"appTime"));
		System.out.println(getFieldValueByObject(dto,"aLoanAmount"));
		System.out.println(getFieldValueByObject(dto,"rLoanAmount"));
		System.out.println(getFieldValueByObject(dto,"rCarLoanAmount"));*/
		
		
		BeanInfo beanInfo = Introspector.getBeanInfo(Student.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		Map<String, String> map = null;
		map = objectToMap(dto, propertyDescriptors);
		System.out.println(map.size());
    }   
	
	public static Map<String, String> objectToMap(Object obj, PropertyDescriptor[] propertyDescriptors) throws Exception {
		if(obj == null)
			return null;

		Map<String, String> map = new HashMap<String, String>();

		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();

			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter!=null ? getter.invoke(obj) : null;

			String strValue = "";
//			if(value instanceof Date){
//				strValue = DateUtil.formatDateTime((Date)value);
//
//			}else {
//				strValue = StringUtil.fixNull(value);
//			}
			map.put(key, strValue);
		}
		obj = null; // 置空，尽快回收
		return map;
	}
	
    /**
     * 将指定对象转化为map
     * @param object
     * @return
     * @throws Exception
     */
    public static Map<String,Object> getAllFieldByObject (Object object) throws Exception {
   	 	Map<String,Object> map=new HashMap<String,Object>();
        // 获取该对象的Class
        Class objClass = object.getClass();
        //Class<?> superClazz = objClass.getSuperclass();
        // 获取所有的属性数组
        Field[] fields = objClass.getDeclaredFields();
        Field[] allFields = getSuperClassFields(fields, objClass);
        for (Field field:allFields) {
            // 属性名称
            String currentFieldName = "";
            // 获取属性上面的注解 import com.fasterxml.jackson.annotation.JsonProperty;
            boolean has_JsonProperty =  field.isAnnotationPresent(JsonProperty.class);
            if(has_JsonProperty){
                currentFieldName = field.getAnnotation(JsonProperty.class).value();
            }else {
                currentFieldName = field.getName();
            }
        	field.setAccessible(true);
        	map.put(currentFieldName, field.get(object));
        }
        return map;
    }
    
    /**
     * 获取指定对象的指定字段值
     * @param object
     * @param targetFieldName
     * @return
     * @throws Exception
     */
    public static Object getFieldValueByObject (Object object , String targetFieldName) throws Exception {
    	 
        // 获取该对象的Class
        Class objClass = object.getClass();
        // 获取所有的属性数组
        Field[] fields = objClass.getDeclaredFields();
        Field[] allFields = getSuperClassFields(fields, objClass);
        for (Field field:allFields) {
            // 属性名称
            String currentFieldName = "";
            // 获取属性上面的注解 import com.fasterxml.jackson.annotation.JsonProperty;
            boolean has_JsonProperty =  field.isAnnotationPresent(JsonProperty.class);
            if(has_JsonProperty){
                currentFieldName = field.getAnnotation(JsonProperty.class).value();
            }else {
                currentFieldName = field.getName();
            }
            if(currentFieldName.equals(targetFieldName)){
            	field.setAccessible(true);            	
                return field.get(object); // 通过反射拿到该属性在此对象中的值(也可能是个对象)
            }
        }
        return null;
    }
    
    /**
     * 为指定对象的指定字段设置值
     * @param object
     * @param targetFieldName     
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    public static void setFieldValueByObject(Object object , String targetFieldName,Object targetFieldValue) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
    	// 获取该对象的Class
        Class objClass = object.getClass();        
        //当前对象的所有字段
		Field[] fields = objClass.getDeclaredFields();
		//当前对象及父类对象所有字段
		Field[] allFields = getSuperClassFields(fields, objClass);
		//设置标识
		boolean flag=false;
		for (Field field:allFields) {
            // 属性名称
            String currentFieldName = "";
            // 获取属性上面的注解 import com.fasterxml.jackson.annotation.JsonProperty;
            boolean has_JsonProperty =  field.isAnnotationPresent(JsonProperty.class);
            if(has_JsonProperty){
                currentFieldName = field.getAnnotation(JsonProperty.class).value();
            }else {
                currentFieldName = field.getName();
            }
            if(currentFieldName.equals(targetFieldName)){
            	field.setAccessible(true);
            	field.setAccessible(true);
                field.set(object, targetFieldValue);
                flag=true;
            }
        }		
        if(!flag) {
        	throw new NoSuchFieldException("目标对象无指定字段");
        }        
    }
    
    /**
     * 迭代获取父级的所有字段
     * @param tableFields
     * @param clazz
     * @return
     */
    private static Field[] getSuperClassFields(Field[] tableFields, Class<?> clazz) {
        Class<?> superClazz = clazz.getSuperclass();        
        if (superClazz.equals(Object.class)) {
            return tableFields;
        }
        Field[] tableSuperFields = superClazz.getDeclaredFields();
        Field[] c = new Field[tableFields.length + tableSuperFields.length];
        System.arraycopy(tableFields, 0, c, 0, tableFields.length);
        System.arraycopy(tableSuperFields, 0, c, tableFields.length, tableSuperFields.length);        
        c =getSuperClassFields(c, superClazz);
        return c;
    }
    
    
	/**
	 * 获取对象全量数据，但是aLoanAmount，rLoanAmount，rCarLoanAmount这类的数据不用@Data注解无法获取值
	 * @param obj
	 * @return
	 * @throws IntrospectionException
	 */
	 public static Map<String,Object> getAllFieldValueByObject(Object obj) throws IntrospectionException{
		 Map<String,Object> map=new HashMap<String,Object>();
		 Class<?> clazz = obj.getClass();
		 Field[] tableFields = clazz.getDeclaredFields();
	        Class<?> superClazz = clazz.getSuperclass();
	        if (superClazz.equals(Object.class)) {
	            System.out.println("没有父类");
	        } else {
	            Field[] tableSuperFields = superClazz.getDeclaredFields();
	            Field[] superFields = new Field[tableFields.length + tableSuperFields.length];
	            System.arraycopy(tableFields, 0, superFields, 0, tableFields.length);	           
	            System.arraycopy(tableSuperFields, 0, superFields, tableFields.length, tableSuperFields.length);
	            //获取父类以上所有字段
	            Field[] allFields = getSuperClassFields(superFields, superClazz);	            
	            for (int i = 0; i < allFields.length; i++) {
	            	try {
	            		PropertyDescriptor pd = new PropertyDescriptor(allFields[i].getName(), clazz);
		                Method getMethod = pd.getReadMethod();//获得get方法
		                Object fieldValue = ReflectionUtils.invokeMethod(getMethod, obj);		                              
		                //System.out.println(allFields[i].getName() + "的值：" +  fieldValue.toString());
		                map.put(allFields[i].getName(), fieldValue);
					} catch (Exception e) {
						System.out.println("拿不到值得的字段"+allFields[i].getName());						
					}	                
	            }
	        }
	        return map;
	 }
	 
	 /**
	  * 获取指定对象的值对象全量数据，但是aLoanAmount，rLoanAmount，rCarLoanAmount这类的数据不用@Data注解无法获取值
	  * @param obj
	  * @param targetFieldName
	  * @return
	  * @throws IntrospectionException
	  */
	 public static Object getFieldValueByTargetName(Object obj,String targetFieldName) throws IntrospectionException{
		 Class<?> clazz = obj.getClass();
		 Field[] tableFields = clazz.getDeclaredFields();
	        Class<?> superClazz = clazz.getSuperclass();
	        if (superClazz.equals(Object.class)) {
	            System.out.println("没有父类");
	        } else {
	            Field[] tableSuperFields = superClazz.getDeclaredFields();
	            Field[] superFields = new Field[tableFields.length + tableSuperFields.length];
	            System.arraycopy(tableFields, 0, superFields, 0, tableFields.length);
	            System.arraycopy(tableSuperFields, 0, superFields, tableFields.length, tableSuperFields.length);
	            Field[] allFields = getSuperClassFields(superFields, superClazz);
	            for (int i = 0; i < allFields.length; i++) {
	            	if(allFields[i].getName().equals(targetFieldName)) {
	            		PropertyDescriptor pd = new PropertyDescriptor(allFields[i].getName(), clazz);
		                Method getMethod = pd.getReadMethod();//获得get方法
		                Object fieldValue = ReflectionUtils.invokeMethod(getMethod, obj);		                             
		                System.out.println(allFields[i].getName() + "的值：" +  fieldValue.toString());
		                return fieldValue;
	            	}
	                
	            }
	        }
	        return null;
	 }	
}
