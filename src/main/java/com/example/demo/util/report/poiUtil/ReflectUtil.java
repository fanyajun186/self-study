package com.example.demo.util.report.poiUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;


public class ReflectUtil{
	

	/***
	 * 方法名：reflectObj
	 * 描述  ：通过对象类型和对象属性值构建对象
	 *
	 * @param klass 对象类型
	 * @param map 属性map，key：属性名，valye：属性值
	 * @return 对象
	 * @throws Exception 构建失败的属性名称
	 */
	public static Object reflectObj(Class<?> klass, Map<String, String> map) throws Exception{

		//得到类的属性
		Field[] fields = klass.getDeclaredFields();

		//声明类的实例
		Object tObject = null;
		try {
			tObject = klass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		//遍历类的属性
		for(Field field : fields){
			String fieldName = null;
			try {
				//得到属性名
				fieldName = field.getName();

				//得到属性类型
				Class<?> typeClass = field.getType();
				//得到类的属性的set方法
				Method method = klass.getMethod(getSetMethodName(fieldName), typeClass);

				//设置的属性值不为空
				if(map.get(fieldName)!=null){

					//为对象的set方法设值
					method.invoke(tObject, turnField(typeClass,map.get(fieldName)));
				}
			} catch (Exception e) {
				throw new Exception(fieldName);
			}
		}

		return tObject;
	}


	/***
	 * 方法名：turnField
	 * 描述  ：根据属性类型返回对应的转换后的属性值
	 *
	 * @param klass
	 * @param str
	 * @return
	 * @throws Exception
	 */
	private static Object turnField(Class<?> klass, String str) throws Exception{

		if(klass!=null && str!=null){
			String classStr = klass.toString();
			//如果是String类型
			if(classStr.equals("class java.lang.String")){
				return str;
			//如果是Integer或者int类型	
			}else if(classStr.equals("class java.lang.Integer") || classStr.equals("int")){
				return Integer.valueOf(str);
			//如果是Long或者long类型	
			}else if(classStr.equals("class java.lang.Long") || classStr.equals("long")){
				return Long.valueOf(str);
			}else{

			}
		}

		return str;
	}

	/***
	 * 方法名：getSetMethodName
	 * 描述  ：把一个字符串的第一个字母大写后拼装成setXxxx格式的字符串
	 *
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	private static String getSetMethodName(String fildeName) throws Exception{
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return "set" + new String(items);
	}
	
	/***
	 * 方法名：getGetMethodName
	 * 描述  ：把一个字符串的第一个字母大写后拼装成getXxxx格式的字符串
	 *
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	private static String getGetMethodName(String fildeName) throws Exception{
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return "get" + new String(items);
	}
	
	
	/***
	 * 方法名：getId
	 * 描述  ：
	 *
	 * @param ioc
	 * @param dbSearcher
	 * @return
	 * @throws Exception
	 */
	public String getId(DBSearcher dbSearcher) throws Exception{

		Ioc ioc = Mvcs.getIoc();
		long id = 0;
		try {
			Object tObject = ioc.get(dbSearcher.getKlass(), dbSearcher.getAnnoName());
			Method method = dbSearcher.getKlass().getMethod(dbSearcher.getMethodName(), String.class);
			id = (long)method.invoke(tObject, dbSearcher.getParaValue());
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return id>0?String.valueOf(id):null;
	}
	
	/***
	 * 方法名：trimObj
	 * 描述  ：过滤对象属性值的前后空格
	 *
	 * @param obj
	 */
	public static void trimObj(Object obj){
		
		Class<?> klass = obj.getClass();
		//得到类的属性
		Field[] fields = klass.getDeclaredFields();

		//遍历类的属性
		for(Field field : fields){
			String fieldName = null;
			try {
				//得到属性名
				fieldName = field.getName();
				//得到属性类型
				Class<?> typeClass = field.getType();
				
				String classStr = typeClass.toString();
				//如果是String类型
				if(classStr.equals("class java.lang.String")){
					
					Method getMethod = klass.getMethod(getGetMethodName(fieldName));
					//得到类的属性的set方法
					Method setMethod = klass.getMethod(getSetMethodName(fieldName), typeClass);

					String fieldValue = (String)getMethod.invoke(obj);
					if(fieldValue != null){
						fieldValue = fieldValue.trim();
						setMethod.invoke(obj, fieldValue);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
