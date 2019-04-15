package com.example.demo.util.randomUtil;

/**
 *    随机生成优惠券码
 * @author fan
 *
 */
public class RedeemCodeUtils {
	
	public static int EIGHT=8;
	public static int TWELVE=12;

	public static void main(String[] args) {
		String st1 = createBigSmallLetterStrOrNumberRadom(12);
		String st2 = createSmallStrOrNumberRadom(12);
		String st3 = createBigStrOrNumberRadom(12);
		System.out.println(st1);
		System.out.println(st2);
		System.out.println(st3);
	}

	/**
	 * 生成num位的随机字符串(数字、大小写字母随机混排)
	 * @param num
	 * @return
	 */
	public static String createBigSmallLetterStrOrNumberRadom(int num) {
	   String str = "";
	   for(int i=0;i < num;i++){  
           int intVal=(int)(Math.random()*58+65);
           if(intVal >= 91 && intVal <= 96){
        	   i--;
           }
           if(intVal < 91 || intVal > 96){
        	   if(intVal%2==0){
        		   str += (char)intVal;  
        	   }else{
        		   str += (int)(Math.random()*10);
        	   }
           }
       }  
	   return str;
	}  

	/**
	 * 生成num位的随机字符串(数字、小写字母随机混排)
	 * @param num
	 * @return
	 */
	public static String createSmallStrOrNumberRadom(int num) {  
		
		String str = "";
		for(int i=0;i < num;i++){  
			int intVal=(int)(Math.random()*26+97);
			if(intVal%2==0){
				str += (char)intVal;  
			}else{
				str += (int)(Math.random()*10);
			}
		}  
		return str;
	}

	/**
	 * 生成num位的随机字符串(小写字母与数字混排)
	 * @param num
	 * @return
	 */
	public static String createBigStrOrNumberRadom(int num) {  
		
		String str = "";
		for(int i=0;i < num;i++){  
			int intVal=(int)(Math.random()*26+65);
			if(intVal%2==0){
				str += (char)intVal;  
			}else{
				str += (int)(Math.random()*10);
			}
		}  
		return str;
	}  

	
}
