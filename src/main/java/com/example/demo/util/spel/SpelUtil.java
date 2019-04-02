package com.example.demo.util.spel;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.spel.Inventor;
import com.example.demo.dto.spel.PlaceOfBirth;
import com.example.demo.dto.spel.Society;

import lombok.extern.slf4j.Slf4j;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * spel学习练手类
 * @author yajun.fan
 * @date 2019年4月2日
 */
@Slf4j
public class SpelUtil {

    /**
     * spel解析器  config ：自动补全空集合、自动填充
     */
    private static final ExpressionParser PARSER = new SpelExpressionParser(new SpelParserConfiguration(Boolean.TRUE,Boolean.TRUE));


    /**
     * 执行el表达式 获取boolean结果
     * @param expression
     * @param data
     * @return
     */
    public static Boolean parserElToBoolean(String expression, Object data){
        Boolean result = PARSER.parseExpression(expression).getValue(data,Boolean.class);
        return Objects.isNull(result) ? Boolean.FALSE : result;
    } 

    /**
     * 校验spel 若不通过，返回提示信息
     * @param spelList
     * @param obj
     * @return
     */
    public static Object parserSpelExpressions(List<ValidateSpel> spelList,Object obj){
        for(ValidateSpel exp : spelList){
        	Object result = null;
            //捕获spel表达式执行时发生的异常
            //result = parserElToString(exp,obj);
            if(obj!=null) {
            	result = PARSER.parseExpression(exp.getExpression()).getValue(obj,String.class);            	
            }else {
            	result = PARSER.parseExpression(exp.getExpression()).getValue();
            }
            if(!StringUtils.isEmpty(result)){
            	System.out.println(result);
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {    	
    	/*List<ValidateSpel> spelList= new ArrayList<ValidateSpel>();
    	ValidateSpel validateSpel = new ValidateSpel();    	
    	validateSpel.setExpression("'Hello World'.bytes.length");
    	spelList.add(validateSpel);
    	parserSpelExpressions(spelList,null);*/    	
    	
    	//baseTest();    	
    	//simlpeTest();
    	//parserTest();
    	//collectionTest();
    	operatorTest();
    	
	}   

    //运算符操作
    //文本是等值 比如: lt (<), gt (>), le (<=), ge (>=), eq (==), ne (!=), div (/), mod (%), not (!). 这些都是不区分大小写。
	private static void operatorTest() {
		boolean trueValue = PARSER.parseExpression("2 == 2").getValue(Boolean.class);		
		boolean trueValue2 = PARSER.parseExpression("2 eq 2").getValue(Boolean.class);		
		int i=PARSER.parseExpression("2 div 2").getValue(int.class);

		// evaluates to false
		boolean falseValue = PARSER.parseExpression("2 < -5.0").getValue(Boolean.class);
		// evaluates to true
		boolean trueValue3 = PARSER.parseExpression("'black' < 'block'").getValue(Boolean.class);		
		boolean falseValue2 = PARSER.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);

		// evaluates to true
		boolean trueValue4 = PARSER.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		//evaluates to false
		boolean falseValue3 = PARSER.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		
		// Addition
		int two = PARSER.parseExpression("1 + 1").getValue(Integer.class); // 2
		String testString = PARSER.parseExpression("'test' + ' ' + 'string'").getValue(String.class); // test string

		// Subtraction
		int four = PARSER.parseExpression("1 - -3").getValue(Integer.class); // 4
		//1e4就是10000,1后面4个0
		double d = PARSER.parseExpression("1e4").getValue(Double.class); // -9000

		// Multiplication
		int six = PARSER.parseExpression("-2 * -3").getValue(Integer.class); // 6		
		double twentyFour = PARSER.parseExpression("2.0 * 3e0 * 4").getValue(Double.class); // 24.0

		// Division
		int minusTwo = PARSER.parseExpression("6 / -3").getValue(Integer.class); // -2
		double one = PARSER.parseExpression("8.0 / 4e1 / 2").getValue(Double.class); // 0.1

		// Modulus
		int three = PARSER.parseExpression("7 % 4").getValue(Integer.class); // 3
		int one2 = PARSER.parseExpression("8 / 5 % 2").getValue(Integer.class); // 1
		// Operator precedence
		int minusTwentyOne = PARSER.parseExpression("1+2-3*8").getValue(Integer.class); // -21
	}

	//基础操作
    private static void baseTest() {    	
    	GregorianCalendar c = new GregorianCalendar();
    	c.set(1856, 7, 9);
    	PlaceOfBirth placeOfBirth=new PlaceOfBirth("沧州","河北");
    	
    	Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
    	tesla.setPlaceOfBirth(placeOfBirth);
    	tesla.setAlive(true);
    	tesla.setInventions(new String[]{"1","2","3","4"});    	
    	
    	Expression exp = PARSER.parseExpression("name");    	
    	EvaluationContext context = new StandardEvaluationContext(tesla);    	
    	System.out.println(exp.getValue(context,String.class));
    	System.out.println(exp.getValue(tesla,String.class));
    	
    	//不区分大小写允许的属性名称的第一个字母可大可小。
    	int year=(Integer) PARSER.parseExpression("birthdate.year +1900").getValue(context);
    	String city = (String) PARSER.parseExpression("placeOfBirth.City").getValue(context);
    	Boolean alive=(Boolean) PARSER.parseExpression("Alive").getValue(context);
    	
    	System.out.println("year:"+year);
    	System.out.println("city:"+city);
    	System.out.println("alive:"+alive);
    	
    	Expression exp2 = PARSER.parseExpression("name=='tom'");
    	Boolean result = exp2.getValue(context, Boolean.class);
    	System.out.println(result);		
    	
    	Expression exp3 = PARSER.parseExpression("T(java.lang.Math).random() * 100.0");
    	Object value = exp3.getValue();
    	System.out.println("aaaa:"+value);
    	
    	// 数组和列表使用方括号获得内容。inventions属性为空会异常，长度不够也会报数组越界
    	String invention =PARSER.parseExpression("inventions[3]").getValue(context,String.class);
    	System.out.println("invention:"+invention);
    	
    	//复杂对象取值
    	Society ieee = new Society();
    	List<Inventor> list=new ArrayList<Inventor>();
    	list.add(tesla);
    	Map officers = new HashMap();
    	officers.put("president", tesla);
    	ieee.setMembers(list);
    	ieee.setOfficers(officers);
    	StandardEvaluationContext societyContext = new StandardEvaluationContext(ieee);    	
    	
    	String name2 = PARSER.parseExpression("Members[0].Name").getValue(societyContext,String.class);
    	System.out.println("Members集合第一个实体名称："+name2);    	
    	String invention2 =PARSER.parseExpression("Members[0].Inventions[3]").getValue(societyContext, String.class);
    	System.out.println("Members集合第一个实体的数组指定位置取值："+invention2);
    	
    	Inventor pupin = PARSER.parseExpression("Officers['president']").getValue(societyContext, Inventor.class);
    	System.out.println("获取Officers集合指定key的取值："+pupin);    	
    	
    	//将沧州改能泊头，因为context没变，下面获取的就是最新的设值
    	PARSER.parseExpression("Officers['president'].PlaceOfBirth.City").setValue(societyContext, "泊头");
    	
    	String city2 = PARSER.parseExpression("Officers['president'].PlaceOfBirth.City").getValue(societyContext, String.class);
    	System.out.println("获取Officers集合指定key的属性的取值："+city2);
    	
    	boolean isMember =PARSER.parseExpression("isMember('Mihajlo Pupin')").getValue(societyContext,boolean.class);    	
    	System.out.println("调用isMember方法："+isMember);
	}

	//类型转换
	private static void simlpeTest() {
		class Simple {
    	    public List<Boolean> booleanList = new ArrayList<Boolean>();
    	}
    	Simple simple = new Simple();
    	simple.booleanList.add(true);
    	
    	StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
    	//此时传false或“fasle”都行
    	PARSER.parseExpression("booleanList[0]").setValue(simpleContext, "false");
    	Boolean b = simple.booleanList.get(0);
    	System.out.println(b);
		
	}
	
	//解析器配置
	//如果数据为 索引到指定索引处的数组或集合的元素是null 它可以自动地创建的元素。
	//当用表达式组合一个链式属性引用时这将非常有用. 如果索引到一个数组或列表 并指定一个索引超出数组的当前大小或 自动增长的数组或队列去容纳
	private static void parserTest() {
		class Demo {
		    public List<String> list;
		}
		// Turn on:
		// - auto null reference initialization
		// - auto collection growing
		//此配置保证取值为空的时不报异常
		SpelParserConfiguration config = new SpelParserConfiguration(true,true);
		ExpressionParser parser = new SpelExpressionParser(config);
		Expression expression = parser.parseExpression("list[3]");
		
		Demo demo = new Demo();
		Object o = expression.getValue(demo);
		System.out.println(o);			
	}

	//集合类方法
	private static void collectionTest() {		
		EvaluationContext context = new StandardEvaluationContext();
		//集合
    	//[1, 2, 3, 4]
    	List numbers = (List) PARSER.parseExpression("{1,2,3,4}").getValue(context);
    	//[belongSale, belongDealer]
    	List strs =  PARSER.parseExpression("{'belongSale','belongDealer'}").getValue(context,List.class);    	
    	//[[a, b], [x, y]]
    	List listOfLists = (List) PARSER.parseExpression("{{'a','b'},{'x','y'}}").getValue();
    	
    	//map
    	//{name=Nikola, dob=10-July-1856}
    	Map inventorInfo = (Map) PARSER.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue();
    	//{name={first=Nikola, last=Tesla}, dob={day=10, month=July, year=1856}}
    	Map mapOfMaps = (Map) PARSER.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue();    	
    
    	//数组
    	//[0, 0, 0, 0],int默认值为0,Integer [null, null, null, null]
    	Integer[] numbers1 = (Integer[]) PARSER.parseExpression("new Integer[4]").getValue();
    	//[1, 2, 3, 4]
    	int[] numbers2 = (int[]) PARSER.parseExpression("new int[]{1,2,3,4}").getValue();
    	//三行四列数组
    	int[][] numbers3 = (int[][]) PARSER.parseExpression("new int[3][4]").getValue();
		
    	
	}
	
	
}
