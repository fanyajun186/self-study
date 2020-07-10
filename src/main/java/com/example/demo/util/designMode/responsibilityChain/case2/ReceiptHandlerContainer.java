package com.example.demo.util.designMode.responsibilityChain.case2;

import com.example.demo.util.designMode.responsibilityChain.case2.impl.Mt2101ReceiptHandler;
import com.example.demo.util.designMode.responsibilityChain.case2.impl.Mt8104ReceiptHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理容器
 */
public class ReceiptHandlerContainer {
    private ReceiptHandlerContainer(){}

    public static List<IReceiptHandler> getReceiptHandlerList(){
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        receiptHandlerList.add(new Mt2101ReceiptHandler());
        receiptHandlerList.add(new Mt8104ReceiptHandler());
        return receiptHandlerList;
    }
}
