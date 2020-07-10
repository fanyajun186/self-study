package com.example.demo.util.designMode.responsibilityChain.case2;

import com.example.demo.util.designMode.strategy.case2.Receipt;

/**
 * 责任链接口
 */
public interface IReceiptHandleChain {

    void handleReceipt(Receipt receipt);
}
