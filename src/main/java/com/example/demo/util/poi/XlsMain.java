package com.example.demo.util.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsMain {

	 public static void main(String[] args) throws IOException {
	        XlsMain xlsMain = new XlsMain();
	        XlsDto xls = null;
	        List<XlsDto> list = xlsMain.readXls("D:/pldrxkxxmb.xls");
	         
	        try {
	            XlsDto2Excel.xlsDto2Excel(list);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        for (int i = 0; i < list.size(); i++) {
	            xls = (XlsDto) list.get(i);
	            System.out.println(xls.getXh() + "    " + xls.getXm() + "    "
	                    + xls.getYxsmc() + "    " + xls.getKcm() + "    "
	                    + xls.getCj());
	        }
	 
	 }
	 
	 /**
	     * 读取xls文件内容
	     * 
	     * @return List<XlsDto>对象
	     * @throws IOException 输入/输出(i/o)异常             
	     */
	    private List<XlsDto> readXls(String path) throws IOException {
	        InputStream is = new FileInputStream(path);
	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);	        
	        XlsDto xlsDto = null;
	        List<XlsDto> list = new ArrayList<XlsDto>();
	        // 循环工作表Sheet
	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            // 循环行Row
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow == null) {
	                    continue;
	                }
	                xlsDto = new XlsDto();
	                // 循环列Cell
	                // 0学号 1姓名 2学院 3课程名 4 成绩
	                // for (int cellNum = 0; cellNum <=4; cellNum++) {
	                HSSFCell xh = hssfRow.getCell(0);
	                if (xh == null) {
	                    continue;
	                }
	                xlsDto.setXh(getValue(xh));
	                HSSFCell xm = hssfRow.getCell(1);
	                if (xm == null) {
	                    continue;
	                }
	                xlsDto.setXm(getValue(xm));
	                HSSFCell yxsmc = hssfRow.getCell(2);
	                if (yxsmc == null) {
	                    continue;
	                }
	                xlsDto.setYxsmc(getValue(yxsmc));
	                HSSFCell kcm = hssfRow.getCell(3);
	                if (kcm == null) {
	                    continue;
	                }
	                xlsDto.setKcm(getValue(kcm));
	                HSSFCell cj = hssfRow.getCell(4);
	                if (cj == null) {
	                    continue;
	                }
	                xlsDto.setCj(Float.parseFloat(getValue(cj)));
	                list.add(xlsDto);
	            }
	        }
	        return list;
	    }
	    
	   
	    private String getValue(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            // 返回布尔类型的值
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            // 返回数值类型的值
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	            // 返回字符串类型的值
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
}
