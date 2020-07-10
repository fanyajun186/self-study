package com.example.demo.util.designMode.responsibilityChain.case2;

import com.example.demo.util.designMode.strategy.case2.Receipt;

/**
 * 抽象回执处理者接口
 */
public interface IReceiptHandler {
    void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain);
}
