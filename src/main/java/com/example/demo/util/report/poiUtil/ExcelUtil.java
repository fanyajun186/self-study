package com.example.demo.util.report.poiUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelUtil {	

	private static ExcelMappingSingleton excelMappingSingleton = ExcelMappingSingleton.getInstance();


	/***
	 * 方法名：analyzeWorkbook
	 * 描述  ：解析excel，按照不同的对象和属性封装成不同的对象treeMap。
	 *   注：1.支持.xls和.xlsx格式数据；
	 *      2.文件中数据类型只支持文本类型；
	 *      3.解析对象只支持String、int、Integer、Long、long类型属性解析。
	 *
	 * @param klass 封装Map的泛型class
	 * @param alias 导入excel对应的配置文件别名
	 * @param importFile 导入文件
	 * @param returnMap 解析失败信息，key：excel行号，value：失败信息
	 * @return 解析返回数据，key：excel行号，value：每行信息转换后的对象
	 * @throws Exception
	 */
	public static Map<Integer,?> analyzeWorkbook(Class<?> klass, String alias, File importFile, Map<Integer, String> returnMap) throws Exception {
		String fileName = importFile.getName();
		//判断文件类型是否合法，支持excel2003、2007
		if(!(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))){
			throw new Exception("文件不是excel格式！");
		}

		Map<Integer, Object> objects = new HashMap<Integer, Object>(); 

		FileInputStream inputStream = null;
		Workbook workbook = null;
		Sheet sheet = null;
		try {
			inputStream = new FileInputStream(importFile);
			//创建workbook
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheetAt(0); 
			//获取第一行行号,忽略第一行
			int startRow = sheet.getFirstRowNum(); 
			//获取最后一行行号
			int lastRow = sheet.getLastRowNum();  
			if(startRow >= lastRow){
				StringUtil.putMsgMap(returnMap, 0, ImportCode.IMPORTFILE_IS_NULL.getDescription());
				return null;
			}
			
			StringUtil.putMsgMap(returnMap, 0, StringUtil.formatImportResult(lastRow));
			String[] titles = getExcelTitles(sheet.getRow(startRow));
			boolean checkNecessaryTitle = checkNecessaryTitle(returnMap, titles, klass.getName(), alias);
			if(!checkNecessaryTitle){
				return objects;
			}
			
			Map<String, String> rowMap = new HashMap<String, String>();
			Row row = null;
			Cell cell = null;
			String cellValue = null;
			ExcelMappingBean excelMapping = null;
			int firstCellNum = 0; 
			int lastCellNum = 0; 
			for(int indexRow = startRow + 1; indexRow <= lastRow; indexRow++){  
				//获取行
				row = sheet.getRow(indexRow);  
				if(row == null){  
					continue;  
				} 
				//获取第一列列号
				firstCellNum = row.getFirstCellNum(); 
				//获取最后一列列号
				lastCellNum = row.getLastCellNum();  
				lastCellNum = lastCellNum > titles.length ? lastCellNum : titles.length;
				
				boolean tag = true;
				//遍历相关的列数据  
				for(int indexCol = 0; indexCol <lastCellNum; indexCol++){  
					//获取单元格
					cell = row.getCell(indexCol);  
					//转换单元格数据为String类型
					try{
						if(cell!=null){
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cellValue = cell.getStringCellValue();
							if(cellValue!=null){
								cellValue = cellValue.trim();
							}
						}
						
						excelMapping = excelMappingSingleton.getExcelMapping(klass.getName(), alias, titles[indexCol]);
						if(excelMapping != null ){
							if(!"false".equals(cellValue=checkCell(returnMap, excelMapping, indexRow+1, cellValue))){
								//将对象属性名和属性值添加到rowMap中
								rowMap.put(excelMapping.getProperty(), cellValue); 
							}else{
								tag = false;
							}
						}
					} catch (Exception e) {
						tag = false;
						System.out.println("indexRow:" + indexRow + ";lastCellNum:" + indexCol + ";cell:" + cell);
					}
					
					excelMapping = null;
					cellValue = null;
					cell = null;
				}
				lastCellNum = 0;
				try {
					//解析rowMap生产object，添加到返回列表中
					if(tag){
						objects.put(indexRow+1, ReflectUtil.reflectObj(klass, rowMap));
					}
					rowMap.clear();
				} catch (Exception e) {
					//将构建失败的属性字段添加到返回信息
					StringUtil.putMsgMap(returnMap, indexRow+1, StringUtil.formatReflectMsg(e.getMessage()));
				}
				row = null;
			}  
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new Exception();
				}
			}
		}
		return objects;
	}

	private static String[] getExcelTitles(Row row){

		int firstCellNum = row.getFirstCellNum(); 
		int lastCellNum = row.getLastCellNum();  
		String[] titles = new String[lastCellNum];
		//遍历相关的列数据  
		for(int indexCol = firstCellNum; indexCol <lastCellNum; indexCol++){  
			//获取单元格
			Cell cell = row.getCell(indexCol);  
			if(cell != null){  
				titles[indexCol] = cell.getStringCellValue(); 
			}  
		}

		return titles;		
	}

	private static String checkCell(Map<Integer, String> returnMap, ExcelMappingBean excelMapping, int index, String cellValue){
		String title = excelMapping.getExcelTitle();
		boolean required = excelMapping.isRequired();
		if(required && StringUtil.strIsNull(cellValue)){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellRequired(title));
			return "false";
		}

		int length = cellValue==null?0:cellValue.length();
		int minLength = excelMapping.getMinLength();
		int maxLenght = excelMapping.getMaxLength();
		if(length!=0 && ((minLength!=0 && minLength>length) || (maxLenght!=0 && maxLenght<length))){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellLengthError(title, minLength, maxLenght));
			return "false";
		}
		String type = excelMapping.getType();
		if("text".equals(type)){
			
		}else if("port".equals(type) && !StringUtil.checkPort(cellValue)){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellPortError(title));
			return "false";
		}else if("ip".equals(type) /*&& !IPUtil.isValid(cellValue)*/){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellIpError(title));
			return "false";
		}else if("phone".equals(type) && !StringUtil.checkMobilePhone(cellValue)){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellPhoneError(title));
			return "false";
		}else if("email".equals(type) && !StringUtil.checkEmail(cellValue)){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellEmailError(title));
			return "false";
		}else if("protocol".equals(type) && !StringUtil.checkProtocol(cellValue)){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellProtocolError(title));
			return "false";
		}else if("idcard".equals(type) && !StringUtil.checkIdentityCard(cellValue)){
			StringUtil.putMsgMap(returnMap, index, StringUtil.formatCheckCellIDCardError(title));
			return "false";
		}else if("map".equals(type)){
			String mapName = excelMapping.getMapName();
			cellValue = excelMappingSingleton.getValueFromDictionary(mapName, cellValue);
			cellValue = StringUtil.strIsNull(cellValue)?excelMapping.getMapDefaultValue():cellValue;
			if(StringUtil.strIsNull(cellValue)){
				return "false";
			}
		}else if("db".equals(type)){
			cellValue = excelMappingSingleton.getValueFromDB(excelMapping, cellValue);
		}else{
			//return "false";
		}

		return cellValue;
	}
	
	private static boolean checkNecessaryTitle(Map<Integer, String> returnMap, String[] excelTitles, String className, String alias){
		List<String> necessaryTitles= excelMappingSingleton.getNecessaryTitles(className, alias);
		if(necessaryTitles == null || necessaryTitles.isEmpty()){
			return true;
		}
		int i = 0;
		for(String necessaryTitle : necessaryTitles){
			boolean tag = false;
			for(String title : excelTitles){
				if(necessaryTitle.equals(title.trim())){
					tag = true;
					i++;
					break;
				}
			}
			
			if(!tag){
				StringUtil.putMsgMap(returnMap, 1, StringUtil.formatNecessaryTitleError(necessaryTitle));
			}
		}
		
		return i == necessaryTitles.size()?true:false;
	}
}
