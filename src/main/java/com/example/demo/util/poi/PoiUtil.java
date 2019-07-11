package com.example.demo.util.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.util.exception.ServiceException;
import com.google.common.collect.Lists;

public class PoiUtil {

	
	public static List<String> getImportData(String path) throws Exception {
		List<String> result = Lists.newArrayList();
		
		File file = new File(path);		
		String fileName =file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		if(!".xls".equals(suffix) && !".xlsx".equals(suffix)) {
	        	throw new ServiceException("文件格式不合规");
	    }
        FileInputStream fileInputStream = new FileInputStream(file);        
        Workbook wbc = WorkbookFactory.create(fileInputStream); 
        Sheet sheet = wbc.getSheetAt(0); 
        int rowNum = sheet.getLastRowNum();        
		if (rowNum == 0) {
			throw new ServiceException("文件中没有数据!");
		}
		for (int i = 0; i <= rowNum; i++) {
			String appCode=readRowNum(sheet,i);
			if(StringUtils.isNotEmpty(appCode)) {
				result.add(appCode);				
			}
		}
		
		return result;
	}

	
	private static String readRowNum(Sheet sheet, int i) {
		Row  row;//行
		int cellNum;// 列数
		Cell cell;//单元格
		row = sheet.getRow(i);
		cellNum = row.getLastCellNum();
		for (int j = 0; j < cellNum; j++) {
			cell = row.getCell(j);				
			if (cell != null) {
				if (j == 0) {
					if (!StringUtils.isEmpty(cell.getStringCellValue())) {
						return cell.getStringCellValue().trim();//订单号
					}
				}
			}
		}
		return null;

	}
}
