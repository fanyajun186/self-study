package com.example.demo.util.calculator;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 信用卡分期金额
 * @author fan
 *
 */
@Data
@Builder			//书写形式可以用对象.builder()后接属性赋值
@NoArgsConstructor    //不加这个就不能 CreditCardInstallmentDTO dto=new CreditCardInstallmentDTO();
@AllArgsConstructor  //构造方法中传所有属性 
public class CreditCardInstallmentDTO {

	/**
	 * 贷款总额
	 */
	private BigDecimal totalAmount;
	
	/**
	 * 贷款期限
	 */
	private Integer termCount;
	
	/**
	 * 贷款利率
	 */
	private BigDecimal loanRate;
	
	/**
	 * 月供
	 */
	private BigDecimal monthFee;
	
	/**
	 * 盈利利率
	 */
	private BigDecimal profitLoanRate;
	
}
