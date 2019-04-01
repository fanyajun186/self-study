package com.example.demo.util.base.varArgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.dto.common.DynamicExportListDTO;
import com.example.demo.util.designMode.reflect.ReflectUtil;




public class TestVarArgus {

	private static Logger logger = LoggerFactory.getLogger(TestVarArgus.class);
	
	public static void main(String[] args) {
		
		dealArray(1,2,3);		
		
	}
	
	public static void dealArray(int... intArray){  
        for (int i : intArray)  
            System.out.print(i +" ");          
        System.out.println();
	} 


	/**
	 * 将源数据和批处理数据通过配置进行组装
	 * @param sourceList
	 * @param sourceFields
	 * @param targetFields
	 * @param batchResults
	 * @throws Exception
	 */
	public static <T> List<DynamicExportListDTO> dealComplexData(List<T> sourceList, String[] sourceFields, String[] targetFields, Map<Object, Map<String, Object>>... batchResults) throws Exception {
		//结果集
		List<DynamicExportListDTO> result=new ArrayList<DynamicExportListDTO>();
		//判断参数格式是否支持组装，不支持的话只将源数据放在目标对象中
		Boolean flag=isNeedPackage(sourceFields,targetFields,batchResults);
		Map<Object, Map<String, Object>>[] arr=batchResults;
		//组装数据
		for(T dto : sourceList) {
			//将源数据封装到目标对象的属性字段中
			DynamicExportListDTO odto=new DynamicExportListDTO();
			odto.setSourceDto(ReflectUtil.getAllFieldByObject(dto));
			if(flag) {
				for(int i=0;i<sourceFields.length;i++) {
					String type=sourceFields[i];//源目标对象字段名
					String target=targetFields[i];//目标对象字段名
					Map<Object, Map<String, Object>> map = arr[i];
					Object object1 =ReflectUtil.getFieldValueByObject(dto, type);				
					Map<String, Object> targetMap = map.get(object1);
					//System.out.println(targetMap);
					ReflectUtil.setFieldValueByObject(odto, target, targetMap);				
				}
			}			
			result.add(odto);
		}
		return result;
	}

	
	private static Boolean isNeedPackage(String[] sourceFields, String[] targetFields,
			Map<Object, Map<String, Object>>[] batchResults) {
		Boolean flag= true;
		if(sourceFields==null || sourceFields.length==0) {
			logger.info("源数据的字段数组为空");
			return false;
		}
		if(targetFields==null || targetFields.length==0) {
			logger.info("目标数据的字段数组为空");
			return false;
		}
		if(batchResults==null || batchResults.length==0) {
			logger.info("批处理结果数据数组为空");
			return false;
		}
		if(sourceFields.length!=targetFields.length || sourceFields.length!=batchResults.length) {
			logger.info("源数据字段");
			return false;
		}
		return flag;
	}
	
}
