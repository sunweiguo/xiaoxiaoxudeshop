package com.njupt.swg.task;

import com.njupt.swg.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author swg.
 * @Date 2020/3/29 20:14
 * @CONTACT 317758022@qq.com
 * @DESC 定时扫描超时未支付的订单
 */
@Component
@Slf4j
public class OrderScanJob {
    @Autowired
    private IOrderService orderService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void autoCloseOrder(){
        log.info("开始执行定时任务，定时修改超时一天的订单状态为关闭");
        int res = orderService.closeOrder();
        log.info("本次定时任务执行完毕，一共处理了{}条超时未关闭工单",res);
    }
}
