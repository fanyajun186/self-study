package com.example.demo.util.spel;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.common.Student;
import com.example.demo.dto.spel.Inventor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * spring spel 解析工具类
 * Created by yanpeng.liu on 2019/3/20.
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
    	
    	baseTest();    	
    	//simlpeTest();
    	//parserTest();
    	//collectionTest();
    	
	}   

	//基础操作
    private static void baseTest() {	
    	
    	GregorianCalendar c = new GregorianCalendar();
    	c.set(1856, 7, 9);
    	Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
    	Expression exp = PARSER.parseExpression("name");
    	
    	EvaluationContext context = new StandardEvaluationContext(tesla);
    	
    	System.out.println(exp.getValue(context,String.class));    	
    	System.out.println(exp.getValue(tesla,String.class));
    	
    	Expression exp2 = PARSER.parseExpression("name=='tom'");
    	Boolean result = exp2.getValue(context, Boolean.class);
    	System.out.println(result);		
    	
    	Expression exp3 = PARSER.parseExpression("T(java.lang.Math).random() * 100.0");
    	Object value = exp3.getValue();
    	System.out.println("aaaa:"+value);
    	
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
