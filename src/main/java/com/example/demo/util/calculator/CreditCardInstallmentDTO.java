package com.example.demo.util.calculator;

import java.math.BigDecimal;

/**
 * 信用卡分期金额
 * @author fan
 *
 */
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
	

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTermCount() {
		return termCount;
	}

	public void setTermCount(Integer termCount) {
		this.termCount = termCount;
	}

	public BigDecimal getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(BigDecimal loanRate) {
		this.loanRate = loanRate;
	}	
	
	public BigDecimal getMonthFee() {
		return monthFee;
	}

	public void setMonthFee(BigDecimal monthFee) {
		this.monthFee = monthFee;
	}

	public BigDecimal getProfitLoanRate() {
		return profitLoanRate;
	}

	public void setProfitLoanRate(BigDecimal profitLoanRate) {
		this.profitLoanRate = profitLoanRate;
	}	
	
}
