package com.example.demo.util.report.poiUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.nutz.mvc.Mvcs;


public class ExcelMappingSingleton{
	
	private static ExcelMappingSingleton excelMappingSingleton = null;

	//创建字典map，key：字典名，value：字典（key：字典文本text，value：字典值value）
	private Map<String, String> dictionaryMap = new HashMap<String, String>();
	private Map<String, ExcelMappingBean> excelMap = new HashMap<String, ExcelMappingBean>();
	private Map<String, List<String>> necessaryTitleMap = new HashMap<String, List<String>>();
	
	/*private final String CONFDIR = Cupid.getContextValueAs(String.class, "conf.dir");*/
	private final String CONFDIR = System.getProperty("conf.dir");
	//private static Map<String, List<EventAttrOption>> eventAttrMap;

	//单例
	public synchronized static ExcelMappingSingleton getInstance(){
		if(excelMappingSingleton == null){
			excelMappingSingleton = new ExcelMappingSingleton();
		}
		
		return excelMappingSingleton;
	}
	//构造函数私有化
	private ExcelMappingSingleton(){
		HttpSession session = Mvcs.getHttpSession();		
		//EventDataTable eventDataTable = (EventDataTable)session.getServletContext().getAttribute("eventDataTable");
		//eventAttrMap = eventDataTable.getAttrDataMap();
		initExcelMap();
	}
	
	private void initExcelMap(){
		
		String fileName = "/importExcelMapping.xml";
		
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(new File(fileName));
			Element root = document.getRootElement();
			List<Element> excelEles = root.elements();
			for(Element excelEle : excelEles){
				String id = excelEle.attributeValue("id");
				String alias = excelEle.attributeValue("alias");
				List<Element> mappingEles = excelEle.elements();
				List<String> titles = new ArrayList<String>();
				for(Element mappingEle : mappingEles){
					
					ExcelMappingBean excelMapping = new ExcelMappingBean();
					excelMapping.setExcelTitle(mappingEle.attributeValue("excelTitle"));
					excelMapping.setProperty(mappingEle.attributeValue("property"));
					excelMapping.setRequired(Boolean.valueOf(mappingEle.attributeValue("required")));
					if(excelMapping.isRequired()){
						titles.add(excelMapping.getExcelTitle());
					}
					
					excelMapping.setType(mappingEle.attributeValue("type"));
					String maxLengthStr = mappingEle.elementText("maxLength");
					excelMapping.setMaxLength((maxLengthStr!=null && !"".equals(maxLengthStr))?Integer.valueOf(maxLengthStr):0);
					String minLengthStr = mappingEle.elementText("minLength");
					excelMapping.setMinLength((minLengthStr!=null && !"".equals(minLengthStr))?Integer.valueOf(minLengthStr):0);
					
					if("map".equals(excelMapping.getType())){
						excelMapping.setMapName(mappingEle.elementText("mapName"));
						excelMapping.setMapDefaultValue(mappingEle.elementText("mapDefaultValue"));
						//setDictionaryMapMap(excelMapping.getMapName());
					}else if("db".equals(excelMapping.getType())){
						excelMapping.setClassName(mappingEle.elementText("className"));
						excelMapping.setAnnoName(mappingEle.elementText("annoName"));
						excelMapping.setMethodName(mappingEle.elementText("methodName"));
					}
					if(StringUtil.strIsNull(alias)){
						excelMap.put(id + "_" + excelMapping.getExcelTitle(), excelMapping);
					}else{
						excelMap.put(id + "$" + alias + "_" + excelMapping.getExcelTitle(), excelMapping);
					}
				}
				
				if(!titles.isEmpty()){
					if(StringUtil.strIsNull(alias)){
						necessaryTitleMap.put(id, titles);
					}else{
						necessaryTitleMap.put(id + "$" + alias, titles);
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		
	}

	/***
	 * 方法名：setDictionaryMapMap
	 * 描述  ：
	 *
	 * @param key
	 */
	
	/*private void setDictionaryMapMap(String key){

		List<EventAttrOption> list = eventAttrMap.get(key);

		//构建字典：text value
		for(EventAttrOption eao : list){
			dictionaryMap.put(key + "_" + eao.getText(), eao.getValue());
		}

	}*/

	/****
	 * 方法名：getValueFromDictionary
	 * 描述  ：通过字典名和字典文本反向获取字典value
	 *
	 * @param dicIndex 字典名
	 * @param text 字典文本
	 * @return 字典value
	 */
	public String getValueFromDictionary(String dicIndex, String text) {

		//根据字典名称获取数据
		String value = dictionaryMap.get(dicIndex + "_" + text);

		return value;
	}
	
	public String getValueFromDB(ExcelMappingBean excelMappingBean,String paraValue){
		String value = null;
		DBSearcher dbSearcher = new DBSearcher();

		try {
			Class<?> klass = Class.forName(excelMappingBean.getClassName());
			dbSearcher.setKlass(klass);
			dbSearcher.setAnnoName(excelMappingBean.getAnnoName());
			dbSearcher.setMethodName(excelMappingBean.getMethodName());
			dbSearcher.setParaValue(paraValue);

			value = new ReflectUtil().getId(dbSearcher);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public ExcelMappingBean getExcelMapping(String excelName, String alias, String excelTitle){
		if(StringUtil.strIsNull(alias)){
			return excelMap.get(excelName + "_" + excelTitle);
		}else{
			return excelMap.get(excelName + "$" + alias + "_" + excelTitle.trim());
		}
	}
	
	public List<String> getNecessaryTitles(String className, String alias){
		if(StringUtil.strIsNull(alias)){
			return necessaryTitleMap.get(className);
		}else{
			return necessaryTitleMap.get(className + "$" + alias);
		}
	}
}

