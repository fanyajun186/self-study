package com.example.demo.util.poi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.util.http.HttpUtil;
import com.example.demo.util.result.RespDTO;
import com.example.demo.util.result.RespStatusEnum;
import com.google.common.collect.Lists;

public class BatchHandleData {
	
	private static Logger logger = LoggerFactory.getLogger(BatchHandleData.class);

	public static void main(String[] args) {
		
		initDoumentData();
	}

	/**
	 * 初始化档案管理数据
	 */
	private static void initDoumentData() {
		try {
			//请求地址
			String url="http://fengyun.mljr.com/boss/document/syn";
			//文件路径
			String path="C:\\Users\\fanya\\Desktop\\需要处理的.xlsx";
			
			//读取配置文件的数据
			List<String> appCodes=PoiUtil.getImportData(path);
			List<String> failAppCodes= Lists.newArrayList();
			for (String appCode : appCodes) {
				Map<String,String> param=new HashMap<String,String>();
				param.put("appCode", appCode);
				param.put("status", "32");			
				try {
					Map<String, String> header = new HashMap<String, String>();
					header.put("orderenv", "oldStatus");
					String jsonData = HttpUtil.post(url, param, header);
					logger.info("spReleasQuota is success ,appCode={}, url={},resp={}", appCode, url,jsonData);
					RespDTO<String> respDTO = JSON.parseObject(jsonData, new TypeReference<RespDTO<String>>(){});
					if(respDTO.getStatus()==RespStatusEnum.SUCCESS.getStatus()) {
						System.out.println("成功的发送的："+JSON.toJSONString(respDTO));
					}else {
						failAppCodes.add(param.get("appCode"));
					}					
				} catch (Exception e) {
					failAppCodes.add(param.get("appCode"));
					e.printStackTrace();
				}
			}
			System.out.println("=============================================下面是没成功的=====================================================");
			System.out.println(JSON.toJSONString(failAppCodes));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
