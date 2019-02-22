package com.example.demo.util.report.poiUtil;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



public class StringUtil {
	private static final Logger logger = Logger.getLogger(StringUtil.class);
	/***
	 * 方法名：strIsNotNull
	 * 描述  ：判断str是否为空
	 *
	 * @param str
	 * @return true：空，false：非空
	 */
	public static boolean strIsNull(String str){

		return (str==null || "".equals(str.trim())) ? true : false;
	}

	/***
	 * 方法名：getParentIdValue
	 * 描述  ：获取树根节点编号，如果为空，则设置为“0”
	 *
	 * @param no 资源组编号
	 * @return 父资源组编号
	 */
	public static String getTreeNodeParentId(String no){
		return strIsNull(no) ? "0" : no;
	}

	/***
	 * 方法名：formatReturnMsg
	 * 描述  ：格式化对象属性逻辑校验失败信息
	 *
	 * @param index excel行号
	 * @param code 校验失败码
	 * @return 校验失败信息
	 */
	public static String formatReturnMsg(String code){
		String sb = ImportCode.FORMAT_RETURN_MSG.getDescription();
		//将"[index]"替换成实际的excel行数
		//将"[desc]"替换成实际的错误原因描述
		sb = sb.replace("[desc]", ImportCode.getDesc(code));

		return sb;
	}

	public static String formatImportResult(int sum){
		String sb = ImportCode.FORMAT_IMPORT_RESULT.getDescription();
		sb = sb.replace("[sum]", String.valueOf(sum));

		return sb;
	}


	/****
	 * 方法名：formatReflectMsg
	 * 描述  ：格式化excel行数据构建失败信息
	 *
	 * @param rowNum excel行号
	 * @param fieldName 属性名
	 * @return 构建失败信息
	 */
	public static String formatReflectMsg(String fieldName){
		String sb = ImportCode.FORMAT_RETURN_MSG_CELL.getDescription();

		//将"[fieldName]"替换成实际的excel列数
		sb = sb.replace("[fieldName]", String.valueOf(fieldName));

		return sb;
	}

	/***
	 * 方法名：getDownloadDir
	 * 描述  ：获取本地下载根目录
	 *
	 * @return 本地下载根目录
	 */
	public static String getDownloadDir() {
		String dir = System.getProperty("webinf.dir");
		dir = dir.replace("\\", "/");
		dir = dir.replace("/WEB-INF", "/downloads/basedata");

		return dir;
	}

	/***
	 * 方法名：getUploadTemp
	 * 描述  ：获取上传路径
	 *
	 * @return 上传路径
	 */
	public static String getUploadTemp(){
		String dir = System.getProperty("webinf.dir");
		dir = dir.replace("\\", "/") + "/temp/";

		return dir;

	}

	/**
	 * 方法名：checkProtocol
	 * 描述  ：校验协议数据是否合法
	 *
	 * @param protocolStr 协议串
	 * @return true：合法，false：不合法
	 */
	public static boolean checkProtocol(String protocolStr){
		try{
			if(protocolStr != null && !"".equals(protocolStr.trim())){
				String[] protocols = protocolStr.split("[,]");
				String protStr = null;
				int port = 0;
				int index = -1;
				for(String protocol : protocols){
					index = protocol.indexOf(":") + 1;

					if(index<1){
						return false;
					}
					protStr = protocol.substring(index);
					port = Integer.valueOf(protStr);
					if(port < 0){
						return false;
					}
					protStr = null;
					port = 0;
					index = -1;
				}
			}

			return true;
		}catch (Exception e) {
			return false;// TODO: handle exception
		}
	}

	/***
	 * 方法名：checkEmail
	 * 描述  ：校验email格式
	 *
	 * @param email email地址
	 * @return true：合法，false：不合法
	 */
	public static boolean checkEmail(String email){
		if(!strIsNull(email)){
			String regEx = "^[a-zA-Z0-9_+.-]+\\@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";  
			Pattern pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher(email);  
			return mat.find();  
		}

		return true;
	}

	/***
	 * 方法名：checkMobilePhone
	 * 描述  ：校验手机号码是否合法
	 *
	 * @param phoneNumber 手机号码
	 * @return true：合法，false：不合法
	 */
	public static boolean checkMobilePhone(String phoneNumber){
		if(!strIsNull(phoneNumber)){
			String regEx = "^[1][3-8]\\d{9}$"; 
			Pattern pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher(phoneNumber);  
			return mat.find();  
		}

		return true;
	}

	/***
	 * 方法名：checkIdentityCard
	 * 描述  ：校验身份证号码是否合法
	 *
	 * @param Id 身份证号码
	 * @return true：合法，false：不合法
	 */
	public static boolean checkIdentityCard(String Id){
		if(!strIsNull(Id)){
			String regEx = "^\\d{15}$|^\\d{17}[0-9|x|X]$";   
			Pattern pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher(Id);  
			return mat.find();  
		}

		return true;
	}

	public static boolean checkPort(String port){
		if(!strIsNull(port)){
			String regEx = "^(\\d{1,4}|([1-5]\\d{4})|([1-6][1-4]\\d{3})|([1-6][1-4][1-4]{2})|([1-6][1-4][1-4][1-2]\\d)|([1-6][1-5][1-5][1-3][1-5]))$";   
			Pattern pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher(port);  
			return mat.find();  
		}
		
		return true;
	}

	/**
	 * 方法名：zeroFillToStr
	 * 描述  ：将数字字符串从高位补0。
	 *     例如：123补0长度为10，结果为0000000123
	 *
	 * @param str 待补0字符
	 * @param length 补零后字符总长度
	 * @return 补0后的字符
	 */
	public static String zeroizeStr(String str, int length){
		StringBuffer sb = new StringBuffer();
		int strLength = strIsNull(str)?0:str.length();
		for(int i = length - strLength; i>0; i--){
			sb.append("0");
		}

		sb.append(str);
		return sb.toString();
	}

	/***
	 * 方法名：strToLong
	 * 描述  ：将两个补零的字符串（长度为20个字符串）分别转化成long，以long数组形式返回
	 *
	 * @param str 长度为40个字符数字字符串
	 * @return
	 */
	public static long[] strToLong(String str){
		long[] strs = new long[2];
		String mid = str.substring(0, 20);
		String aid = str.substring(21, 40);

		strs[0] = Long.parseLong(mid);
		strs[1] = Long.parseLong(aid);

		return strs;
	}

	public static void putMsgMap(Map<Integer, String> msgMap, int index, String msg){
		if(msgMap.get(index) != null){
			msg = msg + msgMap.get(index);
		}

		msgMap.put(index, msg);
	}

	public static void putMsgMapSuccess(Map<Integer, String> msgMap, int success){
		String msg = msgMap.get(0);

		msg = msg.replace("[success]", String.valueOf(success));
		msgMap.put(0, msg);
	}

	public static String formatCheckCellRequired(String title){
		String str = ImportCode.FORMAT_CHECKCELL_REQUIRED.getDescription();

		return str.replace("[title]", title);
	}

	public static String formatCheckCellLengthError(String title, int minLength, int maxLength){
		String str = ImportCode.FORMAT_CHECKCELL_LENGTHERROR.getDescription();
		str = str.replace("[title]", title);
		str = str.replace("[minLength]", String.valueOf(minLength));
		str = str.replace("[maxLength]", String.valueOf(maxLength));
		return str;
	}

	public static String formatCheckCellDicError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_DICERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellDBSearchError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_DBSEARCHEERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellIPError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_IPERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellPhoneError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_PHONEERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellEmailError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_EMAILERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellIpError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_IPERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellProtocolError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_PROTOCOLERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellIDCardError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_IDCARDERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatCheckCellPortError(String title){
		String str = ImportCode.FORMAT_CHECKCELL_PORTERROR.getDescription();
		return str.replace("[title]", title);
	}

	public static String formatMapValueToStr(int index, String value){
		if(index < 1){
			return value;
		}
		String sb = ImportCode.FORMAT_MAPVALUE_TO_STR.getDescription();
		//将"[index]"替换成实际的excel行数
		sb = sb.replace("[index]", String.valueOf(index));

		//将"[desc]"替换成实际的错误原因描述
		sb = sb.replace("[desc]", String.valueOf(value));
		return sb;
	}

	public static String formatNecessaryTitleError(String title){
		String str = ImportCode.FORMAT_NECESSARYTITLE_ERROR.getDescription();
		return str.replace("[title]", title);
	}

	/*public static String formatMasterAuthRepeatError(MasterAuthIntoDB masterAuthIntoDB){
		String str = ImportCode.FORMAT_MASTERAUTH_REPEAT_ERROR.getDescription();
		str = str.replace("[master]", masterAuthIntoDB.getMastername());
		str = str.replace("[res]", masterAuthIntoDB.getResname());
		str = str.replace("[slave]", masterAuthIntoDB.getSlavename());

		return str ;		
	}*/

	public static StringBuffer mapToStr(Map<Integer, String> returnMap){
		StringBuffer returnMsg = new StringBuffer();
		Set<Integer> keySet = returnMap.keySet();
		for(Integer key : keySet){
			returnMsg.append(StringUtil.formatMapValueToStr(key, returnMap.get(key)));
		}

		return returnMsg;
	}

	public static boolean checkMaxLength(String str, int length){
		int strLength = str.length();

		return strLength>length?false:true;
	}

	public static String addBackslash(String no){
		return new StringBuffer("/").append(no).append("/").toString();
	}

	public static String addBackslash(String parentIds, String no){
		return new StringBuffer(parentIds).append(no).append("/").toString();
	}
	
	public static int getLength(String str){
		return str==null?0:str.length();
	}
	
	public static String cutStr(String str, int length){
		int strLen = 0;
		int dbLength = 0;

		char[] ch = str.toCharArray();
		for (char c : ch) {
			dbLength = (String.valueOf(c).getBytes().length>1) ? dbLength + 2:dbLength + 1;
			if(dbLength >= length){
				logger.error("**eventObj.cutStr.str:" + str);
				break;
			}

			strLen ++;
		}

		return str.substring(0,strLen);
	}
	
	/*public static void validateEvent(EventObject eventObj){
		try {
			if(getLength(eventObj.getCstandby5())>2048){
				logger.error("**eventObj.getCstandby5_2048:" + eventObj.getCstandby5());
			}

			if(getLength(eventObj.getCstandby6())>2048){
				logger.error("**eventObj.getCstandby6:_2048" + eventObj.getCstandby6());
			}

			if(getLength(eventObj.getCstandby11())>255){
				logger.error("**eventObj.getCstandby11_255:" + eventObj.getCstandby11());
			}

			if(getLength(eventObj.getCstandby12())>255){
				logger.error("**eventObj.getCstandby12_255:" + eventObj.getCstandby12());
			}
			
		} catch (Exception e) {
			logger.info("**eventObj.Exception:" + e.getMessage());
		}
	}*/
	
}
