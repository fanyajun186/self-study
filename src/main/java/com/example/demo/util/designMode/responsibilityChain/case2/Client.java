package com.example.demo.util.designMode.responsibilityChain.case2;

import com.example.demo.util.designMode.strategy.case2.Receipt;

import java.util.List;

public class Client {

    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        for (Receipt receipt : receiptList) {
            //回执处理链对象
            ReceiptHandleChain receiptHandleChain = new ReceiptHandleChain();
            receiptHandleChain.handleReceipt(receipt);
        }
    }
}
