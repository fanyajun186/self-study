package com.example.demo.util.designMode.strategy.case2;

import com.example.demo.util.designMode.strategy.case2.impl.Mt2101ReceiptHandleStrategy;

import java.util.HashMap;
import java.util.Map;

public class ReceiptHandleStrategyFactory {

    private static Map<String,IReceiptHandleStrategy> map;

    static {
        map=new HashMap<String,IReceiptHandleStrategy>();
        map.put("MT1101",new Mt2101ReceiptHandleStrategy());
        map.put("MT2101",new Mt2101ReceiptHandleStrategy());
        map.put("MT8104",new Mt2101ReceiptHandleStrategy());
        map.put("MT9999",new Mt2101ReceiptHandleStrategy());
    }

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType){
        return map.get(receiptType);
    }
}
