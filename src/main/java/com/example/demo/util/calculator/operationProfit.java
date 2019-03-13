package com.example.demo.util.calculator;

import java.math.BigDecimal;

public class operationProfit {

	
	
	public static void main(String[] args) {
		
		CreditCardInstallmentDTO dto=new CreditCardInstallmentDTO();
		dto.setTotalAmount(new BigDecimal(1200));
		dto.setLoanRate(new BigDecimal(0.0041));
		dto.setMonthFee(new BigDecimal(104.92));
		dto.setTermCount(12);
		dto.setProfitLoanRate(new BigDecimal(0.0031));
		BigDecimal result=calculate(dto);
		System.out.println(result);
	}

	/**
	 * 计算盈利情况
	 * @param dto
	 * @return
	 */
	private static BigDecimal calculate(CreditCardInstallmentDTO dto) {
		BigDecimal totalAmount = dto.getTotalAmount();
		BigDecimal loanRate = dto.getLoanRate();
		BigDecimal monthFee = dto.getMonthFee();
		Integer termCount = dto.getTermCount();
		BigDecimal  profitLoanRate= dto.getProfitLoanRate();
		
		//计算花费
		BigDecimal totalCost =countCost(totalAmount,loanRate,termCount);
		System.out.println(totalCost);
		
		//计算收益
		BigDecimal totalProfit =countProfit(totalAmount,profitLoanRate,termCount,monthFee);
		
		return null;
	}	

	/**
	 * 计算花费
	 * @param totalAmount
	 * @param loanRate
	 * @param termCount
	 * @return
	 */
	private static BigDecimal countCost(BigDecimal totalAmount, BigDecimal loanRate, Integer termCount) {
		BigDecimal totalCost = totalAmount.multiply(loanRate).multiply(new BigDecimal(termCount)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return totalCost;
	}

	/**
	 * 计算收益
	 * @param totalAmount
	 * @param profitTermCount
	 * @param termCount
	 * @return
	 */
	private static BigDecimal countProfit(BigDecimal totalAmount, BigDecimal profitLoanRate, Integer termCount,BigDecimal monthFee) {
		BigDecimal result=new BigDecimal(0);
		BigDecimal toatl=totalAmount;
		for (int i = 0; i <termCount; i++) {
			toatl=totalAmount.subtract(monthFee.multiply(new BigDecimal(i))).setScale(2,BigDecimal.ROUND_HALF_UP);
			System.out.println("第"+i+"月，本金剩余"+toatl);
			BigDecimal eachMonthProfit=countEachMonthProfit(toatl,profitLoanRate);
			result.add(eachMonthProfit);
		}
		
		return null;
	}

	/**
	 * 计算每月收益
	 * @param toatl
	 * @param profitLoanRate
	 * @return
	 */
	private static BigDecimal countEachMonthProfit(BigDecimal toatl, BigDecimal profitLoanRate) {
		System.out.println("资金总额"+toatl);
		BigDecimal eachMonthProfit = toatl.multiply(profitLoanRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		return eachMonthProfit;
	}
	
}
