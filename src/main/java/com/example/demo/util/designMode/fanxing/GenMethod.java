package com.example.demo.util.designMode.fanxing;


public class GenMethod {

	public static void main(String[] args) {
		/*GenMethod.display("123",123);
		GenMethod.display("","");
		GenMethod.display("123f",123f);
		Object obj=new Integer(0);
        System.out.println(obj.getClass());
        TimeEntity t=new TimeEntity();
        t.setStartTime(11111L);
        t.setEndTime(22222L);
        System.out.println(EntityToString.getString(t, TimeEntity.class));*/
     
        float mountPointTotal = 100L;
        float mountPointUsed =20L;
		float score=mountPointUsed/mountPointTotal;
		System.out.println(score);
	}

	public static <T> void display(String str,T t) {
        System.out.println(str);
        System.out.println(t.getClass());
        
        
    }

}
