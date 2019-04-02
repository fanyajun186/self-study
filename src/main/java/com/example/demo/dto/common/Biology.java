package com.example.demo.dto.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Data;

@Data
public class Biology implements Serializable{
	
	private static final long serialVersionUID = 6705418339557986802L;
	
	private Boolean alive;
	
	private int id;	

	@Override
	public String toString() {
		try {
			return this.getClass().getSimpleName() + " = " + JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
