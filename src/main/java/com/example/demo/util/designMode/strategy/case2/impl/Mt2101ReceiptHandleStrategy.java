package com.example.demo.util.designMode.strategy.case2.impl;

import com.example.demo.util.designMode.strategy.case2.IReceiptHandleStrategy;
import com.example.demo.util.designMode.strategy.case2.Receipt;

public class Mt2101ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT2101:" + receipt.getMessage());
    }
}
