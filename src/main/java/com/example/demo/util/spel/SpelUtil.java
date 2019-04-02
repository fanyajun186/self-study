package com.example.demo.util.spel;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.spel.Inventor;
import com.example.demo.dto.spel.PlaceOfBirth;
import com.example.demo.dto.spel.Society;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;


import java.util.ArrayList;
import java.util.Arrays;
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
	
	private static Logger logger = LoggerFactory.getLogger(SpelUtil.class);	

    /**
     * spel解析器  config ：自动补全空集合、自动填充
     */
    private static final ExpressionParser PARSER = new SpelExpressionParser(new SpelParserConfiguration(Boolean.TRUE,Boolean.TRUE));


    /**
     * 处理字串类型spel表达式
     * @Description:@param spel
     * @Description:@param obj
     * @Description:@param context     
     * @return
     */
    public static void executeSpelStr(String spel,Object obj,EvaluationContext context ){
        List<ValidateSpel> expressions = JSONObject.parseArray(spel,ValidateSpel.class);
        executeSpelList(expressions,obj,context);
    }
    
    /**
     * 顺序执行spel集合
     * @param expressions
     * @param obj
     * @return
     */
    public static void executeSpelList(List<ValidateSpel> expressions,Object obj){        
        executeSpelList(expressions,obj,null);
    }
    
    /**
     * 
     * @param expressions
     * @param context
     * @return
     */
    public static void executeSpelList(List<ValidateSpel> expressions,StandardEvaluationContext context ){
    	executeSpelList(expressions,null,context);
    }
    
    /**
     * 顺序执行spel集合
     * @Description:@param expressions
     * @Description:@param obj
     * @Description:@param context
     * @return
     * @throws Exception 
     */
    public static void executeSpelList(List<ValidateSpel> expressions,Object obj,EvaluationContext context ){
        for(ValidateSpel exp : expressions){
            //捕获spel表达式执行时发生的异常
            try {            	
            	parserElToString(exp,obj,context);
            } catch (Exception e) {                
                throw new RuntimeException(String.format("表达式：%s 配置错误：%s",exp.getExpression(),e.getMessage())) ;
            }
        }
    }
    
    /**
     * 执行el表达式获取Str结果
     * @param expression
     * @param data
     * @return
     */
    public static String parserElToString(ValidateSpel expression,Object data,EvaluationContext context ){
        if(Objects.isNull(expression.getExpType()) || ValidateSpelTypeEnum.SPEL_TYPE_BOOLEAN.isEqual(expression.getExpType())){
            return parserElToBoolean(expression.getExpression(),data,context) ? expression.getResultDesc() : null;
        }else if(ValidateSpelTypeEnum.SPEL_TYPE_STRING.isEqual(expression.getExpType())){
            return PARSER.parseExpression(expression.getExpression()).getValue(data,String.class);
        }else if(ValidateSpelTypeEnum.SPEL_TYPE_ASSEMBLE.isEqual(expression.getExpType())){
            //return parserElToAssemble(expression.getExpression(),data);           
        	return null;
        }else if(ValidateSpelTypeEnum.SPEL_TYPE_ADD_DATA.isEqual(expression.getExpType())){
            return parserElAddData(expression.getExpression(),data,context);
        }else {
        	logger.error("未匹配到对应的SPEL表达式类型！expression:{}",expression);
            throw new RuntimeException("未匹配到对应的SPEL表达式类型");
        }
    }
    
    /**
     * 执行el表达式 获取boolean结果
     * @param expression
     * @param data
     * @return
     */
    public static Boolean parserElToBoolean(String expression, Object data ,EvaluationContext context){
    	Boolean result = null;
    	if(context!=null) {
    		result = PARSER.parseExpression(expression).getValue(context,Boolean.class);            
    	}else {
    		result = PARSER.parseExpression(expression).getValue(data,Boolean.class);            
    	}
    	return Objects.isNull(result) ? Boolean.FALSE : result;
    }
    
    /**
     * spel装配类型 解析并校验
     * @param expression
     * @param data
     * @return
     */
    public static String parserElAddData(String expression,Object data,EvaluationContext context){
        if(context!=null) {
        	PARSER.parseExpression(expression).getValue(context);
		}else {
			PARSER.parseExpression(expression).getValue(data);            		
		}
        return null;
    }
    
    /**
     * 将spel表达式和data的操作结果赋值指定的类型
     * @param expression
     * @param data
     * @param class1
     * @return
     */
    public static <T> T parserElToAssignType(String expression,Object data,Class<T> c){
    	return	PARSER.parseExpression(expression).getValue(data,c);
    }
    
    /**
     * 将spel表达式和data的操作结果赋值指定的类型
     * @param expression
     * @param context
     * @param c
     * @return
     */
    public static <T> T parserElToAssignType(String expression,EvaluationContext context,Class<T> c){
    	return	PARSER.parseExpression(expression).getValue(context,c);
    } 
    
    

    public static void main(String[] args) throws Exception {
    	/*long startTime=System.currentTimeMillis();
    	Map<String,Object> data=new HashMap<String,Object>();
    	
    	StandardEvaluationContext context = new StandardEvaluationContext(data);
    	
    	List<ValidateSpel> spelList= new ArrayList<ValidateSpel>();    
    	
    	//获取全量参数
    	ValidateSpel validateSpel1 = new ValidateSpel();    	
    	validateSpel1.setExpression("#root['lists']=T(com.example.demo.util.spel.ConfigCommonUtil).getStudent()");
    	

    	
    	ValidateSpel validateSpel2 = new ValidateSpel();    	
    	validateSpel2.setExpression("#root['params']=T(com.example.demo.util.spel.ConfigCommonUtil).getParams(#root['lists'])");
    	
    	spelList.add(validateSpel1);
    	spelList.add(validateSpel2);
    	
    	validateSpel(spelList,null,context);
    	System.out.println(data.size());
    	long endTime=System.currentTimeMillis();
    	System.out.println(endTime-startTime);*/
    	
    	//baseTest();    	
    	//simlpeTest();
    	//parserTest();
    	//collectionTest();
    	//operatorTest();
    	//thisAndRootTest();
    	
    	GregorianCalendar c = new GregorianCalendar();
    	c.set(1856, 7, 9);
    	PlaceOfBirth placeOfBirth=new PlaceOfBirth("沧州","河北");
    	
    	Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
    	tesla.setPlaceOfBirth(placeOfBirth);
    	tesla.setAlive(true);
    	tesla.setInventions(new String[]{"1","2","3","4"});   
    	
    	String ex="name";
    	
    	String str=parserElToAssignType(ex,tesla, String.class);
    	System.out.println("泛型："+str);
	}   


	public static List<String> validateSpel(List<ValidateSpel> expressions,Object obj,StandardEvaluationContext context ){
        //List<ValidateSpel> expressions = JSONObject.parseArray(spel,ValidateSpel.class);
        List<String> result = new ArrayList<>();
        for(ValidateSpel exp : expressions){
            //捕获spel表达式执行时发生的异常
            try {
            	if(context!=null) {
            		PARSER.parseExpression(exp.getExpression()).getValue(context);
            	}else {
            		PARSER.parseExpression(exp.getExpression()).getValue(obj);            		
            	}
            } catch (Exception e) {
                result.add(String.format("表达式：%s 配置错误：%s",exp.getExpression(),e.getMessage()));
            }
        }
        return result;
    }

   
    //this和root的使用
    //传入函数
	private static void thisAndRootTest() throws Exception {
		List<Integer> primes = new ArrayList<Integer>();
		primes.addAll(Arrays.asList(2,3,5,7,11,13,17));
		
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("primes",primes);
		
		Integer size  = (Integer)PARSER.parseExpression("#primes.size()").getValue(context);//7
		//all prime numbers > 10 from the list (using selection ?{...}) [11, 13, 17]
		List<Integer> primesGreaterThanTen  = (List<Integer>)PARSER.parseExpression("#primes.?[#this>10]").getValue(context);
		
		String reverse = StringUtils.reverse("abcdefg");
		System.out.println(reverse);
		
		//就是为了将方法引入进来，没有进行重写
		context.registerFunction("reverse", StringUtils.class.getDeclaredMethod("reverse", new Class[] {String.class}));
		String helloWorldReversed = PARSER.parseExpression("#reverse('123456')").getValue(context, String.class);
		System.out.println(helloWorldReversed);
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
    	//通过getValue方法也能完成对属性的赋值
    	System.out.println(PARSER.parseExpression("name='kobe'").getValue(context,String.class));
    	
    	
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
    	
    	//构造
    	Inventor einstein = PARSER.parseExpression("new com.example.demo.dto.spel.Inventor('Albert Einstein', 'German')").getValue(Inventor.class);
    	
    	
    	//复杂对象取值
    	Society ieee = new Society();
    	List<Inventor> list=new ArrayList<Inventor>();
    	list.add(tesla);
    	Map officers = new HashMap();
    	Map map = new HashMap();
    	map.put("name", "fan");
    	
    	officers.put("president", tesla);
    	officers.put("advisors", list);
    	officers.put("mapData", map);
    	
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
    	
    	String country2 =PARSER.parseExpression("Officers['advisors'][0].PlaceOfBirth.Country").getValue(societyContext, String.class);
    	System.out.println("获取Officers集合指定key的属性的取值："+country2);
    	
    	String name =PARSER.parseExpression("Officers['mapData']['name']").getValue(societyContext, String.class);
    	System.out.println("获取两层map里的指定值："+name);
    	
    	boolean isMember =PARSER.parseExpression("isMember('Mihajlo Pupin')").getValue(societyContext,boolean.class);    	
    	System.out.println("调用isMember方法："+isMember);
    	
    	//复杂构造,list.add()返回值是boolean类型
    	Boolean value2 = PARSER.parseExpression("members.add(new com.example.demo.dto.spel.Inventor('Albert Einstein', 'German'))").getValue(societyContext,Boolean.class);
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
	
}
