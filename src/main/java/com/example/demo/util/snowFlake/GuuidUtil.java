package com.example.demo.util.snowFlake;


import org.springframework.beans.factory.annotation.Value;

public class GuuidUtil {

    private static long datacenterId = 0L;
    private static long machineId = 0L;

    /**
     * 单例模式创建学法算法对象
     * */
    private enum SnowFlakeSingleton{
        Singleton;
        private SnowflakeIdWorker snowFlake;
        SnowFlakeSingleton(){
            snowFlake = new SnowflakeIdWorker(datacenterId,machineId);
        }
        public SnowflakeIdWorker getInstance(){
            return snowFlake;
        }
    }


    public static long getUUID(){
        return SnowFlakeSingleton.Singleton.getInstance().nextId();
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            System.out.println(GuuidUtil.getUUID());
        }
        System.out.print("雪花算法用时： ");
        System.out.println(System.currentTimeMillis() - start);

    }

}

