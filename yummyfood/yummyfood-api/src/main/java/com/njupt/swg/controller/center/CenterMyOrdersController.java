package com.njupt.swg.controller.center;

import com.njupt.swg.pojo.Orders;
import com.njupt.swg.service.center.IMyOrdersService;
import com.njupt.swg.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author swg.
 * @Date 2020/4/4 21:26
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "用户中心订单相关接口",tags = "用户中心订单相关接口")
@RestController
@RequestMapping("myorders")
public class CenterMyOrdersController {
    @Autowired
    private IMyOrdersService myOrdersService;

    @ApiOperation(value = "用户中心我的订单",notes = "用户中心我的订单",httpMethod = "POST")
    @PostMapping("query")
    public CommonJsonResult query(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus",value = "订单状态",required = false)
            @RequestParam Integer orderStatus,
            @ApiParam(name = "page",value = "当前页",required = true)
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @ApiParam(name = "pageSize",value = "每页显示的数量",required = true)
            @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize){
        return CommonJsonResult.ok(myOrdersService.queryMyOrders(userId,orderStatus,page,pageSize));
    }

    // 商家发货没有后端，所以这个接口仅仅只是用于模拟
    @ApiOperation(value="商家发货", notes="商家发货", httpMethod = "GET")
    @GetMapping("/deliver")
    public CommonJsonResult deliver(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) throws Exception {

        if (StringUtils.isBlank(orderId)) {
            return CommonJsonResult.errorMsg("订单ID不能为空");
        }
        myOrdersService.updateDeliverOrderStatus(orderId);
        return CommonJsonResult.ok();
    }


    @ApiOperation(value="用户确认收货", notes="用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public CommonJsonResult confirmReceive(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) throws Exception {

        CommonJsonResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.updateReceiveOrderStatus(orderId);
        if (!res) {
            return CommonJsonResult.errorMsg("订单确认收货失败！");
        }

        return CommonJsonResult.ok();
    }

    @ApiOperation(value="用户删除订单", notes="用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public CommonJsonResult delete(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) throws Exception {

        CommonJsonResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if (!res) {
            return CommonJsonResult.errorMsg("订单删除失败！");
        }

        return CommonJsonResult.ok();
    }

    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     * @return
     */
    private CommonJsonResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return CommonJsonResult.errorMsg("订单不存在！");
        }
        return CommonJsonResult.ok();
    }




}
