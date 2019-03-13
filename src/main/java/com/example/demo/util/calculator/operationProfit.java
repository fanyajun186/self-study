package com.example.demo.util.calculator;

import java.math.BigDecimal;

public class operationProfit {	
	
	public static void main(String[] args) {
		System.out.println("1200元，分12期，存京东==========》");
		CreditCardInstallmentDTO dto=CreditCardInstallmentDTO.builder()
							.totalAmount(new BigDecimal(1200))
							.loanRate(new BigDecimal(0.0041))
							.monthFee(new BigDecimal(104.92))
							.termCount(12)
							.profitLoanRate(new BigDecimal(0.0323))
							.build();		
		BigDecimal result=calculate(dto);
		System.out.println("总收益"+result);
		
		System.out.println("每月刷1200元，刷12月，存京东==========》");
		BigDecimal totalCost =countCost(new BigDecimal(1200),new BigDecimal(0.0053),1);
		BigDecimal totalProfit =countEachMonthProfit(new BigDecimal(1200),new BigDecimal(0.0323));
		BigDecimal everyMonthProfit =totalProfit.subtract(totalCost);
		System.out.println("月收益"+everyMonthProfit);
		System.out.println("年收益"+everyMonthProfit.multiply(new BigDecimal(12)));
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
		System.out.println("花费"+totalCost);		
		//计算收益
		BigDecimal totalProfit =countProfit(totalAmount,profitLoanRate,termCount,monthFee);
		System.out.println("产出"+totalProfit);		
		return totalProfit.subtract(totalCost);
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
			System.out.print("第"+i+"月，本金剩余"+toatl+"，");
			BigDecimal eachMonthProfit=countEachMonthProfit(toatl,profitLoanRate);
			result=result.add(eachMonthProfit);			
		}		
		return result;
	}

	/**
	 * 计算每月收益
	 * @param toatl
	 * @param profitLoanRate
	 * @return
	 */
	private static BigDecimal countEachMonthProfit(BigDecimal toatl, BigDecimal profitLoanRate) {
		BigDecimal eachMonthProfit = toatl.multiply(profitLoanRate).divide(new BigDecimal(12)).setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("月收益"+eachMonthProfit);
		return eachMonthProfit;
	}
	
}
