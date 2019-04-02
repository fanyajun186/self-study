package com.example.demo.util.spel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.dto.common.Student;

public class ConfigCommonUtil {

	
	public static void main(String[] args) {
		/*List<Student> list=getStudent();
		Map<String,Object> params=getParams(list);*/
	}

	

	/**
	 * 获取源数据
	 * @Description:@return
	 * @return
	 */
	public static List<Student> getStudent() {
		List<Student> list= new ArrayList<Student>();
		for (int i = 0; i < 10; i++) {
			Student s=new Student();
			s.setId(i);
			s.setAge(10+i%4);
			s.setAlive(true);
			s.setDeptCode(Long.parseLong(i+""));
			s.setName("张三"+i);
			list.add(s);
		}
		return list;
	}

	/**
	 * 获取参数
	 * @Description:@param list
	 * @Description:@return
	 * @param list
	 * @return
	 */
	public static Map<String, Object> getParams(List<Student>  list) {
		//List<Student> list =(List<Student>) obj;
		Map<String,Object> params= new HashMap<String,Object>();
		List<Integer> staffIds=new ArrayList<Integer>();
		List<Long> deptCodes=new ArrayList<Long>();
		for (Student student : list) {
			staffIds.add(student.getId());
			deptCodes.add(student.getDeptCode());
		}
		params.put("staffIds", staffIds);
		params.put("deptCodes", deptCodes);
		return params;
	}
	
	
}
