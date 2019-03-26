package com.example.demo.util.base.data;

import java.text.DecimalFormat;

/**
 * 四舍五入相关操作
 * @author: yajun.fan
 * @date: 2019年3月25日
 */
public class CarryOverTest {

	public static void main(String[] args) {
	   String str1="1000";
       System.out.println(Integer.parseInt(fmtMicrometer(str1)));      
       double j=2.4;
       System.out.println("往上凑整:Math.ceil(2.1)=" + (int)Math.ceil(j));
       System.out.println("往下凑整:Math.floor(2.1)=" + (int)Math.floor(j));
       System.out.println("四舍五入凑整:Math.round(2.1)=" + (int)Math.round(j));

	}
	
	/** 
          * 格式化数字为千分位显示； 
     * @param 要格式化的数字； 
     * @return 
     */  
    public static String fmtMicrometer(String text){
        DecimalFormat df = null;  
        if(text.indexOf(".") > 0){
            if(text.length() - text.indexOf(".")-1 == 0){
                df = new DecimalFormat("###,##0.");  
            }else if(text.length() - text.indexOf(".")-1 == 1){
                df = new DecimalFormat("###,##0.0");  
            }else{
                df = new DecimalFormat("###,##0.00");  
            }
        }else{
            df = new DecimalFormat("###,##0");  
        }  
        double number = 0.0;  
        try {
             number = Double.parseDouble(text);  
        } catch (Exception e) {  
            number = 0.0;  
        }  
        return df.format(number);  
    }

}
