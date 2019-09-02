package com.example.demo.util.poi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportExcelTest {

	public static void main(String[] args) throws Exception {
		String title = "表头";
        String[] rowsName = new String[]{"序号","货物运输批次号","提运单号","状态","录入人","录入时间"};
        List<Object[]>  dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < 100; i++) {
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = "TranNo"+i;
            objs[2] = "BillNo"+i;
            objs[3] = "StatusFlagCnName"+i;
            objs[4] = "LoginName"+i;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            objs[5] = date;
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
        ex.export();
	}
}
