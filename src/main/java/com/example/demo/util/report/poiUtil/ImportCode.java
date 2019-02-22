package com.example.demo.util.report.poiUtil;


public enum ImportCode {
	
	SUCCESS("0", "校验成功"),
	
	RG_RESGROUPID_ALREADY_EXIST("10104", "业务系统编号已经存在"),    
	RG_PARENTID_NOT_EXIST("10105", "上级业务系统不存在"),             
	RG_NAME_REPEAT("10106", "同一上级业务系统下业务系统重名"),
	RG_ADD_SUCCESS("10107", "业务系统新增成功"),
	RG_UPDATE_SUCCESS("10108", "业务系统更新成功"),
	RG_DELETE_CONTAINSUBRESGROUP("10109", "删除业务系统失败，当前业务系统包含子业务系统！"),
	RG_DELETE_CONTAINRESOURCE("10110", "删除业务系统失败，当前业务系统包含资源！"),
	RG_DELETE_NORECORD("10111", "删除业务系统失败，当前业务系统不存在！"),
	RG_DELETE_SUCCESS("10112", "业务系统删除成功"),
	
	RES_RESGROUP_NOT_EXIST("10205","资源所属业务系统不存在"),
	RES_NAME_REPEAT("10206","添加失败，资源名称已经存在"),
	RES_PROTOCOL_INVALID("10207","资源协议端口格式错误"),
	RES_UPDATE_SUCCESS("10208", "资源更新成功"),
	RES_UPDATE_FAIL("10209", "资源更新失败"),
	RES_ADD_SUCCESS("10210", "资源添加成功"),
	RES_ADD_FAIL("10211", "资源添加失败"),
	RES_DELETE_CONTAINACCSLAVE("10212", "删除资源[name]失败，当前资源包含从帐号！<br/>"),
	RES_DELETE_CONTAINMASTERAUTHS("10213", "删除资源[name]失败，当前资源已被授权关系引用！<br/>"),
	RES_DELETE_NORECORD("10214", "删除资源[name]失败，不存在当前资源记录！<br/>"),
	RES_DBPORT_ISNULL("10215", "数据库端口不可为空；"),
	RES_DBSID_ISNULL("10217", "数据库实例名不可为空；"),
	RES_DB_REPEAT("10218", "数据库对象已经存在,数据库资源中 ip+数据库端口+实例名+数据库类型  全系统唯一；"),
	RES_PROTOCOL_ISNULL("10219", "资源协议不可为空；"),
	RES_DBIP_REPEAT("10220", "ip地址已经存在；"),
	
	ACCSLAVE_RESOURCE_IS_NULL("10302","从帐号所属资源为空"),
	ACCSLAVE_RESOURCE_NOT_EXIST("10303","从帐号所属资源不存在"),
	ACCSLAVE_NAME_REPEAT("10304", "同一资源下从帐号重名"),
	ACCSLAVE_DELETE_CONTAINMASTERAUTHS("10305", "删除从帐号[name]失败，当前从帐号已被授权关系引用！<br/>"),
	ACCSLAVE_DELETE_NORECORD("10306", "删除资源[name]失败，不存在当前资源记录！<br/>"),
	ACCSLAVE_UPDATE_SUCCESS("10307", "从帐号更新成功"),
	ACCSLAVE_UPDATE_FAIL("10308", "从帐号更新失败"),
	ACCSLAVE_ADD_SUCCESS("10309", "从帐号添加成功"),
	ACCSLAVE_ADD_FAIL("10310", "从帐号添加失败"),
	
	ORG_ORGID_ALREADY_EXIST("10406", "组织编号已经存在"),    
	ORG_PARENTID_NOT_EXIST("10407", "上级组织不存在"),             
	ORG_NAME_REPEAT("10408", "同一上级组织下组织重名"),
	ORG_DELETE_CONTAINSUBORG("10409", "删除组织失败，当前组织包含子组织！"),
	ORG_DELETE_CONTAINMASTER("10410", "删除组织失败，当前组织包含主帐号！"),
	ORG_DELETE_SUCCESS("10411", "组织删除成功"),
	ORG_DELETE_NORECORD("10412", "删除组织失败，当时记录不存在！"),
	ORG_UPDATE_SUCCESS("10413", "组织更新成功"),
	ORG_ADD_SUCCESS("10414", "组织新增成功"),
	
	MASTER_TIME_INVALID("10506","主帐号有效期时间格式错误"),
	MASTER_ORG_NOT_EXIST("10508","主帐号所属组织不存在"),
	MASTER_NAME_REPEAT("10509","主帐号名称已经存在"),
	MASTER_DELETE_CONTAINMASTERAUTHS("10513", "删除主帐号[name]失败，当前主帐号已被授权关系引用！<br/>"),
	MASTER_DELETE_NORECORD("10514", "删除主帐号[name]失败，不存在当前主帐号记录！<br/>"),
	MASTER_UPDATE_SUCCESS("10515", "主帐号更新成功"),
	MASTER_UPDATE_FAIL("10516", "主帐号更新失败"),
	MASTER_ADD_SUCCESS("10517", "主帐号添加成功"),
	MASTER_ADD_FAIL("10518", "主帐号添加失败"),
	
	MASTERAUTH_MASTERNAME_IS_NULL("10601","主帐号名称为空"),
	MASTERAUTH_MASTER_NOT_EXIST("10602","主帐号不存在"),
	MASTERAUTH_RESNAME_IS_NULL("10603","资源名称为空"),
	MASTERAUTH_RESOURCE_NOT_EXIST("10604","资源不存在"),
	MASTERAUTH_ACCSLAVENAME_IS_NULL("10605","从帐号名称为空"),
	MASTERAUTH_ACCSLAVE_NOT_EXIST("10606","从帐号不存在"),
	MASTERAUTH_IS_EXIST("10607","本条授权关系已经存在"),
	
	IMPORTFILE_IS_NULL("99901", "导入文件为空,请确认后重新导入"),
	DAO_ERROR("99902", "数据库持久化失败，请通知管理员"),
	FORMAT_CHECKCELL_REQUIRED("99903", "列名【[title]】为必填字段;"),
	FORMAT_CHECKCELL_LENGTHERROR("99904", "列名【[title]】的字符长度介于[minLength]和[maxLength]之间;"),
	FORMAT_CHECKCELL_DICERROR("99905", "列名【[title]】对应字典不存在;"),
	FORMAT_CHECKCELL_DBSEARCHEERROR("99906", "列名【[title]】的值在当前数据库中不存在;"),
	FORMAT_CHECKCELL_IPERROR("99907", "列名【[title]】的格式不是有效的IP格式;"),
	FORMAT_CHECKCELL_PHONEERROR("99908", "列名【[title]】的格式不是有效的电话号码格式;"),
	FORMAT_CHECKCELL_EMAILERROR("99909", "列名【[title]】的格式不是有效的email格式;"),
	FORMAT_CHECKCELL_PROTOCOLERROR("99910", "列名【[title]】的格式不是有效的协议格式;"),
	FORMAT_CHECKCELL_IDCARDERROR("99911", "列名【[title]】的格式不是有效的身份证格式;"),
	FORMAT_CHECKCELL_PORTERROR("99912", "列名【[title]】的格式不是有效的端口格式;"),
	FORMAT_RETURN_MSG("99913", "[desc]"),
	FORMAT_MAPVALUE_TO_STR("99914", "导入第[index]行数据失败，原因：[desc]<BR/>"),
	FORMAT_RETURN_MSG_CELL("99915", "属性列[fieldName]解析失败;"),
	FORMAT_IMPORT_RESULT("99916","EXCEL文件共[sum]行，成功导入[success]行：<BR/>"),
	FORMAT_NECESSARYTITLE_ERROR("99917", "列名【[title]】为必填列;"),
	FORMAT_MASTERAUTH_REPEAT_ERROR("99918", "授权关系：主帐号【[master]】资源【[res]】从帐号【[slave]】已经存在");

	
	//编码
	private String code;  
	//描述
	private String description;  
	
	private ImportCode(String code, String description) {  
		this.code = code;  
		this.description = description;  
	}  
	
	/****
	 * 方法名：getDesc
	 * 描述  ：通过编码获取描述信息
	 *
	 * @param code
	 * @return
	 */
	public static String getDesc(String code) {  
		for (ImportCode ic : ImportCode.values()) {  
			if (ic.getCode().equals(code)) {  
				return ic.description;  
			}  
		}  
		
		return null;  
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}  

}

