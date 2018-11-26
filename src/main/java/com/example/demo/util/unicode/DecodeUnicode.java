package com.example.demo.util.unicode;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class DecodeUnicode {

    public static void main(String[] args){
        
        try {
        	String url="http://image.yyfq.com/econtract/20181126/contract/23403369/dist/纸质退回来_ZX2018112615375881968703043000.pdf";
            if(StringUtils.isNotEmpty(url) && url.contains(".pdf")){
                String[] arr = url.split("/");
                String name=arr[arr.length-1];
                String[] names = name.split("_");
                String urlString = URLEncoder.encode(names[0], "utf-8");
                url=url.replace(names[0],urlString);
                System.out.println(url);
            }
            /*// 将application/x-www-from-urlencoded字符串转换成普通字符串
            String keyWord = URLDecoder.decode("%C4%E3%BA%C3", "GBK");
            System.out.println(keyWord);  //输出你好
            // 将普通字符创转换成application/x-www-from-urlencoded字符串
            String urlString = URLEncoder.encode("你好", "GBK");  //输出%C4%E3%BA%C3

            System.out.println(urlString);*/
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
