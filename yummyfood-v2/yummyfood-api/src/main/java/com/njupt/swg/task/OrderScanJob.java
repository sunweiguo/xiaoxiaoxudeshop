package com.njupt.swg.task;

import com.njupt.swg.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author swg.
 * @Date 2020/6/29 22:18
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Component
@Slf4j
public class OrderScanJob {
    @Autowired
    private IOrderService orderService;

    /**
     * 定时任务存在佷多的弊端，比较明显的弊端是：
     *  这里我们用的这个spring scheduled不支持集群，或者说需要其他的条件支撑才能在集群条件下跑，否则会出现多个服务器同时关闭相同订单的现象
     *  当然也可以单独抽出这个定时任务来执行，没有集群环境下的困扰。
     *  但是第二个弊端是：效率低。全表扫描极大浪费mysql的查询性能。导致其他业务阻塞。所以定时扫描这种在稍微大一点的体量时就不适用了。
     *  解决方案：用消息队列来做一个延时队列出来处理。后续将会进行改进。
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void autoCloseOrder(){
        log.info("开始执行定时任务，定时修改超时一天的订单状态为关闭");
        int res = orderService.closeOrder();
        log.info("本次定时任务执行完毕，一共处理了{}条超时未关闭工单",res);
    }
}
