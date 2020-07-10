package com.example.demo.util.designMode.responsibilityChain.case2.impl;

import com.example.demo.util.designMode.responsibilityChain.case2.IReceiptHandleChain;
import com.example.demo.util.designMode.responsibilityChain.case2.IReceiptHandler;
import com.example.demo.util.designMode.strategy.case2.Receipt;
import org.apache.commons.lang.StringUtils;

public class Mt2101ReceiptHandler implements IReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT2101",receipt.getType())) {
            System.out.println("解析报文MT2101:" + receipt.getMessage());
        } else {
            //处理不了该回执就往下传递
            handleChain.handleReceipt(receipt);
        }
    }
}
