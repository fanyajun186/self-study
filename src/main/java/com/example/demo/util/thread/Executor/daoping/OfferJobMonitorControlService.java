package com.example.demo.util.thread.Executor.daoping;
/*package com.mljr.offer.monitor.service;

import com.alibaba.fastjson.JSON;
import com.mljr.offer.entity.AutoFinJobDTO;
import com.mljr.offer.monitor.dao.JobMonitorDao;
import com.mljr.offer.monitor.service.thread.OfferJobMonitorThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

*//**
 * @author zhangdaoping
 * @create 2017/11/16 下午3:20
 *//*
@Service("offerJobMonitorControlService")
public class OfferJobMonitorControlService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JobMonitorDao jobMonitorDao;

    @Resource
    private IOfferJobMonitorService offerJobSendMonitorService;

    @Resource
    private IOfferJobMonitorService offerJobBackMonitorService;

    @Resource
    private IOfferJobMonitorService offerJobAccountMonitorService;

    private ThreadPoolExecutor threadPoolExecutor;

    *//**
     * 监控扣款任务
     *//*
    public void monitorJob() {

        try {
            Integer pageSize = 50;
            Integer lastJobId = null;
            int jobCount = jobMonitorDao.getOfferJobCount();
            if(jobCount>0){
                ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(jobCount);
                threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, arrayBlockingQueue);
                CountDownLatch countDownLatch = new CountDownLatch(jobCount);
                for (int i = 0; i < (jobCount + pageSize - 1) / pageSize; i++) {
                    List<AutoFinJobDTO> autoFinJobDTOList = jobMonitorDao.getOfferJobList(pageSize, lastJobId);
                    for (AutoFinJobDTO autoFinJobDTO : autoFinJobDTOList) {
                        switch (autoFinJobDTO.getStatus()) {
                            case 0:
                            case 7: {
                                threadPoolExecutor.execute(new OfferJobMonitorThreadService(offerJobSendMonitorService, autoFinJobDTO, countDownLatch));
                            }
                            break;
                            case 1: {
                                AutoFinJobDTO sendMonitorAutoFinJobDTO = offerJobSendMonitorService.getMonitorInfo(autoFinJobDTO);
                                threadPoolExecutor.execute(new OfferJobMonitorThreadService(offerJobBackMonitorService, sendMonitorAutoFinJobDTO, countDownLatch));
                            }
                            break;
                            case 6: {
                                threadPoolExecutor.execute(new OfferJobMonitorThreadService(offerJobBackMonitorService, autoFinJobDTO, countDownLatch));
                            }
                            break;
                            case 2: {
                                AutoFinJobDTO backMonitorAutoFinJobDTO = offerJobBackMonitorService.getMonitorInfo(autoFinJobDTO);
                                threadPoolExecutor.execute(new OfferJobMonitorThreadService(offerJobAccountMonitorService, backMonitorAutoFinJobDTO, countDownLatch));
                            }
                            break;
                            case 3:
                            case 4: {
                                threadPoolExecutor.execute(new OfferJobMonitorThreadService(offerJobAccountMonitorService, autoFinJobDTO, countDownLatch));
                            }
                            break;
                            default:
                        }
                        lastJobId = autoFinJobDTO.getJobId();
                    }
                }
                //如果由于意外情况导致实际处理的数据小于jobCount则15分钟后停止等待
                countDownLatch.await(15, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            logger.error("修改监控信息异常", e);
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
*/