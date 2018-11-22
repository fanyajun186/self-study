package com.example.demo.util.data;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class dataFormatUtils {

    public static void main(String[] args) throws Exception {
       String str1="1000";
       System.out.println(Integer.parseInt(fmtMicrometer(str1)));      
       double j=2.4;
       System.out.println("往上凑整:Math.ceil(2.1)=" + (int)Math.ceil(j));
       System.out.println("往下凑整:Math.floor(2.1)=" + (int)Math.floor(j));
       System.out.println("四舍五入凑整:Math.round(2.1)=" + (int)Math.round(j));
       
       List<String> list=new ArrayList<String>();
      
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");   
       
       Date d1 = sdf.parse("201605");//定义起始日期
       Date d2 = sdf.parse("201605");//定义结束日期
       Calendar dd = Calendar.getInstance();//定义日期实例
       dd.setTime(d1);//设置日期起始时间
       if(dd.getTime().after(d2)){
           throw new Exception("开始时间大于结束时间"); 
       }else{
           while(dd.getTime().before(d2)){//判断是否到结束日期
               String str = sdf.format(dd.getTime());              
               list.add(str);
               dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
           }
           list.add(sdf.format(d2.getTime()));
       }
       for (String timeStr : list) {
           System.out.println(timeStr);//输出日期结果    
       }
       
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
