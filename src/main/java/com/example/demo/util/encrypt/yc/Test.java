package com.example.demo.util.encrypt.yc;

import java.time.LocalDateTime;

import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {		
		String token = encrypt();
		Ticket ticket = decrypt(token);
		System.out.println(ticket.toString());
	}

	/** 
	 * Description:解密
	 * @param token
	 * @return
	 */
	private static Ticket decrypt(String token) {
		return FormsAuthentication.decrypt(token);
	}

	/** 
	 * Description:加密
	 * @return
	 */
	private static String encrypt() {
		String  result= "";
		JSONObject ticketObject = new JSONObject();
        ticketObject.put("UserId", 48399482);
        ticketObject.put("ShowName", "车手1247MP");

        String ticketName = "车手1247MP";
        String ticketData = ticketObject.toJSONString();

        Ticket ticket = new Ticket(1
                , ticketName
                , LocalDateTime.now()
                , LocalDateTime.now().plusYears(1)
                , true
                , ticketData
                , "/");

        result = ticket.encrypt();
        System.out.println(result);
		return result;
	}

}
