package com.example.demo.util.thread.Executor.daoping;
/*package com.mljr.offer.monitor.service.thread;

import com.mljr.offer.entity.AutoFinJobDTO;
import com.mljr.offer.monitor.service.IOfferJobMonitorService;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

*//**
 * @author zhangdaoping
 * @create 2017/11/15 下午4:23
 *//*
public class OfferJobMonitorThreadService implements Runnable {

    private IOfferJobMonitorService offerJobMonitorService;

    private AutoFinJobDTO autoFinJobDTO;

    private CountDownLatch countDownLatch;

    //CountDownLatch是用来等待线程池完全执行完才进行下一步的，可能你那不需要
    public OfferJobMonitorThreadService(IOfferJobMonitorService offerJobMonitorService, AutoFinJobDTO autoFinJobDTO, CountDownLatch countDownLatch) {
        this.autoFinJobDTO = autoFinJobDTO;
        this.offerJobMonitorService = offerJobMonitorService;
        this.countDownLatch = countDownLatch;
    }

    *//**
     *
     *//*
    @Override
    public void run() {
        try {
            offerJobMonitorService.updateMonitorInfo(autoFinJobDTO);
        } finally {
            countDownLatch.countDown();
        }

    }
}
*/