package com.example.demo.dto.common;

import java.util.Map;

import lombok.Data;

@Data
public class DynamicExportListDTO {
	
	/**
	 * 源数据
	 */
	private Map<String,Object> sourceDto;
	
	/**
	 * 销售数据
	 */
	//private Staff saleStaff;
	private Map<String,Object> saleStaff;
	
	/**
	 * 门店信息
	 */
	//private DealerDTO dealerDTO;
	private Map<String,Object> dealerDTO;
	


}
